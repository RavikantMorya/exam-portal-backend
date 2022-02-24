package com.exam.models;


import com.exam.models.exam.Quiz;

import javax.persistence.*;

@Entity
public class Result  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long result_id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User usr;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Quiz quz;

    private  double score;

    public Long getResult_id() {
        return result_id;
    }

    public void setResult_id(Long result_id) {
        this.result_id = result_id;
    }

    public User getUser() {
        return usr;
    }

    public void setUser(User user) {
        this.usr = user;
    }

    public Quiz getQuiz() {
        return quz;
    }

    public void setQuiz(Quiz quiz) {
        this.quz = quiz;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
