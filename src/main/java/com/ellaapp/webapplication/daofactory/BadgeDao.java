/*
 * 
 */
package com.ellaapp.webapplication.daofactory;

import com.ellaapp.webapplication.entities.Badge;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author nishant
 */
public class BadgeDao extends GenericHibernateDAO<Badge, Long> {

    @Override
    protected Class<Badge> getPersistentClass() {
        return Badge.class;
    }

    public List<Badge> retriveByIds(List<Long> ids) {
        Criterion criterian = Restrictions.in("id", ids); // insted of badge_id
        List<Badge> badges = this.findByCriteria(criterian);
        return badges;
    }
}
