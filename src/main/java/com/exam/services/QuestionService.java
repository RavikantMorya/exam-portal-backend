package com.exam.services;

import com.exam.models.exam.Question;
import com.exam.models.exam.Quiz;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface QuestionService {

    //add the question
   public Question addQuestion(Question question);

   //update the question
   public Question updateQuestion(Question question);

    //fetch all the questions
    public Set<Question> getQuestions();

    //fetch a specific question
    public Question getQuestion(Long questionId);

    //fetch all questions of a quiz
    public Set<Question> getQuestionsOfQuiz(Quiz quiz);

    //delete the question by id
    public  void deleteQuestion(Long quid);
}
