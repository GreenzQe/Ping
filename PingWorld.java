import greenfoot.*;


/**
 * The Ping World is where Balls and Paddles meet to play pong.
 * 
 * @author The teachers 
 * @version 1
 */
public class PingWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;

    GreenfootSound sound = new GreenfootSound("Music.wav");
    /**
     * Constructor for objects of class PingWorld.
     * adds objects Ball, Paddle and RandPaddle.
     * Starts the game music.
     */
    public PingWorld(boolean gameStarted)
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1, false); 
        if (gameStarted)
        {
            
            // Create a new world with WORLD_WIDTHxWORLD_HEIGHT cells with a cell size of 1x1 pixels.
            addObject(new Ball(), WORLD_WIDTH/2, WORLD_HEIGHT/2);
            addObject(new Paddle(100,20), 60, WORLD_HEIGHT - 50);
            addObject(new RandPaddle(100,20), 60, WORLD_HEIGHT - 300);
            addObject(new EnemyPaddle(100,20), 60, WORLD_HEIGHT - 650);
            
            sound.play();
        }
        else
        {
            Greenfoot.setWorld(new IntroWorld());
        }
    }
    
    /**
     * Shows the score and level counter in the top left corner.
     * Stops the music and shows IntroWorld if the game ends.
     */
    public void act() {
        showText("Score: " + Ball.getCounter() + "  Level: " + Ball.getLevel(), 100, 30);
        /*
        GreenfootImage image = new GreenfootImage(200, 30);
        image.setColor(Color.RED);
        image.drawString("Score: " + Ball.getCounter() + "  Level: " + Ball.getLevel(), 100, 30);
        image.drawImage(image, 100, 30);
        */
        if (Ball.getRestartLevel() == true) {
            Greenfoot.setWorld(new IntroWorld());
            sound.stop();
        }
    }
}
