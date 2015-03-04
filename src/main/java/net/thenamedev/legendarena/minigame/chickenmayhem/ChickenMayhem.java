package net.thenamedev.legendarena.minigame.chickenmayhem;

import net.thenamedev.legendapi.exceptions.MistakesWereMadeException;
import net.thenamedev.legendapi.minigames.*;
import net.thenamedev.legendapi.utils.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Created on 3/2/2015
 * @author ThePixelDev
 */
public class ChickenMayhem implements Minigame {

    @NotNull
    private static ArrayList<UUID> players = new ArrayList<>();

    @Nullable
    private Info info = new Info() {
        @NotNull
        public String name() {
            return "Chicken Mayhem";
        }

        @Nullable
        public Rank minJoinRank() {
            return null;
        }

        public World getWorld() {
            return Bukkit.getWorld("chickenmayhem");
        }
    };

    @Nullable
    private MinigameActions actions = new MinigameActions() {
        private boolean enabled = true;
        private boolean running = false;

        public void joinGame(Player p) {
            //
        }

        public void quitGame(@NotNull KickInfo kick) {
            if(kick.target() == null) throw new MistakesWereMadeException("Kick target cannot be null");
            if(kick.target() == null && kick.kicker() == null) throw new MistakesWereMadeException(true, "MISTAKES WERE FUCKING MADE TODAY. (hint: target and kicker are BOTH null)");
            if(kick.isKick()) {
                if(kick.kicker() == null) throw new MistakesWereMadeException("Kicker cannot be null");
                //TODO: Broadcast kick message
            } else {
                //TODO: Add code stuff.. or something to that effect.. I guess.
            }
            //TODO: Remove player from game
        }

        public void start(int startCool) {
            if(running) return;
        }

        public void end(int endCool) {
            if(!running) return;
        }

        public void toggleStatus() {
            enabled = !enabled;
        }

        public boolean isEnabled() {
            return enabled;
        }
    };

    @Nullable
    public Info info() {
        return this.info;
    }

    @Nullable
    public MinigameActions actions() {
        return this.actions;
    }

}
