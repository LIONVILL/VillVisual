package com.villoni.devl.effect;

import com.villoni.devl.model.TriggerContext;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;

public final class SoundEffect implements VisualEffect {
    @Override
    public void execute(TriggerContext context, ConfigurationSection data) {
        String soundName = data.getString("sound");
        if (soundName == null) return;

        Sound sound = Sound.valueOf(soundName.toUpperCase());
        float volume = (float) data.getDouble("volume", 1.0);
        float pitch = (float) data.getDouble("pitch", 1.0);

        context.player().playSound(context.location(), sound, volume, pitch);
    }
}