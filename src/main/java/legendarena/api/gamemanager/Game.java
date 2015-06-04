package legendarena.api.gamemanager;

import net.thenamedev.legendapi.core.exceptions.DoYouEvenKnowWhatYourDoingException;
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

    public Game(PluginManager pm) {
        this.pm = pm;
        this.p = pm.getPlugin("LegendArena");
    }

    /**
     * Register an ArrayList<> of listeners.
     * @param listeners The ArrayList to register
     */
    public void registerListeners(ArrayList<GameListener> listeners) {
        for(GameListener l : listeners)
            pm.registerEvents(l, p);
        this.listeners = listeners;
    }

    /**
     * Register an ArrayList<> of timers.
     * @param timers The ArrayList to register
     */
    public void registerTimers(ArrayList<GameTimer> timers) {
        for(GameTimer t : timers)
            tasks.put(t, Bukkit.getScheduler().runTaskTimer(p, t, t.delay(), t.delay()));
        this.timers = timers;
    }

    /**
     * Register an ArrayList<> of listeners.
     * @param listeners The ArrayList to register
     */
    public void registerListener(ArrayList<GameListener> listeners) {
        for(GameListener l : listeners)
            pm.registerEvents(l, p);
        this.listeners = listeners;
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
     * Get the ArrayList<> of players
     * @param p The players to add
     */
    public void setPlayers(ArrayList<GamePlayer> p) {
        this.players = p;
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
     * Each use of this clears the spawn location, and you can add more than one location in the single call. Just FYI.
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

    ///////////////////////////////////////////
    //                                       //
    //       INTERNAL START/STOP STUFF       //
    //                                       //
    ///////////////////////////////////////////

    /**
     * Starts the game.
     */
    public void start() {
        if(isRunning)
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

}
