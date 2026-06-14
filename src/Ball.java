import processing.core.PApplet;

public class Ball {
    private float x;
    private float y;
    private int size;
    private int color;
    private PApplet screen;
    private int moveX;
    private int moveY;
    private int health;

    public Ball(int xPos, int yPos, PApplet s) {
        x = xPos;
        y = yPos;
        screen = s;
        size = 30;
        color = screen.color(255);
        moveX = 5;
        moveY = -5;
        health = 1;

    }

    public void display() {
        // System.out.println("ball at: " + x + "," + y);
        screen.fill(color);
        screen.circle(x, y, size);

    }

    public void move() {
        x += moveX;
        y += moveY;

        if (x + size / 2 >= screen.width) {
            moveX = -moveX;
        }
        if (x + size / 2 <= 0) {
            moveX = -moveX;
        }

        if (y + size / 2 <= 0) {
            moveY = -moveY;
        }
      

    }

    public void bounceY() {
        moveY = -moveY;
        

        System.out.println("bounced!");
        
    }
    
    public void bounceX() {
        moveX = -moveX;
        

        System.out.println("bounced!");
    }
        
    public float getX(){
        return x;
    
     }public float getY(){
        return y;
    
    }
    public float getLeft(){
        return x - size/2;
    }

    public float getRight(){
        return x + size/2;
    }



    public void reset(){
        x = 700;
        y = 400;
    }
    
}