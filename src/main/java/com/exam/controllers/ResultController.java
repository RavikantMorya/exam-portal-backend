package com.exam.controllers;


import com.exam.models.Result;
import com.exam.models.User;
import com.exam.models.exam.Quiz;
import com.exam.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/result")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping("/")
    public ResponseEntity<?> addResult(@RequestBody Result result)
    {
        System.out.println("Check:"+result.getQuiz().getQid());
        System.out.println("Check:"+result.getUser().getId());
        System.out.println("Check:"+result.getScore());
        return ResponseEntity.ok(this.resultService.addResult(result));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllResult()
    {
        return  ResponseEntity.ok(this.resultService.getResult());
    }

    @GetMapping("/user/{userid}")
    public ResponseEntity<?> getResultByUser(@PathVariable("userid") Long userid)
    {
        User user = new User();
        user.setId(userid);
        return ResponseEntity.ok(this.resultService.getResultByUser(user));
    }


    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getResultByQuiz(@PathVariable("qid") Long qid)
    {
        Quiz quiz = new Quiz();
        quiz.setQid(qid);
        return  ResponseEntity.ok(this.resultService.getResultByQuiz(quiz));
    }


    @GetMapping("/{userId}/{qId}")
    public ResponseEntity<?> getResultByUserAndQuiz(@PathVariable("userId") Long userid,@PathVariable("qId") Long qId)
    {
        User user = new User();
        Quiz quiz = new Quiz();
        user.setId(userid);
        quiz.setQid(qId);
        return  ResponseEntity.ok(
        this.resultService.getResultByQuizAndUser(user,quiz)
        );
    }
}
