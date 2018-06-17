package testapp.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import testapp.logic.TestResult;

@Repository
public class JdbcResultRepository implements ResultRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcResultRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void writeResult(TestResult result) {
        jdbcTemplate.update("insert into Results (userName, startTime, endTime, result, maxPoint) VALUES (?, ?, ?, ?, ?)",
                result.getUser().getUserName(),
                result.getStartTime(),
                result.getEndTime(),
                result.getResult(),
                result.getMaxPoint());
    }
}
