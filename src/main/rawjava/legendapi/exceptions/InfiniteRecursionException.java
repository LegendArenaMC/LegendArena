package legendapi.exceptions;

public class InfiniteRecursionException extends RuntimeException {

	public InfiniteRecursionException() {
		throw new InfiniteRecursionException();
	}

}
