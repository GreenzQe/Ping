import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RandPaddle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RandPaddle extends Actor
{
    private int width;
    private int height;
    private int dx;
    /**
     * Constructs a new paddle with the given dimensions.
     */
    public RandPaddle(int width, int height)
    {
        this.width = width;
        this.height = height;
        dx = 1;
        createImage();
    }
    
    /**
     * Act - do whatever the RandPaddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * Moves the paddle.
     */
    public void act()
    {
        changeLocationSize();
        setLocation(getX() + dx, getY());
    }
    
    /**
     * Creates and sets an image for the paddle, the image will have the same dimensions as the paddles width and height.
     */
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage(width, height);
        image.setColor(Color.WHITE);
        image.fill();
        setImage(image);
    }
    /**
     * Will set the random paddle at an random x location and vary the size by a random amount.
     */
    public void changeLocationSize()
    {
        //Check to see if we are touching the outer boundaries of the world:
        // IF we are touching the right boundary OR we are touching the left boundary:
        if(getX() - width >= getWorld().getWidth() || getX() + width <= 0)
        {
            //Change our 'x' direction to the inverted direction:
            //dx = dx * -1;
            setLocation(0 - width/2, Greenfoot.getRandomNumber(150)+300);
            width = 100 + Greenfoot.getRandomNumber(50);
            createImage();
        }
    }
}
