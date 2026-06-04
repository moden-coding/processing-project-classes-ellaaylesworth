import processing.core.PApplet;

public class Ball {
    private int x;
    private int y;
    private int size;
    private int color;
    private PApplet screen;

    public Ball(int xPos, int yPos, PApplet s) {
        x = xPos;
        y = yPos;
        screen = s;
        size = 50;
        color = screen.color(255);

    }

    public void display() {
        // System.out.println("ball at: " + x + "," + y);
        screen.fill(color);
        screen.circle(x, y, size);
      
    }
    public void move(){
        


    }
}