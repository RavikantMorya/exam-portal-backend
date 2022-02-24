package com.exam.controllers;


import com.exam.models.exam.Category;
import com.exam.models.exam.Quiz;
import com.exam.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizService quizService;

    //add the quiz
    @PostMapping("/")
    public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz)
    {
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    //update the quiz
    @PutMapping("/")
    public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz)
    {
        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    //get all the quizzes
    @GetMapping("/")
    public  ResponseEntity<?> quizzes()
    {
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    @GetMapping("/{qid}")
    public ResponseEntity<?> quiz(@PathVariable("qid") Long qid)
    {
        return ResponseEntity.ok(this.quizService.getQuiz(qid));
    }

    //delete the quiz
    @DeleteMapping("/{quid}")
    public  void delete(@PathVariable("quid") Long quid)
    {
        Quiz quiz = new Quiz();
        quiz.setQid(quid);
        this.quizService.deleteQuiz(quid);
    }

    //get all the quizzes of a specific category
    @GetMapping("/category/{cid}")
    public ResponseEntity<?> getQuizzesByCategory(@PathVariable("cid") Long cId)
    {
        Category category = new Category();
        category.setCid(cId);
        return ResponseEntity.ok( this.quizService.getQuizzesOfCategory(category));
    }


    //get only active quizzes for normal user
    @GetMapping("/active")
    public ResponseEntity<?> getActiveQuizzes()
    {
        return  ResponseEntity.ok(this.quizService.getActiveQuizzes(true));
    }

    //get all the active quizzes of a specific category
    @GetMapping("/category/active/{catid}")
    public ResponseEntity<?> getActiveQuizzesOfCategory(@PathVariable("catid") Long catid)
    {
        Category category = new Category();
        category.setCid(catid);
        return ResponseEntity.ok(this.quizService.getActiveQuizzesOfCategory(category,true));
    }

}
