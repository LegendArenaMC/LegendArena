package net.thenamedev.legendarena.extras.motd;

import org.bukkit.*;
import org.jetbrains.annotations.*;

import java.util.*;

/**
 * @author TheNameMan
 */
public class MOTDRandomizer {

    //TODO: Clean up list
    private static final String[] motdList = {
            "Can connect to server.", //joke of "Can't connect to server" when MC cannot ping a server.
            "Powered by magic.", //Let's be honest, who wouldn't love a server that's powered by MAGIC?
            "Not powered by Enjin.", //for whatever reasons, servers that use Enjin for their site have a "bad rep" - no idea how.
            "How are you holding up? BECAUSE I'M A POTATO.", //Portal 2 joke
            "I BROKE NOTHING WHAT ARE YOU TALKING ABOUt", //Reference to how I break things easily.
            "Contains jokes.", //I have no idea why this is here.
            "May contain peanuts.", //joke on legal-mumbo-jumbo about disclaimers about if [x] food product is produced in a factory that handles nuts/peanuts/etc
            "Server is Optifine-compatible.", //every server is optifine compatible basically, yet some idiots decide to ask anyways
            "Does not support the Adventure Update.", //the Adventure Update is /Beta/ 1.8 - this is normal 1.8 we're on
            "May contain reaction gifs.", //this is obvious.
            "May contain Javert.", //reference to an Imgur joke
            "Has cookies.", //everyone likes cookies
            "The cake is a lie.", //really, do I need to explain what this is from
            "Dispenses fun.", //..is this seriously not obvious
            "Not seen on TV.", //joke of "As seen on TV"
            "Infinite pixels inside.", //unintentional pun of my name.. I guess
            "Does not contain donkeys.", //I have idea why I added this.
            "Does not run Mojang.", //I have no idea why I added this either.
            "Can resolve hostname.", //joke of "Can't resolve hostname" when MC is given an invalid hostname, e.g. "hypixel.nt"
            "Uses c0d3 sp3@k.", //I regret nothing of this one
            "Lennart lennart = new Lennart();", //blatantly stolen from splash screen
            "Has random MOTDs.", //reference to... well, this system
            "Uses Bootstrap.", //reference to the site using a Bootstrap theme
            "Oh good. My slow clap proccessor made it into this thing.", //reference to Portal 2 when glados says "how are you holding up? BECAUSE I'M A POTATO."
            "OBJECTION!", //reference to Pheonix Wright
            "What is a man? A miserable little pile of secrets.", //fine I'll admit it I just stole this quote from a top ten list of game quotes
            "All your base are belong to us.", //You know what this is a reference to.
            "What are 'ya buyin, stranger?", //reference to Resident Evil 4
            "Hey! Listen!", //hint: ZELDA. That's all you need.
            "HEADSHOT!", //no.
            "Snake? SNAAAKE!", //reference to a Metal Gear quote when Snake dies
            "Do a barrel roll!", //if you have no idea what this is from, you're a failure
            ChatColor.ITALIC + "askew" + ChatColor.GREEN, //try this in google. you'll get what this is then.
            "It's a me, Mario!", //if you don't know what this is from you're a failure
            "I used to be an adventurer like you, then I took an arrow to the knee.", //if you have no idea what this is, you're an ultra failure.
            "War. War never changes.", //I have no idea what this is from, I just stole it from a comments section on YouTube
            "SPAAAAAACEEE", //portal reference... again
            "//Made in Java", //reference to Minecraft being made in Java
            "Caused by: java.lang.NullPointerException", //reference to NullPointerException (oh how I have been burned so many times by this one error...)
            "git commit -m \"YOLO M8\" && git push --force", //reference to a "fuck it, yolo"-type git commit
            "That kitty is a bad kitty!" //reference to something that gets said in the Monstercat Podcast, at the beginning and end
    };

    public static String randomize() {
        @NotNull Random r = new Random();
        int msgInt = r.nextInt(motdList.length);
        return motdList[msgInt];
    }

    @NotNull
    public static String[] getList() {
        return motdList;
    }

}
