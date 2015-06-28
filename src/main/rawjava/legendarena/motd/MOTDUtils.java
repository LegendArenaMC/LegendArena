package legendarena.motd;

import legendapi.utils.CalendarUtils;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class MOTDUtils {

    private static String notice = "PUBLIC ALPHA";
    private static volatile String lastMOTDGiven = "";

    private static final String[] referenceList = {
            "Can connect to server.", //joke of "Can't connect to server" when MC cannot ping a server.
            "Can resolve hostname.", //joke of "Can't resolve hostname" when MC is given an invalid hostname, e.g. "mc.hypixel.nt"
            "Server is Optifine-compatible.", //just about every server is optifine compatible basically, yet some idiots decide to ask anyways
            "Does not support the Adventure Update.", //the Adventure Update is /Beta/ 1.8 - this is release 1.8 we're on
            "All your base are belong to us.", //You know what this is a reference to. If you don't know, you fail at the internet.
            "Hype. Hype never changes.", //reference to Fallout 4(?)/3/2/1/NV (temporarily "Hype" instead of "War" because blame Fallout 4)
            "Wake up and smell the ashes.", //half life 2 reference
            "No one is more deserving of a rest", //hl2...
            "Rise and shine, Mr. Freeman. Rise and shine.", //*yawn* hl2..
            "Wake up, and smell the ashes.", //bla bla bla you get the idea by now, HL2 bla bla bla
            "Run, would you kindly?", //bioshock (infinite?) reference
            "\"A man chooses, a slave obeys.\"", //obligatory bioshock quote
            "Just remember to land on one foot.", //portal reference
            "The cake is a lie.", //portal...
            "Good that's still working.", //*yawn* portal, again..
            "SPAAAAAACEEE", //bla bla bla portal reference, you probably get the idea by now
            "Have you tried turning it off and on again?", //reference to IT Crowd (and half of IT)
            "#WhatDelay", //reference to Beam having a 3-sec delay [maybe even lower?] (btw: https://beam.pro/ is the URL for beam)
            "IT'S DONOR, NOT DONATOR", //tiny rant about people using donator instead of donor (seriously, fucking learn the difference, PLEASE)
            "Is a Beefy Miracle.", //reference to Fedora's "Beefy Miracle" release
            "11-10-15", //obligatory reference to Fallout 4's release date
            "Arch Linux!", //reference to Arch Linux, a... well.. Linux distro.. the name kind of gave that away.
            "\"Who the hell are you?\" Nyah!", //Reference to Eggs Guide to Minecraft ep. 15
            "Jason? JAAASONN?", //heavy rain reference
            "Kurz Gesagt!", //reference to the YouTube channel with that name
            "Reddit v4: Now with more Pao!" //reference to the recent #RedditRevolt and Digg v4 (also let's be honest who actually remembers Digg)
    };

    private static final String[] songList = {
            "We will start from the start...", //reference to "Tristam & Braken - Far Away"
            "When all of your wind, is gone...", //again, reference to the above song
            "BARRACUDA", //reference to Barracuda (I forget which artist but I remember the song... good enough? ;-;)
            "| Reconstruction 1.2", //reference to Build The Cities remixes
            "The girl that time forgot...", //reference to "Hellberg - The Girl"
            "We no speak Americano", //reference to a song with that exact name
            "We can stay right here and play...", //reference to "Tristam & Braken - Frame of Mind"
            "Out of sight and out of miiinddd...", //reference to "Au5 ft. Tasha Baxter - Snowblind"
            "We could be legends...", //reference to "Razihel ft. TeamMate - Legends"
            "Stephen Kappa - On Top of the DansGame", //pun on "Stephen [Walking] - On Top of The [World]"
            "Dad, why are you in space?", //reference to "Stephen Walking - Dads In Space"
            "You've, you've you've violated, the law", //reference to "The Imperial Guard Song" ( https://www.youtube.com/watch?v=r_2Jduxc2P8 )
            "The other day...", //reference to "Insan3lik3 - The Other Day"
            "These walls are cold and empty...", //reference to "Muzzy - The Destroyer" (Two Thirds remix)
            "TASTES LIKE CHIKIN", //reference to "Pegboard Nerds - Bassline Kickin" (seriously, the lyrics do kind of sound like this)
            "I hate you. I love you.", //reference to "Aero Chord - Saiko" (also this song is strange as fuck)
            "I'm giving up on leaving you...", //reference to "Savoy, Sound Remedy & Jojee - Leaving You" (also how many artists working on the same song is the world record? I wonder...)
    };

    private static final String[] randomList = {
            "Is not a Zone UHC server.", //pardon me, just poking fun at the Zone UHC server
            "Why circle pizzas? Why not square pizzas?", //#BlameJaden
            "こんにちは日本！", //translated is "Hello Japan!" (yes, I used google translate, too lazy to bother actually trying to translate it (plus because japaneese, pls))
            "Pika-pika!",
            "MadeIn(Kotlin)",
            "Achievement get!",
            "Pay the court a fine or serve your sentence.", //blame Oblivion
            "\"When is HL3 released?\" In 2198-- no, 2431.",
            "M'lady", //reddit pls. just, pls.
            "WELCOME, TO THE MONSTERCAT PODCAST.",
            "Hello, world!",
            "15 Reddit clones on the wall., 15 Reddit clones...",
            "Half Life 3 confirmed", //inb4 /u/WhenIsHL3 bot
            "s/admins/badmins", //reference to Jaden's tweet about reddit admins
            "You guys got any more of them servers?", //reference to voat having mass server problems (#RedditRevolt)
            "I'm a server, not a magician.",
            "Not powered by Enjin.",
            "Powered by magic.",
            "EnderTek Miniman! OHGOD MY SPLEEN WHYY",
            "#PleaseStandBy", //FALLOUT 4 HPYE TRAIN INBOUND
            "WE'RE THE TUNNEL SNAKES", //THAT'S US, AND WE RULE
            "Mostly because Tunnel Snakes rule.",
            "Water cooled!", //reference to water cooling, a thing you can do with a computer in place of (most?) fans
            "Woo, Voat!", //pst: voat.co/v/legendarena
            "#RedditRevolt", //reference to revolts against very shitty actions that the reddit admins made
            "Not seen on TV.",
            "May contain peanuts.",
            "Fun for the whole family!",
            "Dispenses " + (new Random().nextBoolean() ? "fun" : "cake") + (new Random().nextBoolean() ? "." : "!"),
            "May contain reaction GIFs.",
            "Does not use System.out.println()",
            "Uses Bukkit.getOnlinePlayers()",
            "*says no and nods head*",
            "HEADSHOT!",
            "Contains jokes.",
            "*hugs*",
            "dinnerbone pls can i has hug i want hug",
            "Read more JComic!",
            "WHERE'S MY SNAPSHOT!?!? WHERE IS IT!?", //don't mind me, just poking fun at the butthurt fanboys
    };

    public static String getRandomMOTD() {
        if(CalendarUtils.getDate().getDay() == 3 && CalendarUtils.getDate().getMonth() == CalendarUtils.MAY)
            return "Happy birthday, Pixel! <3";
        else if(CalendarUtils.getDate().getDay() == 16 && CalendarUtils.getDate().getMonth() == CalendarUtils.NOVEMBER)
            return "Happy birthday, Jaden! <3";
        Random r = new Random();
        int rL = r.nextInt(3);
        String return1;
        switch(rL) {
            case 1:
                return1 = randomList[r.nextInt(randomList.length)];
                break;
            case 2:
                return1 = songList[r.nextInt(songList.length)];
                break;
            case 3:
                return1 = referenceList[r.nextInt(referenceList.length)];
                break;
            default:
                return1 = randomList[r.nextInt(randomList.length)];
                break;
        }
        while(return1.equals(lastMOTDGiven)) {
            rL = r.nextInt(3);
            switch(rL) {
                case 1:
                    return1 = randomList[r.nextInt(randomList.length)];
                    break;
                case 2:
                    return1 = songList[r.nextInt(songList.length)];
                    break;
                case 3:
                    return1 = referenceList[r.nextInt(referenceList.length)];
                    break;
                default:
                    return1 = randomList[r.nextInt(randomList.length)];
                    break;
            }
        }
        lastMOTDGiven = return1;
        return return1;
    }

    public static HashMap<ListType, String[]> getList() {
        HashMap<ListType, String[]> list = new HashMap<>();
        list.put(ListType.RANDOM, randomList);
        list.put(ListType.REFERENCE, referenceList);
        list.put(ListType.SONG, songList);
        return list;
    }

    public static String[] getList(ListType type) {
        switch(type) {
            case RANDOM:
                return randomList;
            case REFERENCE:
                return referenceList;
            case SONG:
                return songList;

            default:
                return null;
        }
    }

    public static ArrayList<String> getArrayList(ListType type) {
        ArrayList<String> list = new ArrayList<>();
        switch(type) {
            case RANDOM:
                Collections.addAll(list, randomList);
                break;
            case SONG:
                Collections.addAll(list, songList);
                break;
            case REFERENCE:
                Collections.addAll(list, referenceList);
                break;
        }
        return list;
    }

    public static String getNotice() {
        return notice;
    }

    public static String getLastMOTDGiven() {
        return lastMOTDGiven;
    }

    public static void setNotice(String newNotice) {
        notice = newNotice.toUpperCase();
        Bukkit.getPluginManager().getPlugin("LegendArena").getConfig().set("motdNotice", newNotice.toUpperCase());
        Bukkit.getPluginManager().getPlugin("LegendArena").saveConfig();
    }

}