package legendarena.chat

import legendarena.api.utils.Rank

enum class Channel {

    ADMIN(Rank.ADMIN),
    DEV(Rank.DEV),
    ALERT(Rank.HELPER),
    STAFF(Rank.HELPER),
    GLOBAL();

    private var rank: Rank

    private constructor(rank: Rank) {
        this.rank = rank
    }

    private constructor() {
        rank = Rank.MEMBER
    }

    fun getRank(): Rank {
        return rank
    }

}