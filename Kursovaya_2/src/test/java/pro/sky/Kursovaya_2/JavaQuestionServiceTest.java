package pro.sky.Kursovaya_2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.Kursovaya_2.exception.QuestionAlreadyExistsException;
import pro.sky.Kursovaya_2.exception.QuestionNotFoundException;
import pro.sky.Kursovaya_2.exception.QuestionsAreEmptyException;
import pro.sky.Kursovaya_2.model.Question;
import pro.sky.Kursovaya_2.service.QuestionService;
import pro.sky.Kursovaya_2.service.impl.JavaQuestionService;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;



public class JavaQuestionServiceTest {

  private final QuestionService questionService = new JavaQuestionService();

  @BeforeEach
  public void beforeEach() {
    questionService.add(new Question("Q1", "A1"));
    questionService.add(new Question("Q2", "A2"));
    questionService.add(new Question("Q3", "A3"));
  }

  @AfterEach
  public void afterEach() {
    new HashSet<>(questionService.getAll()).forEach(questionService::remove);
  }

  @Test
  public void add1Test() {
    int beforeCount = questionService.getAll().size();
    Question question = new Question("Q4", "A4");

    assertThat(questionService.add(question))
        .isEqualTo(question)
        .isIn(questionService.getAll());
    assertThat(questionService.getAll()).hasSize(beforeCount + 1);
  }

  @Test
  public void add2Test() {
    int beforeCount = questionService.getAll().size();
    Question question = new Question("Q4", "A4");

    assertThat(questionService.add("Q4", "A4"))
        .isEqualTo(question)
        .isIn(questionService.getAll());
    assertThat(questionService.getAll()).hasSize(beforeCount + 1);
  }

  @Test
  public void add1NegativeTest() {
    Question question = new Question("Q1", "A1");

    assertThatExceptionOfType(QuestionAlreadyExistsException.class)
        .isThrownBy(() -> questionService.add(question));
  }

  @Test
  public void add2NegativeTest() {
    assertThatExceptionOfType(QuestionAlreadyExistsException.class)
        .isThrownBy(() -> questionService.add("Q1", "A1"));
  }

  @Test
  public void removeTest() {
    int beforeCount = questionService.getAll().size();
    Question question = new Question("Q2", "A2");

    assertThat(questionService.remove(question))
        .isEqualTo(question)
        .isNotIn(questionService.getAll());
    assertThat(questionService.getAll()).hasSize(beforeCount - 1);
  }

  @Test
  public void removeNegativeTest() {
    assertThatExceptionOfType(QuestionNotFoundException.class)
        .isThrownBy(() -> questionService.remove(new Question("Q4", "A4")));
  }
  @Test
  public void getAllTest() {
    assertThat(questionService.getAll())
        .hasSize(3)
        .containsExactlyInAnyOrder(
            new Question("Q1", "A1"),
            new Question("Q3", "A3"),
            new Question("Q2", "A2")
        );
  }
  @Test
  public void getRandomQuestionTest() {
    assertThat(questionService.getRandomQuestion())
        .isNotNull()
        .isIn(questionService.getAll());
  }

  @Test
  public void getRandomQuestionNegativeTest() {
    afterEach();

    assertThatExceptionOfType(QuestionsAreEmptyException.class)
        .isThrownBy(questionService::getRandomQuestion);
  }

}
