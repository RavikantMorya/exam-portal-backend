package com.exam.repo;

import com.exam.models.exam.Category;
import com.exam.models.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuizRepository extends JpaRepository<Quiz,Long> {

    public Set<Quiz> findByCategory(Category category);

    public Set<Quiz> findByActive(Boolean b);

    public Set<Quiz> findByCategoryAndActive(Category category,Boolean b);
}
