/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ellaapp.webapplication.daofactory;

import com.ellaapp.webapplication.entities.UserBadge;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author nishant
 */
public class UserBadgeDao extends GenericHibernateDAO<UserBadge, Long> {

    @Override
    protected Class<UserBadge> getPersistentClass() {
        return UserBadge.class;
    }

    public List<UserBadge> getUserBadgeByFacebookId(String fbId, String status) {
        List<Criterion> criterions = new ArrayList<Criterion>();
        criterions.add(Restrictions.eq("userId", fbId));  // insted of face_id
        criterions.add(Restrictions.eq("status", status));
        List<UserBadge> userBadges = this.findByCriteria(criterions.get(0), criterions.get(1));
        return userBadges;
    }

    public UserBadge getUserBadgeByFacebookIdAndBagedId(String fbId, Long badgeId) {
        UserBadge userBadge = null;
        List<Criterion> criterions = new ArrayList<Criterion>();
        criterions.add(Restrictions.eq("userId", fbId));  // insted of face_id
        criterions.add(Restrictions.eq("badgeId", badgeId));
        List<UserBadge> userBadges = this.findByCriteria(criterions.get(0), criterions.get(1));
        if (!userBadges.isEmpty()) {
            userBadge = userBadges.get(0);
        }
        return userBadge;
    }
}
