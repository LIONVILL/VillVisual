package com.villoni.devl.config;

import com.villoni.devl.VillVisual;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public final class LangManager {

    private final VillVisual plugin;
    private FileConfiguration langConfig;

    public LangManager(VillVisual plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {
        File langFile = new File(plugin.getDataFolder(), "lang.yml");
        if (!langFile.exists()) {
            plugin.saveResource("lang.yml", false);
        }
        langConfig = YamlConfiguration.loadConfiguration(langFile);
    }

    public Component getMessage(String path) {
        String rawMessage = langConfig.getString("messages." + path, "Missing key: " + path);
        String prefix = langConfig.getString("messages.prefix", "");
        return MiniMessage.miniMessage().deserialize(prefix + rawMessage);
    }
}