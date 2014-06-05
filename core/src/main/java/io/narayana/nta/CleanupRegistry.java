package io.narayana.nta;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Alex Creasy / acreasy@redhat.com /
 */
public class CleanupRegistry {

    private Collection<CleanupCallback> register = new LinkedList<>();

    /**
     *
     * @param callBack
     * @throws NullPointerException
     */
    public void register(CleanupCallback callBack) throws NullPointerException{
        if (callBack == null)
            throw new NullPointerException();
        register.add(callBack);
    }

    /**
     *
     */
    public void cleanup() {
        for (CleanupCallback c : register) {
            c.cleanup();
            register.remove(c);
        }
    }
}
