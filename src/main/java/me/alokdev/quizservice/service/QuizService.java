package me.alokdev.quizservice.service;


import me.alokdev.quizservice.dao.QuizDao;
import me.alokdev.quizservice.feign.QuizInterface;
import me.alokdev.quizservice.model.Question;
import me.alokdev.quizservice.model.QuestionWrapper;
import me.alokdev.quizservice.model.Quiz;
import me.alokdev.quizservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService  {

    @Autowired
    QuizDao quizDao;
//    @Autowired
//    QuestionDao questionDao;
    @Autowired
    QuizInterface quizInterface;
    public ResponseEntity<String> createQuiz(String categoryName, int numQuestions, String title) {
        List<Integer> questions = quizInterface.generateQuestionsForQuiz(categoryName,numQuestions).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
//        Optional<Quiz> quiz = quizDao.findById(id);
//        List<Question> questionFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();
//
//        for(Question q : questionFromDB){
//            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
//            questionForUser.add(qw);
//        }


        return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
//        Quiz quiz = quizDao.findById(id).get();
//        List<Question> questions = quiz.getQuestions();
        int right = 0;
//        int i = 0;
//        for (Response response : responses){
//            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
//                right++;
//            }
//            i++;
//        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
