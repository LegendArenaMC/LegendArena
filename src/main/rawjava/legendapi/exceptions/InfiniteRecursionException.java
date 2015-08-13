package legendapi.exceptions;

public class InfiniteRecursionException extends RuntimeException {

	public InfiniteRecursionException() {
		throw new InfiniteRecursionException(); //OH GOD WHAT HAVE I DONE
	}

}
