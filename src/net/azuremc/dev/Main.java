package net.azuremc.dev;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.azuremc.dev.api.Team;
import net.azuremc.dev.handler.EventHandler;

public class Main extends JavaPlugin
{
	static Main plugin;
	public static List<Team> teams = new ArrayList<Team>();
	
	@SuppressWarnings("unused")
	public void onEnable()
	{
		plugin = this;
		
		File configFile = getConfigFile("config.yml");
		FileConfiguration config = getConfig(configFile);
		
		saveDefaultConfig(configFile);
		
		Team cops = new Team("cops", null);
		List<Location> copSpawns = cops.loadSpawns();
		cops.setSpawns(copSpawns);
		
		Team prisoners = new Team("prisoners", null);
		List<Location> prisonerSpawns = cops.loadSpawns();
		prisoners.setSpawns(prisonerSpawns);
		
		Team lobby = new Team("lobby", null);
		List<Location> lobbySpawns = cops.loadSpawns();
		lobby.setSpawns(lobbySpawns);
		
		teams.add(cops);
		teams.add(prisoners);
		teams.add(lobby);
		
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new EventHandler(), this);
	}
	
	public void onDisable()
	{
		plugin = null;
	}
	
	///////////////
	
	public static File getConfigFile(String name)
	{
		File config = new File(plugin.getDataFolder(), name);
		saveDefaultConfig(config);
		return config;
	}
	
	public static FileConfiguration getConfig(String name)
	{
		saveDefaultConfig(getConfigFile(name));
		FileConfiguration fileConfig;
		fileConfig = YamlConfiguration.loadConfiguration(getConfigFile(name));
		return fileConfig;
	}
	
	public static FileConfiguration getConfig(File config)
	{
		saveDefaultConfig(config);
		FileConfiguration fileConfig;
		fileConfig = YamlConfiguration.loadConfiguration(config);
		return fileConfig;
	}

	public FileConfiguration reloadConfig(File config)
	{
		saveDefaultConfig(config);
		FileConfiguration fileConfig;
		fileConfig = YamlConfiguration.loadConfiguration(config);
		return fileConfig;
	}

	public static void saveDefaultConfig(File config)
	{
		if(!config.exists())
		{
			plugin.saveResource(config.getName(), false);
		}
	}

	///////////////

	public static String colorMsg(String message)
	{
		message = ChatColor.translateAlternateColorCodes('&', message);
		return message;
	}

	public static List<String> formatListMsg(List<String> messages, String replace, String replaceWith)
	{
		List<String> messagesFinal = messages;
		for(int i = 0; i < messages.size(); i++)
		{
			messagesFinal.set(i, ChatColor.translateAlternateColorCodes('&', messages.get(i).replace(replace, replaceWith)));
		}
		return messagesFinal;
	}

	public static void broadcastListMsg(List<String> messages)
	{
		for(int i = 0; i < messages.size(); i++)
		{
			plugin.getServer().broadcastMessage(messages.get(i));
		}
	}

	public static void sendConsoleListMsg(List<String> messages, ConsoleCommandSender console)
	{
		for(int i = 0; i < messages.size(); i++)
		{
			console.sendMessage(messages.get(i));
		}
	}

	public static void sendPlayerListMsg(List<String> messages, Player player)
	{
		for(int i = 0; i < messages.size(); i++)
		{
			player.sendMessage(messages.get(i));
		}
	}

	public static void sendPlayersListMsg(List<String> messages, List<Player> players)
	{
		for(int i = 0; i < players.size(); i++)
		{
			sendPlayerListMsg(messages, players.get(i));
		}
	}
}
