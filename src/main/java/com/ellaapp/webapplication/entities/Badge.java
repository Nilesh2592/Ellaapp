/*
 * 
 */
package com.ellaapp.webapplication.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author nilay
 */
@Entity
@Table(name = "badge_master")
public class Badge implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "badgeLevel")
    private String badgeLevel;

    @Column(name = "image")
    private String image;

    @Column(name = "issuer")
    private String issuer;

    @Column(name = "ribbonType")
    private String ribbonType;

    @Column(name = "ribbonText")
    private String ribbonText;

    @Column(name = "expier_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expDate;

    @Column(name = "competencies")
    private String competencies;

    @OneToMany(mappedBy = "badge_master", fetch = FetchType.EAGER)
    private Set<BadgeFile> badgeFiles;

    private int valid;

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

    public String getBadgeLevel() {
        return badgeLevel;
    }

    public void setBadgeLevel(String badgeLevel) {
        this.badgeLevel = badgeLevel;
    }

    public Set<BadgeFile> getBadgeFiles() {
        return badgeFiles;
    }

    public void setBadgeFiles(Set<BadgeFile> badgeFiles) {
        this.badgeFiles = badgeFiles;
    }

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

    public String getLevel() {
        return badgeLevel;
    }

    public void setLevel(String level) {
        this.badgeLevel = level;
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

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public Badge(Long id, String name, String description, String badgeLevel, String image, String issuer, String ribbonType, String ribbonText, Date expDate, Set<BadgeFile> badgeFiles) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.badgeLevel = badgeLevel;
        this.image = image;
        this.issuer = issuer;
        this.ribbonType = ribbonType;
        this.ribbonText = ribbonText;
        this.expDate = expDate;
//        this.badgeFiles = badgeFiles;
    }

    public Badge() {
    }

    @Override
    public String toString() {
        return "Badge{" + "id=" + id + ", name=" + name + ", description=" + description + ", badgeLevel=" + badgeLevel + ", image=" + image + ", issuer=" + issuer + ", ribbonType=" + ribbonType + ", ribbonText=" + ribbonText + ", expDate=" + expDate + ", competencies=" + competencies + ", badgeFiles=" + badgeFiles + ", valid=" + valid + '}';
    }
}
