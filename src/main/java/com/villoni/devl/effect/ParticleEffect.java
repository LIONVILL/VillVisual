package com.villoni.devl.effect;

import com.villoni.devl.model.TriggerContext;
import org.bukkit.Particle;
import org.bukkit.configuration.ConfigurationSection;

public final class ParticleEffect implements VisualEffect {
    @Override
    public void execute(TriggerContext context, ConfigurationSection data) {
        String particleName = data.getString("particle", "FLAME").toUpperCase();
        int amount = data.getInt("amount", 20);
        double speed = data.getDouble("speed", 0.1);
        double offsetX = data.getDouble("offset.x", 0.2);
        double offsetY = data.getDouble("offset.y", 0.2);
        double offsetZ = data.getDouble("offset.z", 0.2);

        Particle particle;
        try {
            particle = Particle.valueOf(particleName);
        } catch (IllegalArgumentException e) {
            particle = Particle.FLAME;
        }

        context.location().getWorld().spawnParticle(
                particle, context.location(), amount, offsetX, offsetY, offsetZ, speed
        );
    }
}