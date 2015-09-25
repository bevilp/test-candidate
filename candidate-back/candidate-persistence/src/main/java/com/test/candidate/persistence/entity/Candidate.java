package com.test.candidate.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by oleg on 09/08/15.
 */
@Entity
public class Candidate implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Size(max = 30)
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
}

