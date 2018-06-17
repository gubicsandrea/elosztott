package testapp.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import testapp.logic.Answer;
import testapp.logic.Question;
import testapp.logic.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcTestRepository implements TestRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTestRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Test readTest() {
        List<Question> questions = jdbcTemplate.query("SELECT id, question FROM Questions", new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Question(resultSet.getLong("id"),
                        resultSet.getString("question"));
            }
        });

        for (Question question : questions) {
            List<Answer> answers = jdbcTemplate.query("SELECT text, point FROM Answers WHERE question = ?", new RowMapper<Answer>() {
                @Override
                public Answer mapRow(ResultSet resultSet, int i) throws SQLException {
                    return new Answer(resultSet.getString("text"), resultSet.getInt("point"));
                }
            }, question.getId());
            question.setAnswers(answers);
        }

        return new Test(questions);
    }
}
