package org.jboss.narayana.txvis.plugins;

import org.jboss.narayana.txvis.persistence.DataAccessObject;
import org.jboss.narayana.txvis.persistence.entities.Transaction;
import org.jboss.narayana.txvis.persistence.enums.Status;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Alex Creasy &lt;a.r.creasy@newcastle.ac.uk$gt;
 * Date: 10/08/2013
 * Time: 18:39
 */
public class TimeoutPlugin implements Plugin {

    // Percentage of total transactions timing out which will trigger an alert
    public static final float THRESHOLD = 5f;

    public static final String TITLE = "High Level of Timeouts";

    public static final String BODY =
            "A significant number of transactions in your system are timing out (>" + THRESHOLD + "%)." +
            "<br/>To see more information about these transactions you can use the filter option and select TIMEDOUT." +
            "<br/>To fix this you may need to modify your application logic so as the transactional workload does not" +
            " take so long, alternatively you can change your server configuration to increase the default timeout from the" +
            " current value of " + System.getProperty("ARJ_DEFAULT_TIMEOUT", "300") + " seconds.";

    private Set<Issue> issues = new HashSet<>();

    private DataAccessObject dao;

    @Override
    public void setup() {    }

    @Override
    public void tearDown() {    }

    @Override
    public Set<Issue> getIssues() {
        return Collections.unmodifiableSet(issues);
    }

    @Override
    public void findIssues() {
        final Collection<Transaction> allTimeOut = dao.findAllTopLevelTransactionsWithStatus(Status.TIMEDOUT);

        final double totalTimeOut = (double) allTimeOut.size();
        final double totalTx = (double) dao.findAllTopLevelTransactions().size();

        if (totalTx != 0 && totalTimeOut != 0) {
            // issue != null check ensures we don't overwrite the issue if it already exists as
            // the web framework may have already parsed this plugin
            if ((totalTimeOut / totalTx * 100f > THRESHOLD)) {
                final Issue issue = new Issue();
                issue.setTitle(TITLE);
                issue.setBody(BODY);
                issue.setCause(allTimeOut.iterator().next());
                issue.addTag("Transaction");
                issue.addTag("Timeout");
                issues.add(issue);
            }
        }
    }

    @Override
    public void injectDAO(DataAccessObject dao) {
        this.dao = dao;
    }
}
