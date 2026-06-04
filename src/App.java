import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {
    ArrayList<Brick> bricks;
    ArrayList<Control> controls;
    Control control;
    Ball ball;
    int scene;
    int speed = 5;
    

    boolean moveLeft = false;
    boolean moveRight = false;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        bricks = new ArrayList<>();
        ball = new Ball(475,575,this);
        brickMaker();
        controlMaker();

    }

    public void settings() {
        size(1000, 800);

    }

    public void draw() {
        background(0);
        if (scene == 0) {
          

            for (Brick b : bricks) {
                b.display();
            }
           
            control.display();
            ball.display();
            

            if (moveLeft == true) {

                control.goLeft();
                control.constrainLeft();

            }
        }
        if (moveRight == true) {

            control.goRight();
            control.constrainRight();
        }
     
    }

    public void brickMaker() {
        int y = 10;
        for (int j = 0; j < 6; j++) {
            y += 40;
            for (int i = 0; i < 10; i++) {
                int x = 50 + i * 90;

                Brick brick = new Brick(x, y, this);
                bricks.add(brick);

            }
        }

    }

    public void controlMaker() {
        int paddlex = 425;
        int paddley = 600;
        control = new Control(paddlex, paddley, this);

    }

    public void keyPressed() {
        if (keyCode == LEFT)

        {
            moveLeft = true;

        }
        if (keyCode == RIGHT)

        {
            moveRight = true;

        }
    }

    public void keyReleased() {
        if (keyCode == LEFT) {
            moveLeft = false;
        }
        if (keyCode == RIGHT) {
            moveRight = false;
        }
    }
}