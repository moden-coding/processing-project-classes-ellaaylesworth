import processing.core.PApplet;

public class Control {
    private int x;
    private int y;
    private int h;
    private int w;
    private PApplet screen;

    public Control(int xPos, int yPos, PApplet s) {
        x = xPos;
        y = yPos;
        w = 100;
        h = 30;
        screen = s;
    }

    public void display() {
        screen.fill(40, 90, 255);
        screen.rect(x, y, w, h);
        

    }

    public void goLeft() {
        x -= 10;
        

    }

    public void goRight() {
        x += 10;
    }
}
