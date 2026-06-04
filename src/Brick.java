import processing.core.PApplet;

public class Brick {
    private int x;
    private int y;
    private int h;
    private int w;
    private int color;
    private int health;
    private int points;
    private int size;
    private PApplet screen;

    public Brick(int xPos, int yPos, PApplet s) {
        x = xPos;
        y = yPos;
        w = 90;
        h = 40;
        screen = s;
        health = 1;
        points = 0;
        size = 2;
        // screen = one;

    }

    public void display() {
        screen.stroke(150,8,8);
        screen.fill(255, 90, 80);
        screen.rect(x, y, w, h, size);

        if (health == 0) {
            points++;

        }
    }
}
