package net.thenamedev.legendapi.core.exceptions;

/**
 * The spelling error on "WhatYourDoing" is intentional, by the way.
 *
 * And for the grammar nazis, it's really "WhatYoureDoing" that I was gonna use but that seemed derpy, due to no apostrohpe (or however you spell it, I regret disabling IntelliJ's spell check now).
 *
 * Created on 4/3/2015
 *
 * @author ThePixelDev
 */
public class DoYouEvenKnowWhatYourDoingException extends RuntimeException {
    public DoYouEvenKnowWhatYourDoingException(String s) {
        super(s);
    }

    public DoYouEvenKnowWhatYourDoingException() {
        super();
    }
}
