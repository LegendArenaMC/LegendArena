package legendarena.api.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.Random;

/**
 * MOTD randomizer.
 *
 * @author ThePixelDev
 */
public class MOTDRandomizer {

    private static String notice = "PUBLIC ALPHA";

    private static final String[] motdList = {
            "Can connect to server.", //joke of "Can't connect to server" when MC cannot ping a server.
            "Can resolve hostname.", //joke of "Can't resolve hostname" when MC is given an invalid hostname, e.g. "mc.hypixel.nt"
            "Server is Optifine-compatible.", //just about every server is optifine compatible basically, yet some idiots decide to ask anyways
            "Does not support the Adventure Update.", //the Adventure Update is /Beta/ 1.8 - this is release 1.8 we're on
            "All your base are belong to us.", //You know what this is a reference to. If you don't know, you fail at the internet.
            "What are 'ya buyin, stranger?", //reference to Resident Evil 4
            ChatColor.ITALIC + "askew", //try this in google. you'll get what this is then.
            "Hype. Hype never changes.", //reference to Fallout 4(?)/3/2/1/NV (temporarily "Hype" instead of "War" for Fallout 4)
            "Made in Java", //reference to Minecraft being made in Java, and thus basically everything that uses Minecraft pretty much needs to be in Java, or re-invent the wheel.
            "Wake up and smell the ashes.", //half life 2 reference
            "No one is more deserving of a rest", //hl2...
            "Rise and shine, Mr. Freeman. Rise and shine.", //*yawn* hl2..
            "Wake up, and smell the ashes.", //bla bla bla you get the idea by now, HL2 bla bla bla
            "Run, would you kindly?", //bioshock (infinite?) reference
            "Just remember to land on one foot.", //portal reference
            "The cake is a lie.", //portal...
            "Good that's still working.", //*yawn* portal, again..
            "SPAAAAAACEEE", //bla bla bla portal reference, you probably get the idea by now
            "Have you tried turning it off and on again?", //reference to IT Crowd
            "#WhatDelay", //reference to Beam having a 3-sec delay [maybe even lower?] (btw: https://beam.pro/ is the URL for beam)
            "Cannot be shellshocked.", //reference to Shellshock, a Bash vulnerability
            "IT'S DONOR, NOT DONATOR", //tiny rant about people using donator instead of donor
            "(dance break) I quit.", //reference to "An Interpretive Dance For My Boss Set To Kanye West's Gone"
            "Is a Beefy Miracle.", //reference to Fedora's "Beefy Miracle" release
            "Arch Linux!", //reference to Arch Linux, a... well.. Linux distro.. the name kind of gave that away.
            "\"Who the hell are you?\" Nyah!", //Reference to Eggs Guide to Minecraft ep. 15
            "Jason? JAAASONN?", //heavy rain reference
            "The catch? Catch-22.", //reference to Catch-22, in which fighting a situation would be accepting it (wikipedia has a slightly better explanation, but obviously take it with a grain of salt)

            // BEGIN SONG REFERENCES THAT HAVE NO REASON TO BE HERE BUT FUCK IT WHO WOULD CARE ANYWAYS, IT'S RANDOM MOTDS, AAAAND NOW I'M RAMBLING IN ALL CAPS, FACK

            "We will start from the start...", //reference to "Tristam & Braken - Far Away"
            "When all of your wind, is gone...", //again, reference to the above song
            "BARRACUDA", //reference to Barracuda (I forget which artist but I remember the name... good enough?)
            "| Reconstruction 1.2", //reference to Build The Cities remixes
            "The girl that time forgot...", //reference to "Hellberg - The Girl"
            "We no speak Americano", //reference to a song with that exact name
            "We can stay right here and play...", //reference to "Tristam & Braken - Frame of Mind"
            "Out of sight and out of miiinddd...", //reference to "Au5 ft. Tasha Baxter - Snowblind"
            "We could be legends...", //reference to "Razihel ft. TeamMate - Legends"
            "Stephen Kappa - On Top of the DansGame", //pun on "Stephen [Walking] - On Top of The [World]"
            "The other day...", //reference to "Insan3lik3 - The Other Day"
            "These walls are cold and empty...", //reference to "Muzzy - The Destroyer" (Two Thirds remix)
            "TASTES LIKE CHIKIN", //reference to "Pegboard Nerds - Bassline Kickin" (seriously, the lyrics do kind of sound like this)
            "I hate you. I love you.", //reference to "Aero Chord - Saiko" (also this song is strange as fuck)
            "I'm giving up on leaving you...", //reference to "Savoy, Sound Remedy & Jojee - Leaving You" (also how many artists working on the same song is the world record? I wonder...)

            //BEGIN VERY STUPIDLY RANDOM MOTDS

            "HYPE TRAIN INBOUND", //THE HYPE TRAIN HAS NO BRAKES, CHOO CHOOO
            "Kurz Gesagt!", //reference to the YouTube channel with that name
            "Is not a Zone UHC server.", //pardon me, just poking fun at the Zone UHC server
            "Why circle pizzas? Why not square pizzas?", //#BlameJaden
            "こんにちは日本！", //translated is "Hello Japan!" (yes, I used google translate, too lazy to bother actually trying to translate it (plus because japaneese, pls))
            "Pika-pika!",
            "Hey, look! That guy's blac- aand I'm banned.", //shhhhhhhh..... we must never speak of this
            "Go jump off a bri- oh and i'm banned kbye",
            "DU DU DU DUDUDUDUDU DU DU DU",
            "Aaand it's gone.",
            "Rekt-22", //shhh...
            "\"When is HL3 released?\" In 2198-- wait, I mean 2373.",
            "WELCOME, TO THE MONSTERCAT PODCAST.",
            "Hello, world!",
            "No, Intel is not a person.",
            "Not powered by Enjin.",
            "Does not contain donkeys.",
            "Powered by magic.",
            "Has cookies.",
            "EnderTek Miniman! OHGOD MY SPLEEN WHYY",
            "#PleaseStandBy", //FALLOUT 4 HPYE TRAIN INBOUND
            "WE'RE THE TUNNEL SNAKES", //THAT'S US, AND WE RULE
            "Not seen on TV.",
            "May contain peanuts.",
            "Fun for the whole family!",
            "Dispenses " + (new Random().nextBoolean() ? "fun" : "cake") + (new Random().nextBoolean() ? "." : "!"),
            "May contain reaction GIFs.",
            "Does not use System.out.println()",
            "Uses Bukkit.getOnlinePlayers()",
            "IT'S SNEAK PEEK, NOT SNEAK PEAK YOU FOOLS",
            "*says no and nods head*",
            "Contains references.",
            "HEADSHOT!",
            "Contains jokes.",
            "*hugs*",
            "dinnerbone pls can i has hug i want hug",
            "Read more JComic!",
            "Woo, reddit!", //pst: /r/LegendArena
            "WHERE'S MY SNAPSHOT!?!? WHERE IS IT!?", //don't mind me, just poking fun at the butthurt fanboys

            //BEGIN ANIME REFERENCES (#BlameJapan)

            "Are you a red or blue Kämpfer?", //reference to Kampfer
    };

    @Deprecated
    public static String randomize() {
        return getRandomMOTD();
    }

    public static String getRandomMOTD() {
        if(Day.getDate().getDay() == 3 && Day.getDate().getMonth() == Day.MAY)
            return "Happy birthday, Pixel! <3"; //shh...
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
