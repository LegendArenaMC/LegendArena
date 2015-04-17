package net.thenamedev.legendapi.bulkcmd;

/**
 * Created on 4/16/2015
 *
 * @author ThePixelDev
 */
public class BulkCommandExec {

    public enum Operators {
        AND("&&"),
        OR("||");

        private String runChars;

        Operators(String runChars) {
            this.runChars = runChars;
        }

        public String getRunChars() {
            return runChars;
        }
    }

}
