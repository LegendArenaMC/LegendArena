/*
 * Copyright (C) 2014 Lord_Ralex
 *
 * This file is a part of FishbansAPI
 *
 * FishbansAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FishbansAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FishbansAPI.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.ae97.fishbansapi;

import net.ae97.fishbansapi.list.*;
import org.apache.commons.lang3.*;

import java.util.*;

/**
 * This is the ban record for a user. It contains the list of bans this user
 * (determined by UUID or username) in no particular order.
 *
 * @since 1.0
 *
 * @author Lord_Ralex
 */
public class FishbansPlayer {

    private final ImmutableArrayList<Ban> banlist;
    private final EnumMap<BanService, List<Ban>> counts = new EnumMap<>(BanService.class);
    private final String playerName;
    private final UUID playerUUID;

    protected FishbansPlayer(List<Ban> banlist, String name, UUID uuid) {
        this.banlist = new ImmutableArrayList<>(banlist);
        for (BanService service : BanService.values()) {
            counts.put(service, new LinkedList<Ban>());
        }
        for (Ban ban : this.banlist) {
            if (ban.getService() != null) {
                counts.get(ban.getService()).add(ban);
            }
        }
        for (BanService service : BanService.values()) {
            counts.put(service, new ImmutableArrayList<Ban>(counts.remove(service)));
        }
        this.playerName = name;
        this.playerUUID = uuid;
    }

    /**
     * Get the list of {@link Ban}s this particular player has. This is never
     * null.
     *
     * @return List of Bans for this player, never null.
     */
    public List<Ban> getBanList() {
        return banlist;
    }

    /**
     * Get the list of {@link Ban}s this particular player has from a given
     * {@link BanService}. This is never null.
     *
     * @param service Service to retrieve bans from
     *
     * @return List of Bans from that service, never null
     */
    public List<Ban> getBanList(BanService service) {
        return counts.get(service);
    }

    /**
     * Gets the number of bans this player has
     *
     * @return Number of bans
     */
    public int getBanCount() {
        return banlist.size();
    }

    /**
     * Get the number of {@link Ban}s this particular player has from a given
     * {@link BanService}
     *
     * @param service Service to retrieve ban count from
     *
     * @return Number of bans
     */
    public int getBanCount(BanService service) {
        return counts.get(service).size();
    }

    /**
     * Returns the {@link UUID} for this player
     *
     * @return {@link UUID} for this player
     */
    public UUID getUUID() {
        return playerUUID;
    }

    /**
     * Returns the username for this player
     *
     * @return Username for this player
     */
    public String getName() {
        return playerName;
    }

    @Override
    public String toString() {
        return "FishbanPlayer{name=" + playerName + ", uuid=" + playerUUID.toString() + ", banlist={" + StringUtils.join(banlist, ", ") + "}";
    }
}
