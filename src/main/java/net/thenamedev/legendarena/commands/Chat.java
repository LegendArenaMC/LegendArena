package net.thenamedev.legendarena.commands;import net.thenamedev.legendapi.utils.PluginUtils;import net.thenamedev.legendapi.utils.Rank;import net.thenamedev.legendarena.extras.staffchat.SCType;import net.thenamedev.legendarena.extras.staffchat.StaffChat;import org.bukkit.Bukkit;import org.bukkit.ChatColor;import org.bukkit.command.Command;import org.bukkit.command.CommandExecutor;import org.bukkit.command.CommandSender;import org.bukkit.entity.Player;/** * @author TheNameMan */public class Chat implements CommandExecutor {    public boolean onCommand(CommandSender sender, Command label, String c, String[] args) {        if(args.length == 0) {            sender.sendMessage(                            (Rank.getRank(sender, Rank.GM) ? ChatColor.GREEN + "• GM" : ChatColor.RED + "• GM")                            + '\n'                            + (Rank.getRank(sender, Rank.Mod) ? ChatColor.GREEN + "• MOD" : ChatColor.RED + "• MOD")                            + '\n'                            + (Rank.getRank(sender, Rank.Mod) ? ChatColor.GREEN + "• STAFF" : ChatColor.RED + "• STAFF")                            + '\n'                            + (Rank.getRank(sender, Rank.Mod) ? ChatColor.GREEN + "• ALERT" : ChatColor.RED + "• ALERT")                            + '\n'                            + (Rank.getRank(sender, Rank.Helper) ? ChatColor.GREEN + "• HELPER" : ChatColor.RED + "• HELPER")                            + '\n'                            + (Rank.getRank(sender, Rank.VIP) ? ChatColor.GREEN + "• VIP" : ChatColor.RED + "• VIP")                            + '\n'                            + ChatColor.GREEN + "• SHOW [on|off]"                            + '\n'                            + (Rank.getRank(sender, Rank.Mod) ? ChatColor.GREEN + "• OFF [user]" : ChatColor.GREEN + "• OFF")                            + '\n'                            + ChatColor.YELLOW + "Current channel: " + ChatColor.BLUE + SCType.getType(((Player) sender).getUniqueId())            );        } else {            if(args[0].equalsIgnoreCase("gm")) {                if(!Rank.getRank(sender, Rank.GM)) {                    Rank.noPermissions(sender, Rank.GM);                    return true;                }                if(args.length == 1) {                    if(SCType.hasStaffchat(((Player) sender).getUniqueId(), SCType.GM)) {                        SCType.clearStaffchat(((Player) sender).getUniqueId());                        sender.sendMessage(PluginUtils.msgNormal + "Left GM chat.");                    } else {                        SCType.addUser(((Player) sender).getUniqueId(), SCType.GM);                        sender.sendMessage(PluginUtils.msgNormal + "Joined GM chat.");                    }                } else {                    args[0] = ""; //nullify the channel name string so we can ignore it                    String cast = "";                    for(String s : args) {                        if(s.equals("")) continue;                        cast += s + " ";                    }                    StaffChat.staffChat(SCType.GM, (Player) sender, cast);                }            } else if(args[0].equalsIgnoreCase("notify")) {                if(!Rank.getRank(sender, Rank.Dev)) {                    Rank.noPermissions(sender, Rank.Dev);                    return true;                }                if(args.length == 1) {                    if(SCType.hasStaffchat(((Player) sender).getUniqueId(), SCType.NOTIFICATION)) {                        SCType.clearStaffchat(((Player) sender).getUniqueId());                        sender.sendMessage(PluginUtils.msgNormal + "Stopped broadcasting notifications.");                    } else {                        SCType.addUser(((Player) sender).getUniqueId(), SCType.NOTIFICATION);                        sender.sendMessage(PluginUtils.msgNormal + "Started broadcasting notifications.");                    }                } else {                    sender.sendMessage(ChatColor.BLUE + "Absolutely not.");                }            } else if(args[0].equalsIgnoreCase("mod")) {                if(!Rank.getRank(sender, Rank.Mod)) {                    Rank.noPermissions(sender, Rank.Mod);                    return true;                }                if(args.length == 1) {                    if(SCType.hasStaffchat(((Player) sender).getUniqueId(), SCType.MOD)) {                        SCType.clearStaffchat(((Player) sender).getUniqueId());                        sender.sendMessage(PluginUtils.msgNormal + "Left MOD chat.");                    } else {                        SCType.addUser(((Player) sender).getUniqueId(), SCType.MOD);                        sender.sendMessage(PluginUtils.msgNormal + "Joined MOD chat.");                    }                } else {                    args[0] = ""; //nullify the chat string                    String cast = "";                    for(String s : args) {                        if(s.equals("")) continue;                        cast = cast + s + " ";                    }                    StaffChat.staffChat(SCType.MOD, (Player) sender, cast);                }            } else if(args[0].equalsIgnoreCase("staff")) {                if(!Rank.getRank(sender, Rank.Mod)) {                    Rank.noPermissions(sender, Rank.Mod);                    return true;                }                if(args.length == 1) {                    if(SCType.hasStaffchat(((Player) sender).getUniqueId(), SCType.STAFF)) {                        SCType.clearStaffchat(((Player) sender).getUniqueId());                        sender.sendMessage(PluginUtils.msgNormal + "Left STAFF chat.");                    } else {                        SCType.addUser(((Player) sender).getUniqueId(), SCType.STAFF);                        sender.sendMessage(PluginUtils.msgNormal + "Joined STAFF chat.");                    }                } else {                    args[0] = ""; //nullify the chat string                    String cast = "";                    for(String s : args) {                        if(s.equals("")) continue;                        cast = cast + s + " ";                    }                    StaffChat.staffChat(SCType.STAFF, (Player) sender, cast);                }            } else if(args[0].equalsIgnoreCase("alert")) {                if(!Rank.getRank(sender, Rank.Mod)) {                    Rank.noPermissions(sender, Rank.Mod);                    return true;                }                if(args.length == 1) {                    if(SCType.hasStaffchat(((Player) sender).getUniqueId(), SCType.ALERT)) {                        SCType.clearStaffchat(((Player) sender).getUniqueId());                        sender.sendMessage(PluginUtils.msgNormal + "Stopped broadcasting alerts.");                    } else {                        SCType.addUser(((Player) sender).getUniqueId(), SCType.ALERT);                        sender.sendMessage(PluginUtils.msgNormal + "Started broadcasting alerts.");                    }                } else {                    args[0] = ""; //nullify the channel name string so we can ignore it                    String cast = "";                    for(String s : args) {                        if(s.equals("")) continue;                        cast += s + " ";                    }                    StaffChat.staffChat(SCType.GM, (Player) sender, cast);                }            } else if(args[0].equalsIgnoreCase("vip")) {                if(!Rank.getRank(sender, Rank.VIP)) {                    Rank.noPermissions(sender, Rank.VIP);                    return true;                }                if(args.length == 1) {                    if(SCType.hasStaffchat(((Player) sender).getUniqueId(), SCType.VIP)) {                        SCType.clearStaffchat(((Player) sender).getUniqueId());                        sender.sendMessage(PluginUtils.msgNormal + "Left VIP chat.");                    } else {                        SCType.addUser(((Player) sender).getUniqueId(), SCType.VIP);                        sender.sendMessage(PluginUtils.msgNormal + "Joined VIP chat.");                    }                } else {                    args[0] = ""; //nullify the chat string                    String cast = "";                    for(String s : args) {                        if(s.equals("")) continue;                        cast = cast + s + " ";                    }                    StaffChat.staffChat(SCType.VIP, (Player) sender, cast);                }            } else if(args[0].equalsIgnoreCase("helper")) {                if(!Rank.getRank(sender, Rank.Helper)) {                    Rank.noPermissions(sender, Rank.Helper);                    return true;                }                if(args.length == 1) {                    if(SCType.hasStaffchat(((Player) sender).getUniqueId(), SCType.HELPER)) {                        SCType.clearStaffchat(((Player) sender).getUniqueId());                        sender.sendMessage(PluginUtils.msgNormal + "Left HELPER chat.");                    } else {                        SCType.addUser(((Player) sender).getUniqueId(), SCType.HELPER);                        sender.sendMessage(PluginUtils.msgNormal + "Joined HELPER chat.");                    }                } else {                    args[0] = ""; //nullify the chat string                    String cast = "";                    for(String s : args) {                        if(s.equals("")) continue;                        cast = cast + s + " ";                    }                    StaffChat.staffChat(SCType.HELPER, (Player) sender, cast);                }            } else if(args[0].equalsIgnoreCase("show")) {                if(!SCType.canSeeStaffchat(((Player) sender).getUniqueId())) {                    SCType.setCanSeeStaffchat(((Player) sender).getUniqueId(), true);                    sender.sendMessage(PluginUtils.msgNormal + "Toggled channel view on.");                } else {                    SCType.setCanSeeStaffchat(((Player) sender).getUniqueId(), false);                    SCType.clearStaffchat(((Player) sender).getUniqueId());                    sender.sendMessage(PluginUtils.msgNormal + "Toggled channel view off.");                }            } else if(args[0].equalsIgnoreCase("off")) {                if(args.length == 1) {                    SCType.clearStaffchat(((Player) sender).getUniqueId());                    sender.sendMessage(PluginUtils.msgNormal + "Left all channels.");                } else if(args.length >= 3) {                    SCType.clearStaffchat(((Player) sender).getUniqueId());                    sender.sendMessage(PluginUtils.msgNormal + "Left all channels.");                } else {                    if(!Rank.getRank(sender, Rank.Mod)) {                        SCType.clearStaffchat(((Player) sender).getUniqueId());                        sender.sendMessage(PluginUtils.msgNormal + "Left all channels.");                        return true;                    }                    if(Bukkit.getPlayer(args[1]) == null) {                        sender.sendMessage(ChatColor.RED + "That player was not found!");                    } else {                        SCType.clearStaffchat(Bukkit.getPlayer(args[1]).getUniqueId());                        sender.sendMessage(PluginUtils.msgNormal + ChatColor.YELLOW + "Forced player " + ChatColor.RED + Bukkit.getPlayer(args[1]).getName() + ChatColor.YELLOW + " to leave all channels.");                        Bukkit.getPlayer(args[1]).sendMessage(PluginUtils.msgNormal + ChatColor.RED + "Forced to leave all channels by staff member " + sender.getName() + ".");                    }                }            } else {                sender.sendMessage(                        (Rank.getRank(sender, Rank.GM) ? ChatColor.GREEN + "• GM" : ChatColor.RED + "• GM")                                + '\n'                                + (Rank.getRank(sender, Rank.Mod) ? ChatColor.GREEN + "• MOD" : ChatColor.RED + "• MOD")                                + '\n'                                + (Rank.getRank(sender, Rank.Mod) ? ChatColor.GREEN + "• STAFF" : ChatColor.RED + "• STAFF")                                + '\n'                                + (Rank.getRank(sender, Rank.Mod) ? ChatColor.GREEN + "• ALERT" : ChatColor.RED + "• ALERT")                                + '\n'                                + (Rank.getRank(sender, Rank.Helper) ? ChatColor.GREEN + "• HELPER" : ChatColor.RED + "• HELPER")                                + '\n'                                + (Rank.getRank(sender, Rank.VIP) ? ChatColor.GREEN + "• VIP" : ChatColor.RED + "• VIP")                                + '\n'                                + ChatColor.GREEN + "• SHOW [on|off]"                                + '\n'                                + (Rank.getRank(sender, Rank.Mod) ? ChatColor.GREEN + "• OFF [user]" : ChatColor.GREEN + "• OFF")                                + '\n'                                + ChatColor.YELLOW + "Current channel: " + ChatColor.BLUE + SCType.getType(((Player) sender).getUniqueId())                );            }        }        return true;    }}