/*
 * 
 */
package com.ellaapp.webapplication;

import com.ellaapp.webapplication.beans.BadgeBean;
import com.ellaapp.webapplication.daofactory.BadgeDao;
import com.ellaapp.webapplication.daofactory.UserBadgeDao;
import com.ellaapp.webapplication.daofactory.UserDao;
import com.ellaapp.webapplication.entities.Badge;
import com.ellaapp.webapplication.entities.BadgeEvaltion;
import com.ellaapp.webapplication.entities.User;
import com.ellaapp.webapplication.entities.UserBadge;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Nishantc
 */
@Path("/api")
public class BadgeService {

    @GET
    @Path("/badges")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retriveBadges() {
        System.out.println("!>>>>>>>>>>>> Called... ");
        Badge badge = new Badge();
        BadgeDao badgeDao = new BadgeDao();
        Set<Badge> allBadge = new HashSet<Badge>(badgeDao.findAll());

        System.out.println("All Badge >>>>> " + allBadge);
        return Response.status(200).entity(allBadge).build();
    }

    @POST
    @Path("/earnIt")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response earnBadge(@QueryParam("badge_id") Long badge_id, @QueryParam("facebook_id") String facebook_id) {
        System.out.println(badge_id + " ==== " + facebook_id);
        UserBadge userBadge;
        UserDao userDao = new UserDao();

        Badge badge;
        BadgeDao badgeDao = new BadgeDao();
        badge = badgeDao.findById(badge_id);
        BadgeBean badgeBean = new BadgeBean(badge);

        if (facebook_id != null) {
            User user = userDao.getUserByFacebookId(facebook_id);
            if (user == null) {
                user = new User();
                user.setFacebook_id(facebook_id);
                userDao.save(user);
            }
        }

        if (facebook_id != null && badge_id != null) {
            UserBadgeDao userBadgeDao = new UserBadgeDao();
            userBadge = userBadgeDao.getUserBadgeByFacebookIdAndBagedId(facebook_id, badge_id);
            if (userBadge == null) {
                userBadge = new UserBadge();
                userBadge.setUserId(facebook_id);
                userBadge.setBadgeId(badge_id);
                userBadge.setStartDate(new Date());
                userBadge.setStatus("0");
                userBadgeDao.save(userBadge);
            }
            badgeBean.setUser_id(userBadge.getUserId());
            badgeBean.setStatus(userBadge.getStatus());
            badgeBean.setEvalCrite(userBadge.getEvalCrite());
            badgeBean.setAttechFile(userBadge.getAttechFile());
        }
        return Response.status(200).entity(badgeBean).build();
    }

    @POST
    @Path("/submitBadge")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response submitBadge(BadgeEvaltion badgeEvaltion) {
        UserBadgeDao userBadgeDao = new UserBadgeDao();
        UserBadge userBadge = null;
        if (badgeEvaltion.getFbId() != null && badgeEvaltion.getBadgeId() != null) {
            userBadge = userBadgeDao.getUserBadgeByFacebookIdAndBagedId(badgeEvaltion.getFbId(), badgeEvaltion.getBadgeId());
            if (userBadge == null) {
                userBadge = new UserBadge();
                userBadge.setUserId(badgeEvaltion.getFbId());
                userBadge.setBadgeId(badgeEvaltion.getBadgeId());
                userBadge.setStartDate(new Date());
            }

            userBadge.setEvalCrite(badgeEvaltion.getAns());
            if (badgeEvaltion.getFileName().isEmpty()) {
                userBadge.setAttechFile("");
                userBadge.setStatus("0");
            } else {
                userBadge.setAttechFile(badgeEvaltion.getFileName());
                userBadge.setStatus("1");
            }
            userBadgeDao.makePersistent(userBadge);
        }
        return Response.status(200).entity(userBadge).build();
    }
}
