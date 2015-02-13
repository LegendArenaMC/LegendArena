package net.thenamedev.legendarena.history;

//Code stolen from here: https://github.com/ParkerKemp/UsernameHistory
//
//Not sure if credit is required, but I'd feel bad if I didn't say at the very least this much.

import com.google.gson.*;
import net.thenamedev.legendarena.core.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

class OldUsername implements Comparable<OldUsername> {
	String name;
	long changedToAt;

	public int compareTo(OldUsername other) {
		return Long.compare(this.changedToAt, other.changedToAt);
	}
}

public class UsernameHistory implements CommandExecutor {
	ConsoleCommandSender console = Bukkit.getConsoleSender();
	private HashMap<String, OldUsername[]> cache = new HashMap<String, OldUsername[]>();

	public boolean onCommand(CommandSender sender, Command cmd, String label,
		String[] args) {
        if(!Rank.getRank(sender, Rank.Mod)) {
            Rank.noPermissions(sender, Rank.Mod);
            return true;
        }
        if(args.length == 0) {
            sender.sendMessage(ChatColor.BLUE + "Usage: /namehistory <player>");
            return true;
        }
		getHistory(sender, args[0]);
		return true;
	}

	private void getHistory(CommandSender sender, String username) {
		OldUsername[] oldNames = cache.get(username.toLowerCase());
		if(oldNames != null) {
			reportHistory(sender, oldNames);
		} else {
			getHistoryFromWeb(sender, username);
		}
	}

	private void getHistoryFromWeb(final CommandSender sender,
			final String username) {

		new Thread() {
			public void run() {
				UUID uuid;
				String uuidString;
				Player player = Bukkit.getPlayer(username);
				if(player != null)
					uuidString = player.getUniqueId().toString();
				else {
					try {
						uuid = UUIDFetcher.getUUIDOf(username);
					} catch(IOException e) {
						e.printStackTrace();
						return;
					}
					if(uuid != null)
						uuidString = uuid.toString();
					else {
						sender.sendMessage("");
						sender.sendMessage(ChatColor.RED
								+ "Player could not be found!");
						return;
					}
				}
				OldUsername[] names = usernames(uuidString, username);
				cache.put(username.toLowerCase(), names);
				reportHistory(sender, names);
			}
		}.start();
	}

	private void reportHistory(CommandSender sender, OldUsername[] names) {
		sender.sendMessage("");
		if(names.length == 1)
			sender.sendMessage(ChatColor.GREEN + names[0].name + ChatColor.GOLD
					+ " has never changed their name.");
		else {
			sender.sendMessage(ChatColor.GOLD + "Originally " + ChatColor.GREEN
					+ names[0].name);
			sender.sendMessage("");

			for(int i = 1; i < names.length; i++) {
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date date = new Date(names[i].changedToAt);
				String formattedDate = df.format(date);
				sender.sendMessage(ChatColor.BLUE + formattedDate
						+ ChatColor.GOLD + " - changed to " + ChatColor.GREEN
						+ names[i].name);
			}
		}
	}

	private OldUsername[] usernames(String uuid, String current) {
		Gson gson = new GsonBuilder().create();
		String compactUuid = uuid.replace("-", "");
		try {
			URL url = new URL("https://api.mojang.com/user/profiles/"
					+ compactUuid + "/names");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			OldUsername[] oldNames = gson.fromJson(reader, OldUsername[].class);
			Arrays.sort(oldNames);
			reader.close();
			conn.disconnect();
			return oldNames;
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
