/*
 * ProblemControllers.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package io.daocloud.controllers;

import io.daocloud.logic.problem.BusyLogic;
import io.daocloud.logic.problem.DeadLockLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alarm10086
 *
 */
@RestController
public class ProblemControllers {

    @Autowired
    private BusyLogic busyLogic;

    @Autowired
    private DeadLockLogic deadLockLogic;

    @RequestMapping(value = "/busy")
    public String busy() {
        busyLogic.busy();
        return "buys done";
    }

    @RequestMapping(value = "/deadlock")
    public String deadLock() {
        try {
            deadLockLogic.deadLock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "deadLock resolve";
    }
}
