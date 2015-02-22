package net.thenamedev.legendarena.extras.hub.motd;

import org.bukkit.*;
import org.jetbrains.annotations.*;

import java.util.*;

/**
 * @author TheNameMan
 */
public class MOTDRandomizer {

    private static final String[] motdList = {
            "Can connect to server.", //joke of "Can't connect to server" when MC cannot ping a server.
            "Powered by magic.", //Let's be honest, who wouldn't love a server that's powered by MAGIC?
            "Not powered by Enjin.", //for whatever reasons, servers that use Enjin for their site have a "bad rep" - no idea how.
            "Oh, hi. So. How are you holding up? BECAUSE I'M A POTATO.", //Portal joke
            "Oh good. My slow clap proccessor made it into this thing.", //portal reference again
            "[clap clap] Good that's still working.", //reference to one of the following lines of the above reference
            "Just remember to land on one foot.", //portal reference yet again
            "SPAAAAAACEEE", //bla bla bla, portal reference, you get the idea by now
            "The cake is a lie.", //bla bla bla, too lazy to repeat above sentence, bla bla bla
            "No, nothing broke, what are you talking about?", //Reference to how I break things easily.
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
            "Lennart lennart = new Lennart();", //blatantly stolen from a splash screen
            "Has random MOTDs.", //reference to... well, this system.. I guess
            "Uses Bootstrap.", //reference to the site using a Bootstrap theme
            "OBJECTION!", //reference to Pheonix Wright
            "What is a man? A miserable little pile of secrets.", //fine I'll admit it I just stole this quote from a top ten list of game quotes
            "All your base are belong to us.", //You know what this is a reference to.
            "What are 'ya buyin, stranger?", //reference to Resident Evil 4
            "Hey! Listen!", //hint: ZELDA. That's all you need.
            "HEADSHOT!",
            "Snake? SNAAAKE!", //reference to a Metal Gear quote when Snake dies
            "Do a barrel roll!", //if you have no idea what this is from, you're a failure
            ChatColor.ITALIC + "askew" + ChatColor.GREEN, //try this in google. you'll get what this is then.
            "It's a me, Mario!", //if you don't know what this is from you're a massive failure
            "I used to be an adventurer like you, then I took an arrow to the knee.", //if you have no idea what this is, you're an ultra failure.
            "War. War never changes.", //reference to Fallout 3/2/1
            "//Made in Java", //reference to Minecraft being made in Java, and thus basically everything that uses Minecraft pretty much needs to be in Java, or re-invent the wheel.
            "Caused by: java.lang.NullPointerException", //reference to a java NullPointerException (oh how I have been burned so many times by this one error...)
            "git commit -m \"What can I break this time?\" && git push --force", //reference to a "What can I break this time"-type git commit
            "Not compatible with Minicraft.", //reference to a Ludem Dare Notch did that he called "Minicraft"
            "Uses Bukkit.getOnlinePlayers()", //reference to the Bukkit.getOnlinePlayers() function
            "for(Player p : onlinePlayers) { p.sendMessage(\"You're awesome!\"); }" //reference to a for loop sending everyone online a message saying "You're awesome!"
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
