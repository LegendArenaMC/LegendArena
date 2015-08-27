package legendarena.api.exceptions;

public class FailedKotlinException extends RuntimeException {

    public FailedKotlinException() {
        super("Failed to load Kotlin - are you sure the KotlinLoader plugin is installed and enabled?");
    }

}
