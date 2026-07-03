package com.villoni.devl.model;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public record TriggerContext(Player player, Location location, Object rawTarget) {}