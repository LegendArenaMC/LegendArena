package net.thenamedev.legendarena.extras.motd;

import org.bukkit.*;
import org.jetbrains.annotations.*;

import java.util.*;

/**
 * @author ThePixelDev
 */
public class MOTDRandomizer {

    private static final String[] motdList = {
            "Can connect to server.", //joke of "Can't connect to server" when MC cannot ping a server.
            "Powered by magic.", //Let's be honest, who wouldn't love a server that's powered by MAGIC?
            "Not powered by Enjin.", //for whatever reasons, servers that use Enjin for their site have a "bad rep" - no idea how.
            "The cake is a lie.", //bla bla bla, too lazy to repeat above sentence, bla bla bla
            "Contains jokes.", //I have no idea why this is here.
            "May contain peanuts.", //joke on legal-mumbo-jumbo about disclaimers about if [x] food product is produced in a factory that handles nuts/peanuts/etc
            "Server is Optifine-compatible.", //every server is optifine compatible basically, yet some idiots decide to ask anyways
            "Does not support the Adventure Update.", //the Adventure Update is /Beta/ 1.8 - this is normal 1.8 we're on
            "May contain reaction GIFs.", //this is obvious.
            "May contain Javert.", //reference to an Imgur joke
            "Has cookies.", //everyone likes cookies, right?
            "Dispenses fun.", //..is this seriously not obvious
            "Not seen on TV.", //joke of "As seen on TV"
            "Fun for the whole family!", //reference to TV commercials really
            "Does not contain donkeys.", //I have idea why I added this.
            "Can resolve hostname.", //joke of "Can't resolve hostname" when MC is given an invalid hostname, e.g. "mc.hypixel.nt"
            "Uses c0d3 sp3@k.", //I regret nothing of this one
            "Has random MOTDs.", //reference to... well, this system.. I guess
            "Uses Bootstrap.", //reference to the site using a Bootstrap theme
            "OBJECTION!", //reference to Pheonix Wright
            "All your base are belong to us.", //You know what this is a reference to.
            "What are 'ya buyin, stranger?", //reference to Resident Evil 4
            "Hey! Listen!", //hint: ZELDA. That's all you need.
            "Snake? SNAAAKE!", //reference to a Metal Gear quote when Snake dies
            "Do a barrel roll!", //if you have no idea what this is from, you're a failure
            ChatColor.ITALIC + "askew" + ChatColor.GREEN, //try this in google. you'll get what this is then.
            "It's a me, Mario!", //if you don't know what this is from you're a massive failure
            "War. War never changes.", //reference to Fallout 3/2/1
            "//Made in Java", //reference to Minecraft being made in Java, and thus basically everything that uses Minecraft pretty much needs to be in Java, or re-invent the wheel.
            "Not compatible with Minicraft.", //reference to a Ludem Dare Notch did that he called "Minicraft"
            "Now has tokens!", //reference to the recently implemented tokens system
            "Uses Bukkit.getOnlinePlayers()", //reference to the Bukkit.getOnlinePlayers() function
            "No, Intel is not a person.", //reference to idiots reading "Intel Inside" like "Intel" is a person (I have no idea why this would happen either, but hey, the saying "Make something idiot proof and nature will find a way to make a better idiot" stands true to this day...)
            "I am Alpha and Omega, the begenning and end.", //reference to the bible (if it isn't in there, the Fallout3 bible)
            "igotthatreference.gif", //reference to "I Got That Reference" gifs
            "Just remember to land on one foot.", //portal reference
            "Good that's still working.", //portal...
            "SPAAAAAACEEE", //bla bla bla portal reference, you get the idea by now
            "It's black and blue!", //THE DRESS ;-;
            "It's white and gold!", //THE DRESS [AGAIN] ;-;
            ChatColor.DARK_RED + "The ban hammer has spoken!" + ChatColor.GREEN, //default ban message for a LOT of servers
            "We will start from the start...", //reference to "Tristam & Braken - Far Away"
            "We no speak Americano", //reference to a song with that exact name
            "We can stay right here and play...", //reference to "Tristam & Braken - Frame of Mind"
            "Out of sight and out of miiinddd..", //refence to "Au5 ft. Tasha Baxter - Snowblind"
            "Cannot be shellshocked.", //reference to Shellshock, a Bash (Bourne Again SHell) vulnerability
            "Can I have some sugar in my Java?", //java can mean coffee, so.. yeeeahhh.. yeah that was bad ill leave now
            "IT'S DONOR, NOT DONATOR", //tiny rant about people using donator instead of donor
            //BEGIN STUPIDLY RANDOM MOTDS
            "<insert random MOTD here>",
            "Does not use System.out.println()",
            "*says no and nods head*",
            "Contains references.",
            "WOAH-OH-OH-AH-AH-AH-AAAA-HA",
            "Who's the bae?", //...why the hell did I add this MOTD
            "It doesn't work? Oh well ¯\\(ツ)/¯",
            "HEADSHOT!",
            "Kappa",
            "SourPls",
    };

    public static String randomize() {
        @NotNull Random r = new Random();
        int msgInt = r.nextInt(motdList.length);
        while(motdList[msgInt].toCharArray().length > 49) { //sanity checks
            msgInt = r.nextInt(motdList.length);
            if(motdList[msgInt].toCharArray().length <= 49) //it's 44 [or less] chars, so break out of the while() loop (just incase)
                break;
        }
        return motdList[msgInt];
    }

    @NotNull
    public static String[] getList() {
        return motdList;
    }

}
