package me.polaris.superpoke;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperPoke extends JavaPlugin
{
	public static SuperPoke plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	
	@Override
	public void onEnable()
	{
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info("[" + pdfFile.getName() + "] v" + pdfFile.getVersion() + " has been enabled.");
	}
	
	@Override
	public void onDisable()
	{
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info("[" + pdfFile.getName() + "] has been disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args)
	{
		readCommand((Player) sender, CommandLabel, args);
		return false;
	}
	
	public void readCommand(Player sender, String command, String[] args)
	{
		if(command.equalsIgnoreCase("poke"))
		{
			if(sender.isOp())
			{
				if(args.length == 0)
				{
					PluginDescriptionFile pdfFile = this.getDescription();
					sender.sendMessage(ChatColor.GOLD + "SuperPoke v" + pdfFile.getVersion());
					sender.sendMessage(ChatColor.GOLD + "For a commands list type /poke help");
					return;
				}
				else if(args[0].equalsIgnoreCase("poke"))
				{
					if(args.length > 1)
					{
						Player[] players = Bukkit.getOnlinePlayers();
						int i = 0;
						while(i < players.length)
						{

							if(players[i].getName().equalsIgnoreCase(args[1]))
							{
								if(players[i].getName().equalsIgnoreCase(sender.getName()))
								{
									sender.sendMessage(ChatColor.GOLD + "You cannot poke yourself!");
									return;
								}
								else
								{
									players[i].playEffect(players[i].getLocation(), Effect.BOW_FIRE, 1);
									players[i].sendMessage(ChatColor.AQUA + sender.getName() + ChatColor.GOLD + " has poked you!");
									sender.sendMessage(ChatColor.GOLD + "You have poked " + ChatColor.AQUA + players[i].getName());
									Bukkit.broadcastMessage(ChatColor.AQUA + sender.getName() + ChatColor.GOLD + " has poked " + ChatColor.AQUA + players[i].getName());
									return;
								}
							}
							i++;
						}

					}
					else if(args.length == 1)
					{
						sender.sendMessage(ChatColor.RED + "Please include a player name after the poke command");
						return;
					}
			
				}
				else if(args[0].equalsIgnoreCase("throw"))
				{
					if(args.length == 3)
					{
						Player[] players = Bukkit.getOnlinePlayers();
						int i = 0;
						while(i < players.length)
						{

							if(players[i].getName().equalsIgnoreCase(args[1]))
							{
								if(players[i].getName().equalsIgnoreCase(sender.getName()))
								{
									sender.sendMessage(ChatColor.GOLD + "You cannot throw something at yourself!!");
									return;
								}
								else
								{
									players[i].playEffect(players[i].getLocation(), Effect.BOW_FIRE, 1);
									players[i].sendMessage(ChatColor.AQUA + sender.getName() + ChatColor.GOLD + " has thrown a(n) " + args[2] + " at you!");
									sender.sendMessage(ChatColor.GOLD + "You have thrown a(n) " + args[2] + " at " + ChatColor.AQUA + players[i].getName());
									Bukkit.broadcastMessage(ChatColor.AQUA + sender.getName() + ChatColor.GOLD + " has thrown a(n) " + args[2] + " at " + ChatColor.AQUA + players[i].getName());
									return;
								}
							}
							i++;
						}
						sender.sendMessage(ChatColor.RED + "The player you tried to throw something at is offline!");
					}
					else sender.sendMessage(ChatColor.RED + "Insufficient amount of arguments.");
				}
				else if(args[0].equalsIgnoreCase("act"))
				{
					if(args.length == 3)
					{
						Player[] players = Bukkit.getOnlinePlayers();
						int i = 0;
						while(i < players.length)
						{

							if(players[i].getName().equalsIgnoreCase(args[1]))
							{
								if(players[i].getName().equalsIgnoreCase(sender.getName()))
								{
									sender.sendMessage(ChatColor.GOLD + "You have " + args[2] + " yourself!!");
									Bukkit.broadcastMessage(ChatColor.AQUA + sender.getName() + ChatColor.GOLD + " has " + args[2] + " himself/herself!");
									return;
								}
								else
								{
									players[i].playEffect(players[i].getLocation(), Effect.BOW_FIRE, 1);
									players[i].sendMessage(ChatColor.AQUA + sender.getName() + ChatColor.GOLD + " has " + args[2] + " you!");
									sender.sendMessage(ChatColor.GOLD + "You have " + args[2] + " " + ChatColor.AQUA + players[i].getName());
									Bukkit.broadcastMessage(ChatColor.AQUA + sender.getName() + ChatColor.GOLD + " has " + args[2] + " " + ChatColor.AQUA + players[i].getName());
									return;
								}
							}
							i++;
						}
						sender.sendMessage(ChatColor.RED + "The player you are trying to act upon is offline!");
					}
					else sender.sendMessage(ChatColor.RED + "Insufficient amount of arguments.");
				}
				else if(args[0].equalsIgnoreCase("help"))
				{
					sender.sendMessage(ChatColor.GOLD + "/poke : Version information");
					sender.sendMessage(ChatColor.GOLD + "/poke poke (player) : 'Pokes' a player");
					sender.sendMessage(ChatColor.GOLD + "/poke throw (player) (object) : 'Throws' something at a player");
					sender.sendMessage(ChatColor.GOLD + "/poke act (player) (past tense verb) : 'Does' something to a player");
					sender.sendMessage(ChatColor.GOLD + "NOTE: These commands just display messages for fun.");
					return;
				}
				else return;
			}
			else sender.sendMessage(ChatColor.RED + "You must be OP to use this command!!");
		}
	}
}
