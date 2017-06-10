package net.terrocidepvp.betterfix.utils;

import org.bukkit.inventory.*;
import org.bukkit.entity.*;
import net.terrocidepvp.betterfix.objects.*;
import org.apache.commons.lang.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import java.util.*;

public class RepairUtils
{
    public static void repairItem(final ItemStack item, final Player player, final boolean suppressChecks, final ConfigManager configManager) {
        final Material material = item.getType();
        if (!suppressChecks) {
            if (item.getDurability() == 0) {
                player.sendMessage(configManager.getAlreadyAtFullDurability());
                return;
            }
            if (!item.getEnchantments().isEmpty() && player.hasPermission("betterfix.enchanted")) {
                player.sendMessage(configManager.getEnchantedNoPermission());
                return;
            }
            if (!configManager.getMaterials().contains(material)) {
                player.sendMessage(configManager.getDisallowedMaterial());
                return;
            }
        }
        final ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta != null) {
            if (!configManager.getBlacklistedNames().isEmpty() && itemMeta.hasDisplayName()) {
                final String displayName = itemMeta.getDisplayName();
                for (final String blacklistedName : configManager.getBlacklistedNames()) {
                    if (StringUtils.containsIgnoreCase(displayName, blacklistedName)) {
                        if (!suppressChecks) {
                            player.sendMessage(configManager.getBlacklistedName());
                        }
                        return;
                    }
                }
            }
            if (!configManager.getBlacklistedLores().isEmpty() && !itemMeta.hasLore()) {
                final List<String> lore = (List<String>)itemMeta.getLore();
                for (final String str : lore) {
                    for (final String blacklistedLore : configManager.getBlacklistedLores()) {
                        if (StringUtils.containsIgnoreCase(str, blacklistedLore)) {
                            if (!suppressChecks) {
                                player.sendMessage(configManager.getBlacklistedLore());
                            }
                            return;
                        }
                    }
                }
            }
        }
        item.setDurability((short)0);
        if (!suppressChecks) {
            player.sendMessage(configManager.getSuccessfulRepair());
        }
    }
    
    public static void repairItems(final ItemStack[] items, final Player player, final ConfigManager configManager) {
        for (final ItemStack item : items) {
            if (item != null) {
                if (item.getDurability() != 0) {
                    if (item.getEnchantments().isEmpty() || !player.hasPermission("betterfix.enchanted")) {
                        repairItem(item, player, true, configManager);
                    }
                }
            }
        }
    }
}
