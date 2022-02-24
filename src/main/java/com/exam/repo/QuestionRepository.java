package com.exam.repo;

import com.exam.models.exam.Question;
import com.exam.models.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    Set<Question> findByQuiz(Quiz quiz);  //custom function in repository
    //findByQuiz me B capital hona chahiye. Quiz is in camelcase from quiz variable of Question Model.
}
