package com.villoni.devl;

import com.villoni.devl.command.EffectsCommand;
import com.villoni.devl.config.ConfigManager;
import com.villoni.devl.config.LangManager;
import com.villoni.devl.effect.*;
import com.villoni.devl.listener.TriggerListener;
import com.villoni.devl.registry.EffectRegistry;
import com.villoni.devl.service.EffectService;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class VillVisual extends JavaPlugin {

    private ConfigManager configManager;
    private LangManager langManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.configManager = new ConfigManager(this);
        this.langManager = new LangManager(this);
        EffectRegistry effectRegistry = new EffectRegistry();

        effectRegistry.register("FLAME", new FlameEffect());
        effectRegistry.register("TITLE", new TitleEffect());
        effectRegistry.register("SOUND", new SoundEffect());
        effectRegistry.register("EXPLOSION", new ExplosionEffect());
        effectRegistry.register("CIRCLE", new CircleEffect());
        effectRegistry.register("HELIX", new HelixEffect());
        effectRegistry.register("PARTICLE", new ParticleEffect());
        effectRegistry.register("GEOMETRY", new GeometryEffect());
        effectRegistry.register("LIGHTNING", new LightningEffect());
        effectRegistry.register("FIREWORK", new FireworkEffect());

        EffectService effectService = new EffectService(this, configManager, effectRegistry);

        getServer().getPluginManager().registerEvents(new TriggerListener(effectService), this);

        PluginCommand command = getCommand("visualeffects");
        if (command != null) {
            EffectsCommand effectsCommand = new EffectsCommand(configManager, langManager);
            command.setExecutor(effectsCommand);
            command.setTabCompleter(effectsCommand);
        }
    }

    @Override
    public void onDisable() {
    }
}