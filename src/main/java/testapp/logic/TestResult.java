package testapp.logic;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestResult implements Serializable {

    private transient Test test;

    private User user;

    private Date startTime;

    private Date endTime;

    //Stores the given answers: key - question number, value - answer number
    private transient Map<Integer, Integer> answersGiven = new HashMap<>();

    private int result;

    private int maxPoint;

    public TestResult() {
    }

    public TestResult(Test test, User user) {
        this.test = test;
        this.user = user;
    }

    public Test getTest() {
        return test;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getMaxPoint() {
        return maxPoint;
    }

    public void setMaxPoint(int maxPoint) {
        this.maxPoint = maxPoint;
    }

    public void addAnswer(int questionNumber, int answerNumber) {
        if (questionNumber >= test.getQuestions().size() || questionNumber < 0) {
            throw new IllegalArgumentException("Bad question number");
        }
        if (answerNumber < 0 || answerNumber >= test.getQuestions().get(questionNumber).getAnswers().size()) {
            throw new IllegalArgumentException("Bad answer number");
        }
        answersGiven.put(questionNumber, answerNumber);
    }

    public void evaluateTest() {
        result = sumOfPoints();
        maxPoint = test.getMaxPoint();
    }

    private int sumOfPoints() {
        int sum = 0;
        for (Integer number : answersGiven.keySet()) {
            sum += test.getQuestions().get(number).getAnswers().get(answersGiven.get(number)).getPoint();
        }
        return sum;
    }
}
