package finalproject.entity;

import java.util.Scanner;
import static finalproject.main.Main.mode;

public class Answer implements Inputable {
    public static int ID_ANSWER = 1;

    private final int id;
    private String content;

    public Answer() {
        this.id = ID_ANSWER;
        ID_ANSWER++;
    }

    public Answer(int id) {
        ID_ANSWER = 0;
        this.id = ID_ANSWER;
        ID_ANSWER++;
    }

    public Answer(String content) {
        this.id = ID_ANSWER++;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        if (mode) {
            return "Answer: " +
                    "'" + content + '\'';
        } else {
            return "Nghĩa là: " +
                    "'" + content + '\'';
        }
    }

    @Override
    public void input(Scanner sc) {
        System.out.println("Please enter the content of the answer: ");
        this.setContent(sc.nextLine());
    }
}
