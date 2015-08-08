package legendapi.exceptions;

public class FailedKotlinException extends RuntimeException {

    public FailedKotlinException() {
        super("Failed to load Kotlin - are you sure the Kotlin plugin is enabled?");
    }

}
