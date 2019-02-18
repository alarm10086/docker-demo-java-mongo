/*
 * BusyLogic.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package io.daocloud.logic.problem;

import org.springframework.stereotype.Service;

/**
 * @author alarm10086
 *
 */
@Service
public class BusyLogic {
    private static long FIVE_MINUTES = 5 * 60 * 1000;

    /**
     * 死循环 5 分钟
     */
    public void busy() {
        long maxFailureTime = System.currentTimeMillis() + FIVE_MINUTES;
        String tmp = "a";
        while (System.currentTimeMillis() < maxFailureTime) {
            System.out.println("CurrentTimeMillis: " + System.currentTimeMillis());
        }
    }
}
