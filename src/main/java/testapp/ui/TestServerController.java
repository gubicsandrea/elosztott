package testapp.ui;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testapp.logic.Test;
import testapp.logic.TestResult;
import testapp.logic.TestService;

@RestController
public class TestServerController {

    private TestService testService;

    public TestServerController(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(value = "/api/test", method = RequestMethod.GET)
    public Test getTest() {
        return testService.getTest();
    }

    @RequestMapping(value = "/api/result", method = RequestMethod.POST)
    public String saveResult(@RequestBody TestResult result) {
        testService.saveResult(result);
        return "{\"status\": \"ok\"}";
    }

    @ExceptionHandler({DataAccessException.class})
    public ResponseEntity<String> handleException() {
        return new ResponseEntity<>("{\"status\": \"failed\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
