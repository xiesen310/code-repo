package org.n3r.idworker;

/**
 * @author 谢森
 */
public interface RandomCodeStrategy {
    /**
     * init
     */
    void init();

    /**
     * prefix
     * @return int
     */
    int prefix();

    /**
     * next
     * @return int
     */
    int next();

    /**
     * release
     */
    void release();
}
