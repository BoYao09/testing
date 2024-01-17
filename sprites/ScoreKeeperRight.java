import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ScoreKeeperRight implements DisplayableSprite {
	
	//a sprite that can be displayed and moves based on its own polling of the keyboard object

	private static Image image;	
	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;	

	private final double VELOCITY = 200;
	
	private int score;
	
	public ScoreKeeperRight(double centerX, double centerY, int playerScore) {

		this.centerX = centerX;
		this.centerY = centerY;
        this.score = playerScore;

		
		if (image == null) {
			try {
				image = ImageIO.read(new File(String.format("res/numFamily/num%d.png", playerScore)));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}		
	}


	public Image getImage() {
		return image;
	}
	
	public Image setImage(int num) {
		try {
            // Update the image based on the current playerScore
            this.image = ImageIO.read(new File(String.format("res/numFamily/num%d.png", num)));
            System.out.println("num" + num);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
		return image;
	}
	
	//DISPLAYABLE
	
	public boolean getVisible() {
		return true;
	}
	
	public double getMinX() {
		return centerX - (width / 2);
	}

	public double getMaxX() {
		return centerX + (width / 2);
	}

	public double getMinY() {
		return centerY - (height / 2);
	}

	public double getMaxY() {
		return centerY + (height / 2);
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public double getCenterX() {
		return centerX;
	};

	public double getCenterY() {
		return centerY;
	};
	
	
	public boolean getDispose() {
		return dispose;
	}

	public void setDispose(boolean dispose) {
		this.dispose = dispose;
	}


	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		
		double velocityX = 0;
		double velocityY = 0;
		
		//set velocity based on current state of the keyboard
		
		//LEFT ARROW
		if (keyboard.keyDown(37)) {
			velocityX = -VELOCITY;
		}
		//UP ARROW
		if (keyboard.keyDown(38)) {
			velocityY = -VELOCITY;			
		}
		//RIGHT ARROW
		if (keyboard.keyDown(39)) {
			velocityX += VELOCITY;
		}
		// DOWN ARROW
		if (keyboard.keyDown(40)) {
			velocityY += VELOCITY;			
		}

		//calculate new position based on velocity and time elapsed
		//this.centerX += actual_delta_time * 0.001 * velocityX;
		//this.centerY += actual_delta_time * 0.001 * velocityY;
				
	}
	
    public int getScore() {
        return score;
    }

    public void updateScore(int newScore) {
        this.score = newScore;
    }

}
