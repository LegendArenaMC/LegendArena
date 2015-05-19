package net.thenamedev.legendapi.gamemanager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created on 5/16/2015
 *
 * @author ThePixelDev
 */
public class Game {

    private final Plugin p;
    private final PluginManager pm;

    private ArrayList<GameListener> listeners;
    private ArrayList<GameTimer> timers;
    private HashMap<GameTimer, BukkitTask> tasks = new HashMap<>();

    public Game(PluginManager pm) {
        this.pm = pm;
        this.p = pm.getPlugin("LegendArena");
    }

    public void registerListeners(ArrayList<GameListener> listeners) {
        for(GameListener l : listeners)
            pm.registerEvents(l, p);
        this.listeners = listeners;
    }

    public void registerTimers(ArrayList<GameTimer> timers) {
        for(GameTimer t : timers)
            tasks.put(t, Bukkit.getScheduler().runTaskTimer(p, t, t.delay(), t.delay()));
        this.timers = timers;
    }

    public void registerListener(ArrayList<GameListener> listeners) {
        for(GameListener l : listeners)
            pm.registerEvents(l, p);
        this.listeners = listeners;
    }

    public void registerTimer(GameTimer t) {
            tasks.put(t, Bukkit.getScheduler().runTaskTimer(p, t, t.delay(), t.delay()));
        if(timers == null)
            timers = new ArrayList<>();
        timers.add(t);
    }

    public ArrayList<GameTimer> getTimers() {
        return timers;
    }

    public ArrayList<GameListener> getListeners() {
        return listeners;
    }

    public void unregisterTimer(GameTimer timer) {
        if(!timers.contains(timer) || !tasks.containsKey(timer))
            return; //sanity check
        tasks.get(timer).cancel();
        tasks.remove(timer);
    }

}
