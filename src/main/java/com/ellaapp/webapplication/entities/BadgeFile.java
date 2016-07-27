/*
 * 
 */
package com.ellaapp.webapplication.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author nilay
 */
@Entity
@Table(name = "badge_file")
public class BadgeFile implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "file_type")
    private String fileType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_id", nullable = false)
    private Badge badge_master;

    @JsonIgnore
    public Badge getBadge_master() {
        return badge_master;
    }

    @JsonIgnore
    public void setBadge_master(Badge badge_master) {
        this.badge_master = badge_master;
    }

    public BadgeFile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public BadgeFile(Long id, String url, String fileType, Badge badge_master) {
        this.id = id;
        this.url = url;
        this.fileType = fileType;
//        this.badge_master = badge_master;
    }

    @Override
    public String toString() {
        return "BadgeFile{" + "id=" + id + ", url=" + url + ", fileType=" + fileType + '}';
    }

}
