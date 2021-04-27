package cz.cvut.fel.pjv.model;

import javafx.scene.image.ImageView;
import javafx.scene.shape.SVGPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * "Super class" for all other object on scene.
 */
public abstract class Actor {
	protected double positionX; //initial x position
	protected double positionY; //initial y position
	protected SVGPath spriteBound;
	protected List<String> imageNameList = new ArrayList<>();
	protected ImageView spriteFrame = new ImageView();

	public Actor(double positionX, double positionY, String spriteBound, String... imageName) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.spriteBound = new SVGPath();
		this.spriteBound.setContent(spriteBound);
		imageNameList.addAll(Arrays.asList(imageName));
	}

	/**
	 * This abstract method specifies what will each ancestor of Actor class do in each pulse of GamePlayLoop
	 */
	public abstract void update();

	public double getPositionX() {
		return positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public ImageView getSpriteFrame() {
		return spriteFrame;
	}

	public SVGPath getSpriteBound() {
		return spriteBound;
	}

	public void setSpriteBound(SVGPath spriteBound) {
		this.spriteBound = spriteBound;
	}


}
