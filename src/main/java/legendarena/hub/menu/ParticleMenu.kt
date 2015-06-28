package legendarena.hub.menu

import legendapi.utils.ChatUtils
import legendapi.utils.MenuUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.Plugin

class ParticleMenu : Listener {

    EventHandler
    public fun onInventoryClick(ev: InventoryClickEvent) {
        if (!ev.getInventory().getName().equals(inv!!.getName())) return
        try {
            /*if(ev.getCurrentItem().getItemMeta() == null) return;
            if(!ParticleCore.multiParicle.contains(ev.getWhoClicked().getUniqueId()) && !ev.getCurrentItem().getItemMeta().getDisplayName().contains("Multi-Particle"))
                ev.getWhoClicked().closeInventory();
            if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Slime")) {
                ev.setCancelled(true);
                Player p = (Player) ev.getWhoClicked();
                new Message(MessageType.SUBTITLE).append(ChatColor.GREEN + "Enabled " + ChatColor.RED + "SLIME" + ChatColor.GREEN + " particles.").send(Sound.ORB_PICKUP, p);
                ParticleCore.addType(p.getName(), Type.SLIME);
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Hearts")) {
                ev.setCancelled(true);
                Player p = (Player) ev.getWhoClicked();
                new Message(MessageType.SUBTITLE).append(ChatColor.GREEN + "Enabled " + ChatColor.RED + "HEART" + ChatColor.GREEN + " particles.").send(Sound.ORB_PICKUP, p);
                ParticleCore.addType(p.getName(), Type.HEART);
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Portal")) {
                ev.setCancelled(true);
                Player p = (Player) ev.getWhoClicked();
                new Message(MessageType.SUBTITLE).append(ChatColor.GREEN + "Enabled " + ChatColor.RED + "PORTAL" + ChatColor.GREEN + " particles.").send(Sound.ORB_PICKUP, p);
                ParticleCore.addType(p.getName(), Type.PORTAL);
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Enchant")) {
                ev.setCancelled(true);
                Player p = (Player) ev.getWhoClicked();
                new Message(MessageType.SUBTITLE).append(ChatColor.GREEN + "Enabled " + ChatColor.RED + "ENCHANT" + ChatColor.GREEN + " particles.").send(Sound.ORB_PICKUP, p);
                ParticleCore.addType(p.getName(), Type.ENCHANT);
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Happy Villager")) {
                ev.setCancelled(true);
                Player p = (Player) ev.getWhoClicked();
                new Message(MessageType.SUBTITLE).append(ChatColor.GREEN + "Enabled " + ChatColor.RED + "HAPPY VILLAGER" + ChatColor.GREEN + " particles.").send(Sound.ORB_PICKUP, p);
                ParticleCore.addType(p.getName(), Type.HAPPYVILL);
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Angry Villager")) {
                ev.setCancelled(true);
                Player p = (Player) ev.getWhoClicked();
                new Message(MessageType.SUBTITLE).append(ChatColor.GREEN + "Enabled " + ChatColor.RED + "ANGRY VILLAGER" + ChatColor.GREEN + " particles.").send(Sound.ORB_PICKUP, p);
                ParticleCore.addType(p.getName(), Type.ANGRYVILL);
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Firework")) {
                ev.setCancelled(true);
                Player p = (Player) ev.getWhoClicked();
                new Message(MessageType.SUBTITLE).append(ChatColor.GREEN + "Enabled " + ChatColor.RED + "FIREWORK SPARK" + ChatColor.GREEN + " particles.").send(Sound.ORB_PICKUP, p);
                ParticleCore.addType(p.getName(), Type.FIREWORK);
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Colourful")) {
                ev.setCancelled(true);
                Player p = (Player) ev.getWhoClicked();
                new Message(MessageType.SUBTITLE).append(ChatColor.GREEN + "Enabled " + ChatColor.RED + "COLOURFUL EFFECTS" + ChatColor.GREEN + " particles.").send(Sound.ORB_PICKUP, p);
                ParticleCore.addType(p.getName(), Type.COLORFULEFFCTS);
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Flame")) {
                ev.setCancelled(true);
                Player p = (Player) ev.getWhoClicked();
                new Message(MessageType.SUBTITLE).append(ChatColor.GREEN + "Enabled " + ChatColor.RED + "FLAME" + ChatColor.GREEN + " particles.").send(Sound.ORB_PICKUP, p);
                ParticleCore.addType(p.getName(), Type.FLAME);
            }

            else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Off")) {
                ev.setCancelled(true);
                Player p = (Player) ev.getWhoClicked();
                new Message(MessageType.SUBTITLE).append(ChatColor.GREEN + "Disabled particles.").send(Sound.ORB_PICKUP, p);
                ParticleCore.removePlayer(p);
                if(ParticleCore.multiParicle.contains(ev.getWhoClicked().getUniqueId())) {
                    ParticleCore.multiParicle.remove(ev.getWhoClicked().getUniqueId());
                    ev.getInventory().setItem(0, MenuUtils.createItem(Material.LEVER, ChatColor.GREEN + "Multi-Particle", ChatColor.RED + "OFF" + ChatColor.GRAY + " (click to toggle)"));
                }
            }

            else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("⇐ Back")) {
                ev.setCancelled(true);
                ev.getWhoClicked().closeInventory();
                MainMenu.show((Player) ev.getWhoClicked());
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Multi-Particle")) {
                ev.setCancelled(true);
                if(ParticleCore.multiParicle.contains(ev.getWhoClicked().getUniqueId())) {
                    new Message(MessageType.SUBTITLE).append(ChatColor.GREEN + "Disabled multi-particle selector.").send(Sound.ORB_PICKUP, (Player) ev.getWhoClicked());
                    ParticleCore.removePlayer((Player) ev.getWhoClicked());
                    if(ParticleCore.multiParicle.contains(ev.getWhoClicked().getUniqueId()))
                        ParticleCore.multiParicle.remove(ev.getWhoClicked().getUniqueId());
                    ev.getInventory().setItem(0, MenuUtils.createItem(Material.LEVER, ChatColor.GREEN + "Multi-Particle", ChatColor.RED + "OFF" + ChatColor.GRAY + " (click to toggle)"));
                } else {
                    new Message(MessageType.SUBTITLE).append(ChatColor.GREEN + "Enabled multi-particle selector.").send(Sound.ORB_PICKUP, (Player) ev.getWhoClicked());
                    ParticleCore.multiParicle.add(ev.getWhoClicked().getUniqueId());
                    ev.getInventory().setItem(0, MenuUtils.createItem(Material.REDSTONE_TORCH_ON, ChatColor.GREEN + "Multi-Particle", ChatColor.GREEN + "ON" + ChatColor.GRAY + " (click to toggle)"));
                }
            }

            else { //failsafe
                ev.setCancelled(true);
            }*/

        } catch (ignore: Exception) {
            // Ignore the error
        }

    }

    companion object {

        private var inv: Inventory? = null
        private var init = false

        private fun init(p: Plugin) {
            if (init) return

            inv = Bukkit.createInventory(null, 36, ChatUtils.getCustomMsg("Menus") + "Particle Selector")

            //TODO: Make this a lot more cleaner

            /*val back = MenuUtils.createItem(Material.BED, ChatColor.GRAY + "⇐ Back", "")

            val hearts = MenuUtils.createItem(Material.WOOL, ChatColor.GREEN + "Hearts", ChatColor.BLUE + "Heart particles. Yay!")
            val slime = MenuUtils.createItem(Material.SLIME_BALL, ChatColor.GREEN + "Slime", ChatColor.BLUE + "Slime particles. Yay!")
            val portal = MenuUtils.createItem(Material.ENDER_PORTAL_FRAME, ChatColor.GREEN + "Portal", ChatColor.BLUE + "Portal particles. Yay!")
            val enchant = MenuUtils.createItem(Material.ENCHANTED_BOOK, ChatColor.GREEN + "Enchant", ChatColor.BLUE + "Enchant particles. Yay!")
            val villager = MenuUtils.createItem(Material.DIAMOND_BLOCK, ChatColor.GREEN + "Happy Villager", ChatColor.BLUE + "Happy villager particles. Yay!")
            val villager2 = MenuUtils.createItem(Material.DIRT, ChatColor.GREEN + "Angry Villager", ChatColor.BLUE + "Angry villager particles. Yay!")
            val fw = MenuUtils.createItem(Material.FIREWORK, ChatColor.GREEN + "Firework Spark", ChatColor.BLUE + "Firework Spark particles. Yay!")
            val coloEff = MenuUtils.createItem(Material.POTION, ChatColor.GREEN + "Colourful Effect", ChatColor.BLUE + "Colourful Effect particles. Yay!")
            val flames = MenuUtils.createItem(Material.BLAZE_POWDER, ChatColor.GREEN + "Flame", ChatColor.BLUE + "Flame particles. Yay!")

            val off = MenuUtils.createItem(Material.REDSTONE_LAMP_OFF, ChatColor.RED + "Off", ChatColor.BLUE + "Turns off your currently displaying particles. Aww.")

            inv!!.setItem(8, off)
            inv!!.setItem(4, back)

            inv!!.setItem(27, hearts)
            inv!!.setItem(28, slime)
            inv!!.setItem(29, portal)
            inv!!.setItem(30, enchant)
            inv!!.setItem(31, villager)
            inv!!.setItem(32, villager2)
            inv!!.setItem(33, fw)
            inv!!.setItem(34, coloEff)
            inv!!.setItem(35, flames)*/

            Bukkit.getPluginManager().registerEvents(ParticleMenu(), p)

            init = true
        }

        @suppress("UNUSED_PARAMETER")
        public fun show(p: Player) {
            /*init(Bukkit.getPluginManager().getPlugin("LegendArena"));
        Inventory pInv = Bukkit.createInventory(null, 36, ChatUtils.getCustomMsg("Menus") + "Particle Selector");
        pInv.setContents(inv.getContents());
        if(ParticleCore.multiParicle.contains(p.getUniqueId()))
            pInv.setItem(0, MenuUtils.createItem(Material.REDSTONE_TORCH_ON, ChatColor.GREEN + "Multi-Particle", ChatColor.GREEN + "ON" + ChatColor.GRAY + " (click to toggle)"));
        else
            pInv.setItem(0, MenuUtils.createItem(Material.LEVER, ChatColor.GREEN + "Multi-Particle", ChatColor.RED + "OFF" + ChatColor.GRAY + " (click to toggle)"));*/
            //p.openInventory(pInv);
        }
    }
}