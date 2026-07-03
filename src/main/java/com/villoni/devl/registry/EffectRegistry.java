package com.villoni.devl.registry;

import com.villoni.devl.effect.VisualEffect;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class EffectRegistry {

    private final Map<String, VisualEffect> effects = new ConcurrentHashMap<>();

    public void register(String type, VisualEffect effect) {
        effects.put(type.toUpperCase(), effect);
    }

    public Optional<VisualEffect> getEffect(String type) {
        return Optional.ofNullable(effects.get(type.toUpperCase()));
    }
}