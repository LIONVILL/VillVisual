package com.villoni.devl.effect;

import com.villoni.devl.model.TriggerContext;
import org.bukkit.Color;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Firework;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.List;

public final class FireworkEffect implements VisualEffect {
    @Override
    public void execute(TriggerContext context, ConfigurationSection data) {
        Firework fw = context.location().getWorld().spawn(context.location(), Firework.class);
        FireworkMeta meta = fw.getFireworkMeta();

        List<String> colorsStr = data.getStringList("colors");
        Color color = colorsStr.isEmpty() ? Color.RED : Color.fromRGB(Integer.parseInt(colorsStr.getFirst(), 16));

        org.bukkit.FireworkEffect effect = org.bukkit.FireworkEffect.builder()
                .with(Type.valueOf(data.getString("shape", "BALL").toUpperCase()))
                .withColor(color)
                .flicker(data.getBoolean("flicker", false))
                .trail(data.getBoolean("trail", false))
                .build();

        meta.addEffect(effect);
        meta.setPower(data.getInt("power", 1));
        fw.setFireworkMeta(meta);
    }
}