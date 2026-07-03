package com.villoni.devl.command;

import com.villoni.devl.config.ConfigManager;
import com.villoni.devl.config.LangManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public final class EffectsCommand implements CommandExecutor, TabCompleter {

    private final ConfigManager configManager;
    private final LangManager langManager;

    public EffectsCommand(ConfigManager configManager, LangManager langManager) {
        this.configManager = configManager;
        this.langManager = langManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("visualeffects.admin")) {
            sender.sendMessage(langManager.getMessage("no-permission"));
            return true;
        }

        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            configManager.reload();
            langManager.reload();
            sender.sendMessage(langManager.getMessage("reload-success"));
            return true;
        }

        sender.sendMessage(langManager.getMessage("unknown-command"));
        return true;
    }

    @Override
    public @NotNull List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1 && sender.hasPermission("visualeffects.admin")) {
            return List.of("reload");
        }
        return Collections.emptyList();
    }
}