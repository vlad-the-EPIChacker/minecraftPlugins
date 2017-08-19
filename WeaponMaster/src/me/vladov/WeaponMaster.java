package me.vladov;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class WeaponMaster extends JavaPlugin {

	public final Logger logger = Logger.getLogger("Minecraft");
	public static WeaponMaster plugin;
	public final MyBlockListener bl = new MyBlockListener(this);

	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has Been Disabled");
	}

	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has Been Enabled");
		Bukkit.getPluginManager().registerEvents(bl, this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ToggleBuildMode")) {
			bl.ToggleBuildMode();
		}

		return false;
	}
}
