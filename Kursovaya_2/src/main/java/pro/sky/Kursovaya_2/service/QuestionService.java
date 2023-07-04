package pro.sky.Kursovaya_2.service;

import java.util.Collection;

import pro.sky.Kursovaya_2.model.Question;

public interface QuestionService {

  Question add(String question, String answer);

  Question add(Question question);

  Question remove(Question question);

  Collection<Question> getAll();

  Question getRandomQuestion();

}
