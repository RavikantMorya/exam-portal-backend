package com.exam.services;

import com.exam.models.Result;
import com.exam.models.User;
import com.exam.models.exam.Quiz;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface ResultService {
    public Result addResult(Result result);

    public Set<Result> getResult();

    public  Set<Result> getResultByUser(User user);

    public Set<Result> getResultByQuiz(Quiz quiz);

    public Set<Result> getResultByQuizAndUser(User user,Quiz quiz);
}
