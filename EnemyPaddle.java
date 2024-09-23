import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CopyOfRandPaddle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyPaddle extends Actor
{
    private int width;
    private int height;
    private int dx;
    /**
     * Constructs a new paddle with the given dimensions.
     */
    public EnemyPaddle(int width, int height)
    {
        this.width = width;
        this.height = height;
        dx = 1;
        createImage();
    }
    
    /**
     * Act - do whatever the EnemyPaddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * Moves the paddle towards the x coordinate of the ball.
     */
    public void act()
    {
        getBallLocation();
    }
    
    /**
     * Creates and sets an image for the paddle, the image will have the same dimensions as the paddles width and height.
     */
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage(width, height);
        image.setColor(Color.BLUE);
        image.fill();
        setImage(image);
    }
    
    /**
     * Gets the x location of the ball and moves the paddle towards it.
     */
    private void getBallLocation() {
        Ball ball = (Ball) getWorld().getObjects(Ball.class).get(0);
        if (ball != null) {
            int getLocationX = ball.getX();
            if (getLocationX >= this.getX()) {
                setLocation(getX() + dx, getY());
            } else if (getLocationX <= this.getX()) {
                setLocation(getX() - dx, getY());
            }
        }
    }
}
