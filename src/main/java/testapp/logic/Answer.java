package testapp.logic;

public class Answer {

    private String text;
    private int point;

    public Answer(String text, int point) {
        this.text = text;
        this.point = point;
    }

    public Answer() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
