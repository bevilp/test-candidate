package com.test.candidate.service.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * CandidateForm
 *
 * @author Ben
 * @since 26/09/2015
 */
public class CandidateForm {

    @NotNull
    @Size(min = 1, max = 30)
    private String name;

    private boolean enabled;

    public CandidateForm() {
    }

    public CandidateForm(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "CandidateForm{" +
                "name='" + name + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
