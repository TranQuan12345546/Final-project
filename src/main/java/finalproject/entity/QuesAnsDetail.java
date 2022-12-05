package finalproject.entity;


import finalproject.constant.StatusValue;

import java.util.Scanner;
import static finalproject.main.Main.mode;

public class QuesAnsDetail {

    public static int ID_COUPLE = 1;

    private final int id;
    private Question question;
    private Answer answer;
    private int checkMemorized;
    private String status;



    public QuesAnsDetail(int id) {
        ID_COUPLE = 0;
        this.id = ID_COUPLE;
        ID_COUPLE++;
        this.status = StatusValue.NOT_MEMORIZED.value;
    }

    public QuesAnsDetail(Question question, Answer answer, String status) {
        this.id = ID_COUPLE++;
        this.question = question;
        this.answer = answer;
        this.status = status;
    }

    public QuesAnsDetail() {
        this.id = ID_COUPLE++;
        this.status = StatusValue.NOT_MEMORIZED.value;
    }

    public int getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public int getCheckMemorized() {
        return checkMemorized;
    }

    public void setCheckMemorized() {
        checkMemorized++;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        if(mode) {
            return "" + question +
                    answer;
        } else {
            return "" + question +
                    answer;
        }
    }

    public void inputQuesAns(Scanner sc) {
        Question question = new Question();
        Answer answer = new Answer();
        question.input(sc);
        answer.input(sc);
        this.question = question;
        this.answer = answer;
    }

}
