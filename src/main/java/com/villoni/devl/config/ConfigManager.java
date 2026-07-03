package com.villoni.devl.config;

import com.villoni.devl.VillVisual;
import com.villoni.devl.model.EffectDefinition;
import com.villoni.devl.model.Rule;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public final class ConfigManager {

    private final VillVisual plugin;
    private final List<Rule> rules = new CopyOnWriteArrayList<>();

    public ConfigManager(VillVisual plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {
        plugin.reloadConfig();
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();

        rules.clear();
        List<Map<?, ?>> rulesList = config.getMapList("rules");

        for (Map<?, ?> ruleMap : rulesList) {
            String trigger = (String) ruleMap.get("trigger");
            if (trigger == null) continue;

            String targetStr = (String) ruleMap.get("target");
            Optional<String> target = Optional.ofNullable(targetStr);

            List<EffectDefinition> effects = new ArrayList<>();
            Object effectsObj = ruleMap.get("effects");

            if (effectsObj instanceof List<?> list) {
                for (Object item : list) {
                    if (item instanceof Map<?, ?> effectMap) {
                        String type = (String) effectMap.get("type");
                        if (type == null) continue;

                        MemoryConfiguration section = new MemoryConfiguration();
                        convertMapToSection(effectMap, section);

                        effects.add(new EffectDefinition(type.toUpperCase(), section));
                    }
                }
            }

            rules.add(new Rule(trigger.toUpperCase(), target.map(String::toUpperCase), Collections.unmodifiableList(effects)));
        }
    }

    @SuppressWarnings("unchecked")
    private void convertMapToSection(Map<?, ?> map, ConfigurationSection section) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            String key = entry.getKey().toString();
            Object value = entry.getValue();

            if (value instanceof Map<?, ?> subMap) {
                ConfigurationSection subSection = section.createSection(key);
                convertMapToSection(subMap, subSection);
            } else {
                section.set(key, value);
            }
        }
    }

    public List<Rule> getRules() {
        return rules;
    }
}