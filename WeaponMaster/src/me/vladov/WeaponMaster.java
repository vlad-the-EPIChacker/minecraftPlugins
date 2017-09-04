package me.vladov;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class WeaponMaster extends JavaPlugin {

	public final Logger logger = Logger.getLogger("Minecraft");
	public static WeaponMaster plugin;
	ScoreboardManager manager = Bukkit.getScoreboardManager();
	Scoreboard board1 = manager.getNewScoreboard();
	Scoreboard board2 = manager.getNewScoreboard();
	Objective objective1 = board1.registerNewObjective("showhealth", "health");
	Objective objective2 = board2.registerNewObjective("showkills", "playerKillCount");

	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has Been Disabled");
	}

	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has Been Enabled");
		objective1.setDisplaySlot(DisplaySlot.BELOW_NAME);
		objective2.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective1.setDisplayName("♡");
		for(Player online : Bukkit.getOnlinePlayers()){
			  online.setScoreboard(board1);
			  online.setScoreboard(board2);
			  online.setHealth(online.getHealth());
		}
	}
	
	 @EventHandler
	 public void onKill(PlayerDeathEvent e) {
		 String killed = e.getEntity().getName();
		 String killer = e.getEntity().getKiller().getName();
		 e.setDeathMessage(ChatColor.RED + killed + " has been slain by " + killer);
	 }

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		return false;
	}
}
