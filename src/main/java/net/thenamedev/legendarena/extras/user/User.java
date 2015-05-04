package net.thenamedev.legendarena.extras.user;

import java.util.UUID;

/**
 * @author ThePixelDev
 */
public interface User {
	
	UUID uuid();

    /**
     * This is cached and may be up to 30 minutes old. If you require the latest amount, use the EmeraldsCore `getEmeralds` function.
     */
    int emeraldCount();
	
}
