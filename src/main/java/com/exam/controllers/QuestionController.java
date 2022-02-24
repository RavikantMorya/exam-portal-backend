package com.exam.controllers;


import com.exam.models.exam.Question;
import com.exam.models.exam.Quiz;
import com.exam.services.QuestionService;
import com.exam.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.NumberFormat;
import java.util.*;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController{

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuizService quizService;


    //add the question
    @PostMapping("/")
    public ResponseEntity<?> addQuestion(@RequestBody Question question)
    {
        return  ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    //update the question
    @PutMapping("/")
    public ResponseEntity<?> updateQuestion(@RequestBody Question question)
    {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    //get max number of the questions of quiz
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qid") Long qid)
    {
        Quiz quiz = this.quizService.getQuiz(qid);
        Set<Question> questions1 = quiz.getQuestions();
        List<Question> list=new ArrayList(questions1);
        if (Integer.parseInt(quiz.getNumberOfQuestions())<list.size())
        {
            list=list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions())+1);
        }

        //traverse each question and set answer null so that answer is not sent at fornt-end
        list.forEach(
                (question -> {
                    question.setAnswer("");
                })
        );
        //shuffling the list
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    //get all the questions of the quiz

    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getAllQuestionOfQuiz(@PathVariable("qid") Long qid)
    {
        Quiz quiz=new Quiz();
        quiz.setQid(qid);
        Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
        return ResponseEntity.ok(questionsOfQuiz);
    }

    //get a single question by question id
    @GetMapping("/{quesId}")
    public  ResponseEntity<?> getQuestion(@PathVariable("quesId") Long quesId)
    {
        return ResponseEntity.ok(this.questionService.getQuestion(quesId));
    }

    //delete the question
    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId") Long quesId)
    {
        Question question = new Question();
        question.setQuesid(quesId);
        this.questionService.deleteQuestion(quesId);
    }

    //evaluate the quiz
    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List< Question> questions)
    {
        double marksGot=0;
        int correctAnswers=0;
        int attempted=0;
        for(Question q:questions)
        {
            Question question = this.questionService.getQuestion(q.getQuesid());
            if(q.getGivenAnswer()!=null)
            {
                attempted++;
            }
            if(question.getAnswer().equals(q.getGivenAnswer()))
            {
                correctAnswers++;
                double markofSingleQuestion= Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) /questions.size();
                marksGot+=markofSingleQuestion;
            }
        }


       //format obtained marks upto two decimal
        NumberFormat nf= NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.format(marksGot);


        //set three parameters in the map
        //The Map.of and Map.ofEntries are static factory methods that return unmodifiable Map containing specified mapping.
        Map<String, Object> map= Map.of("marksGot", marksGot, "correctAnswers", correctAnswers, "attemptedQuestions", attempted);
        return  ResponseEntity.ok(map);
    }

}
