/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ellaapp.webapplication.daofactory;

import com.ellaapp.webapplication.entities.User;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author nishant
 */
public class UserDao extends GenericHibernateDAO<User, Long> {

    @Override
    protected Class<User> getPersistentClass() {
        return User.class;
    }

    public User getUserByFacebookId(String fbId) {
        Criterion criterion = Restrictions.eq("facebook_id", fbId); // insted of face_id
        List<User> users = this.findByCriteria(criterion);
        if (users != null && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }
}
