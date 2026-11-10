package dev.enderdevelopment.asyncmapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

final class MapperConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("asyncmapper.json");

    boolean enabled = true;
    int targetSlot = 2;
    boolean notifications = true;

    static MapperConfig load() {
        if (!Files.exists(FILE)) return new MapperConfig();
        try {
            MapperConfig config = GSON.fromJson(Files.readString(FILE), MapperConfig.class);
            if (config == null) config = new MapperConfig();
            config.targetSlot = Math.max(0, Math.min(8, config.targetSlot));
            return config;
        } catch (IOException | RuntimeException exception) {
            System.err.println("[AsyncMapper] Could not read config; using defaults: " + exception.getMessage());
            return new MapperConfig();
        }
    }

    void save() {
        try {
            Files.createDirectories(FILE.getParent());
            Files.writeString(FILE, GSON.toJson(this));
        } catch (IOException exception) {
            System.err.println("[AsyncMapper] Could not save config: " + exception.getMessage());
        }
    }
}
