package net.terrocidepvp.betterfix.objects;

import org.bukkit.*;
import net.terrocidepvp.betterfix.*;
import org.bukkit.plugin.*;
import net.terrocidepvp.betterfix.utils.*;
import org.bukkit.configuration.*;
import java.util.logging.*;
import java.util.*;

public class ConfigManager
{
    private List<Material> materials;
    private List<String> blacklistedNames;
    private List<String> blacklistedLores;
    private String noPermission;
    private String invalidArguments;
    private String notHoldingItems;
    private String alreadyAtFullDurability;
    private String enchantedNoPermission;
    private String blacklistedName;
    private String blacklistedLore;
    private String disallowedMaterial;
    private String successfulRepair;
    
    public ConfigManager(final BetterFix plugin) {
        this.materials = new LinkedList<Material>();
        this.blacklistedNames = new LinkedList<String>();
        this.blacklistedLores = new LinkedList<String>();
        final MemoryConfiguration config = (MemoryConfiguration)plugin.getConfig();
        final Logger logger = plugin.getLogger();
        final List<String> materialList = (List<String>)config.getStringList("materials");
        if (materialList.isEmpty()) {
            logger.severe("You have no materials defined! Disabling plugin...");
            plugin.getServer().getPluginManager().disablePlugin((Plugin)plugin);
            return;
        }
        for (String mat : materialList) {
            mat = mat.toUpperCase().replaceAll("[^A-Z0-9_]", "_");
            try {
                this.materials.add(Material.valueOf(mat));
            }
            catch (IllegalArgumentException ignore) {
                logger.severe("Unknown material in 'materials': " + mat);
            }
        }
        this.blacklistedNames = (List<String>)config.getStringList("blacklist.names");
        this.blacklistedLores = (List<String>)config.getStringList("blacklist.lores");
        this.noPermission = ColorCodeUtils.translate(config.getString("plugin-messages.no-permission"));
        this.invalidArguments = ColorCodeUtils.translate(config.getString("plugin-messages.invalid-arguments"));
        this.notHoldingItems = ColorCodeUtils.translate(config.getString("plugin-messages.not-holding-items"));
        this.alreadyAtFullDurability = ColorCodeUtils.translate(config.getString("plugin-messages.already-at-full-durability"));
        this.enchantedNoPermission = ColorCodeUtils.translate(config.getString("plugin-messages.enchanted-no-permission"));
        this.blacklistedName = ColorCodeUtils.translate(config.getString("plugin-messages.blacklisted-name"));
        this.blacklistedLore = ColorCodeUtils.translate(config.getString("plugin-messages.blacklisted-lore"));
        this.disallowedMaterial = ColorCodeUtils.translate(config.getString("plugin-messages.disallowed-material"));
        this.successfulRepair = ColorCodeUtils.translate(config.getString("plugin-messages.successful-repair"));
    }
    
    public List<Material> getMaterials() {
        return this.materials;
    }
    
    public List<String> getBlacklistedNames() {
        return this.blacklistedNames;
    }
    
    public List<String> getBlacklistedLores() {
        return this.blacklistedLores;
    }
    
    public String getNoPermission() {
        return this.noPermission;
    }
    
    public String getInvalidArguments() {
        return this.invalidArguments;
    }
    
    public String getNotHoldingItems() {
        return this.notHoldingItems;
    }
    
    public String getAlreadyAtFullDurability() {
        return this.alreadyAtFullDurability;
    }
    
    public String getEnchantedNoPermission() {
        return this.enchantedNoPermission;
    }
    
    public String getBlacklistedName() {
        return this.blacklistedName;
    }
    
    public String getBlacklistedLore() {
        return this.blacklistedLore;
    }
    
    public String getDisallowedMaterial() {
        return this.disallowedMaterial;
    }
    
    public String getSuccessfulRepair() {
        return this.successfulRepair;
    }
}
