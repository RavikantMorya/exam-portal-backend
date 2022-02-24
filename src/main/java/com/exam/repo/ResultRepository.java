package com.exam.repo;

import com.exam.models.Result;
import com.exam.models.User;
import com.exam.models.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ResultRepository extends JpaRepository  <Result,Long> {

    public Set<Result> findByUsr(User user);
    public Set<Result> findByQuz(Quiz quiz);
    public Set<Result> findByUsrAndQuz(User user,Quiz quiz);
}
