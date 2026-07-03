package com.villoni.devl.effect;

import com.villoni.devl.model.TriggerContext;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.ConfigurationSection;

public final class GeometryEffect implements VisualEffect {
    @Override
    public void execute(TriggerContext context, ConfigurationSection data) {
        String shape = data.getString("shape", "SPHERE").toUpperCase();
        String particleStr = data.getString("particle", "END_ROD").toUpperCase();
        double size = data.getDouble("size", 1.5);

        Particle particle;
        try { particle = Particle.valueOf(particleStr); } catch (Exception e) { particle = Particle.END_ROD; }

        Location loc = context.location();
        switch (shape) {
            case "SPHERE" -> spawnSphere(loc, particle, size);
            case "CUBE" -> spawnCube(loc, particle, size);
            case "TORNADO" -> spawnTornado(loc, particle, size);
            case "STAR" -> spawnStar(loc, particle, size);
            default -> spawnSphere(loc, particle, size);
        }
    }

    private void spawnSphere(Location loc, Particle p, double r) {
        for (double u = 0; u <= Math.PI; u += Math.PI / 10) {
            for (double v = 0; v <= 2 * Math.PI; v += Math.PI / 10) {
                double x = r * Math.sin(u) * Math.cos(v);
                double y = r * Math.cos(u);
                double z = r * Math.sin(u) * Math.sin(v);
                loc.add(x, y, z);
                loc.getWorld().spawnParticle(p, loc, 1, 0, 0, 0, 0);
                loc.subtract(x, y, z);
            }
        }
    }

    private void spawnCube(Location loc, Particle p, double size) {
        double half = size / 2;
        for (double x = -half; x <= half; x += 0.3) {
            for (double y = -half; y <= half; y += 0.3) {
                for (double z = -half; z <= half; z += 0.3) {
                    int components = 0;
                    if (Math.abs(x) >= half - 0.01) components++;
                    if (Math.abs(y) >= half - 0.01) components++;
                    if (Math.abs(z) >= half - 0.01) components++;
                    if (components >= 2) { // Рисуем только ребра куба
                        loc.add(x, y, z);
                        loc.getWorld().spawnParticle(p, loc, 1, 0, 0, 0, 0);
                        loc.subtract(x, y, z);
                    }
                }
            }
        }
    }

    private void spawnTornado(Location loc, Particle p, double maxRadius) {
        for (double y = 0; y < 3.0; y += 0.2) {
            double radius = (y / 3.0) * maxRadius;
            for (double angle = 0; angle < 2 * Math.PI; angle += Math.PI / 6) {
                double x = radius * Math.cos(angle + y * 2);
                double z = radius * Math.sin(angle + y * 2);
                loc.add(x, y, z);
                loc.getWorld().spawnParticle(p, loc, 1, 0, 0, 0, 0);
                loc.subtract(x, y, z);
            }
        }
    }

    private void spawnStar(Location loc, Particle p, double size) {
        for (int i = 0; i < 5; i++) {
            double angle1 = i * 2 * Math.PI / 5;
            double angle2 = (i + 2) * 2 * Math.PI / 5;
            double x1 = size * Math.cos(angle1), z1 = size * Math.sin(angle1);
            double x2 = size * Math.cos(angle2), z2 = size * Math.sin(angle2);
            for (double d = 0; d <= 1; d += 0.1) {
                double x = x1 + (x2 - x1) * d;
                double z = z1 + (z2 - z1) * d;
                loc.add(x, 0.5, z);
                loc.getWorld().spawnParticle(p, loc, 1, 0, 0, 0, 0);
                loc.subtract(x, 0.5, z);
            }
        }
    }
}