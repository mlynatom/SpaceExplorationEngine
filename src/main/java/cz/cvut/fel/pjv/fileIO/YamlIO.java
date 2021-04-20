package cz.cvut.fel.pjv.fileIO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class YamlIO {
	private static final Logger LOGGER = Logger.getLogger(YamlIO.class.getName());
	private static final String LEVEL_PATH = "level.yaml";
	private static final String PLAYER_PATH = "player.yaml";

	public static LevelData loadLevelDataYaml() {
		try {
			ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
			return objectMapper.readValue(new File(LEVEL_PATH), LevelData.class);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Loading of level data from YAML file failed ", e.getMessage());
			return null;
		}
	}

	public static PlayerData loadPlayerDataYaml() {
		try {
			ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
			return objectMapper.readValue(new File(PLAYER_PATH), PlayerData.class);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Loading of player data from YAML file failed ", e.getMessage());
			return null;
		}
	}

	public static void savePlayerDataYaml(PlayerData playerData) {
		try {
			ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
			objectMapper.writeValue(new File(PLAYER_PATH), playerData);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Saving of player data failed ", e.getMessage());
		}
	}
}
