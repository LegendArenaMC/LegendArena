package legendarena.api.exceptions;

public class FailedKotlinException extends RuntimeException {

    public FailedKotlinException() {
        super("Failed to load Kotlin - are you sure the LegendFrame plugin is installed and enabled?");
    }

}
