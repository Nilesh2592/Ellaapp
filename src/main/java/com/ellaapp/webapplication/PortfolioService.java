/*
 * 
 */
package com.ellaapp.webapplication;

import com.ellaapp.webapplication.daofactory.BadgeDao;
import com.ellaapp.webapplication.daofactory.UserBadgeDao;
import com.ellaapp.webapplication.entities.Badge;
import com.ellaapp.webapplication.entities.UserBadge;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Nishantc
 */
@Path("/port")
public class PortfolioService {

    @GET
    @Path("/portfolio/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retriveNotifications(@PathParam("id") String facebookid) {
        UserBadgeDao userBadgeDao = new UserBadgeDao();
        BadgeDao badgeDao = new BadgeDao();
        List<UserBadge> userBadges = userBadgeDao.getUserBadgeByFacebookId(facebookid, "1");
        System.out.println("userBadges >>>>" + userBadges);
        List<Badge> badges = new ArrayList<Badge>();
        List<Long> ids = new ArrayList<Long>();
        if (userBadges != null && userBadges.size() > 0) {
            for (UserBadge userBadge : userBadges) {
                ids.add(userBadge.getBadgeId());
            }

            Calendar tenYear = Calendar.getInstance();
            tenYear.add(Calendar.YEAR, 10);
            for (Badge badge : badgeDao.retriveByIds(ids)) {
                System.out.println("badge >>>>>" + badge);
                Calendar expiryDate = Calendar.getInstance();
                badges.add(badge);
            }
        }
        return Response.status(200).entity(badges).build();
    }
}
