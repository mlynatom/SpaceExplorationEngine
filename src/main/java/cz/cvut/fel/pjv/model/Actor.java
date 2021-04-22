package cz.cvut.fel.pjv.model;

import javafx.scene.image.Image;
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
	protected List<Image> imageList = new ArrayList<>(); // list for all image states
	protected ImageView spriteFrame;
	protected SVGPath spriteBound;

	public Actor(double positionX, double positionY, String spriteBound, Image... spriteImage) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.spriteBound = new SVGPath();
		this.spriteBound.setContent(spriteBound);
		spriteFrame = new ImageView(spriteImage[0]);
		imageList.addAll(Arrays.asList(spriteImage));

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

	public List<Image> getImageList() {
		return imageList;
	}

	public void setImageList(List<Image> imageList) {
		this.imageList = imageList;
	}

	public ImageView getSpriteFrame() {
		return spriteFrame;
	}

	public void setSpriteFrame(ImageView spriteFrame) {
		this.spriteFrame = spriteFrame;
	}

	public SVGPath getSpriteBound() {
		return spriteBound;
	}

	public void setSpriteBound(SVGPath spriteBound) {
		this.spriteBound = spriteBound;
	}


}
