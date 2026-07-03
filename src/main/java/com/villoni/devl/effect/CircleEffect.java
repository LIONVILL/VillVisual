package com.villoni.devl.effect;

import com.villoni.devl.model.TriggerContext;
import org.bukkit.Particle;
import org.bukkit.configuration.ConfigurationSection;

public final class CircleEffect implements VisualEffect {
    @Override
    public void execute(TriggerContext context, ConfigurationSection data) {
        double radius = data.getDouble("radius", 1.5);
        int points = data.getInt("points", 30);
        String particleName = data.getString("particle", "HAPPY_VILLAGER").toUpperCase();

        Particle particle;
        try {
            particle = Particle.valueOf(particleName);
        } catch (IllegalArgumentException e) {
            particle = Particle.HAPPY_VILLAGER;
        }

        double increment = (2 * Math.PI) / points;
        for (int i = 0; i < points; i++) {
            double angle = i * increment;
            double x = radius * Math.cos(angle);
            double z = radius * Math.sin(angle);

            context.location().add(x, 0.1, z);
            context.location().getWorld().spawnParticle(particle, context.location(), 1, 0, 0, 0, 0);
            context.location().subtract(x, 0.1, z);
        }
    }
}