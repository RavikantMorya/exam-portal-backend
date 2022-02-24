package com.exam.services.impl;
import com.exam.models.exam.Category;
import com.exam.models.exam.Quiz;
import com.exam.repo.QuizRepository;
import com.exam.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {

        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Set<Quiz> getQuizzes() {
        return new HashSet<>(this.quizRepository.findAll());
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        return this.quizRepository.findById(quizId).get();
    }

    @Override
    public void deleteQuiz(Long quizId) {
        Quiz quiz = new Quiz();
        quiz.setQid(quizId);
        this.quizRepository.delete(quiz);
    }

    @Override
    public Set<Quiz> getQuizzesOfCategory(Category category) {
        return this.quizRepository.findByCategory(category);
    }

    @Override
    public Set<Quiz> getActiveQuizzes(Boolean b) {
        return this.quizRepository.findByActive(true);
    }

    @Override
    public Set<Quiz> getActiveQuizzesOfCategory(Category category, Boolean b) {
        return this.quizRepository.findByCategoryAndActive(category,true);
    }


}
