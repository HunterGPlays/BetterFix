package net.terrocidepvp.betterfix.commands;

import net.terrocidepvp.betterfix.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import net.terrocidepvp.betterfix.utils.*;
import org.bukkit.inventory.*;

public class CommandHandler implements CommandExecutor
{
    private BetterFix plugin;
    
    public CommandHandler(final BetterFix plugin) {
        this.plugin = plugin;
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player)sender;
            if (command.getName().equalsIgnoreCase("fix")) {
                if (!sender.hasPermission("betterfix.fix")) {
                    sender.sendMessage(this.plugin.getConfigManager().getNoPermission());
                    return true;
                }
                final int[] serverVersion = this.plugin.getServerVersion();
                ItemStack item;
                if (serverVersion[0] >= 1 && serverVersion[1] >= 9) {
                    item = player.getInventory().getItemInMainHand();
                }
                else {
                    item = player.getInventory().getItemInHand();
                }
                if (item == null) {
                    sender.sendMessage(this.plugin.getConfigManager().getNotHoldingItems());
                    return true;
                }
                RepairUtils.repairItem(item, player, false, this.plugin.getConfigManager());
            }
            else if (command.getName().equalsIgnoreCase("fixall")) {
                if (!sender.hasPermission("betterfix.fixall")) {
                    player.sendMessage(this.plugin.getConfigManager().getNoPermission());
                    return true;
                }
                RepairUtils.repairItems(player.getInventory().getContents(), player, this.plugin.getConfigManager());
            }
            return false;
        }
        sender.sendMessage("This command can only be executed by a player!");
        return true;
    }
}
