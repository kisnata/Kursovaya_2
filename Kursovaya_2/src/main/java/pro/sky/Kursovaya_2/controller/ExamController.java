package pro.sky.Kursovaya_2.controller;

import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.Kursovaya_2.model.Question;
import pro.sky.Kursovaya_2.service.ExaminerService;

@RestController
public class ExamController {
  private final ExaminerService examinerService;

  public ExamController(ExaminerService examinerService) {
    this.examinerService = examinerService;
  }

  @GetMapping("/get/{amount}")
  public Collection<Question> getQuestions(@PathVariable int amount) {
    return examinerService.getQuestions(amount);
  }
}
