package legendarena.motd;

import legendapi.utils.CalendarUtils;
import legendapi.utils.ChatUtils;
import legendapi.utils.Months;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class MOTDUtils {

    private static String notice = "PUBLIC ALPHA";
    private static volatile String lastMOTDGiven = "";

    private static String override = "";
    private static boolean noDecor = false;

    /*
      to the person who adds the 300th MOTD, please put the 300th one as:
      "Madness? THIS. IS. SPARTAAAA!"
      blame jaden for it by the way
     */

    private static final String[] referenceList = {
            "Can connect to server.", //joke of "Can't connect to server" when MC cannot ping a server.
            "Can resolve hostname.", //joke of "Can't resolve hostname" when MC is given an invalid hostname, e.g. "mc.hypixel.nt"
            "Server is Optifine-compatible.", //just about every server is optifine compatible basically, yet some idiots decide to ask anyways
            "Does not support the Adventure Update.", //the Adventure Update is /Beta/ 1.8 - this is release 1.8 we're on
            "All your base are belong to us.", //You know what this is a reference to. If you don't know, you fail at the internet.
            "Have you tried turning it off and on again?", //reference to IT Crowd (and half of IT)
            "#WhatDelay", //reference to Beam having a 3-sec delay [maybe even lower?] (btw: https://beam.pro/ is the URL for beam)
            "IT'S DONOR, NOT DONATOR", //tiny rant about people using donator instead of donor (seriously, fucking learn the difference, PLEASE)
            "Is a Beefy Miracle.", //reference to Fedora's "Beefy Miracle" release
            "Arch Linux!", //reference to Arch Linux, a... well.. Linux distro.. the name kind of gave that away.
            "Ubuntu!", //reference to Ubuntu, another Linux distribution
            "Fedora!", //reference to Fedora, yet another Linux distribution...
            "USE GENTOO, NOOB", //reference to Gentoo, another Linux distribution - similar to Arch Linux, but you have to compile /everything/ - even the actual kernel during installation. (good luck with that btw)
            "Windows krill!", //reference to /r/LinuxMasterRace calling Windows users krill (which tbh is actually pretty fitting considering tux is a penguin)
            "You Mac squid!", //again, reference to /r/LinuxMasterRace
            "Kurz Gesagt!", //reference to the YouTube channel with that name (which frankly is amazing)
            "Reddit v4: Now with more Pao!", //reference to the recent #RedditRevolt and Digg v4 (also let's be honest who actually remembers Digg)
    };

    private static final String[] songList = {
            "Everybody wants to rule the world...", //reference to "Lorde - Everybody Wants To Rule The World"
            "We will start from the start...", //reference to "Tristam & Braken - Far Away"
            "what is 'real'?", //reference to "Varien - Supercell"'s music video
            "When all of your wind, is gone...", //again, reference to the above song
            "The girl that time forgot...", //reference to "Hellberg - The Girl"
            "We are the music makers.", //reference to "Direct - Lonely Soul"
            "And we, are the dreamers of dreams.", //reference to above song
            "We can stay right here and play...", //reference to "Tristam & Braken - Frame of Mind"
            "Out of sight and out of miiinddd...", //reference to "Au5 ft. Tasha Baxter - Snowblind"
            "We could be legends...", //reference to "Razihel ft. TeamMate - Legends"
            "Stephen Kappa - On Top of the DansGame", //pun on "Stephen [Walking] - On Top of The [World]"
            "Dad, why are you in space?", //reference to "Stephen Walking - Dads In Space"
            "The other day...", //reference to "Insan3lik3 - The Other Day"
            "These walls are cold and empty...", //reference to "Muzzy - The Destroyer" (Two Thirds remix)
            "And it went oh-woaah-ohhhhh...", //reference to "Tristam - I Remember"
            "We are [NVIDIA] Titans", //reference to "Razihel & Aero Chord - Titans" (and also NVIDIA Titan graphic cards)
            "I hate you. I love you.", //reference to "Aero Chord - Saiko" (also this song is strange as fuck)
            "I came in like a wreeecking baaallll...", //this is very, very obvious.
            "I'm giving up on leaving you...", //reference to "Savoy, Sound Remedy & Jojee - Leaving You" (also how many artists working on the same song is the world record? I wonder...)
    };

    private static final String[] randomList = {
            "Why circle pizzas? Why not square pizzas?", //#BlameJaden
            "Pika pi!",
            "MadeIn(Kotlin())",
            "Uses.some(new Java());",
            "var lennart = Lennart()", //kotlin version of the splash screen message "Lennart lennart = new Lennart();"
            "Achievement get!",
            "\"When is HL3 released?\" In 2198-- no, 2628.",
            "WELCOME, TO THE MONSTERCAT PODCAST.",
            "15 Reddit clones on the wall, 15 Reddit clones...",
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
            "Woo, reddit!",
            "#RedditRevolt", //reference to revolts against very shitty actions that the reddit admins made
            "67.1k coins!", //reference to around pixel's insane amount of Arcade coins on Hypxel (..I'll admit it, I'm addicted to Build Battle (that and I'm a coin hoarder))
            "A hacker has DOWNLOADED YOUR FREEDOM!", //good 'ol bash.org...
            "But this is Ireland!",
            "*oof, gasp* Minecon...",
            "Free wiffy?", //reference to the villager saying "free wifi" as "free wiffy" in the Minecon 2015 opening ceremony animation
            "Mmm, wiffy...",
            "git rekt", //reference to "get rekt", with "git" in place of "get"
            "Who you gonna call? [Ghostbusters!]",
            "I ain't afraid of no ghosts.",
            "But this is too much paperwork to do!",
            "Skittles have taken the internet!",
            "London! London! London! London! Hurry!", //[Mmph! Mmph!] London! London! London! London! [Mmph! Mmph!]
            "Animating elements since 2010.",
            "Do you want to be a boat instead?",
            "I am also a boat.",
            "We are all part of a boat.",
            "That's right #7, it's a cube.",
            "What are you doing here? This is not your battle!",
            "Introducing the all new Power Drill!",
            "100? 200 more for sparta!", //100 MOTD HYPE
            "5 Best Minecraft Funny An-OHGODWHYMYSPLEEEEN",
            "Get your brand new Spork! Now with 40% more spoon!",
            "\"Hello mom!\" Hi son!",
            "It is a terrible day for villager-kind.",
            "Nice to meet you! I'm villager number- BOOM!",
            "It's so beast like!",
            "...am I floating.. AIEEEEEEEEEEeeeee... *CLANG*",
            "\"Do you know what time it is!?\" Uh... no?",
            "Wait for it... wait for it... WUBBBBB", //ah, the drop is always the best part. (...usually, at least)
            "Not seen on TV.",
            "May contain peanuts.",
            "Fun for the whole family!",
            "May contain reaction GIFs.",
            "*says no and nods head*",
            "HEADSHOT!",
            "Contains jokes.",
            "*hugs*",
            "dinnerbone pls can i has hug i want hug",
            "Read more JComic!",
            "NSAdows 10: Now with 150% more NSA!",
            "Chips? Screw that, I'll have a bag of air! For one dollar!",
            "Mmm, air. So refreshing.",
            "It's bigger on the inside!",
            "Scribbledy bibbledy hoodelly hoo", //vault 106 pls
            "Wing wang bricka bang choo choo choo", //pls
            "Upsideup popsicle tastes like blue", //just pls
            "Ghosts in the hall go boo boo boo!", //vault 106 why are you so strange
            "JUSTIN BIEBER IS ILLUMANITY CONFIRMED", //I have no idea if I spelled Illumanity wrong or not, but fuck it, I don't care
            "helo am spodermen n 2dai am guna play meincreft",
            "Behind the scenes with NullPointerException",
            "I may be bionic, but seriously I won't do your homework.",
            "Oh yeah? Well I can type 75 words per SECOND!",
            "Now now, don't forget to eat your vegetables!",
            "I'll just check this quick BuzzFeed post. <2 Hours Later>",
            "I'll just go on Reddit for a moment. <10 Hours Later>",
            "It'll only take 3 minutes to download. <10 Years Later>",
            "I'll have a <insert interesting french thing here>",
            "Something happened", //Something happened
            "JumpPads - now with 250% more bounce!",
            "Deck the halls with cheesy breadsticks!",
            "omnomnompotatoes.gif",
            "I think that dragon likes potatoes.",
            "That rainbow didn't taste like Skittles at all!",
            "Your server is bad and you should feel bad!",
            "Who parked their car.. on my SANDWICH?",
            "Useless button, do not press",
            "Dezmond The Moon Bear!",
            "How did I get here? [THE END]",
            "What time is it? It's Muffin Time!",
            "Actually, it's 12:30.",
            "MEEGAANNNNN!", //MEGAN! MEGAN!
            ":compileJava [100 Errors]", //I hate Java.
            "Every other page, splat.",
            "I got a thousand dollars, now I'll get Starbucks.",
            "f u liek dis video raet comment n subskrieb",
            "pls give 5 stars thnx",
            "Do you want to build a sn- *OOF*",
            "Ah, the 'ol MOTD-switch-aroo...", //reddit switcharoo, you never fail to amaze me (..sometimes)
            "Yes, I'll have a half-decaf Late..", //hi Starbucks, yes, I know you exist DansGame
            "\"Is this Legend Arena?\" No, this is Patrick.",
            "Taking all night, but we're adding on, we promise",
            "Put a fountain in, as fountains are cool.",
            "ALL HAIL OUR LORD AND SAVIOR, GABEN", //@valvesoftware.com
            "We're gonna have to blow up some cars...",
            "Dinosaurs on a space ship",
            "Have you ever sneezed so hard you turned into Peter Capaldi?",
            "The moon landing was a fake!",
            "9/11 was a fak- MY SPLEEEENNN", //we get it, "9/11 Truthers". You think 9/11 was fake. NOW FUCK OFF. YOU'RE BEATING A DEAD HORSE FOR WHAT IT'S WORTH. </rant>
            "Poor Taren.", //if you don't get the reference, Taren (am I even getting that right? fuck it) is a Linus Media Group employee, i.e. the company(?) who makes LinusTechTips
            "Dead physically, alive memorably",
            "Derpy sister is derpy", //OHGOD WHAT IS JADEN'S SISTER DOING NOW
            "Go back to your Linux subs with your tinfoil hats", //fuck you too "PCMasterRace" elitists. Seriously, please. For the love of GabeN, DON'T BE AN ELITIST.
            "\"I would switch to Linux, but...\"", //WE GET IT, YOU DON'T HAVE YOUR "[Generic Shitty Triple-A Game Title]" on Linux (ahem - sorry, GNU/Linux). Plus, if you used GNU/Linux, and showed support for a Linux port of said game, maybe there actually would be a GNU/Linux version of the game. </rant>
            "I'd just like to interject for a moment..", //What you refer to as "Linux", is, in fact, GNU/Linux, as I've recently taken to calling it, GNU+Linux. (sorry)
            "Windows? Pfft. I scoff at your licenses.", //..I need to make a linuxJokeList.
            (new Random().nextBoolean() ? "sudo pacman -S" : (new Random().nextBoolean() ? "sudo apt-get install" : (new Random().nextBoolean() ? "sudo dnf install" : "yaourt -S"))) + " linux", //LIIINUUUUXXXX (..I really do need a linuxJokeList just for these)
            "Sticky keys? What's that?", //obligatory "windows krill" commment (someone remind me to go make that linuxJokeList)
            "o man i cannot into CSS", //see /r/Ooer (or for ultra "WTF"-ness - /r/OoerIntensifies (also, good luck with that one))
    };

    private static final String[] gameList = {
            "Hype. Hype never changes.", //reference to Fallout 4(?)/3/2/1/NV (temporarily "Hype" instead of "War" because blame Fallout 4)
            "Wake up and smell the ashes.", //half life 2 reference
            "No one is more deserving of a rest", //hl2...
            "Rise and shine, Mr. Freeman. Rise and shine.", //*yawn* hl2..
            "Wake up, and smell the ashes.", //bla bla bla you get the idea by now, HL2 bla bla bla
            "Run, would you kindly?", //bioshock (infinite?) reference
            "11-10-15", //obligatory reference to Fallout 4's release date
            "Now pick up that can.", //half life 2 episode 1 (0? eh, unsure about it and frankly don't care enough to check right now) reference. now pick up that can.
            "λ 3", //[Half Life] 3 - the special character is a Lambda, which is used as Half Life's logo
            "Pay the court a fine or serve your sentence.", //blame Oblivion
            "Your stolen goods are now forfeit.", //again, blame Oblivion
            "\"A man chooses, a slave obeys.\"", //bioshock quote
            "Obsessive Blocky Race Cars!", //reference to Turbo Kart Racers on Hypixel (also god damn is it addicting as hell)
            "The Minecraft movie is a block buster!", //blame jaden
            "Just remember to land on one foot.", //portal reference
            "The cake is a lie.", //portal...
            "Good that's still working.", //*yawn* portal, again..
            "SPAAAAAACEEE", //bla bla bla portal reference, you probably get the idea by now
            "News flash! Creepers on the loose!",
            "Five Nights at Freddy's 4! Now with 400% more idiocy!",
            "Half Life 3 confirmed", //inb4 /u/WhenIsHL3 bot
            "gaben can i pls have herf lerf fer", //HALF LIFE 4 CONFIRMED GUYS!
            "Please stop with pointless FPS games, thanks",
    };

    /**
     * Overrides the default MOTD, optionally displaying only the override text.
     * @param noDecor Should we replace only the random MOTD (false) or all of the MOTD (true)?
     * @param override The text to display instead of the normal MOTD
     */
    public static void override(boolean noDecor, String override) {
        MOTDUtils.override = override;
        MOTDUtils.noDecor = noDecor;
    }

    public static String getRandomMOTD() {
        //yes, hard-coded, full-out overrides. because fuck you[tm]
        if(new CalendarUtils().getDay() == 3 && new CalendarUtils().getMonth() == Months.MAY)
            return "Happy birthday, Pixel! <3";
        else if(new CalendarUtils().getDay() == 16 && new CalendarUtils().getMonth() == Months.NOVEMBER)
            return "Happy birthday, Jaden! <3";

        if(!override.equals(""))
            return override;

        Random r = new Random();
        int rL = r.nextInt(4);
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
            case 4:
                return1 = gameList[r.nextInt(gameList.length)];
                break;
            default:
                return1 = randomList[r.nextInt(randomList.length)];
                break;
        }

        while(return1.equals(lastMOTDGiven)) {
            rL = r.nextInt(4);
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
                case 4:
                    return1 = gameList[r.nextInt(gameList.length)];
                    break;
                default:
                    return1 = randomList[r.nextInt(randomList.length)];
                    break;
            }
        }

        lastMOTDGiven = return1;
        return return1;
    }

    public static String getBuiltMOTD() {
        String random = getRandomMOTD();

        ChatColor randomC1 = ChatColor.RED;
        ChatColor randomC2 = ChatColor.GREEN;

        if(!noDecor) return "" + randomC1 + "Legend Arena" + ChatColor.YELLOW + " {" + MOTDUtils.getNotice() + "}" +
                '\n' + ChatColor.DARK_GRAY + "//" + randomC2 + random;

        else return override;
    }

    public static HashMap<ListType, String[]> getList() {
        HashMap<ListType, String[]> list = new HashMap<>();

        list.put(ListType.RANDOM, randomList.clone());
        list.put(ListType.REFERENCE, referenceList.clone());
        list.put(ListType.SONG, songList.clone());
        list.put(ListType.GAMING, gameList.clone());

        return list;
    }

    public static String[] getList(ListType type) {
        switch(type) {
            case RANDOM:
                return randomList.clone();
            case REFERENCE:
                return referenceList.clone();
            case SONG:
                return songList.clone();
            case GAMING:
                return gameList.clone();

            default:
                return null;
        }
    }

    public static ArrayList<String> getArrayList(ListType type) {
        ArrayList<String> list = new ArrayList<>();
        if(type == null)
            throw new NullPointerException();
        if(getList(type) == null)
            throw new NullPointerException();
        //shut up intellij, there's two null checks right above this damn call
        //noinspection ConstantConditions
        Collections.addAll(list, getList(type));
        return list;
    }

    //intellij shut the fuck up
    @SuppressWarnings("ConstantConditions")
    public static ArrayList<String> getFullMOTDArrayList() {
        ArrayList<String> built = new ArrayList<>();

        Collections.addAll(built, getList(ListType.REFERENCE));
        Collections.addAll(built, getList(ListType.SONG));
        Collections.addAll(built, getList(ListType.RANDOM));
        Collections.addAll(built, getList(ListType.GAMING));

        return built;
    }

    public static String getNotice() {
        return notice;
    }

    public static int getAmountOfMOTDs() {
        return getFullMOTDArrayList().size();
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