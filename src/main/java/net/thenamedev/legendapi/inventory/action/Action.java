package net.thenamedev.legendapi.inventory.action;

/**
 * @author TheNameMan
 */
public interface Action {

    String itemName();

    boolean useContains();

    void whenClicked();

}
