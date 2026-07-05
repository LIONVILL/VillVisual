# ⚙️ Configuration `config.yml`

This is the main file where you describe all the logic: **which event** (`trigger`) on **which object** (`target`) triggers **which effects** (`effects`).

## Overall structure

```yaml
rules:
  - trigger: <TRIGGER>
    target: <TARGET>
    effects:
      - type: <EFFECT_TYPE>
        # effect parameters
```

The file consists of a list of **rules** (`rules`). Each rule is an "if → then":

- **`trigger`** — which event we're listening for (see the [full list of triggers](triggers.md))
- **`target`** — exactly what we're reacting to: a specific block, item, world, etc. (depends on the trigger, see [targets](targets.md))
- **`effects`** — a list of one or more effects that will fire (see the [full list of effects](effects.md))

!!! tip "You can have as many rules as you want"
    Just add new blocks with `- trigger:` at the same indentation level — they run independently of each other.

---

## Example #1 — a simple rule

```yaml
rules:
  - trigger: BLOCK_PLACE
    target: DIAMOND_BLOCK
    effects:
      - type: FLAME
        amount: 50
        speed: 0.05
        offset:
          x: 0.5
          y: 0.5
          z: 0.5
```

Line by line:

| Line | Meaning |
|---|---|
| `trigger: BLOCK_PLACE` | Listening for the "player placed a block" event |
| `target: DIAMOND_BLOCK` | Only react if it was specifically a diamond block (case-sensitive!) |
| `effects:` | Start of the effects list for this rule |
| `type: FLAME` | Effect type — a regular particle (`PARTICLE`), in this case a flame |
| `amount: 50` | 50 particles will spawn at once |
| `speed: 0.05` | Particles spread out slowly |
| `offset.x/y/z: 0.5` | Particles appear within a 0.5-block radius around the event location |

**Result:** when a player places a `DIAMOND_BLOCK`, 50 fire particles appear around the block, spreading slowly within a 0.5-block radius.

---

## Example #2 — combo effects

```yaml
  - trigger: CRAFT_ITEM
    target: CRAFTING_TABLE
    effects:
      - type: FLAME
        amount: 50
        speed: 0.05
        offset:
          x: 0.5
          y: 0.5
          z: 0.5

      - type: FIREWORK
        shape: STAR
        flicker: true
        trail: true
        power: 1
        colors:
          - "FFD700"
          - "FFFFFF"

      - type: TITLE
        title: "<gradient:gold:yellow>First workbench!</gradient>"
        subtitle: "<white>The start of a great journey</white>"
        fadeIn: 20
        stay: 60
        fadeOut: 20
```

!!! info "The main feature — combos"
    You can attach an **unlimited** number of effects to a **single** trigger. They all fire at the same time, one after another in the `effects:` list.

What happens when crafting a `CRAFTING_TABLE`:

1. **`FLAME`** — 50 fire particles spread around the player/block
2. **`FIREWORK`** — a firework launches in a star shape (`STAR`), with flicker (`flicker: true`) and a smoke trail (`trail: true`); colors are gold (`FFD700`) and white (`FFFFFF`)
3. **`TITLE`** — a large text "First workbench!" is shown on the player's screen for 3 seconds (`stay: 60` ticks), with a gold→yellow gradient, plus the subtitle "The start of a great journey"

All three effects happen **simultaneously** at the moment of the event — this is how "milestone" events are made, like "first craft", "first death", "first diamond", etc.

---

## Indentation rules (YAML)

YAML is very sensitive to indentation — this is a common source of errors:

- Each nesting level = **2 spaces** (not a Tab!)
- `trigger`, `target`, `effects` must be at the same indentation level
- Each effect in the list starts with `- type:`
- It's recommended (but not required) to leave a blank line between rules (`- trigger:`) for readability

```yaml
rules:
  - trigger: ...      # level 1: start of the rule
    target: ...        # same level as trigger
    effects:            # same level
      - type: ...        # level 2: start of the effect
        amount: ...        # same level as type
```

!!! warning "Common mistake"
    If you shift `target:` or `effects:` even one space out of place, the config might either fail to load or (worse) silently not work. Use an online YAML validator if something isn't working.

---

## Building your own rule — template

```yaml
rules:
  - trigger: YOUR_TRIGGER          # see guides/triggers.md
    target: YOUR_TARGET              # see guides/targets.md
    effects:
      - type: YOUR_EFFECT_1          # see guides/effects.md
        parameter1: value
        parameter2: value

      - type: YOUR_EFFECT_2           # you can add more effects
        parameter1: value
```

See also:

- [Full list of triggers](triggers.md)
- [Full list of effects and their parameters](effects.md)
- [Targets](targets.md)
