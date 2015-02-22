package net.thenamedev.legendarena.commands.backends;import net.thenamedev.legendapi.utils.*;import net.thenamedev.legendarena.extras.staffchat.*;import net.thenamedev.legendarena.extras.warp.*;import org.bukkit.*;import org.bukkit.command.*;import org.bukkit.entity.*;import org.jetbrains.annotations.*;import java.util.*;/** * Command method is deprecated in favor of LookupUser. Use that instead. [This is still here for legacy reasons] * @author TheNameMan */public class Info {    @NotNull    private static HashMap<UUID, Cooldown> cooldown = new HashMap<>();    public static void run(@NotNull CommandSender sender, @NotNull String[] args) {        if(cooldown.containsKey(((Player) sender).getUniqueId()) && !cooldown.get(((Player) sender).getUniqueId()).done()) {            sender.sendMessage(cooldown.get(((Player) sender).getUniqueId()).getTimeRemaining());            return;        }        if(args.length == 0 || args.length == 1) {            sender.sendMessage(ChatColor.BLUE + "Usage: /lalookup info <user>");            return;        } else if(args.length >= 3) {            sender.sendMessage(ChatColor.BLUE + "Usage: /lalookup info <user>");            return;        }        if(Bukkit.getPlayer(args[1]) != null) {            Player p = Bukkit.getPlayer(args[1]);            sender.sendMessage(ChatUtils.formattedCmd("Info: " + p.getName(), false));            sender.sendMessage(ChatColor.YELLOW + "User rank " + PluginUtils.chars[1] + ChatColor.GREEN + " " + Rank.getRank(p));            sender.sendMessage(ChatColor.YELLOW + "Staff chat channel " + PluginUtils.chars[1] + ChatColor.GREEN + " " + SCType.getType(p.getUniqueId()));            sender.sendMessage(ChatColor.YELLOW + "Can see sc " + PluginUtils.chars[1] + ChatColor.GREEN + " " + SCType.canSeeStaffchat(p.getUniqueId()));            //sender.sendMessage(ChatColor.YELLOW + "Connecting IP (may be CF) " + PluginUtils.chars[1] + ChatColor.GREEN + " " + p.getAddress().getAddress() + ":" + p.getAddress().getPort());            sender.sendMessage(ChatColor.YELLOW + "Food level " + PluginUtils.chars[1] + ChatColor.GREEN + " " + p.getFoodLevel());            sender.sendMessage(ChatColor.YELLOW + "Saturation level " + PluginUtils.chars[1] + ChatColor.GREEN + " " + p.getSaturation());            sender.sendMessage(ChatColor.YELLOW + "Display name " + PluginUtils.chars[1] + ChatColor.GREEN + " " + p.getDisplayName());            sender.sendMessage(ChatColor.YELLOW + "Gamemode " + PluginUtils.chars[1] + ChatColor.GREEN + " " + p.getGameMode());            sender.sendMessage(ChatColor.YELLOW + "UUID " + PluginUtils.chars[1] + ChatColor.GREEN + " " + p.getUniqueId());            sender.sendMessage(ChatColor.YELLOW + "Level " + PluginUtils.chars[1] + ChatColor.GREEN + " " + p.getLevel());            sender.sendMessage(ChatColor.YELLOW + "Speed; WALK " + PluginUtils.chars[1] + ChatColor.GREEN + " " + p.getWalkSpeed());            sender.sendMessage(ChatColor.YELLOW + "Speed; FLY " + PluginUtils.chars[1] + ChatColor.GREEN + " " + p.getFlySpeed());            sender.sendMessage(ChatColor.YELLOW + "World (backend name) " + PluginUtils.chars[1] + ChatColor.GREEN + " " + WarpBackend.getWorld(p));            sender.sendMessage(ChatUtils.formattedCmd("Info: " + p.getName(), false));            //10 second cooldown            cooldown.put(((Player) sender).getUniqueId(), new Cooldown(10));        } else {            sender.sendMessage(ChatColor.RED + "Player \"" + args[1] + "\" was not found!"); //the player was not found        }    }}