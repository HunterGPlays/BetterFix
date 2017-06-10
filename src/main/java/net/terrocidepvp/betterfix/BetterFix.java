package net.terrocidepvp.betterfix;

import org.bukkit.plugin.java.*;
import net.terrocidepvp.betterfix.objects.*;
import org.bukkit.plugin.*;
import net.terrocidepvp.betterfix.utils.*;
import net.terrocidepvp.betterfix.commands.*;
import org.bukkit.command.*;

public class BetterFix extends JavaPlugin
{
    private int[] serverVersion;
    private ConfigManager configManager;
    private static BetterFix instance;
    
    public static BetterFix getInstance() {
        return BetterFix.instance;
    }
    
    public void onEnable() {
        (BetterFix.instance = this).saveDefaultConfig();
        this.reloadConfig();
        if (!this.getConfig().isSet("config-version")) {
            this.getLogger().severe("The config.yml file is broken!");
            this.getLogger().severe("The plugin failed to detect a 'config-version'.");
            this.getLogger().severe("The plugin will not load until you generate a new, working config OR if you fix the config.");
            this.getServer().getPluginManager().disablePlugin((Plugin)this);
            return;
        }
        final int configVersion = 1;
        if (this.getConfig().getInt("config-version") != configVersion) {
            this.getLogger().severe("Your config is outdated!");
            this.getLogger().severe("The plugin will not load unless you change the config version to " + configVersion + ".");
            this.getLogger().severe("This means that you will need to reset your config, as there may have been major changes to the plugin.");
            this.getServer().getPluginManager().disablePlugin((Plugin)this);
            return;
        }
        this.configManager = new ConfigManager(this);
        this.serverVersion = VersionUtils.getMCVersion(this.getServer().getVersion());
        this.getLogger().info("Running server version " + Integer.toString(this.serverVersion[0]) + "." + Integer.toString(this.serverVersion[1]));
        this.getLogger().info("Registering listeners...");
        final CommandHandler commandHandler = new CommandHandler(this);
        this.getCommand("fix").setExecutor((CommandExecutor)commandHandler);
        this.getCommand("fixall").setExecutor((CommandExecutor)commandHandler);
    }
    
    public int[] getServerVersion() {
        return this.serverVersion;
    }
    
    public ConfigManager getConfigManager() {
        return this.configManager;
    }
}
