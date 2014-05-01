package io.narayana.nta.angularui;

import io.narayana.nta.persistence.DataAccessObject;
import io.narayana.nta.persistence.entities.Transaction;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Amila
 * Date: 07/04/14
 * Time: 22:39
 * To change this template use File | Settings | File Templates.
 */
@Path("/transaction")
@Stateless
public class TransactionAPI
{
    @EJB
    DataAccessObject dao;

    @GET
    @Produces("application/json")
    public Response getTransactions()
    {
        Collection<Transaction> transactions = dao.findAllTransactions();

        return Response.ok(transactions).build();
    }

   /* @GET
    @Produces("application/json")
    public Response getTransactionById(Long id)
    {
        Transaction transaction = dao.findTransaction(id);

        return Response.ok(transaction).build();
    }*/
}
