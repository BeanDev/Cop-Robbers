package net.azuremc.dev.handler;

import java.util.List;
import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import net.azuremc.dev.Main;
import net.azuremc.dev.api.Team;

public class EventHandler implements Listener
{
	List<Team> teams = Main.teams;
	Team cops = teams.get(1);
	Team prisoners = teams.get(2);
	Team lobby = teams.get(3);
	
	@org.bukkit.event.EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		lobby.addPlayer(player);
		player.teleport(lobby.getSpawn().get(new Random().nextInt()));
	}
	
	@org.bukkit.event.EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		if(lobby.getPlayers().contains(player)) event.setCancelled(true);
	}
}
