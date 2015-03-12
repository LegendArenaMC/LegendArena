package net.thenamedev.legendarena.minigame.buildmything;

import net.thenamedev.legendapi.exceptions.MistakesWereMadeException;
import net.thenamedev.legendapi.minigames.KickInfo;
import net.thenamedev.legendapi.minigames.Minigame;
import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

//import MinigameUtils;

/**
 * Created on 3/4/2015
 *
 * @author ThePixelDev
 */
public class BuildMyThing implements Minigame {

    private Info info = new Info() {
        public String name() {
            return "Build My Thing";
        }

        public Rank minJoinRank() {
            return null;
        }

        public World getWorld() {
            return Bukkit.getWorld("buildmything");
        }

        public ArrayList<UUID> playersInGame() {
            return null;
        }
    };

    private MinigameActions actions = new MinigameActions() {
        private boolean enabled = true;
        private boolean running = false;

        public void joinGame(Player p) {
            //
        }

        public void quitGame(KickInfo kick) {
            if(kick.target() == null) throw new MistakesWereMadeException("Kick target cannot be null");
            if(kick.isKick()) {
                if(kick.kicker() == null) throw new MistakesWereMadeException("Kicker cannot be null");
                //TODO: Broadcast kick message
            }
            //MinigameUtils;
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

    public Info info() {
        return null;
    }

    public MinigameActions actions() {
        return null;
    }

}
