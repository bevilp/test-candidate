package com.test.candidate.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by oleg on 09/08/15.
 */
@Entity
public class Candidate implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue
    private int id;

    @Column(length = 30)
    private String name;

    private boolean enabled;

    Candidate() {
    }

    public Candidate(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

