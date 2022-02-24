package com.exam.services;

import com.exam.models.exam.Category;
import com.exam.models.exam.Quiz;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface QuizService {

    //add the quiz
    public Quiz addQuiz(Quiz quiz);

    //update the quiz
    Quiz updateQuiz(Quiz quiz);

    //return all the quizzes
      Set<Quiz> getQuizzes();
      //give a quiz with a specific id
       Quiz getQuiz(Long quizId);
    //delete the quiz
    void deleteQuiz(Long quizId);

    //get all quizzes of a category
    public Set<Quiz> getQuizzesOfCategory(Category category);

    //get all the active quizzes
    public  Set<Quiz>  getActiveQuizzes(Boolean b);

    //get all the active quizzes of a specific category
    public  Set<Quiz> getActiveQuizzesOfCategory(Category category,Boolean b);
}
