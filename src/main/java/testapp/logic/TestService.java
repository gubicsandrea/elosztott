package testapp.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import testapp.data.ResultRepository;
import testapp.data.TestRepository;

import java.util.Date;

@Service
public class TestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestService.class);

    private TestRepository testRepository;
    private ResultRepository resultRepository;

    public TestService(TestRepository testRepository, ResultRepository resultRepository) {
        this.testRepository = testRepository;
        this.resultRepository = resultRepository;
    }

    public Test getTest() {
        LOGGER.info("Tesztet lekérték. {}", new Date());
        return testRepository.readTest();
    }

    public void saveResult(TestResult result) {
        LOGGER.info("Eredmény beérkezett. Felhasználó: {}, Eredménye: {}/{}",
                result.getUser().getUserName(),
                result.getResult(),
                result.getMaxPoint());
        resultRepository.writeResult(result);
    }

}
