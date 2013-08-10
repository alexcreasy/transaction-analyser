package org.jboss.narayana.txvis.logparsing.as8.handlers;

import java.util.regex.Matcher;

/**
 * @Author Alex Creasy &lt;a.r.creasy@newcastle.ac.uk$gt;
 * Date: 10/08/2013
 * Time: 17:25
 */
public class TimeoutHandler extends JbossAS8AbstractHandler {


    private static final String REGEX = "TransactionReaper::doCancellations.*?successfully\\scanceled\\sTX\\s" + PATTERN_TXUID;

    public TimeoutHandler() {
        super(REGEX);
    }

    @Override
    public void handle(Matcher matcher, String line) {
        service.txTimedOut(matcher.group(TXUID), parseTimestamp(matcher.group(TIMESTAMP)));
    }
}
