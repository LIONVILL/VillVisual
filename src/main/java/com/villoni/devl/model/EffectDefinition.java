package com.villoni.devl.model;

import org.bukkit.configuration.ConfigurationSection;

public record EffectDefinition(String type, ConfigurationSection data) {}