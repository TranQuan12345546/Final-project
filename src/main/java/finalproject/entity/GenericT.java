package finalproject.entity;

import java.util.Scanner;

public class GenericT <D>{
    private D content;

    public D getContent() {
        return content;
    }

    public void setContent(D content) {
        this.content = content;
    }

    public GenericT(D content) {
        this.content = content;
    }

    public void input (D content) {

    }
}
