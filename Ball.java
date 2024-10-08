import greenfoot.*;


/**
 * A Ball is a thing that bounces of walls and paddles (or at least i should).
 * 
 * @author The teachers 
 * @version 1
 */
public class Ball extends Actor
{
    private static final int BALL_SIZE = 25;
    private static final int BOUNCE_DEVIANCE_MAX = 15;
    private static final int STARTING_ANGLE_WIDTH = 90;
    private static final int DELAY_TIME = 100;
    
    GreenfootSound hitEffect = new GreenfootSound("PadHit.wav");
    

    private int speed;
    private boolean hasBouncedHorizontally;
    private boolean hasBouncedVertically;
    private int delay;
    private static int counter = 0;
    private static int score = 0;
    private static int level = 0;
    private static boolean restartLevel = false;
    
    public static int getLevel() {
        return level;
    }
    
    public static int getScore() {
        return score;
    }
    
    public static int getCounter() {
        return counter;
    }
    
    public static boolean getRestartLevel() {
        return restartLevel;
    }
    
    /**
     * Contructs the ball and sets it in motion!
     */
    public Ball()
    {
        init();
        createImage();
    }
    
    /**
     * Creates and sets an image of a gray ball to this actor.
     * On game level up changes the image color to match the level.
     */
    
    private void createImage()
    {
        GreenfootImage ballImage = new GreenfootImage(BALL_SIZE,BALL_SIZE);
        if (level == 1) {
            ballImage.setColor(Color.RED);
        } else if (level == 2) {
            ballImage.setColor(Color.ORANGE);
        } else if (level == 3) {
            ballImage.setColor(Color.YELLOW);
        } else if (level == 4) {
            ballImage.setColor(Color.GREEN);
        } else if (level == 5) {
            ballImage.setColor(Color.BLUE);
        } else if (level >= 6) {
            ballImage.setColor(Color.MAGENTA);
        } else {
            ballImage.setColor(Color.GRAY);
        }
        ballImage.fillOval(0, 0, BALL_SIZE, BALL_SIZE);
        setImage(ballImage);
    }

    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * 
     * Gives the ball a starting delay and then runs checks to keep the ball bouncing.
     * For every 10 point counts, runs the gameLevelUp.
     */
    public void act() 
    {
        if (delay > 0)
        {
            delay--;
        }
        else
        {
            move(speed);
            checkBounceOffWalls();
            checkBounceOffCeiling();
            checkBounceOffPaddle();
            checkBounceOffRandPaddle();
            checkBounceOffEnemyPaddle();
            checkRestart();
        }
        if (counter >= 10) {
            counter = 0;
            gameLevelUp();
        }
    }    

    /**
     * Returns true if the ball is touching one of the side walls.
     */
    private boolean isTouchingSides()
    {
        return (getX() <= BALL_SIZE/2 || getX() >= getWorld().getWidth() - BALL_SIZE/2);
    }

    /**
     * Returns true if the ball is touching the ceiling.
     */
    private boolean isTouchingCeiling()
    {
        return (getY() <= BALL_SIZE/2);
    }
    
    /**
     * Checks if the ball is touching the Random Paddle and from which direction.
     * 
     */
    private boolean isTouchingRandPaddleDirection() {
        if (isTouching(RandPaddle.class) && getRotation() >= 180)
        {
            revertVertically();
            Greenfoot.playSound("PadHit.wav");
            //Greenfoot.stop();
            return (getY() <= BALL_SIZE/2);
        } else {
            return false;
        }
    }
    
        /**
     * Checks if the ball is touching the Random Paddle and from which direction.
     * 
     */
    private boolean isTouchingEnemyPaddleDirection() {
        if (isTouching(EnemyPaddle.class) && getRotation() >= 180)
        {
            revertVertically();
            Greenfoot.playSound("PadHit.wav");
            //Greenfoot.stop();
            return (getY() <= BALL_SIZE/2);
        } else {
            return false;
        }
    }
    
    /**
     * Checks if the ball is touching the Paddle and from which direction.
     * Adds to the counter and score.
     */
    private boolean isTouchingPaddleDirection() {
        if (isTouching(Paddle.class) && getRotation() <= 180)
        {
            revertVertically();
            Greenfoot.playSound("PadHit.wav");
            //Greenfoot.stop();
            counter++;
            score++;
            return (getY() <= BALL_SIZE/2);
        } else {
            return false;
        }
    }
    
    /**
     * Runs on game level up and increases the speed and level counter.
     * 
     */
    private void gameLevelUp()
    {
        speed++;
        level++;
        createImage();
    }
    
    /**
     * Returns true if the ball is touching the floor.
     */
    private boolean isTouchingFloor()
    { 
        return (getY() >= getWorld().getHeight() - BALL_SIZE/2);
    }
    
    /**
     * Check to see if the ball should bounce off one of the walls.
     * If touching one of the walls, the ball is bouncing off.
     */
    private void checkBounceOffWalls()
    {
        if (isTouchingSides())
        {
            if (! hasBouncedHorizontally)
            {
                revertHorizontally();
                Greenfoot.playSound("PadHit.wav");
            }
        }
        else
        {
            hasBouncedHorizontally = false;
        }
    }

    /**
     * Check to see if the ball should bounce off the ceiling.
     * If touching the ceiling the ball is bouncing off.
     */
    private void checkBounceOffCeiling()
    {
        if (isTouchingCeiling())
        {
            if (! hasBouncedVertically)
            {
                revertVertically();
                Greenfoot.playSound("PadHit.wav");
            }
        }
        else
        {
            hasBouncedVertically = false;
        }
    }
    
     /**
     * Check to see if the ball should bounce off the paddle.
     * If touching the paddle the ball is bouncing off.
     */
    
    private void checkBounceOffPaddle()
    {
        
        if (isTouchingPaddleDirection() && !hasBouncedVertically)
        {
            if (! hasBouncedVertically)
            {
                revertVertically();
                
            }
        }
        else
        {
            hasBouncedVertically = false;
        }
    }   
    
         /**
     * Check to see if the ball should bounce off the random paddle.
     * If touching the random paddle the ball is bouncing off.
     */
    private void checkBounceOffRandPaddle()
    {
        if (isTouchingRandPaddleDirection() && !hasBouncedVertically)
        {
            if (! hasBouncedVertically)
            {
                revertVertically();
            }
        }
        else
        {
            hasBouncedVertically = false;
        }
    }  
    
             /**
     * Check to see if the ball should bounce off the random paddle.
     * If touching the random paddle the ball is bouncing off.
     */
    private void checkBounceOffEnemyPaddle()
    {
        if (isTouchingEnemyPaddleDirection() && !hasBouncedVertically)
        {
            if (! hasBouncedVertically)
            {
                revertVertically();
            }
        }
        else
        {
            hasBouncedVertically = false;
        }
    }  
    
    /**
     * Check to see if the ball should be restarted.
     * If touching the floor the game is restarted.
     */
    private void checkRestart()
    {
        if (isTouchingFloor())
        {
            //init();
            setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            restartLevel = true;
        }
    }

    /**
     * Bounces the ball back from a vertical surface.
     */
    private void revertHorizontally()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((180 - getRotation()+ randomness + 360) % 360);
        hasBouncedHorizontally = true;
    }

    /**
     * Bounces the ball back from a horizontal surface.
     */
    private void revertVertically()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((360 - getRotation()+ randomness + 360) % 360);
        hasBouncedVertically = true;
    }

    /**
     * Initialize the ball settings.
     */
    private void init()
    {
        speed = 2;
        delay = DELAY_TIME;
        hasBouncedHorizontally = false;
        hasBouncedVertically = false;
        setRotation(Greenfoot.getRandomNumber(STARTING_ANGLE_WIDTH)+STARTING_ANGLE_WIDTH/2);
        counter = 0;
        score = 0;
        level = 0;
        restartLevel = false;
    }
}

