package legendarena.commands

import legendarena.api.fanciful.FancyMessage
import legendarena.api.utils.ChatUtils
import legendarena.api.utils.Rank
import legendarena.api.utils.StringUtils
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Help : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        if(StringUtils.getSize(args) == 0) {
            FancyMessage("It looks like you want help.\n \n")
                        .color(ChatColor.LIGHT_PURPLE)
                    .then("Maybe you could ask a staff member?")
                        .command("/stafflist")
                        .tooltip("Click to view the online staff")
                        .color(ChatColor.GREEN)
                    .send(sender)
             return true
        } else if(args[0].equals("ranks")) {
            ChatUtils.fancyMsg("" + Rank.FOUNDER.getNameColor() + "Founder", "Network owners. That's all.").send(sender)
            ChatUtils.fancyMsg("" + Rank.DEV.getNameColor() + "Developer", "Network developers.").send(sender)
            ChatUtils.fancyMsg("" + Rank.ADMIN.getNameColor() + "Admin", "Admins. They're the people who you should ask first if you need help.").send(sender)
            ChatUtils.fancyMsg("" + Rank.MOD.getNameColor() + "Mod", "Moderators. If asking an admin doesn't work, try asking these staff.").send(sender)
            ChatUtils.fancyMsg("" + Rank.HELPER.getNameColor() + "Helper", "Helpers. They do what it sounds like - help people.").send(sender)
            ChatUtils.fancyMsg("" + Rank.VIP.getNameColor() + "VIP", "Youtubers and Twitch streamers.").send(sender)
            ChatUtils.fancyMsg("" + Rank.MEMBERPLUS.getNameColor() + "Member+", "Donators. That's all.").send(sender)
            ChatUtils.fancyMsg("" + Rank.MEMBER.getNameColor() + "Member", "Normal players.").send(sender)
        } else
            FancyMessage("It looks like you want help.\n \n")
                        .color(ChatColor.LIGHT_PURPLE)
                    .then("Maybe you could ask a staff member?")
                        .command("/stafflist")
                        .tooltip("Click to view the online staff")
                        .color(ChatColor.GREEN)
                    .send(sender)
        return true
    }

}