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
	protected double iX; //initial x position
	protected double iY; //initial y position
	protected List<Image> imageList = new ArrayList<>(); // list for all image states
	protected ImageView spriteFrame;
	protected SVGPath spriteBound;

	public Actor(double iX, double iY, String spriteBound, Image... spriteImage) {
		this.iX = iX;
		this.iY = iY;
		this.spriteBound = new SVGPath();
		this.spriteBound.setContent(spriteBound);
		spriteFrame = new ImageView(spriteImage[0]);
		imageList.addAll(Arrays.asList(spriteImage));

	}

	/**
	 * This abstract method specifies what will each ancestor of Actor class do in each pulse of GamePlayLoop
	 */
	public abstract void update();

	public double getiX() {
		return iX;
	}

	public double getiY() {
		return iY;
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
