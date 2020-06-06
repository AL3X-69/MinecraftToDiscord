/*
 * This file (MessageEmbed.java), part of Minecraft to Discord Plugin by AL3X_69, is licensed under CC BY-SA 4.0. 
 * To view a copy of this license, visit https://creativecommons.org/licenses/by-sa/4.0
 * You are free to:
 * -> Share — copy and redistribute the material in any medium or format
 * -> Adapt — remix, transform, and build upon the material
 *            for any purpose, even commercially.
 *
 * This license is acceptable for Free Cultural Works.
 * The licensor cannot revoke these freedoms as long as you follow the license terms.
 */
package fr.alex6.mc2discord.discord.entities;

public class MessageEmbed {
	public static class Field {
		private String name;
		private String value;
		private boolean inline;
		
		public Field(String name, String value, boolean inline) {
			this.name = name;
			this.value = value;
			this.inline = inline;
		}

		public String getName() {
			return name;
		}

		public String getValue() {
			return value;
		}

		public boolean isInline() {
			return inline;
		}
	}
}
