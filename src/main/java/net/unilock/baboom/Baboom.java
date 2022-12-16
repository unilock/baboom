package net.unilock.baboom;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Baboom implements ModInitializer {
	public static BaboomConfig config;

	public static final Logger LOGGER = LoggerFactory.getLogger("baboom");

	@Override
	public void onInitialize() {
		LOGGER.info("INITIALIZED");
		config = BaboomConfig.load(BaboomConfig.class, BaboomConfig::new);
	}
}
