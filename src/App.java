import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.*;

public class App extends PApplet {
    ArrayList<Brick> bricks;
    ArrayList<Control> controls;
    Control control;
    Ball ball;
    int scene = 0;
    int speed = 5;
    int paddleX = 425;
    int paddleY = 600;
    int brickX = 90;
    int brickY = 40;
    int ballSize = 30;
    double count = 0;
    double highScore = 0;
    int health = 1;
    int startSideX = 450;
    int startSideY = 450;
    int startSideWidth = 100;
    int startSideHeight = 50;

    boolean moveLeft = false;
    boolean moveRight = false;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        readHighscore();
        bricks = new ArrayList<>();// creates arraylist for bricks
        ball = new Ball(800, 585, this);// creates ball
        brickMaker();
        controlMaker();

    }

    public void settings() {
        size(1000, 800);

    }

    public void draw() {
        if (scene == 0) {
            health = 1;
            background(0);
            startScreen();
        } else if (scene == 1) {
            background(0);
            sceneOneWords();
            checkTouchBricks();
            control.display();
            ball.display();
            checkTouchPaddle();
            ball.move();
            health();
            movement();

        } else if (scene == 2) {
            background(0);
            deathScreen();
            brickMaker();

        } else if (scene == 3) {
            sceneThreeWords();

        }
    }

    public void brickMaker() { // creates bricks
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

    public void controlMaker() {// creates paddle

        control = new Control(paddleX, paddleY, this);

    }

    public boolean checkTouchPaddle() {// checks if paddle is touching the ball

        float closestXOnRect = constrain(ball.getX(), control.getX(), control.getX() + control.getW());
        float closestYOnRect = constrain(ball.getY(), control.getY(), control.getY() + control.getH());
        circle(closestXOnRect, closestYOnRect, 5);

        if (dist(closestXOnRect, closestYOnRect, ball.getX(), ball.getY()) < ballSize / 2) {

            if (ball.getLeft() >= control.getX() && ball.getRight() <= control.getX() + control.getW()) { // touching
                                                                                                          // sides
                ball.bounceY();

            } else {
                ball.bounceX();
            }

            ;

        }
        return true;

    }

    public boolean checkTouchBricks() {// checking if the bal touches the bricks then removes them if touched

        for (int i = bricks.size() - 1; i >= 0; i--) {// chatGPT
            Brick b = bricks.get(i);

            b.display();
            float closestXOnRect = constrain(ball.getX(), b.getX(), b.getX() + b.getW());
            float closestYOnRect = constrain(ball.getY(), b.getY(), b.getY() + b.getH());

            if (dist(closestXOnRect, closestYOnRect, ball.getX(), ball.getY()) < ballSize / 2) {
                count++;
                bricks.remove(i);

                return true;
            }

        }

        return false;
    }

    public void keyPressed() { // controls direction of paddle
        if (keyCode == LEFT)

        {
            moveLeft = true;

        }
        if (keyCode == RIGHT)

        {
            moveRight = true;

        }
    }

    public void health() {// controls dying

        if (scene == 1) {
            if (ball.getY() > 800) {
                health = 0;
                if (health == 0) {
                    scene = 2;

                }
            }
        }
    }

    public void keyReleased() {// helps with controlling paddle
        if (keyCode == LEFT) {
            moveLeft = false;
        }
        if (keyCode == RIGHT) {
            moveRight = false;
        }
    }

    public void mousePressed() {// switching scenes,
        if (scene == 0) {
            if (mouseX > startSideX && mouseX < startSideX + startSideWidth && mouseY > startSideY
                    && mouseY < startSideY + startSideHeight) {
                scene = 1;
            }
        }
        if (scene == 2) {
            if (mouseX > startSideX && mouseX < startSideX + startSideWidth && mouseY > startSideY
                    && mouseY < startSideY + startSideHeight) {
                bricks.clear();
                scene = 0;
                health = 1;
                count = 0;
                ball.reset();
                brickMaker();
                checkTouchBricks();

            }
        }if (scene == 3){
             if (mouseX > startSideX && mouseX < startSideX + startSideWidth && mouseY > startSideY
                    && mouseY < startSideY + startSideHeight) {
                scene = 0;
            }

        }

    }

    public void startScreen() {// whats one the first screen

        fill(255, 90, 80);
        rect(startSideX, startSideY, startSideWidth, startSideHeight);
        textSize(200);
        fill(40, 90, 255);
        text("ARKANOID", 70, 300);
        textSize(40);
        fill(255);
        text("Click to start -->", 180, 490);

    }

    public void deathScreen() {// whats on the screen when you die

        fill(255, 90, 80);
        rect(startSideX, startSideY, startSideWidth, startSideHeight);
        textSize(250);
        fill(40, 90, 255);
        text("DEAD", 220, 300);
        textSize(40);
        stroke(0);
        fill(255);
        text("Click to replay -->", 140, 490);
        fill(255, 90, 80);
        text("Score: " + count, 370, 670);
        textSize(50);
        fill(40, 90, 255);
        text("Highscore: " + highScore, 300, 600);
        if (count > highScore) {
            highScore = count;
            saveScore();
        }

    }

    public void saveScore() {// keeps the highscore

        double numberToSave = highScore;
        String filePath = "highscore.txt";
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(numberToSave);
            writer.close();
            System.out.println("Integer saved to file successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

    }

    public void readHighscore() {// helps keeping the highscore
        try (Scanner scanner = new Scanner(Paths.get("highscore.txt"))) {

            while (scanner.hasNextLine()) {

                String row = scanner.nextLine();

                highScore = Double.valueOf(row);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

            saveScore();
        }
    }

    public void movement() {// contains what goes on with moving the paddle
        if (moveLeft == true) {

            control.goLeft();
            control.constrainLeft();

        }

        if (moveRight == true)

        {

            control.goRight();
            control.constrainRight();
        }

        if (checkTouchBricks()) {
            ball.bounceY();

        }
    }

    public void sceneOneWords() {// the words for health and score in the game scene
        fill(255);
        textSize(30);
        text("SCORE: " + count, 50, 35);
        text("HEALTH: " + health, 210, 35);
    }

    public void endScene() {// what happends if you win
        fill(255, 90, 80);
        rect(startSideX, startSideY, startSideWidth, startSideHeight);
        if (bricks.size() == 0) {
            scene = 3;
             

        }

    }

    public void sceneThreeWords() {// words if you win
        textSize(200);
        fill(40, 90, 255);
        text("YOU WON!", 70, 300);
    }
}