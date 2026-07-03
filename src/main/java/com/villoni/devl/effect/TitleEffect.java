package com.villoni.devl.effect;

import com.villoni.devl.model.TriggerContext;
import net.kyori.adventure.title.Title;
import org.bukkit.configuration.ConfigurationSection;
import java.time.Duration;

public final class TitleEffect implements VisualEffect {
    @Override
    public void execute(TriggerContext context, ConfigurationSection data) {
        String titleText = data.getString("title", "");
        String subtitleText = data.getString("subtitle", "");

        Title.Times times = Title.Times.times(
                Duration.ofMillis(data.getInt("fadeIn", 10) * 50L),
                Duration.ofMillis(data.getInt("stay", 40) * 50L),
                Duration.ofMillis(data.getInt("fadeOut", 10) * 50L)
        );

        context.player().showTitle(Title.title( // Просто, блядь без вопросов
                net.kyori.adventure.text.minimessage.MiniMessage.miniMessage().deserialize(titleText),
                net.kyori.adventure.text.minimessage.MiniMessage.miniMessage().deserialize(subtitleText),
                times
        ));
    }
}