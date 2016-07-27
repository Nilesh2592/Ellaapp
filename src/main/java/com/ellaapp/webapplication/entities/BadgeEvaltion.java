/*
 * 
 */
package com.ellaapp.webapplication.entities;

/**
 *
 * @author Nishantc
 */
public class BadgeEvaltion {

    private Long badgeId;
    private String fbId;
    private String ans;
    private String fileName;

    public BadgeEvaltion(long badgeId, String fbId, String ans, String fileName) {
        this.badgeId = badgeId;
        this.fbId = fbId;
        this.ans = ans;
        this.fileName = fileName;
    }

    public BadgeEvaltion() {
    }

    public Long getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Long badgeId) {
        this.badgeId = badgeId;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
