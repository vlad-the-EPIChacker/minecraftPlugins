package me.vladov;

import java.util.Dictionary;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin implements Listener{
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public static MyPlugin plugin;
	public Dictionary<Player,Integer> playerStat;
	public Object[] onlinePlayers = Bukkit.getOnlinePlayers().toArray();
	
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has Been Disabled");
	}

	
	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has Been Enabled");
	}
	
	public void onCommand(Command sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("ResetGame")) {
			this.logger.info(ChatColor.GOLD + "Game Rested");
			for(int i=0; i<onlinePlayers.length; i++) {
				Player onlinePlayer = (Player)onlinePlayers[i];
				playerStat.put(onlinePlayer,1000);
			}
			
		}
		
	}
	
	private void openGUI(Player player) {
		Inventory shop = Bukkit.createInventory(null, 18, "Shop");
		ItemStack stick = new ItemStack(Material.STICK);
		ItemMeta stickMeta = stick.getItemMeta();
		
		stickMeta.setDisplayName(ChatColor.DARK_RED + "Stick");
		stick.setItemMeta(stickMeta);
		
		shop.setItem(0, stick);
		
		player.openInventory(shop);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Shop")) {
			return;
		}
		
		Player player = (Player)event.getWhoClicked();
		event.setCancelled(true);
		
		if(event.getCurrentItem()==null || event.getCurrentItem().getType()==Material.AIR ||!event.getCurrentItem().hasItemMeta()) {
			player.closeInventory();
			return;
		}
		
		switch(event.getCurrentItem().getType()) {
			case STICK:
				player.getInventory().addItem(new ItemStack(Material.STICK));
				player.sendMessage(String.format("%sBought %sStick",ChatColor.GOLD,ChatColor.DARK_RED));
				break;
		
			default:
				player.closeInventory();
				break;
		}
	}
	
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.getPlayer().getInventory().addItem(new ItemStack(Material.COMPASS));
	}
	
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Action a =event.getAction();
		ItemStack is = event.getItem();
		
		if(a==Action.PHYSICAL || is == null || is.getType == Material.AIR) {
			return;
		}
		
		if (is.getType() == Material.COMPASS) {
			openGUI(event.getPlayer());
		}
		
	}
	
	
	
}
