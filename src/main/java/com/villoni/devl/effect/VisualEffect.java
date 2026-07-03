package com.villoni.devl.effect;

import com.villoni.devl.model.TriggerContext;
import org.bukkit.configuration.ConfigurationSection;

public interface VisualEffect {
    void execute(TriggerContext context, ConfigurationSection data);
}