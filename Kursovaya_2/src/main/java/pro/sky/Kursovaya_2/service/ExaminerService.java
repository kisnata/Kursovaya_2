package pro.sky.Kursovaya_2.service;

import pro.sky.Kursovaya_2.model.Question;

import java.util.Collection;

public interface ExaminerService {

  Collection<Question> getQuestions(int amount);

}
