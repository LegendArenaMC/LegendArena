package legendarena.users;

/**
 * Created on 6/4/2015
 *
 * @author ThePixelDev
 */
public enum Rank {

    FOUNDER(new String[] { "ThePixelDev", "JadenJFilms" }),
    ADMIN("legendarena.rank.admin"),
    MOD("legendarena.rank.mod"),
    HELPER("legendarena.rank.helper"),
    YOUTUBE("legendarena.rank.yt"),
    MEMBERPLUS("legendarena.rank.memberplus"),
    MEMBER("legendarena.rank.member");

    private String permission;
    private String[] users;

    Rank(String permission) {
        this.permission = permission;
        this.users = null;
    }

    Rank(String[] users) {
        this.users = users;
        this.permission = null;
    }

}
