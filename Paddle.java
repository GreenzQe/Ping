import greenfoot.*;


/**
 * A paddle is an object that goes back and forth. Though it would be nice if balls would bounce of it.
 * 
 * @author The teachers 
 * @version 1
 */
public class Paddle extends Actor
{
    private int width;
    private int height;
    private int dx;
    private GreenfootImage image1;
    private GreenfootImage image2;
    private int timer;
    private int ovalSize;
    GreenfootSound hitEffect = new GreenfootSound("PadHit.wav");
    /**
     * Constructs a new paddle with the given dimensions.
     */
    public Paddle(int width, int height)
    {
        image1 = new GreenfootImage("Paddle1.png");
        image2 = new GreenfootImage("Paddle2.png");
        
        this.width = width;
        this.height = height;
        dx = 1;
        setImage(image1);
    }
 
    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Control movement
        if (Greenfoot.isKeyDown("left")) {
            move(-2);
        } 
        if (Greenfoot.isKeyDown("right")) {
            move(2);
        }

        // When touching the ball, set the timer and reset the circle size
        if (isTouching(Ball.class)) {
            timer = 60;  // Reset the timer to 10
            ovalSize = 0;  // Reset the oval size
            System.out.println("Timer: " + timer);
            hitEffect.play();
        }

        // If the timer is running, update the circle size and redraw the rectangle
        if (timer > 0) {
            timer--;  // Decrement the timer by 1 each frame

            // Increase the size of the circle gradually
            ovalSize += 2;  // Adjust the increment based on your desired speed

            // Redraw the rectangle with the expanding oval
            updateImage();
        }
    }
    
    private void updateImage() {
        // Create a new image with size 100x20 (your rectangle size)
        GreenfootImage image = new GreenfootImage(100, 20);

        // Set the background color of the rectangle
        image.setColor(Color.BLACK);
        image.fillRect(0, 0, 100, 20);

        // Set the color for the expanding circle
        image.setColor(Color.GRAY);

        // Draw the expanding oval (circle) at the center of the rectangle
        image.fillOval((image.getWidth() - ovalSize) / 2, (image.getHeight() - ovalSize) / 2, ovalSize, ovalSize);

        // Set the updated image to the actor
        setImage(image);
    }
    
    /**
     * Creates and sets an image for the paddle, the image will have the same dimensions as the paddles width and height.
     */
    /*
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage(width, height);
        image.setColor(Color.BLACK);
        image.fill();
        setImage(image);
    }
    */
}
