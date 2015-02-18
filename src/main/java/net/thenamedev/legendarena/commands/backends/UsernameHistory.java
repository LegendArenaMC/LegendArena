package net.thenamedev.legendarena.commands.backends;

//Code stolen from here: https://github.com/ParkerKemp/UsernameHistory
//
//Not sure if credit is required, but I'd feel bad if I didn't say at the very least this much.

import com.google.gson.*;
import net.thenamedev.legendarena.core.*;
import net.thenamedev.legendarena.utils.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.jetbrains.annotations.*;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

@SuppressWarnings("NullableProblems")
class OldUsername implements Comparable<OldUsername> {
	String name;
	long changedToAt;

	public int compareTo(@NotNull OldUsername other) {
		return Long.compare(this.changedToAt, other.changedToAt);
	}
}

public class UsernameHistory {
	ConsoleCommandSender console = Bukkit.getConsoleSender();
    @NotNull private static HashMap<String, OldUsername[]> cache = new HashMap<>();

    @NotNull
    private static HashMap<UUID, Cooldown> cooldown = new HashMap<>();

	public static void run(@NotNull CommandSender sender, @NotNull String[] args) {
        if(!Rank.getRank(sender, Rank.Mod)) {
            Rank.noPermissions(sender, Rank.Mod);
            return;
        }
        if(cooldown.containsKey(((Player) sender).getUniqueId()) && !cooldown.get(((Player) sender).getUniqueId()).done()) {
            sender.sendMessage(cooldown.get(((Player) sender).getUniqueId()).getTimeRemaining());
            return;
        }
        if(args.length == 0 || args.length == 1) {
            sender.sendMessage(ChatColor.BLUE + "Usage: /lalookup history <player> [--nocache]");
            return;
        }
		if(args.length == 2) {
            getHistory(sender, args[1], false);
        } else {
            getHistory(sender, args[1], args[2].equalsIgnoreCase("--nocache"));
        }
        //5 second cooldown
        cooldown.put(((Player) sender).getUniqueId(), new Cooldown(5));
	}

	private static void getHistory(@NotNull CommandSender sender, @NotNull String username, boolean ovCa) {
        if(ovCa) { //they want to override cache, so let them (if they're an admin)
            if(Rank.getRank(sender, Rank.Admin)) {
                sender.sendMessage("");
                sender.sendMessage(ChatColor.BLUE + "----- .[ " + ChatColor.GOLD + "Name History for " + ChatColor.GREEN + username + ChatColor.BLUE + " ]. -----");
                sender.sendMessage(PluginUtils.msgNormal + ChatColor.GOLD + "Overriding cache; checking via Mojang API...");
                getHistoryFromWeb(sender, username);
                return;
            } else {
                //they are not an admin, don't let them override cache
                sender.sendMessage(ChatColor.RED + "You may NOT override cache! (You require the ADMIN rank to override cache)");
            }
        }
		OldUsername[] oldNames = cache.get(username.toLowerCase());
		if(oldNames != null) {
            sender.sendMessage("");
            sender.sendMessage(ChatColor.BLUE + "----- .[ " + ChatColor.GOLD + "Name History for " + ChatColor.BLUE + username + ChatColor.BLUE + " ]. -----");
            sender.sendMessage(PluginUtils.msgNormal + "Found player's name history in cache.");
			reportHistory(sender, oldNames);
        } else {
            sender.sendMessage("");
            sender.sendMessage(ChatColor.BLUE + "----- .[ " + ChatColor.GOLD + "Name History for " + ChatColor.BLUE + username + ChatColor.BLUE + " ]. -----");
            sender.sendMessage(PluginUtils.msgNormal + "Name history not in cache; checking via Mojang API...");
			getHistoryFromWeb(sender, username);
        }
	}

	private static void getHistoryFromWeb(@NotNull final CommandSender sender, @NotNull final String username) {

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
				@Nullable OldUsername[] names = usernames(uuidString, username);
				cache.put(username.toLowerCase(), names);
				reportHistory(sender, names);
			}
		}.start();
	}

	private static void reportHistory(@NotNull CommandSender sender, @NotNull OldUsername[] names) {
		sender.sendMessage("");
		if(names.length == 1)
			sender.sendMessage(ChatColor.BLUE + names[0].name + ChatColor.GREEN
					+ " has never changed their name.");
		else {
			sender.sendMessage(ChatColor.GREEN + "Originally " + ChatColor.BLUE
					+ names[0].name);
			sender.sendMessage("");

			for(int i = 1; i < names.length; i++) {
				@NotNull DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				@NotNull Date date = new Date(names[i].changedToAt);
				@NotNull String formattedDate = df.format(date);
				sender.sendMessage(ChatColor.YELLOW + formattedDate
						+ ChatColor.GREEN + " - changed to " + ChatColor.BLUE
						+ names[i].name);
			}
            sender.sendMessage("");
            sender.sendMessage(PluginUtils.msgNormal + "Done.");
        }
	}

	private static OldUsername[] usernames(@NotNull String uuid, String current) {
		@NotNull Gson gson = new GsonBuilder().create();
		String compactUuid = uuid.replace("-", "");
		try {
			@NotNull URL url = new URL("https://api.mojang.com/user/profiles/"
					+ compactUuid + "/names");
			@NotNull HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			@NotNull BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			OldUsername[] oldNames = gson.fromJson(reader, OldUsername[].class);
			Arrays.sort(oldNames);
			reader.close();
			conn.disconnect();
			return oldNames;
		} catch(IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
