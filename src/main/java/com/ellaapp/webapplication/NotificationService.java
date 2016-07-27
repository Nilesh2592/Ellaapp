/*
 *
 */
package com.ellaapp.webapplication;

import com.ellaapp.webapplication.daofactory.BadgeDao;
import com.ellaapp.webapplication.daofactory.UserBadgeDao;
import com.ellaapp.webapplication.entities.Badge;
import com.ellaapp.webapplication.entities.UserBadge;
import java.util.ArrayList;
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
@Path("/notf")
public class NotificationService {

    @GET
    @Path("/notification/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retriveNotifications(@PathParam("id") String facebookid) {
        UserBadgeDao userBadgeDao = new UserBadgeDao();
        BadgeDao badgeDao = new BadgeDao();
        List<UserBadge> userBadges = userBadgeDao.getUserBadgeByFacebookId(facebookid, "0");
        List<Long> ids = new ArrayList<Long>();
        if (userBadges != null && userBadges.size() > 0) {
            for (UserBadge userBadge : userBadges) {
                ids.add(userBadge.getBadgeId());
            }
        }
        List<Badge> badges = null;
        if (ids.isEmpty()) {
            badges = new ArrayList<Badge>();
        } else {
            badges = badgeDao.retriveByIds(ids);
        }
        return Response.status(200).entity(badges).build();
    }
}
