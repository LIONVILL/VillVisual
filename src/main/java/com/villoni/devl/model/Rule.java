package com.villoni.devl.model;

import java.util.List;
import java.util.Optional;

public record Rule(String triggerType, Optional<String> target, List<EffectDefinition> effects) {}