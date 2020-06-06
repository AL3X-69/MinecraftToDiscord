/*
 * This file (WebhookEmbedBuilder.java), part of Minecraft to Discord Plugin by AL3X_69, is licensed under CC BY-SA 4.0. 
 * To view a copy of this license, visit https://creativecommons.org/licenses/by-sa/4.0
 * You are free to:
 * -> Share — copy and redistribute the material in any medium or format
 * -> Adapt — remix, transform, and build upon the material
 *            for any purpose, even commercially.
 *
 * This license is acceptable for Free Cultural Works.
 * The licensor cannot revoke these freedoms as long as you follow the license terms.
 */
package fr.alex6.mc2discord.discord;

import java.awt.Color;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.alex6.mc2discord.discord.entities.MessageEmbed.Field;

public class WebhookEmbedBuilder {
	private String title = "";
	private String description = "";
	private String url = "";
	private Color color = Color.DARK_GRAY;
	private Instant timestamp = null;
	private String footer_icon_url = "";
	private String footer_text = "";
	private String thumbnail_url = ""; 
	private String author_name = "";
	private String author_url = ""; 
	private String author_icon_url = "";
	private List<Field> fields = new LinkedList<Field>();
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setTitle(String title, String url) {
		this.title = title;
		this.url = url;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public void setTimestamp() {
		this.timestamp = Instant.now();
	}
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
	
	public void setFooter(String iconUrl, String iconText) {
		this.footer_icon_url = iconUrl;
		this.footer_text = iconText;
	}
	
	public void setThumbnail(String url) {
		this.thumbnail_url = url;
	}
	
	public void setAuthor(String name) {
		this.author_name = name;
	}
	
	public void setAuthor(String name, String iconUrl) {
		this.author_name = name;
		this.author_icon_url = iconUrl;
	}
	
	public void setAuthor(String name, String url, String iconUrl) {
		this.author_name = name;
		this.author_url = url;
		this.author_icon_url = iconUrl;
	}
	
	public void addField(String name, String value, boolean inline) {
		fields.add(new Field(name, value, inline));
	}
	
	public JSONObject build() {
		JSONObject finalObject = new JSONObject();
		JSONArray embeds = new JSONArray();
		JSONObject embed = new JSONObject();
		
		embed.put("title", title);
		embed.put("description", description);
		embed.put("url", url);
		embed.put("color", Integer.parseInt(String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()), 16));
		if (timestamp != null) embed.put("timestamp", timestamp.toString());
		
		JSONObject footer = new JSONObject();
		footer.put("icon_url", footer_icon_url);
		footer.put("text", footer_text);
		embed.put("footer", footer);
		
		JSONObject thumbnail = new JSONObject();
		thumbnail.put("url", thumbnail_url);
		embed.put("thumbnail", thumbnail);
		
		JSONObject author = new JSONObject();
		author.put("name", author_name);
		author.put("url", author_url);
		author.put("icon_url", author_icon_url);
		embed.put("author", author);
		
		JSONArray fieldsArray = new JSONArray();
		fields.forEach(f -> {
			JSONObject field = new JSONObject();
			field.put("name", f.getName());
			field.put("value", f.getValue());
			field.put("inline", f.isInline());
			fieldsArray.put(field);
		});
		embed.put("fields", fieldsArray);
		
		embeds.put(embed);
		finalObject.put("embeds", embeds);
				
		return finalObject;
	}
}
