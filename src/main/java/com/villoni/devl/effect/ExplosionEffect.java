package com.villoni.devl.effect;

import com.villoni.devl.model.TriggerContext;
import org.bukkit.Particle;
import org.bukkit.configuration.ConfigurationSection;

public final class ExplosionEffect implements VisualEffect {
    @Override
    public void execute(TriggerContext context, ConfigurationSection data) {
        String type = data.getString("explosion-type", "NORMAL").toUpperCase();
        boolean breakBlocks = data.getBoolean("break-blocks", false);
        float power = (float) data.getDouble("power", 2.0);

        Particle particle = switch (type) {
            case "HUGE" -> Particle.EXPLOSION_EMITTER;
            default -> Particle.EXPLOSION;
        };

        context.location().getWorld().spawnParticle(
                particle,
                context.location(),
                1,
                0.0, 0.0, 0.0,
                0.0
        );

        if (breakBlocks) {
            context.location().getWorld().createExplosion(context.location(), power, false, true);
        }
    }
}