# Full list of TRIGGERS (`trigger:`)

| Trigger name | What to put in `target:` (case-sensitive!) | Event description |
|---|---|---|
| `BLOCK_PLACE` | Block name (`DIAMOND_BLOCK`, `STONE`) | Player placed a block |
| `BLOCK_BREAK` | Block name (`OAK_LOG`, `DIRT`) | Player broke a block |
| `BLOCK_INTERACT` | Block name (`CHEST`, `LEVER`) | Left/right click on a block |
| `CRAFT_ITEM` | Item name (`CRAFTING_TABLE`) | Player crafted an item |
| `EAT` | Food name (`GOLDEN_APPLE`) | Player ate food |
| `DRINK` | Potion/bucket name (`POTION`, `MILK_BUCKET`) | Player drank something |
| `DROP_ITEM` | Item name (`DIAMOND`) | Player dropped an item |
| `PICKUP_ITEM` | Item name (`IRON_INGOT`) | Player picked up an item |
| `OPEN_CHEST` | `CHEST` | Player opened a regular chest |
| `OPEN_INVENTORY` | Inventory type (`ANVIL`, `FURNACE`) | Opening other containers |
| `DAMAGE` | Damage cause (`FALL`, `LAVA`, `FIRE`) | Player took damage |
| `DEATH` | Not used | Player died |
| `KILL_PLAYER` | Player nickname | Player killed another player |
| `KILL_ENTITY` | Mob type (`CREEPER`, `ZOMBIE`) | Player killed a mob |
| `TELEPORT` | Cause (`ENDER_PEARL`, `COMMAND`) | Player teleported |
| `PORTAL` | Cause (`NETHER_PORTAL`, `ENDER_PORTAL`) | Player entered a portal |
| `PLAYER_COMMAND` | Full command (`/spawn`, `/home`) | Player entered a command |
| `SEND_MESSAGE` | Message text | Player wrote in chat |
| `WORLD` | World name (`world_nether`, `world_the_end`) | Player changed world |
| `LEVEL_UP` | Level number (`30`) | Player leveled up experience |
| `ADVANCEMENT` | Achievement ID (`minecraft:story/mine_diamond`) | Achievement earned |
| `BUCKET_FILL` | Bucket name (`WATER_BUCKET`) | Filled a bucket with liquid |
| `BUCKET_EMPTY` | Bucket name (`LAVA_BUCKET`) | Emptied a bucket of liquid |
| `JOIN` / `QUIT` | Not used | Joined / left the server |
| `RESPAWN` | Not used | Respawned at spawn/bed |
| `WAKEUP` | Not used | Player got out of bed |
| `BED_ENTER` / `BED_LEAVE` | Not used | Got in bed / got out of bed |
| `MOVE` / `JUMP` | Not used | Moved a block / jumped |
| `SNEAK` / `SPRINT` | Not used | Started sneaking / sprinting |
| `SWIM` / `GLIDE` / `FLY` | Not used | Swimming / elytra gliding / fly mode |
