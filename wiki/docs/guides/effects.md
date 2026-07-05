# 🎨 Full list of EFFECTS (`type:`) and their parameters

## `PARTICLE` — Regular particles

| Parameter | Example | Description |
|---|---|---|
| `particle` | `FLAME` | Any particle name from the game: `HEART`, `LAVA`, `SMOKE`, `SOUL_FIRE_FLAME`... |
| `amount` | `20` | Number of particles |
| `speed` | `0.1` | Spread speed |
| `offset.x / y / z` | `0.2` | Radius of the spawn area spread |

---

## `GEOMETRY` — 3D shapes made of particles

| Parameter | Example | Description |
|---|---|---|
| `shape` | `SPHERE` | Shapes: `SPHERE`, `CUBE`, `TORNADO`, `STAR` |
| `particle` | `END_ROD` | Any particle from the game |
| `size` | `1.5` | Size or radius of the shape |

---

## `FIREWORK` — Custom fireworks

| Parameter | Example | Description |
|---|---|---|
| `shape` | `BALL` | Shapes: `BALL`, `LARGE_BALL`, `STAR`, `BURST`, `CREEPER` |
| `colors` | `["FF0000", "00FF00"]` | List of colors in HEX format without the hash |
| `flicker` | `true` / `false` | Flicker effect on the stars after the explosion |
| `trail` | `true` / `false` | Smoke trail during launch |
| `power` | `1` | Flight height before exploding, from 1 to 3 |

---

## `SOUND` — Game sounds

| Parameter | Example | Description |
|---|---|---|
| `sound` | `ENTITY_PLAYER_LEVELUP` | Any Bukkit API sound constant |
| `volume` | `1.0` | Volume |
| `pitch` | `1.0` | Pitch/speed |

---

## `TITLE` — Full-screen text

| Parameter | Example | Description |
|---|---|---|
| `title` | `"<gradient:gold:yellow>Title</gradient>"` | Supports MiniMessage colors |
| `subtitle` | `"<white>Subtitle</white>"` | — |
| `fadeIn` | `20` | Fade-in duration in ticks, 20 ticks = 1 second |
| `stay` | `60` | On-screen duration in ticks |
| `fadeOut` | `20` | Fade-out duration in ticks |

---

## `EXPLOSION` — Explosions

| Parameter | Example | Description |
|---|---|---|
| `explosion-type` | `NORMAL` | `NORMAL` — regular explosion, `HUGE` — mega explosion |
| `break-blocks` | `true` / `false` | Whether the explosion breaks blocks and damages the world |
| `power` | `2.0` | Radius and strength of the physical explosion |

---

## `LIGHTNING` — Lightning

| Parameter | Example | Description |
|---|---|---|
| `visual-only` | `true` / `false` | `true` — purely visual, no damage; `false` — a real lightning bolt that shocks and sets things on fire |
