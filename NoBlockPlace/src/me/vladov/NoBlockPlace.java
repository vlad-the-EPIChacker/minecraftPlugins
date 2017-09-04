package me.vladov;


import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NoBlockPlace extends JavaPlugin implements Listener{

	public final Logger logger = Logger.getLogger("Minecraft");
	public static NoBlockPlace plugin;
	public static Material[] whitelistPLACE = {Material.AIR};
	public static Material[] whitelistBREAK = {Material.AIR};
	public static boolean buildMode = false;

	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has Been Disabled");
	}

	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has Been Enabled");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ToggleBuildMode")) {
			if(sender.isOp()) {
				ToggleBuildMode();
			} else {
				sender.sendMessage(ChatColor.RED + "You don't have access to this command!");
			}
		}

		return false;
	}
	
	@EventHandler
	public void OnBlockPlace(BlockPlaceEvent event) {
		Material block = event.getBlock().getType();
		Player player = event.getPlayer();

		if (!buildMode) {
			for (Material allowed : whitelistPLACE) {
				if (allowed == block) {}else {
					if (player.isOp()) {} else {
						event.setCancelled(true);
						player.sendMessage(ChatColor.RED + "You can't place " +block.name().toLowerCase()+ " here!");
					}
				}
			}
		}
	}
	
	@EventHandler
	public void OnBlockBreak(BlockBreakEvent event) {
		Material block = event.getBlock().getType();
		Player player = event.getPlayer();

		if (!buildMode) {
			for (Material allowed : whitelistBREAK) {
				if (allowed == block) {}else {
					if (player.isOp()) {} else {
						event.setCancelled(true);
						player.sendMessage(ChatColor.RED + "You can't destroy this "+block.name().toLowerCase()+"!");
					}

				}
			}
		}
	}

	public void ToggleBuildMode() {
		buildMode = !buildMode;
	}
}