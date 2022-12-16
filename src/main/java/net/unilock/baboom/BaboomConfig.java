package net.unilock.baboom;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.function.Supplier;

public class BaboomConfig {
    public ArrayList<String> targetUuids;
    public String targetDimension;
    public boolean createFire;
    public float explosionPower;

    public BaboomConfig() {
        this.targetUuids = new ArrayList<>();
        this.targetDimension = "minecraft:the_nether";
        this.createFire = true;
        this.explosionPower = 4.0F;
    }

    public static final Gson GSON = new GsonBuilder()
        .setPrettyPrinting()
        .disableHtmlEscaping()
        .serializeNulls()
        .create();

    private static final File configDir = FabricLoader.getInstance().getConfigDir().toFile();

    private static final File CONFIG_FILE = new File(configDir + File.separator + "baboom.json");

    public static <T> @NotNull T load(Class<T> configClass, Supplier<T> defaultConfig) {
        var file = CONFIG_FILE;
        T config = null;

        if (file.exists()) {
            try (var fileReader = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(file)
                )
            )) {
                config = GSON.fromJson(fileReader, configClass);
            } catch (IOException e) {
                Baboom.LOGGER.error("Error loading config!");
                e.printStackTrace();
            }
        }

        if (config == null) {
            config = defaultConfig.get();
        }

        BaboomConfig.save(config);
        return config;
    }

    public static <T> void save(T config) {
        var file = CONFIG_FILE;

        try (var fileWriter = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            GSON.toJson(config, fileWriter);
        } catch (IOException e) {
            Baboom.LOGGER.error("Error saving config!");
            e.printStackTrace();
        }
    }
}
