package me.vladov;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class MyBlockListener implements Listener {
	public static WeaponMaster plugin;
	public static Material[] whitelist = {};
	public static boolean buildMode = false;

	public MyBlockListener(WeaponMaster instance) {
		plugin = instance;
	}

	public void OnBlockPlace(BlockPlaceEvent event) {
		Material block = event.getBlock().getType();
		Player player = event.getPlayer();

		if (!buildMode) {
			for (Material allowed : whitelist) {
				if (allowed == block) {
					if (player.isOp()) {
					} else {
						event.getBlock().setType(Material.AIR);
						player.chat(ChatColor.RED + "You can't place that here!");
					}

				}
			}
		}
	}

	public void ToggleBuildMode() {
		buildMode = !buildMode;
	}

}
