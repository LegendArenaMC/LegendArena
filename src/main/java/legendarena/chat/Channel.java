/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.chat;

import legendapi.utils.Rank;

public enum Channel {

    ADMIN(Rank.ADMIN),
    DEV(Rank.DEV),
    ALERT(Rank.HELPER),
    STAFF(Rank.HELPER),
    GLOBAL();

    private Rank rankRequired = null;

    Channel(Rank rank) {
        this.rankRequired = rank;
    }

    Channel() {
        rankRequired = Rank.MEMBER;
    }

    public Rank getRank() {
        return rankRequired;
    }

}
