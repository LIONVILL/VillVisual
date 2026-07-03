package com.villoni.devl.effect;

import com.villoni.devl.model.TriggerContext;
import org.bukkit.Particle;
import org.bukkit.configuration.ConfigurationSection;

public final class FlameEffect implements VisualEffect {
    @Override
    public void execute(TriggerContext context, ConfigurationSection data) {
        int amount = data.getInt("amount", 10);
        double speed = data.getDouble("speed", 0.1);
        double offsetX = data.getDouble("offset.x", 0.0);
        double offsetY = data.getDouble("offset.y", 0.0);
        double offsetZ = data.getDouble("offset.z", 0.0);

        context.location().getWorld().spawnParticle(
                Particle.FLAME, context.location(), amount, offsetX, offsetY, offsetZ, speed
        );
    }
}