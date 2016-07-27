/*
 * 
 */
package com.ellaapp.webapplication.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author nilay
 */
@Entity
@Table(name = "user_badge")
public class UserBadge implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "badge_id")
    private Long badgeId;

    @Column(name = "status")
    private String status;

    @Column(name = "eva_crite")
    private String evalCrite;

    @Column(name = "attech_file")
    private String attechFile;

    @Column(name = "start_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    
    private int valid;
    
    public UserBadge() {
    }

    public UserBadge(Long id, String userId, Long badgeId, String status, String evalCrite, String attechFile, Date startDate) {
        this.id = id;
        this.userId = userId;
        this.badgeId = badgeId;
        this.status = status;
        this.evalCrite = evalCrite;
        this.attechFile = attechFile;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Long badgeId) {
        this.badgeId = badgeId;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    @Override
    public String toString() {
        return "UserBadge{" + "id=" + id + ", userId=" + userId + ", badgeId=" + badgeId + ", status=" + status + ", evalCrite=" + evalCrite + ", attechFile=" + attechFile + '}';
    }
}
