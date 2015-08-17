package legendarena.motd;

import legendapi.utils.CalendarUtils;
import legendapi.utils.Months;
import legendarena.utils.ConfigUtils;
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

    private static final String[] songList = {
            "Everybody wants to rule the world...", //reference to "Lorde - Everybody Wants To Rule The World"
            "what is 'real'?", //reference to "Varien - Supercell"'s music video
            "$varien_supercell", //reference to the above song
            "'eyes' => 'open'", //*yawn* yet again...
            "$communicate - Is this real?", //you get the idea by now
            "We will start from the start...", //reference to "Tristam & Braken - Far Away"
            "When all of your wind, is gone...", //reference to the above song
            "You scored: 0", //reference to "Astronaut, Eyes - Pinball (Dubsidia Remix)"
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
            "I'm giving up on leaving you...", //reference to "Savoy, Sound Remedy & Jojee - Leaving You" (also how many artists working on the same song is the world record? I wonder...)
            "Casual noob!" //I regret this. ( oh right. reference. https://www.youtube.com/watch?v=Kzv0FVKcZ8A )
    };

    private static final String[] randomList = {
            "Have you tried turning it off and on again?", //reference to IT Crowd (and half of IT)
            "#WhatDelay", //reference to Beam having a 3-sec delay [maybe even lower?] (btw: https://beam.pro/ is the URL for beam)
            "Kurz Gesagt!", //reference to the YouTube channel with that name (which frankly is amazing)
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
            "NSAdows 10: Now with 150% more NSA!", //this seems fittingly correct now that NSAdows 10 is actually released.
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
            "But it's only 1.5 Gigabytes!", //ah linux distros, you never fail to surprise me with how large your ISOs are. (..I'm looking at you, Linux Mint, Korora & Fedora)
            "Something happened", //Something happened
            "JumpPads - now with 250% more bounce!",
            "Deck the halls with cheesy breadsticks!",
            "omnomnompotatoes.gif", //OM POTATOES (..sorry)
            "I think that dragon likes potatoes.",
            "That rainbow didn't taste like Skittles at all!",
            "Your server is bad and you should feel bad!",
            "Useless button, do not press",
            "Dezmond The Moon Bear!",
            "How did I get here? [THE END]",
            "What time is it? It's Muffin Time!",
            "Actually, it's 12:30.",
            "MEEGAANNNNN!", //MEGAN! MEGAN!
            ":compileJava [100 Errors]", //I hate Java.[tm]
            "Every other page, splat.",
            "I got a thousand dollars, now I'll get Starbucks.",
            "f u liek dis video raet comment n subskrieb",
            "pls give 5 stars thnx",
            "Do you want to build a sn- *OOF*",
            "Ah, the 'ol MOTD-switch-aroo...", //reddit switcharoo, you never fail to amaze me (..sometimes, at least)
            "Put a fountain in, as fountains are cool.",
            "We're gonna have to blow up some cars...",
            "Dinosaurs on a space ship",
            "The moon landing was a fake!",
            "9/11 was a fak- AHH MY SPLEEEENNN", //we get it, "9/11 Truthers". You think 9/11 was fake. NOW GO AWAY. PLEASE. YOU'RE BEATING A DEAD HORSE FOR WHAT IT'S WORTH. </rant>
            "Poor Taren.", //if you don't get the reference, Taren (am I even getting that right? fuck it) is a Linus Media Group employee, i.e. the company(?) who makes LinusTechTips
            "Derpy sister is derpy", //OHGOD WHAT IS JADEN'S SISTER DOING NOW
            "GNU Terry Pratchett", //reference to the "X-Clacks-Overhead: GNU Terry Pratchett" header in responses from some servers ( see: http://clacksoverhead.discworld.us/ )
            "314 clicks has resolved the issue.", //program not responding? CLICKCLICKCLICKCLICKCLICKCLICKCLICKCLICKCLICKCLICKCLICKCLICKCLICKCLICKCLICKCLICK<and so on>
            "WE'VE BEEN SMECKLEDORFED!", //see: http://redd.it/3g2e5j
            "It broke.", //Something happened
            "Banning Hackers Wi- oh wait this isn't MCPvP",
            "Unintellegent Text Spammer!", //british nub go away, ty
            "Vidya gaemz.", //vidya gaemz are best gaemz. (I regret nothing of this by the way)
            "Akame!", //anime. pls. why are you so strange. pls. just, pls. i fna.
            "IntelliJ isn't always intellegent.", //INTELLIJ. THERE IS A NULL CHECK TWO LINES ABOVE THIS CALL. LOOK.
            "This isn't a batmobile in disguise, move on!", //blame jaden 2k15
            "Time to check my email! In like 4 years!", //strongbad reference (and blame jaden again)
            "I like to break windows. Colourful or not.", //blame jaden yet again
            "\"Colorful\" Banned DansGame", //jaden 2k15 is a nub (british spelling op)
            "That's a monsterous feline from that jukebox.", //jaden yet again
            "Too many splitkillers...", //*yawn* jaden, again.. (and agario)
            "Everybody do the flop!", //blame both asdfmovie and jaden
            "ThePixelDev was thrown in the void by JadenJFilms", //again, jaden
            "Mario must live on Mushroom Island!", //blame jaden 2k15 v1224
            "JadenJFilms was thrown in the void by ThePixelDev",
            "ThePixelDev was killed by Ste-wait, what?", //steve pls
            "This is a minigame on Min- AAAH MY SPLEEEEN", //PLEASE STOP RECORDING EXCLUSIVELY ON MINEPLEX. PLEASE.
            "Monstercat - Now with 1,000% more Future Bass!", //also HOLY MOTHER OF WHAT, SOMEONE PREDICTED THE FUTURE (...bass) http://redd.it/3gxl3x
    };

    private static final String[] gameList = {
            "War. War never changes.", //reference to Fallout 4(?)/3/2/1/NV
            "War has changed.", //reference to the MGS4 opening cutscene
            "Wake up and smell the ashes.", //half life 2 reference
            "No one is more deserving of a rest", //hl2...
            "\"Is this Legend Arena?\" No, this is Patrick.",
            "ALL HAIL OUR LORD AND SAVIOR, GABEN", //@valvesoftware.com
            "67.1k coins!", //reference to around pixel's insane amount of Arcade coins on Hypxel (..I'll admit it, I'm addicted to Build Battle (that and I'm a coin hoarder))
            "Rise and shine, Mr. Freeman. Rise and shine.", //*yawn* hl2..
            "Wake up, and smell the ashes.", //bla bla bla you get the idea by now, HL2 bla bla bla
            "Now pick up that can.", //too lazy to copy/pasta the above comment. now pick up that can.
            "Run, would you kindly?", //bioshock (infinite?) reference
            "11-10-15", //obligatory reference to Fallout 4's release date
            "λ 3", //[Half Life] 3 - the special character is a Lambda, which is used as Half Life's logo
            "Pay the court a fine or serve your sentence.", //blame Oblivion
            "Your stolen goods are now forfeit.", //again, blame Oblivion
            "\"A man chooses, a slave obeys.\"", //bioshock quote
            "Obsessive Blocky Race Cars!", //reference to Turbo Kart Racers on Hypixel (also god damn, is it addicting as hell)
            "The Minecraft movie is a block buster!", //blame jaden
            "Just remember to land on one foot.", //portal reference
            "The cake is a lie.", //portal...
            "Good that's still working.", //*yawn* portal, again..
            "SPAAAAAACEEE", //bla bla bla portal reference, you probably get the idea by now
            "News flash! Creepers on the loose!",
            "Five Nights at Freddy's 4! Now with 400% more idiocy!",
            "Half Life 3 confirmed", //inb4 /u/WhenIsHL3 bot
            "gaben can i pls have herf lerf fer", //HALF LIFE 4 CONFIRMED GUYS!
            "Please stop with pointless FPS games, thanks", //I'm looking at you, Call of Duty...
            "Make me a sandvich!", //sandvich is op
    };

    private static final String[] linuxList = {
            "Go back to your Linux subs with your tinfoil hats", //fuck you too "PCMasterRace" elitists. Seriously, please. For the love of GabeN, DON'T BE AN ELITIST. PC DOES NOT MEAN WINDOWS EXCLUSIVELY.
            "\"I would switch to Linux, but...\"", //WE GET IT, you don't have [Generic Shitty Triple-A Game Title] on Linux (ahem - sorry, GNU/Linux). Plus, if you used GNU/Linux, and showed support for a GNU/Linux port of said game, maybe there actually would be a GNU/Linux version of the game. </rant>
            "I'd just like to interject for a moment..", //What you refer to as "Linux", is, in fact, GNU/Linux, as I've recently taken to calling it, GNU+Linux. (sorry)
            "Windows? Pfft. I scoff at your licenses.",
            (new Random().nextBoolean() ? "sudo pacman -S" : (new Random().nextBoolean() ? "sudo apt-get install" : (new Random().nextBoolean() ? "sudo dnf install" : "yaourt -S"))) + " linux",
            "Sticky keys? What's that?",
            "Arch Linux!",
            "Ubuntu!",
            "Fedora!",
            "Is a Beefy Miracle.", //reference to Fedora's "Beefy Miracle" release
            "Minty fresh!", //reference to both Linux Mint (another Linux (fine, GNU/Linux) distrobution...), and the mint flavour (whynotboth.jpg)
            "USE GENTOO, NOOB",
            "Windows krill!",
            //"You Mac squid!", //disclaimer by Pixel: we do not condone the practice of calling mac users "squids" in real life.
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
        //yes, hard-coded, full-out overrides. because why not.[tm]
        if(new CalendarUtils().getDay() == 3 && new CalendarUtils().getMonth() == Months.MAY)
            return "Happy birthday, Pixel! <3";
        else if(new CalendarUtils().getDay() == 16 && new CalendarUtils().getMonth() == Months.NOVEMBER)
            return "Happy birthday, Jaden! <3";

        if(!override.equals(""))
            return override;

        Random r = new Random();

        //will this ever get hit? maybe. would it be interesting if it was hit and someone posted it? very yes.
        if(r.nextInt(50) / 5 == 3)
            return "Easter egg!";

        int rL = r.nextInt(4) + 1;
        String return1;

        switch(rL) {
            case 1:
                return1 = randomList[r.nextInt(randomList.length)];
                break;
            case 2:
                return1 = songList[r.nextInt(songList.length)];
                break;
            case 3:
                return1 = gameList[r.nextInt(gameList.length)];
                break;
            case 4:
                return1 = linuxList[r.nextInt(linuxList.length)];
                break;
            default:
                return1 = randomList[r.nextInt(randomList.length)];
                break;
        }

        while(return1.equals(lastMOTDGiven)) {
            rL = r.nextInt(4) + 1;
            switch(rL) {
                case 1:
                    return1 = randomList[r.nextInt(randomList.length)];
                    break;
                case 2:
                    return1 = songList[r.nextInt(songList.length)];
                    break;
                case 3:
                    return1 = gameList[r.nextInt(gameList.length)];
                    break;
                case 4:
                    return1 = linuxList[r.nextInt(linuxList.length)];
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

        if(!noDecor)
            return ChatColor.RED + "Legend Arena" + ChatColor.GRAY + " { " + ChatColor.YELLOW + getNotice() + ChatColor.GRAY + " }" +
                '\n' + ChatColor.DARK_GRAY + "//" + ChatColor.GREEN + random;

        else return override;
    }

    public static HashMap<ListType, String[]> getList() {
        HashMap<ListType, String[]> list = new HashMap<>();

        list.put(ListType.RANDOM, randomList.clone());
        list.put(ListType.SONG, songList.clone());
        list.put(ListType.GAMING, gameList.clone());
        list.put(ListType.GAMING, linuxList.clone());

        return list;
    }

    public static String[] getList(ListType type) {
        switch(type) {
            case RANDOM:
                return randomList.clone();
            case SONG:
                return songList.clone();
            case GAMING:
                return gameList.clone();
            case LINUX:
                return linuxList.clone();

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
        //shut up intellij, there's two null checks right above this damn call.
        //noinspection ConstantConditions
        Collections.addAll(list, getList(type));
        return list;
    }

    //intellij shut the fuck up
    @SuppressWarnings("ConstantConditions")
    public static ArrayList<String> getFullMOTDArrayList() {
        ArrayList<String> built = new ArrayList<>();

        Collections.addAll(built, getList(ListType.SONG));
        Collections.addAll(built, getList(ListType.RANDOM));
        Collections.addAll(built, getList(ListType.GAMING));
        Collections.addAll(built, getList(ListType.LINUX));

        return built;
    }

    public static String getNotice() {
        return notice;
    }

    public static int getAmountOfMOTDs() {
        return getSize(ListType.RANDOM) + getSize(ListType.LINUX) + getSize(ListType.SONG) + getSize(ListType.GAMING);
    }

    public static int getSize(ListType type) {
        switch(type) {
            case RANDOM:
                return randomList.length;
            case SONG:
                return songList.length;
            case LINUX:
                return linuxList.length;
            case GAMING:
                return gameList.length;

            default:
                return 0;
        }
    }

    public static String getLastMOTDGiven() {
        return lastMOTDGiven;
    }

    public static void setNotice(String newNotice) {
        notice = newNotice.toUpperCase();
        ConfigUtils.config.set("notice", newNotice); //horrible storage of the notice? absolutely. works? yes. do I care? not right now.
    }

}