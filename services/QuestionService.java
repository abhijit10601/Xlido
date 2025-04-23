package com.crio.xlido.services;

import java.util.Comparator;
import java.util.List;
import com.crio.xlido.entities.Event;
import com.crio.xlido.entities.Question;
import com.crio.xlido.entities.Replier;
import com.crio.xlido.entities.User;
import com.crio.xlido.repositories.IEventRepository;
import com.crio.xlido.repositories.IQuestionRepository;
import com.crio.xlido.repositories.IReplierRepository;
import com.crio.xlido.repositories.IUserRepository;

public class QuestionService {
    private final IEventRepository eventRepository;
    private final IUserRepository userRepository;
    private final IQuestionRepository questionRepository;
    private final IReplierRepository replierRepository;


    public QuestionService (IEventRepository eventRepository, IUserRepository userRepository, IQuestionRepository questionRepository, IReplierRepository replierRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.replierRepository = replierRepository;
    }

    public Question createQuestion(String name, Long userId, Long eventId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with an id " + userId + " does not exist"));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event with an id " + eventId + " does not exist"));
        
        Question newQuestion = questionRepository.create(new Question(name, user, event));

        return newQuestion;
    }

    public void deleteQuestion(Long questionId, Long userId) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new RuntimeException("Question with an id " + questionId + " does not exist"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with an id " + userId + " does not exist"));

        if(question.getUser().getId() != user.getId()){
            throw new RuntimeException("User with an id " + userId + " is not an author of question with an id " + questionId);
        }

        questionRepository.deleteById(questionId);
    }

    public void upvoteQuestion(Long questionId, Long userId){
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new RuntimeException("Question with an id " + questionId + " does not exist"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with an id " + userId + " does not exist"));

        if(question.getListOfUpvotedUsers().contains(user)) {
            throw new RuntimeException("User with an id " + userId + " has already upvoted a question with an id " + questionId);
        }

        question.upvoteByUser(user);
    }

    public void replyQuestion(Long questionId, Long userId, String reply){
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new RuntimeException("Question with an id " + questionId + " does not exist"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with an id " + userId + " does not exist"));

        replierRepository.add(new Replier(user, question, reply));
    }

    public void listAllQuestions(Long eventId, String sortMethod) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event with an id " + eventId + " does not exist"));
        List<Question> questionList = questionRepository.findByEventId(eventId);

        if("POPULAR".equals(sortMethod)){
            // Sort by totalUpvotes in descending order
            questionList.sort(Comparator.comparingInt(Question::getTotalUpvotes).reversed());
        } else if("RECENT".equals(sortMethod)){
            // Sort by recently added questions in descending order
            questionList.sort(Comparator.comparingLong(Question::getId).reversed());
        }

        for (Question question : questionList) {
            System.out.println("Question ID: " + question.getId());
            System.out.println("Content: " + question.getContent());
            System.out.println("Votes: " + question.getTotalUpvotes());
            System.out.println("Replies:");
        
            List<Replier> repliers = replierRepository.findByQuestionId(question.getId());
            if (repliers == null || repliers.isEmpty()) {
                System.out.println(); // Blank line between questions
                continue;
            } else {
                for (Replier reply : repliers) {
                    System.out.println("  - User " + reply.getUser().getId() + ": " + reply.getReply());
                }
            }
        
            System.out.println(); // Blank line between questions
        }
    }
}
