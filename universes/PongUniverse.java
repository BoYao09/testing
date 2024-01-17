import java.util.ArrayList;

public class PongUniverse implements Universe {

	private boolean complete = false;	
	private DisplayableSprite player1 = null;
	private DisplayableSprite player2 = null;
	private ScoreKeeperLeft leftScoreKeeper =null;
	private ScoreKeeperRight rightScoreKeeper =null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	private ArrayList<Background> backgrounds = new ArrayList<Background>();
	private ArrayList<DisplayableSprite> disposalList = new ArrayList<DisplayableSprite>();
	private int rightPlayerScore = 0;
	private int leftPlayerScore = 0;
	
	
	public PongUniverse () {

		this.setXCenter(0);
		this.setYCenter(0);
		player1 = new PongSpriteLeft(50 - (AnimationFrame.SCREEN_WIDTH/2),0);
		sprites.add(player1);
		
		player2 = new PongSpriteRight((AnimationFrame.SCREEN_WIDTH/2) -50, 0);
		sprites.add(player2);
		
		PongBallSprite PongBallsprite = new PongBallSprite(100 - 200 , -100 , 200, 200);			
		sprites.add(PongBallsprite);
		
		leftScoreKeeper = new ScoreKeeperLeft(50 - (AnimationFrame.SCREEN_WIDTH/2), (AnimationFrame.SCREEN_HEIGHT/2) - 50, leftPlayerScore);
		sprites.add(leftScoreKeeper);
		
		rightScoreKeeper = new ScoreKeeperRight((AnimationFrame.SCREEN_WIDTH/2) -50, (AnimationFrame.SCREEN_HEIGHT/2) -50, rightPlayerScore);
		sprites.add(rightScoreKeeper);
		
		//top
		sprites.add(new PongBarrierSprite(AnimationFrame.SCREEN_WIDTH / -2,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / 2, AnimationFrame.SCREEN_HEIGHT / -2 + 16, true));
		//bottom
		sprites.add(new PongBarrierSprite(AnimationFrame.SCREEN_WIDTH / -2,AnimationFrame.SCREEN_HEIGHT / 2 - 16, AnimationFrame.SCREEN_WIDTH / 2, AnimationFrame.SCREEN_HEIGHT / 2, true));
		//left
		sprites.add(new PongBarrierSprite(AnimationFrame.SCREEN_WIDTH / -2,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / -2 + 16, AnimationFrame.SCREEN_HEIGHT / 2, true));
		//right
		sprites.add(new PongBarrierSprite(AnimationFrame.SCREEN_WIDTH / 2 - 16,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / 2, AnimationFrame.SCREEN_HEIGHT / 2, true));
				
			
	}

	public double getScale() {
		return 1;
	}

	public double getXCenter() {
		return 0;
	}

	public double getYCenter() {
		return 0;
	}

	public void setXCenter(double xCenter) {
	}

	public void setYCenter(double yCenter) {
	}

	public boolean isComplete() {
		return complete;
	}
	
	public void setComplete(boolean complete) {
		complete = true;
	}
	
	public ArrayList<Background> getBackgrounds() {
		return backgrounds;
	}	

	public DisplayableSprite getPlayer1() {
		return player1;
	}

	public ArrayList<DisplayableSprite> getSprites() {
		return sprites;
	}

	public boolean centerOnPlayer() {
		return false;
	}		
	
	public void incrementRightPlayerScore() {
        rightPlayerScore++;
        rightScoreKeeper.updateScore(rightPlayerScore);
        rightScoreKeeper.setImage(rightPlayerScore);
    }

    public void incrementLeftPlayerScore() {
        leftPlayerScore++;
        leftScoreKeeper.updateScore(leftPlayerScore);
        leftScoreKeeper.setImage(leftPlayerScore);

    }
	
	public void update(KeyboardInput keyboard, long actual_delta_time) {
		
		if (keyboard.keyDownOnce(27)) {
			complete = true;
			this.player1.setDispose(true);
		}
		
		if (rightPlayerScore >=7) {
			System.out.println("rightPlayerWon");
			complete = true;
			this.player1.setDispose(true);
		}else if(leftPlayerScore >= 7) {
			System.out.println("leftPlayerWon");
			complete = true;
			this.player1.setDispose(complete);
		}
		
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
    	} 
		
		disposeSprites();
		
	}
	
    protected void disposeSprites() {
        
    	//collect a list of sprites to dispose
    	//this is done in a temporary list to avoid a concurrent modification exception
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
    		if (sprite.getDispose() == true) {
    			disposalList.add(sprite);
    		}
    	}
		
		//go through the list of sprites to dispose
		//note that the sprites are being removed from the original list
		for (int i = 0; i < disposalList.size(); i++) {
			DisplayableSprite sprite = disposalList.get(i);
			sprites.remove(sprite);
    	}
		
		//clear disposal list if necessary
    	if (disposalList.size() > 0) {
    		disposalList.clear();
    	}
    }


	public String toString() {
		return "ShellUniverse";
	}
	

}
