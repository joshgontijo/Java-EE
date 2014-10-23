/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.externaldb;

import com.sample.multiple.datasources.Users;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * 
 * @author iFood
 */
@RequestScoped
public class BMT {

    @Inject
    @CustomDatabase
    private EntityManager em;
    
    public List<Users> fetchUsersFromDynamicDatasource(){
        Query query = em.createNativeQuery("SELECT * FROM secundary.sec_users", Users.class);
        return query.getResultList();
    }
    
}
