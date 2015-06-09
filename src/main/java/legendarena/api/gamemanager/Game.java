package legendarena.api.gamemanager;

import legendarena.api.exceptions.DoYouEvenKnowWhatYourDoingException;
import legendarena.api.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Game instance. Use <code>GameManager.getNewInstance()</code> to get an instance.
 *
 * @author ThePixelDev
 */
public class Game {

    private boolean singleSpawn = true;
    private boolean isRunning = false;
    private final Plugin p;
    private final PluginManager pm;

    private ArrayList<GameListener> listeners;
    private ArrayList<GameTimer> timers;
    private ArrayList<GamePlayer> players;
    private HashMap<GameTimer, BukkitTask> tasks = new HashMap<>();
    private ArrayList<Location> spawnLocations = new ArrayList<>();
    private int autostart = 10;
    private int maxPlayers = 20;
    private GameTimer autoStartTimer;

    public Game(PluginManager pm) {
        this.pm = pm;
        this.p = pm.getPlugin("LegendArena");
    }

    /**
     * Register listeners in bulk.
     * @param listeners The listeners to register
     */
    public void registerListeners(GameListener... listeners) {
        for(GameListener l : listeners)
            registerListener(l);
    }

    /**
     * Register a single listener.
     * @param listener The listener to register
     */
    public void registerListener(GameListener listener) {
        pm.registerEvents(listener, p);
        if(this.listeners == null)
            this.listeners = new ArrayList<>();
        this.listeners.add(listener);
    }

    /**
     * The timer to register.
     * @param t The timer
     */
    public void registerTimer(GameTimer t) {
            tasks.put(t, Bukkit.getScheduler().runTaskTimer(p, t, t.delay(), t.delay()));
        if(timers == null)
            timers = new ArrayList<>();
        timers.add(t);
    }

    /**
     * Bulk register timers.
     * @param t The timers to register
     */
    public void registerTimers(GameTimer... t) {
        for(GameTimer t1 : t) {
            registerTimer(t1);
        }
    }

    /**
     * Get the ArrayList<> of timers
     * @return The timers
     */
    public ArrayList<GameTimer> getTimers() {
        return timers;
    }
    /**
     * Get the ArrayList<> of listeners
     * @return The timers
     */
    public ArrayList<GameListener> getListeners() {
        return listeners;
    }

    /**
     * Get the ArrayList<> of spawn locations
     * @return The spawn locations
     */
    public ArrayList<Location> getSpawnLocations() {
        return spawnLocations;
    }

    /**
     * The players to add to the game
     * @param players The players to add
     */
    public void addPlayers(GamePlayer... players) {
        if(this.players == null)
            this.players = new ArrayList<>();
        if(players.length == 1) {
            this.players.add(players[0]);
            return;
        }
        Collections.addAll(this.players, players);
    }

    /**
     * The players to remove from the game
     * @param players The players to remove
     */
    public void removePlayers(GamePlayer... players) {
        if(this.players == null)
            this.players = new ArrayList<>();
        if(players.length == 1) {
            this.players.add(players[0]);
            return;
        }
        for(GamePlayer p : players) {
            if(!this.players.contains(p)) continue;
            this.players.remove(p);
        }
    }

    /**
     * Get the ArrayList<> of players in the game
     * @return The timers
     */
    public ArrayList<GamePlayer> getPlayers() {
        return players;
    }

    /**
     * Unregisters a timer
     */
    public void unregisterTimer(GameTimer timer) {
        if(!timers.contains(timer) || !tasks.containsKey(timer))
            return; //sanity check
        tasks.get(timer).cancel();
        tasks.remove(timer);
    }

    /**
     * Set the spawn location(s)<br><br>
     *
     * Each use of this clears the spawn locations already set, and you can add more than one location in the single call. Just FYI.
     * @param l The locations to set
     */
    public void setSpawn(Location... l) {
        if(l.length > 1) {
            //there's more than one spawn so set the internal flag to false
            singleSpawn = false;
        } else {
            //there's one spawn so set the internal flag to true
            if(!singleSpawn) singleSpawn = true; //the if() is so we don't constantly set singleSpawn to true
        }

        spawnLocations.clear(); //clear the spawn location list
        Collections.addAll(spawnLocations, l); //add the spawn locations
    }

    /**
     * Set the player count to autostart the game at.<br><br>
     *
     * If not set, this function isn't used.
     * @param players Amount of players to auto-start at
     */
    public void setAutoStartCount(final int players) {
        if(players <= 1)
            throw new DoYouEvenKnowWhatYourDoingException("You must be drunk. (you can't have the game autostart at /one/ player or lower)");
        if(autoStartTimer != null) unregisterTimer(autoStartTimer);
        autoStartTimer = new GameTimer() {
            @Override
            public void run() {
                if(getPlayers().size() >= players) {
                    start();
                    unregisterTimer(this);
                }
            }

            @Override
            public int delay() {
                return 10;
            }
        };
        registerTimer(autoStartTimer);
    }

    public void setMaxPlayerCount(int max) {
        maxPlayers = max;
        if(getPlayers().size() > max) {
            //start removing players from the game due to new max size
            for(GamePlayer p : getPlayers()) {
                p.getPlayer().sendMessage(ChatUtils.Messages.errorMsg + "Due to a new size limit on the minigame, you have been removed from the minigame.");
                removePlayers(p);
                if(getPlayers().size() <= max)
                    break; //we've either (somehow) gotten below our max or to the max, so stop kicking players
            }
        }
    }

    ///////////////////////////////////////////
    //                                       //
    //       INTERNAL START/STOP STUFF       //
    //                                       //
    ///////////////////////////////////////////

    /**
     * Starts the game.
     */
    public void start() {
        if(!sanity())
            throw new DoYouEvenKnowWhatYourDoingException();
        isRunning = true;
        for(GamePlayer p : getPlayers()) {
            if(!p.getPlayer().isOnline()) {
                removePlayers(p);
                continue;
            }
            if(!singleSpawn) {
                //multiple spawn points, so use a randomizer of sorts
                Random r = new Random(); //ah randomizers... the only thing I hate more than iNub (if you don't get that reference don't worry about it)
            }
        }
    }

    /**
     * Starts the game, overriding sanity checks.
     * @param overrideSanity Whether we should check sanity or not (use with caution, please!)
     */
    public void start(boolean overrideSanity) {
        if(!overrideSanity)
            if(!sanity())
                throw new DoYouEvenKnowWhatYourDoingException();
        isRunning = true;
        for(GamePlayer p : getPlayers()) {
            if(!p.getPlayer().isOnline()) {
                removePlayers(p);
                continue;
            }
            if(!singleSpawn) {
                //multiple spawn points, so use a randomizer of sorts
                Random r = new Random(); //ah randomizers... the only thing I hate more than iNub (if you don't get that reference don't worry about it)
            }
        }
    }

    /**
     * Ends the game.
     */
    public void stop() {
        if(!isRunning)
            throw new MinigameNotRunningException();
        isRunning = false;
        for(GamePlayer p : getPlayers()) {
            if(!p.getPlayer().isOnline()) {
                continue;
            }
            removePlayers(p);
        }
    }

    ///////////////////////////////////////////
    //                                       //
    //        SANITY CHECK (..THINGS)        //
    //                                       //
    ///////////////////////////////////////////

    /**
     * Does the current setup for the game fail sanity checks? (false if fails, true if passed)
     */
    private boolean sanity() {
        if(isRunning) return false;
        if(listeners == null) return false;
        if(timers == null) return false;
        if(players == null) return false; //if this is ever hit, this is a mega "are you fucking drunk" error
        if(getPlayers().size() > maxPlayers) return false; //this is probably due to the game manager being drunk if it hits this
        return true;
    }

}
