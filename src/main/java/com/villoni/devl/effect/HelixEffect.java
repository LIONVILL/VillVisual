package com.villoni.devl.effect;

import com.villoni.devl.model.TriggerContext;
import org.bukkit.Particle;
import org.bukkit.configuration.ConfigurationSection;

public final class HelixEffect implements VisualEffect {
    @Override
    public void execute(TriggerContext context, ConfigurationSection data) {
        double radius = data.getDouble("radius", 1.0);
        double height = data.getDouble("height", 2.0);
        int particles = data.getInt("amount", 60);
        Particle particle = Particle.SOUL_FIRE_FLAME;

        for (int i = 0; i < particles; i++) {
            double ratio = (double) i / particles;
            double angle = ratio * 4 * Math.PI; // 2 полных оборота
            double y = ratio * height;
            double x = radius * Math.cos(angle);
            double z = radius * Math.sin(angle);

            context.location().add(x, y, z);
            context.location().getWorld().spawnParticle(particle, context.location(), 1, 0, 0, 0, 0);
            context.location().subtract(x, y, z);
        }
    }
}