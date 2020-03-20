package org.n3r.idworker;

/**
 * @author 谢森
 */
public interface WorkerIdStrategy {
    /**
     * initialize
     */
    void initialize();

    /**
     * availableWorkerId
     *
     * @return long
     */
    long availableWorkerId();

    /**
     * release
     */
    void release();
}
