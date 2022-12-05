package finalproject.entity;



public class InputContent <D> {
    private D content;

    public InputContent() {
    }

    public InputContent(D content) {
        this.content = content;
    }

    public D getContent() {
        return content;
    }

    public void setContent(D content) {
        this.content = content;
    }
}
