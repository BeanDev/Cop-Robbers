package net.azuremc.dev.api;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Team extends Object
{
	
	private List<Location> spawn = new ArrayList<Location>();
	private String name;
	private List<Player> players = new ArrayList<Player>();
	
	public Team(String name, List<Location> spawn)
	{
		this.spawn = spawn;
		this.name = name;
	}
	
	public void addPlayer(Player player)
	{
		this.players.add(player);
	}
	
	public List<Player> getPlayers()
	{
		return this.players;
	}
	
	public List<Location> getSpawn()
	{
		return this.spawn;
	}
	
	public void removePlayer(Player player) throws NullPointerException
	{
		if(!this.players.contains(player)) throw new NullPointerException();
		this.players.remove(player);
	}
}
