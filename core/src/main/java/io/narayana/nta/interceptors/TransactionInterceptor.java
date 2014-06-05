package io.narayana.nta.interceptors;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import io.narayana.nta.persistence.EnitityManagerProvider;
/**
 * @author Alex Creasy / acreasy@redhat.com /
 */
public class TransactionInterceptor {

    @EJB
    EnitityManagerProvider emp;

    /**
     * 
     * @param ctx
     * @return
     * @throws Exception
     */
    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        try {
            emp.beginTransaction();
            ctx.proceed();
            emp.commitTransaction();
        } catch (Exception e) {
            emp.rollbackTransaction();
        }
        return ctx;
    }
}
