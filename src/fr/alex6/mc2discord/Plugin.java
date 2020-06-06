/*
 * This file (Plugin.java), part of Minecraft to Discord Plugin by AL3X_69, is licensed under CC BY-SA 4.0. 
 * To view a copy of this license, visit https://creativecommons.org/licenses/by-sa/4.0
 * You are free to:
 * -> Share — copy and redistribute the material in any medium or format
 * -> Adapt — remix, transform, and build upon the material
 *            for any purpose, even commercially.
 *
 * This license is acceptable for Free Cultural Works.
 * The licensor cannot revoke these freedoms as long as you follow the license terms.
 */
package fr.alex6.mc2discord;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.bukkit.plugin.java.JavaPlugin;

import fr.alex6.mc2discord.discord.WebhookService;
import fr.alex6.mc2discord.listeners.LogsListener;

public class Plugin extends JavaPlugin {

	@Override
	public void onEnable() {
		saveDefaultConfig();
		getLogger().info("Plugin Started !");
		getLogger().info("Trying to reach the Discord Webhook...");
		try {
			int statusCode = WebhookService.getWebhookStatus(getConfig().getString("discord.webhook-url"));
			if ((int)(statusCode / 100) == 2) {
				getLogger().info("Received 2XX response");
			} else if (statusCode == 404 || statusCode == 401) {
				getLogger().severe("Invalid Webhook url, disabling plugin !");
				getServer().getPluginManager().disablePlugin(this);
			} else {
				getLogger().info("Received an unexpected status code ("+statusCode+"), disabling plugin !");
				getServer().getPluginManager().disablePlugin(this);
			}
		} catch (ClientProtocolException e) {
			getLogger().severe("Error encountred while trying to reach the Discord Webhook with message "+e.getMessage());
			e.printStackTrace();
			getServer().getPluginManager().disablePlugin(this);
		} catch (IOException e) {
			getLogger().severe("Error encountred while trying to reach the Discord Webhook with message "+e.getMessage());
			e.printStackTrace();
			getServer().getPluginManager().disablePlugin(this);
		}
		getServer().getPluginManager().registerEvents(new LogsListener(this), this);
	}

	@Override
	public void onDisable() {
		getLogger().info("Plugin Disabled !");
	}
	
}
