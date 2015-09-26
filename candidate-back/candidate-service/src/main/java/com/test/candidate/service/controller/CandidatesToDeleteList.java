package com.test.candidate.service.controller;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * candidate
 *
 * @author Ben
 * @since 26/09/2015
 */
public class CandidatesToDeleteList {

    @NotNull
    private List<Integer> ids;

    public CandidatesToDeleteList() {
    }

    public CandidatesToDeleteList(List<Integer> ids) {
        this.ids = ids;
    }

    public List<Integer> getIds() {
        return ids;
    }
}
