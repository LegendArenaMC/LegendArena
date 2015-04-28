package net.thenamedev.legendarena.extras;

import net.thenamedev.legendapi.utils.Day;
import net.thenamedev.legendarena.LegendArena;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.Random;

/**
 * @author ThePixelDev
 */
public class MOTDRandomizer {

    private static String notice = "PUBLIC ALPHA";

    private static final String[] motdList = {
            "Can connect to server.", //joke of "Can't connect to server" when MC cannot ping a server.
            "Can resolve hostname.", //joke of "Can't resolve hostname" when MC is given an invalid hostname, e.g. "mc.hypixel.nt"
            "Server is Optifine-compatible.", //just about every server is optifine compatible basically, yet some idiots decide to ask anyways
            "Does not support the Adventure Update.", //the Adventure Update is /Beta/ 1.8 - this is release 1.8 we're on
            "May contain Javert.", //reference to an Imgur joke
            "Has random MOTDs.", //reference to... well, this system.. I guess
            "OBJECTION!", //reference to Pheonix Wright
            "All your base are belong to us.", //You know what this is a reference to.
            "What are 'ya buyin, stranger?", //reference to Resident Evil 4
            "Hey! Listen!", //hint: ZELDA. That's all you need.
            "Snake? SNAAAKE!", //reference to a Metal Gear quote when Snake dies
            "Do a barrel roll!", //if you have no idea what this is from, you're a failure
            ChatColor.ITALIC + "askew" + ChatColor.LIGHT_PURPLE, //try this in google. you'll get what this is then.
            "It's a me, Mario!", //if you don't know what this is from you're a failure
            "War. War never changes.", //reference to Fallout 3/2/1/NV
            "//Made in Java", //reference to Minecraft being made in Java, and thus basically everything that uses Minecraft pretty much needs to be in Java, or re-invent the wheel. (also a reference to a code comment)
            "Not compatible with Minicraft.", //reference to a Ludem Dare Notch did that he called "Minicraft"
            "I am Alpha and Omega, the beginning and end.", //reference to Fallout 3
            "igotthatreference.gif", //reference to "I Got That Reference" gifs
            "Wake up and smell the ashes.", //half life 2 reference
            "No one is more deserving of a rest", //hl2...
            "Rise and shine, Mr. Freeman. Rise and shine.", //*yawn* hl2..
            "Run, would you kindly?", //bioshock (infinite?) reference
            "Just remember to land on one foot.", //portal reference
            "The cake is a lie.", //portal...
            "Good that's still working.", //*yawn* portal, again..
            "SPAAAAAACEEE", //bla bla bla portal reference, you get the idea by now
            ChatColor.DARK_RED + "The ban hammer has spoken!" + ChatColor.LIGHT_PURPLE, //default ban message for a LOT of servers (and I mean a damn lot)
            "#WhatDelay", //reference to Beam having a 3-sec delay [maybe even lower?] (btw: https://beam.pro/ is the URL for beam)
            "Cannot be shellshocked.", //reference to Shellshock, a Bash vulnerability
            "IT'S DONOR, NOT DONATOR", //tiny rant about people using donator instead of donor
            "(dance break) I quit.", //reference to "An Interpretive Dance For My Boss Set To Kanye West's Gone"
            "Is a Beefy Miracle.", //reference to Fedora's "Beefy Miracle" release
            "\"Who the hell are you?\" Nyah!", //Reference to Eggs Guide to Minecraft ep. 15
            "The catch? Catch-22.", //reference to Catch-22, in which fighting a situation would be accepting it (wikipedia has a slightly better explanation, but obviously don't /fully/ trust it)
            "Uses `./gradlew build`.", //reference to how the plugin is [usually] built

            // BEGIN MASS SONG REFERENCES

            "We will start from the start...", //reference to "Tristam & Braken - Far Away"
            "When all of your wind, is gone...", //again, reference to the above song
            "The girl that time forgot...", //reference to "Hellberg - The Girl"
            "We no speak Americano", //reference to a song with that exact name
            "We can stay right here and play...", //reference to "Tristam & Braken - Frame of Mind"
            "Out of sight and out of miiinddd..", //refence to "Au5 ft. Tasha Baxter - Snowblind"
            "Talent goes by...", //reference to "Tristam - Talent Goes By"
            "Stephen Kappa - On Top of the DansGame", //pun on "Stephen [Walking] - On Top of The [World]"

            //BEGIN VERY STUPIDLY RANDOM MOTDS

            "Syncs data via magic.",
            "Does not contain Darude - Sandstorm.", //CAN WE PLEASE FUCKING KILL THE DARUDE SANDSTORM JOKE NOW IT'S DRIVING ME INSANE -PIXEL
            "i like magic",
            "$ echo \"Hello, world!\"",
            "\"When is HL3 released?\" In 3034- I mean, 3035.",
            "Hello, world!",
            "No, Intel is not a person.",
            "Not powered by Enjin.",
            "Does not contain donkeys.",
            "Powered by magic.",
            "Has cookies.",
            "Not seen on TV.",
            "May contain peanuts.",
            "Fun for the whole family!",
            "Dispenses fun.",
            "May contain reaction GIFs.",
            "Does not use System.out.println()",
            "Uses Bukkit.getOnlinePlayers()",
            "*says no and nods head*",
            "Contains references.",
            "WOAH-OH-OH-AH-AH-AH-AAAA-HA",
            "Who's the bae?", //...why the hell did I add this MOTD
            "It doesn't work? Oh well.",
            "HEADSHOT!",
            "Contains jokes.",
            "Uses c0d3 sp3@k.",
            "*hugs*",
            "dinnerbone pls can i has hug",
            "Read more JComic!",
            "WHERE'S MY SNAPSHOT!?!? WHERE IS IT!?" //don't mind me, just poking fun at the butthurt fanboys
    };

    public static String randomize() {
        if(Day.getDate().isAprilFools())
            return "DARUDE - SANDSTORM (April Fools <3)"; //shhh.... don't tell the players...
        Random r = new Random();
        int msgInt = r.nextInt(motdList.length);
        return motdList[msgInt];
    }

    public static String[] getList() {
        return motdList;
    }

    public static String getNotice() {
        return notice;
    }

    public static void setNotice(String newNotice) {
        notice = newNotice.toUpperCase();
        Bukkit.getPluginManager().getPlugin("LegendArena").getConfig().set("motdNotice", newNotice.toUpperCase());
        Bukkit.getPluginManager().getPlugin("LegendArena").saveConfig();
    }

}