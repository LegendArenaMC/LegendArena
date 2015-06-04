package legendarena.chat;

import legendarena.users.Rank;

/**
 * Created on 6/4/2015
 *
 * @author ThePixelDev
 */
public interface Channel {

    String channelName();

    Rank minimumRank();

}
