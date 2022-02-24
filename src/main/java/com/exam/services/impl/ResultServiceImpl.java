package com.exam.services.impl;

import com.exam.models.Result;
import com.exam.models.User;
import com.exam.models.exam.Quiz;
import com.exam.repo.ResultRepository;
import com.exam.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public Result addResult(Result result) {

        return this.resultRepository.save(result) ;
    }

    @Override
    public Set<Result> getResult() {
        return new HashSet<>(this.resultRepository.findAll());
    }


    @Override
    public Set<Result> getResultByUser(User user) {
        return this.resultRepository.findByUsr(user);
    }

    @Override
    public Set<Result> getResultByQuiz(Quiz quiz) {
        return this.resultRepository.findByQuz(quiz);
    }

    @Override
    public Set<Result> getResultByQuizAndUser(User user, Quiz quiz) {
        return this.resultRepository.findByUsrAndQuz(user,quiz);
    }
}
