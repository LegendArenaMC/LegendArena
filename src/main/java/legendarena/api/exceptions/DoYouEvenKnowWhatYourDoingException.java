package legendarena.api.exceptions;

/**
 * The spelling error on "WhatYourDoing" is intentional, by the way.<br><br>
 *
 * And for the grammar nazis, it's really "WhatYoureDoing" that I was gonna use but that seemed derpy, due to no apostrohpe (or however you spell it, I regret disabling IntelliJ's spell check now).
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
