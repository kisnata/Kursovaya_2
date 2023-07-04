package pro.sky.Kursovaya_2.service.impl;


import org.springframework.stereotype.Service;
import pro.sky.Kursovaya_2.exception.QuestionAlreadyExistsException;
import pro.sky.Kursovaya_2.exception.QuestionNotFoundException;
import pro.sky.Kursovaya_2.exception.QuestionsAreEmptyException;
import pro.sky.Kursovaya_2.model.Question;
import pro.sky.Kursovaya_2.service.QuestionService;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

  private final Set<Question> questions = new HashSet<>();
  private final Random random = new Random();

  @Override
  public Question add(String question, String answer) {
    return add(new Question(question, answer));
  }

  @Override
  public Question add(Question question) {
    if (!questions.add(question)) {
      throw new QuestionAlreadyExistsException();
    }
    return question;
  }

  @Override
  public Question remove(Question question) {
    if (!questions.remove(question)) {
      throw new QuestionNotFoundException();
    }
    return question;
  }

  @Override
  public Collection<Question> getAll() {
    return Collections.unmodifiableCollection(questions);
  }

  @Override
  public Question getRandomQuestion() {
    if (questions.isEmpty()) {
      throw new QuestionsAreEmptyException();
    }
    return new ArrayList<>(questions).get(random.nextInt(questions.size()));
  }

}
