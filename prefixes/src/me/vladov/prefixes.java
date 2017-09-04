package me.vladov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class prefixes extends JavaPlugin implements Listener{

	public final Logger logger = Logger.getLogger("Minecraft");
	public static prefixes plugin;
	private static String OwnerFormat = ChatColor.GREEN + "[OWNER]";
	private static String AdminFormat = ChatColor.RED + "[ADMIN]";
	private static String VIPFormat = ChatColor.GOLD + "[VIP]"+ "%1$s: %2$s";
	public static List<String> VIP = new ArrayList<>(Arrays.asList(VIPFormat));;
	public static List<String> Admin = new ArrayList<>(Arrays.asList(AdminFormat));;
	public static List<String> Owner = new ArrayList<>(Arrays.asList(OwnerFormat));;
	public static Map<String,List<String>> ranks = new HashMap<String,List<String>> ();
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has Been Disabled");
	}

	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has Been Enabled");
		if (ranks.isEmpty()) {
			ranks.put("VIP", VIP);
			ranks.get("VIP").add(VIPFormat);
			
			ranks.put("Admin", Admin);
			ranks.get("Admin").add(AdminFormat);
			
			ranks.put("Owner", Owner);
			ranks.get("Owner").add(OwnerFormat);
		}
		if (!ranks.get("Owner").contains("chimp2000")) {
			ranks.get("Owner").add("chimp2000");
		}
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
	}
	

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
    		
    		for (String rank : ranks.keySet()) {
	        if(ranks.get(rank).contains(e.getPlayer().getName())) {
	        		e.getPlayer().setDisplayName((ranks.get(rank).get(0) + e.getPlayer().getName()));
	        }
    		}
    		e.getPlayer().setDisplayName("aaaaaaaaaaa");
    		this.logger.info("aaaaaa");
    }
        
    
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName() == "rankset") {
			if (args.length == 2) {
					
			} else {
				sender.sendMessage(ChatColor.RED+"/rankset <player> <rank>");
			}
		}
		
		
		return false;
	}
}
