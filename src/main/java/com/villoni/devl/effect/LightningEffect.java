package com.villoni.devl.effect;

import com.villoni.devl.model.TriggerContext;
import org.bukkit.configuration.ConfigurationSection;

public final class LightningEffect implements VisualEffect {
    @Override
    public void execute(TriggerContext context, ConfigurationSection data) {
        boolean visualOnly = data.getBoolean("visual-only", true);
        if (visualOnly) {
            context.location().getWorld().strikeLightningEffect(context.location());
        } else {
            context.location().getWorld().strikeLightning(context.location());
        }
    }
}