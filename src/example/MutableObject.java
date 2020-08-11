package example;

public class MutableObject {
    public int x;

    public MutableObject(int x) {
        this.x = x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "[x: " + this.x + "]";
    }
}