/*
 * 
 */
package com.ellaapp.webapplication.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author nilay
 */
@Entity
@Table(name = "user_master")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "face_id")
    private String facebook_id;

    public User() {
    }

    public User(Long id, String facebook_id) {
        this.id = id;
        this.facebook_id = facebook_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", facebook_id=" + facebook_id + '}';
    }

}
