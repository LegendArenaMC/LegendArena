package net.thenamedev.legendarena.utils;

import java.util.Random;

/**
 * Created by thepixeldev on 4/30/15.
 */
public class BMTWordUtils {

    public static final String[] words = {
            "window", //ahh, let's open a windo- WAIT WHAT WHY ARE THERE FLIES EVERYWHERE NOW WHY OH GOSH PLEASE STOP THE MADNESS
            "penguin", //quac- oh wait no penguins don't quack
            "windows", //WHY DO YOU KEEP CLOSING MINECRAFT BECAUSE OF "LOW RAM" DAMNIT, YOU'RE ONLY USING 70% OF THE MEMORY FOR FUCKS SAKES, AND YOU'RE USING 30% YOURSELF
            "google", //omg i liek googel it tells me stuffs heeueueueue
            "twitter", //tweeter tweet
            "boat", //WHY DO YOU KEEP BREAKING WHEN I TOUCH A SINGLE BLOCK DAMNIT
            "grass block",
            "tall grass",
            "question", //i have a question: why is this a word in here
            "sheep", //baaa.
            "cow", //moo
            "pig",
            "stick",
            "admin", //admin can i has op pls i fna
            "diamond",
            "emerald", //You now have <10,000,000> emeralds!
            "button", //i think /r/thebutton is leaking
            "timer", //yep it's official /r/thebutton is leaking
            "alien", //hi! wait what are you doi- AAAHHH, MY SPLEEEEN!
            "sun", //THE SUN, IT BURNSSS </vampirejoke>
            "moon", //<howl goes here>
            "potato",
            "couch",
            "bow",
            "ruler",
            "squirrel",
            "cat", //meow
            "monster",
            "circle", //shh... we must never speak of this...
            "Half Life 3", //Where's Half Lif- oh wait sorry this joke is old, I'll leave
            "pokeball", //JOE, I CHOOSE YOU!
            "gaben" //GABEN IS OUR LOR- oh wait that joke is no longer relevant okay bye
    };

    public static String getRandomWord() {
        Random r = new Random();
        int seed = r.nextInt(words.length);
        if(seed < 5) {
            //reroll, since the seed is less than 5
            while(seed < 5) {
                seed = r.nextInt(words.length);
                if(seed >= 5)
                    break;
            }
        }
        int randomInt = r.nextInt(seed);
        return words[randomInt];
    }

}
