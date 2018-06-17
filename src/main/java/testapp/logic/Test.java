package testapp.logic;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Test {

    private List<Question> questions;

    public Test(List<Question> questions) {
        this.questions = questions;
    }

    public Test() {
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getMaxPoint() {
        int maxPoint = 0;
        for (Question question : questions) {
            maxPoint += question.getMaxPoint();
        }
        return maxPoint;
    }
}
