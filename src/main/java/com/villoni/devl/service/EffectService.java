package com.villoni.devl.service;

import com.villoni.devl.VillVisual;
import com.villoni.devl.config.ConfigManager;
import com.villoni.devl.model.EffectDefinition;
import com.villoni.devl.model.Rule;
import com.villoni.devl.model.TriggerContext;
import com.villoni.devl.registry.EffectRegistry;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class EffectService {

    private final VillVisual plugin;
    private final ConfigManager configManager;
    private final EffectRegistry effectRegistry;

    public EffectService(VillVisual plugin, ConfigManager configManager, EffectRegistry effectRegistry) {
        this.plugin = plugin;
        this.configManager = configManager;
        this.effectRegistry = effectRegistry;
    }

    public void processTrigger(String triggerType, TriggerContext context) {
        CompletableFuture.runAsync(() -> {
            List<Rule> activeRules = configManager.getRules().stream()
                    .filter(rule -> rule.triggerType().equals(triggerType))
                    .filter(rule -> rule.target().isEmpty() || checkTarget(rule.target().get(), context.rawTarget()))
                    .toList();

            for (Rule rule : activeRules) {
                for (EffectDefinition def : rule.effects()) {
                    effectRegistry.getEffect(def.type()).ifPresent(effect ->
                            Bukkit.getScheduler().runTask(plugin, () -> effect.execute(context, def.data()))
                    );
                }
            }
        });
    }

    private boolean checkTarget(String ruleTarget, Object contextTarget) {
        if (contextTarget == null) return false;
        return ruleTarget.equalsIgnoreCase(contextTarget.toString());
    }
}