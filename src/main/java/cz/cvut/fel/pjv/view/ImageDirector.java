package cz.cvut.fel.pjv.view;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * This class provides data structure and methods for calling images only by their names.
 */
public class ImageDirector {
	private final Map<String, Image> stringImageMap = new HashMap<>();

	/**
	 * This method adds pair of name and image to Map
	 * @param name This is name (key) of inserted image.
	 * @param image This is the inserted image.
	 */
	public void addImage(String name, Image image){
		stringImageMap.put(name, image);
	}

	/**
	 * This method returns image upon its name.
	 * @param key This is key for searching in Map
	 * @return image which is in pair with inserted key.
	 */
	public Image getImage(String key) {
		return stringImageMap.get(key);
	}

}
