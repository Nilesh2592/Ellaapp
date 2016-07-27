/*
 * 
 */
package com.ellaapp.webapplication.beans;

import com.ellaapp.webapplication.entities.Badge;
import com.ellaapp.webapplication.entities.BadgeFile;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author nilay
 */
public class BadgeBean {

    private Long id;
    private String name;
    private String description;
    private String badgeLevel;
    private String image;
    private String issuer;
    private String ribbonType;
    private String ribbonText;
    private Date expDate;
    private String competencies;
    private Set<BadgeFile> badgeFiles;
    private String user_id;
    private String status;
    private String evalCrite;
    private String attechFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBadgeLevel() {
        return badgeLevel;
    }

    public void setBadgeLevel(String badgeLevel) {
        this.badgeLevel = badgeLevel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getRibbonType() {
        return ribbonType;
    }

    public void setRibbonType(String ribbonType) {
        this.ribbonType = ribbonType;
    }

    public String getRibbonText() {
        return ribbonText;
    }

    public void setRibbonText(String ribbonText) {
        this.ribbonText = ribbonText;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getCompetencies() {
        return competencies;
    }

    public void setCompetencies(String competencies) {
        this.competencies = competencies;
    }

    public Set<BadgeFile> getBadgeFiles() {
        return badgeFiles;
    }

    public void setBadgeFiles(Set<BadgeFile> badgeFiles) {
        this.badgeFiles = badgeFiles;
    }

    public BadgeBean() {
    }

    public BadgeBean(Badge badge) {
        this.id = badge.getId();
        this.name = badge.getName();
        this.description = badge.getDescription();
        this.badgeLevel = badge.getBadgeLevel();
        this.image = badge.getImage();
        this.issuer = badge.getIssuer();
        this.ribbonType = badge.getRibbonType();
        this.ribbonText = badge.getRibbonText();
        this.expDate = badge.getExpDate();
        this.competencies = badge.getCompetencies();
        this.badgeFiles = new HashSet<BadgeFile>();
        this.badgeFiles.addAll(badge.getBadgeFiles());
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEvalCrite() {
        return evalCrite;
    }

    public void setEvalCrite(String evalCrite) {
        this.evalCrite = evalCrite;
    }

    public String getAttechFile() {
        return attechFile;
    }

    public void setAttechFile(String attechFile) {
        this.attechFile = attechFile;
    }

}
