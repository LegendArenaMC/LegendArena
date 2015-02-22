package net.thenamedev.legendarena.listeners;import net.thenamedev.legendapi.utils.*;import net.thenamedev.legendarena.*;import org.bukkit.*;import org.bukkit.event.*;import org.bukkit.event.player.*;import org.jetbrains.annotations.*;/** * @author TheNameMan */public class ChatListener implements Listener {    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)    public void onChat(@NotNull AsyncPlayerChatEvent ev) {        boolean isStaff = Rank.getRank(ev.getPlayer(), Rank.Helper);        if(LegendArena.isChatMuted() && !isStaff) {            ev.setCancelled(true);            ev.getPlayer().sendMessage(ChatColor.RED + "Chat is globally muted!");        } else            ev.setFormat(ChatUtils.getFormattedChat(ev.getMessage(), ev.getPlayer()));    }}