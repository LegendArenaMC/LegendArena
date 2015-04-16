package net.thenamedev.legendapi.exceptions;

/**
 * The spelling error on "WhatYourDoing" is intentional, by the way.
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
