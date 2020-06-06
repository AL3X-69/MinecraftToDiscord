/*
 * This file (LogsListener.java), part of Minecraft to Discord Plugin by AL3X_69, is licensed under CC BY-SA 4.0. 
 * To view a copy of this license, visit https://creativecommons.org/licenses/by-sa/4.0
 * You are free to:
 * -> Share — copy and redistribute the material in any medium or format
 * -> Adapt — remix, transform, and build upon the material
 *            for any purpose, even commercially.
 *
 * This license is acceptable for Free Cultural Works.
 * The licensor cannot revoke these freedoms as long as you follow the license terms.
 */
package fr.alex6.mc2discord.listeners;

import java.awt.Color;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;

import fr.alex6.mc2discord.discord.WebhookEmbedBuilder;
import fr.alex6.mc2discord.discord.WebhookService;

public class LogsListener implements Listener {
	private Plugin plugin;
	
	public LogsListener(Plugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (plugin.getConfig().getBoolean("minecraft.events.player.join")) {
			WebhookEmbedBuilder builder = new WebhookEmbedBuilder();
			builder.setAuthor(event.getPlayer().getName());
			builder.setDescription(":arrow_forward: "+event.getPlayer().getName()+" joined the server !");
			builder.setColor(Color.GREEN);
			builder.setTimestamp();
			
			try {
				WebhookService.postWebhook(plugin.getConfig().getString("discord.webhook-url"), builder.build());
			} catch (ClientProtocolException e) {
				plugin.getLogger().severe("Error encountred while trying to reach the Discord Webhook with message "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				plugin.getLogger().severe("Error encountred while trying to reach the Discord Webhook with message "+e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event) {
		if (plugin.getConfig().getBoolean("minecraft.events.player.login")) {
			WebhookEmbedBuilder builder = new WebhookEmbedBuilder();
			builder.setAuthor(event.getPlayer().getName());
			builder.setDescription(":repeat: "+event.getPlayer().getName()+" attempt to login...");
			builder.setColor(Color.BLUE);
			builder.setTimestamp();
						
			try {
				WebhookService.postWebhook(plugin.getConfig().getString("discord.webhook-url"), builder.build());
			} catch (ClientProtocolException e) {
				plugin.getLogger().severe("Error encountred while trying to reach the Discord Webhook with message "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				plugin.getLogger().severe("Error encountred while trying to reach the Discord Webhook with message "+e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		if (plugin.getConfig().getBoolean("minecraft.events.player.leave")) {
			WebhookEmbedBuilder builder = new WebhookEmbedBuilder();
			builder.setAuthor(event.getPlayer().getName());
			builder.setDescription(":arrow_backward: "+event.getPlayer().getName()+" leave the server !");
			builder.setColor(Color.RED);
			builder.setTimestamp();
			
			try {
				WebhookService.postWebhook(plugin.getConfig().getString("discord.webhook-url"), builder.build());
			} catch (ClientProtocolException e) {
				plugin.getLogger().severe("Error encountred while trying to reach the Discord Webhook with message "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				plugin.getLogger().severe("Error encountred while trying to reach the Discord Webhook with message "+e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) {
		if (plugin.getConfig().getBoolean("minecraft.events.player.kick")) {
			WebhookEmbedBuilder builder = new WebhookEmbedBuilder();
			builder.setAuthor(event.getPlayer().getName());
			builder.setDescription(":no_entry: "+event.getPlayer().getName()+" has been kicked !");
			builder.addField("Reason", event.getReason(), false);
			builder.setColor(Color.RED);
			builder.setTimestamp();
			
			try {
				WebhookService.postWebhook(plugin.getConfig().getString("discord.webhook-url"), builder.build());
			} catch (ClientProtocolException e) {
				plugin.getLogger().severe("Error encountred while trying to reach the Discord Webhook with message "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				plugin.getLogger().severe("Error encountred while trying to reach the Discord Webhook with message "+e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		if (plugin.getConfig().getBoolean("minecraft.events.player.death")) {
			WebhookEmbedBuilder builder = new WebhookEmbedBuilder();
			builder.setAuthor(event.getEntity().getName());
			builder.setDescription(":skull: "+event.getEntity().getName()+" died !");
			builder.addField("Reason", event.getDeathMessage(), false);
			builder.setColor(Color.BLACK);
			builder.setTimestamp();
			
			try {
				WebhookService.postWebhook(plugin.getConfig().getString("discord.webhook-url"), builder.build());
			} catch (ClientProtocolException e) {
				plugin.getLogger().severe("Error encountred while trying to reach the Discord Webhook with message "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				plugin.getLogger().severe("Error encountred while trying to reach the Discord Webhook with message "+e.getMessage());
				e.printStackTrace();
			}
		}
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		if (plugin.getConfig().getBoolean("minecraft.events.player.chat")) {
			WebhookEmbedBuilder builder = new WebhookEmbedBuilder();
			builder.setAuthor(event.getPlayer().getName());
			builder.setDescription("**:speech_balloon: ["+event.getPlayer().getName()+"]:** "+event.getMessage());
			builder.setColor(Color.WHITE);
			builder.setTimestamp();
			
			try {
				WebhookService.postWebhook(plugin.getConfig().getString("discord.webhook-url"), builder.build());
			} catch (ClientProtocolException e) {
				plugin.getLogger().severe("Error encountred while trying to reach the Discord Webhook with message "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				plugin.getLogger().severe("Error encountred while trying to reach the Discord Webhook with message "+e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	@EventHandler
	public void onPluginEnable(PluginEnableEvent event) {
		if (plugin.getConfig().getBoolean("minecraft.events.server.plugins.enabled")) {
			WebhookEmbedBuilder builder = new WebhookEmbedBuilder();
			builder.setDescription(":white_check_mark: "+event.getPlugin().getName()+" are now enabled !");
			builder.setColor(Color.GREEN);
			builder.setTimestamp();
			
			try {
				WebhookService.postWebhook(plugin.getConfig().getString("discord.webhook-url"), builder.build());
			} catch (ClientProtocolException e) {
				plugin.getLogger().severe("Error encountred while trying to reach the Discord Webhook with message "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				plugin.getLogger().severe("Error encountred while trying to reach the Discord Webhook with message "+e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public void onPluginDisable(PluginDisableEvent event) {
		if (plugin.getConfig().getBoolean("minecraft.events.server.plugins.disabled")) {
			WebhookEmbedBuilder builder = new WebhookEmbedBuilder();
			builder.setDescription(":x: "+event.getPlugin().getName()+" are now disabled !");
			builder.setColor(Color.RED);
			builder.setTimestamp();
			
			try {
				WebhookService.postWebhook(plugin.getConfig().getString("discord.webhook-url"), builder.build());
			} catch (ClientProtocolException e) {
				plugin.getLogger().severe("Error encountred while trying to reach the Discord Webhook with message "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				plugin.getLogger().severe("Error encountred while trying to reach the Discord Webhook with message "+e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public void onMapInit(MapInitializeEvent event) {
		if (plugin.getConfig().getBoolean("minecraft.events.server.map.initialized")) {
			WebhookEmbedBuilder builder = new WebhookEmbedBuilder();
			builder.setDescription(":map: The map is now Initialized !");
			builder.setColor(Color.BLUE);
			builder.setTimestamp();
			
			try {
				WebhookService.postWebhook(plugin.getConfig().getString("discord.webhook-url"), builder.build());
			} catch (ClientProtocolException e) {
				plugin.getLogger().severe("Error encountred while trying to reach the Discord Webhook with message "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				plugin.getLogger().severe("Error encountred while trying to reach the Discord Webhook with message "+e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
