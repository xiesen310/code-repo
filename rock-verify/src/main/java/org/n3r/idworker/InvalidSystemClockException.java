package org.n3r.idworker;

/**
 * @author 谢森
 */
public class InvalidSystemClockException extends RuntimeException {
    public InvalidSystemClockException(String message) {
        super(message);
    }
}
