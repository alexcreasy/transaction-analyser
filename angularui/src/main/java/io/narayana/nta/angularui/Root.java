package io.narayana.nta.angularui;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Amila
 * Date: 07/04/14
 * Time: 22:24
 * To change this template use File | Settings | File Templates.
 */
@ApplicationPath("rest/api/v1")
public class Root extends Application
{
    HashSet<Object> singletons = new HashSet<Object>();

    public Root()
    {
        singletons.add(new TransactionAPI());
        singletons.add(new ResourceManagerAPI());
    }

    @Override
    public Set<Object> getSingletons()
    {
         return singletons;
    }

    @Override
    public Set<Class<?>> getClasses()
    {
        HashSet<Class<?>> set = new HashSet<Class<?>>();
        return set;
    }

    private void initializeCorsFilter()
    {
        //Need to find the dependency for CorsFilter;
    }
}
