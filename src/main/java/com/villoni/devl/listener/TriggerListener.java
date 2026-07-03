package com.villoni.devl.listener;

import com.villoni.devl.model.TriggerContext;
import com.villoni.devl.service.EffectService;
import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;

public final class TriggerListener implements Listener {

    private final EffectService effectService;

    public TriggerListener(EffectService effectService) {
        this.effectService = effectService;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        effectService.processTrigger("BLOCK_PLACE", new TriggerContext(
                event.getPlayer(), event.getBlock().getLocation(), event.getBlock().getType().name()
        ));
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        effectService.processTrigger("BLOCK_BREAK", new TriggerContext(
                event.getPlayer(), event.getBlock().getLocation(), event.getBlock().getType().name()
        ));
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK)) {
            effectService.processTrigger("BLOCK_INTERACT", new TriggerContext(
                    event.getPlayer(), event.getClickedBlock().getLocation(), event.getClickedBlock().getType().name()
            ));
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().split(" ")[0];
        effectService.processTrigger("PLAYER_COMMAND", new TriggerContext(
                event.getPlayer(), event.getPlayer().getLocation(), command
        ));
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onSendMessage(AsyncPlayerChatEvent event) {
        effectService.processTrigger("SEND_MESSAGE", new TriggerContext(
                event.getPlayer(), event.getPlayer().getLocation(), event.getMessage()
        ));
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onCraftItem(CraftItemEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            effectService.processTrigger("CRAFT_ITEM", new TriggerContext(
                    player, player.getLocation(), event.getRecipe().getResult().getType().name()
            ));
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        effectService.processTrigger("JOIN", new TriggerContext(
                event.getPlayer(), event.getPlayer().getLocation(), null
        ));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(PlayerQuitEvent event) {
        effectService.processTrigger("QUIT", new TriggerContext(
                event.getPlayer(), event.getPlayer().getLocation(), null
        ));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        effectService.processTrigger("RESPAWN", new TriggerContext(
                event.getPlayer(), event.getRespawnLocation(), null
        ));
    }


    @EventHandler(priority = EventPriority.MONITOR)
    public void onWorldChanged(PlayerChangedWorldEvent event) {
        effectService.processTrigger("WORLD", new TriggerContext(
                event.getPlayer(), event.getPlayer().getLocation(), event.getPlayer().getWorld().getName()
        ));
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getFrom().getBlockX() != event.getTo().getBlockX() ||
                event.getFrom().getBlockZ() != event.getTo().getBlockZ() ||
                event.getFrom().getBlockY() != event.getTo().getBlockY()) {

            effectService.processTrigger("MOVE", new TriggerContext(
                    event.getPlayer(), event.getTo(), null
            ));
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerJump(PlayerJumpEvent event) {
        effectService.processTrigger("JUMP", new TriggerContext(
                event.getPlayer(), event.getTo(), null
        ));
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onSneak(PlayerToggleSneakEvent event) {
        if (event.isSneaking()) {
            effectService.processTrigger("SNEAK", new TriggerContext(
                    event.getPlayer(), event.getPlayer().getLocation(), null
            ));
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onSprint(PlayerToggleSprintEvent event) {
        if (event.isSprinting()) {
            effectService.processTrigger("SPRINT", new TriggerContext(
                    event.getPlayer(), event.getPlayer().getLocation(), null
            ));
        }
    }



    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onFly(PlayerToggleFlightEvent event) {
        if (event.isFlying()) {
            effectService.processTrigger("FLY", new TriggerContext(
                    event.getPlayer(), event.getPlayer().getLocation(), null
            ));
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onTeleport(PlayerTeleportEvent event) {
        effectService.processTrigger("TELEPORT", new TriggerContext(
                event.getPlayer(), event.getTo(), event.getCause().name()
        ));
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPortal(PlayerPortalEvent event) {
        if (event.getTo() != null) {
            effectService.processTrigger("PORTAL", new TriggerContext(
                    event.getPlayer(), event.getTo(), event.getCause().name()
            ));
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDeath(PlayerDeathEvent event) {
        effectService.processTrigger("DEATH", new TriggerContext(
                event.getPlayer(), event.getPlayer().getLocation(), null
        ));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            Player killer = event.getEntity().getKiller();
            if (event.getEntity() instanceof Player deadPlayer) {
                effectService.processTrigger("KILL_PLAYER", new TriggerContext(
                        killer, deadPlayer.getLocation(), deadPlayer.getName()
                ));
            } else {
                effectService.processTrigger("KILL_ENTITY", new TriggerContext(
                        killer, event.getEntity().getLocation(), event.getEntityType().name()
                ));
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            effectService.processTrigger("DAMAGE", new TriggerContext(
                    player, player.getLocation(), event.getCause().name()
            ));
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEatDrink(PlayerItemConsumeEvent event) {
        String type = event.getItem().getType().name();
        String trigger = (type.contains("POTION") || type.contains("BOTTLE") || type.equals("MILK_BUCKET")) ? "DRINK" : "EAT";

        effectService.processTrigger(trigger, new TriggerContext(
                event.getPlayer(), event.getPlayer().getLocation(), type
        ));
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onDropItem(PlayerDropItemEvent event) {
        effectService.processTrigger("DROP_ITEM", new TriggerContext(
                event.getPlayer(), event.getItemDrop().getLocation(), event.getItemDrop().getItemStack().getType().name()
        ));
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPickupItem(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player player) {
            effectService.processTrigger("PICKUP_ITEM", new TriggerContext(
                    player, player.getLocation(), event.getItem().getItemStack().getType().name()
            ));
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onOpenInventory(InventoryOpenEvent event) {
        if (event.getPlayer() instanceof Player player) {
            String targetType = event.getInventory().getType().name();
            String trigger = targetType.equals("CHEST") ? "OPEN_CHEST" : "OPEN_INVENTORY";

            effectService.processTrigger(trigger, new TriggerContext(
                    player, player.getLocation(), targetType
            ));
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onLevelUp(PlayerLevelChangeEvent event) {
        if (event.getNewLevel() > event.getOldLevel()) {
            effectService.processTrigger("LEVEL_UP", new TriggerContext(
                    event.getPlayer(), event.getPlayer().getLocation(), String.valueOf(event.getNewLevel())
            ));
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onAdvancement(PlayerAdvancementDoneEvent event) {
        if (!event.getAdvancement().getKey().getKey().startsWith("recipes/")) {
            effectService.processTrigger("ADVANCEMENT", new TriggerContext(
                    event.getPlayer(), event.getPlayer().getLocation(), event.getAdvancement().getKey().toString()
            ));
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBedEnter(PlayerBedEnterEvent event) {
        effectService.processTrigger("BED_ENTER", new TriggerContext(
                event.getPlayer(), event.getBed().getLocation(), null
        ));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBedLeave(PlayerBedLeaveEvent event) {
        effectService.processTrigger("BED_LEAVE", new TriggerContext(
                event.getPlayer(), event.getBed().getLocation(), null
        ));
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBucketFill(PlayerBucketFillEvent event) {
        effectService.processTrigger("BUCKET_FILL", new TriggerContext(
                event.getPlayer(), event.getBlock().getLocation(), event.getBucket().name()
        ));
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBucketEmpty(PlayerBucketEmptyEvent event) {
        effectService.processTrigger("BUCKET_EMPTY", new TriggerContext(
                event.getPlayer(), event.getBlock().getLocation(), event.getBucket().name()
        ));
    }
}