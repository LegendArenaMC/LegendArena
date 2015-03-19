/*
    Copyright 2015 Legend Arena Developers

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */

package net.thenamedev.legendapi;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created on 3/4/2015
 *
 * @author ThePixelDev
 */
public class LegendAPI extends JavaPlugin {

    public static final String apiVersion = "1.0";
    public static final String versionName = "API Liftoff";

    //think of the following as you wish.
    public static final String debugCat =
            ChatColor.BLUE +
            "                      _                        \n" +
            "                      \\`*-.                    \n" +
            "                       )  _`-.                 \n" +
            "                      .  : `. .                \n" +
            "                      : _   '  \\               \n" +
            "                      ; *` _.   `*-._          \n" +
            "                      `-.-'          `-.       \n" +
            "                        ;       `       `.     \n" +
            "                        :.       .        \\    \n" +
            "                        . \\  .   :   .-'   .   \n" +
            "                        '  `+.;  ;  '      :   \n" +
            "                        :  '  |    ;       ;-. \n" +
            "                        ; '   : :`-:     _.`* ;\n" +
            "               [bug] .*' /  .*' ; .*`- +'  `*' \n" +
            "                     `*-*   `*-*  `*-*'        \n" +
            ChatColor.RED;
    //yes that is what I do with my spare time. make debug cat ascii art. dont question it

    public static boolean debug = true;

    public void onEnable() {
        //
    }

    public void onDisable() {
        //
    }

    /**
     * Just throws a NullPointerException with the message "I FOUND THE UNDEFINED FUNCTION". That's it.
     *
     * No really, that's all this does.<br><br>
     *
     * and yes was stupidly drunk when making this
     */
    public static void undefined() {
        throw new NullPointerException("I FOUND THE UNDEFINED FUNCTION");
    }

}
