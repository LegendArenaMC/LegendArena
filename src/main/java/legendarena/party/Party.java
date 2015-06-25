package legendarena.party;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created on 6/24/2015
 *
 * @author ThePixelDev
 */
public class Party {

    private ArrayList<UUID> party = new ArrayList<>();
    private int id;

    public Party(int id, Player... players) {
        this.id = id;
        for(Player p : players)
            party.add(p.getUniqueId());
    }

    public int getId() {
        return id;
    }

    public ArrayList<UUID> getPlayers() {
        return party;
    }

    public void add(Player p) {
        party.add(p.getUniqueId());
    }

    public void remove(Player p) {
        party.remove(p.getUniqueId());
    }

}
