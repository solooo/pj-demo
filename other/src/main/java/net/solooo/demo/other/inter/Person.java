package net.solooo.demo.other.inter;

/**
 * Created by Eric on 2017/7/1.
 */
public interface Person {
    default int getId() {
        return 0;
    }
}
