package testapp.data;

import testapp.logic.TestResult;

public interface ResultRepository {

    void writeResult(TestResult result);
}
