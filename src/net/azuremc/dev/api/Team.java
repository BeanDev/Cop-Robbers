package net.azuremc.dev.api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.azuremc.dev.Main;

public class Team extends Object
{
	private List<Location> spawns = new ArrayList<Location>();
	private String name;
	private List<Player> players = new ArrayList<Player>();
	
	public Team(String name, List<Location> spawn)
	{
		this.spawns = spawn;
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
	
	public List<Location> getSpawns()
	{
		return this.spawns;
	}
	
	public List<Location> loadSpawns()
	{
		File configFile = Main.getConfigFile("config.yml");
		FileConfiguration config = Main.getConfig(configFile);
		
		List<Location> spawns = new ArrayList<Location>();
		for(int i = 0; i < config.getStringList("teams.cops.spawns").size(); i++)
		{
			String world = config.getStringList("teams.cops.spawns").get(i).toString();
			Double x = config.getDouble("teams." + this.name + ".spawns." + world + ".x");
			Double y = config.getDouble("teams." + this.name + ".spawns." + world + ".y");
			Double z = config.getDouble("teams." + this.name + ".spawns." + world + ".z");
			Float yaw = Float.parseFloat(config.getString("teams." + this.name + ".spawns." + world + ".yaw"));
			Float pitch = Float.parseFloat(config.getString("teams." + this.name + ".spawns." + world + ".pitch"));
			spawns.add(new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch));
		}
		return spawns;
	}
	
	public void setSpawns(List<Location> spawns)
	{
		this.spawns = spawns;
	}
	
	public void randomSpawn(Player player)
	{
		Random random = new Random();
		if(spawns.size() != 0)
		{
			int i = random.nextInt(spawns.size());
			player.teleport(this.getSpawns().get(i));
		}
	}
	
	public void removePlayer(Player player) throws NullPointerException
	{
		if(!this.players.contains(player)) throw new NullPointerException();
		this.players.remove(player);
	}
}
