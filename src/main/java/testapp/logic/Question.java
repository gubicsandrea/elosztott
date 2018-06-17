package testapp.logic;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

public class Question {
    private long id;
    private String question;
    private List<Answer> answers;

    public Question() {
    }

    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public Question(long id, String question) {
        this.id = id;
        this.question = question;
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @XmlElementWrapper(name = "answers")
    @XmlElement(name = "answer")
    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public int getMaxPoint() {
        int maxPoint = 0;
        for (Answer answer : answers) {
            maxPoint += answer.getPoint() > 0 ? answer.getPoint() : 0;
        }
        return maxPoint;
    }
}
