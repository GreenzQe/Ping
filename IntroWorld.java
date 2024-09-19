import greenfoot.*;
/**
 * Write a description of class IntroWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    
    /**
     * Constructor for objects of class IntroWorld.
     * Displays the intro.
     * Displays the previous games score if it was 1 or higher.
     */
    public IntroWorld()
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        GreenfootImage background = getBackground();
        background.setColor(Color.RED);
        if (Ball.getScore() >= 1) {
            background.drawString("Last Score: " + Ball.getScore(), WORLD_WIDTH / 2 - 30, WORLD_HEIGHT / 2);
            background.drawString("Hit <enter> to start game...", WORLD_WIDTH / 2 - 70, WORLD_HEIGHT / 2 + 30);
        } else {
            background.drawString("Intro world. Hit <enter> to start game...", WORLD_WIDTH / 2 - 100, WORLD_HEIGHT / 2);
        }
        
    }
    
    /**
     * Initiates PingWorld if the enter key is pressed.
     */
    public void act()
    {
        String key = Greenfoot.getKey();
        if (key != null && key.equals("enter"))
        {
            Greenfoot.setWorld(new PingWorld(true));
        }
    }
    
}
