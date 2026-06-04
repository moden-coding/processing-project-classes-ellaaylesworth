import processing.core.PApplet;

public class Control {
    private int x;
    private int y;
    private int h;
    private int w;
    private int size;
    private PApplet screen;

    public Control(int xPos, int yPos, PApplet s) {
        x = xPos;
        y = yPos;
        w = 100;
        h = 30;
        size = 5;
        screen = s;
    }

    public void display() {
        screen.stroke(10,10,150);
        screen.fill(40, 90, 255);
        screen.rect(x, y, w, h,size);
        

    }

    public void goLeft() {
        x -= 10;
        

    }

    public void goRight() {
        x += 10;
    }
    public void constrainLeft(){
        if (x < 0 ){
            x = 0;

           
        }

    } public void constrainRight(){
        if (x > 900 ){
            x = 900;

           
        }

    }
}
