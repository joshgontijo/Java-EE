/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.externaldb;

import com.sample.multiple.datasources.Users;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

/**
 *
 * @author iFood
 */
@RequestScoped
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class BMT {

    @Inject
    @CustomDatabase
    private EntityManager em;

    @Inject
    InMemoryDbProperties properties;

    public List<Users> fetchUsersFromDynamicDatasource() {

        Query query = em.createNativeQuery(properties.findProp("query").toString(), Users.class);
        return query.getResultList();
    }
    private static final Logger LOG = Logger.getLogger(BMT.class.getName());

}
