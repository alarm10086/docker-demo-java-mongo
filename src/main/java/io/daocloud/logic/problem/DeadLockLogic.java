/*
 * DeadLockLogic.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package io.daocloud.logic.problem;

import io.daocloud.domains.problem.DeadLockSample;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author alarm10086
 *
 */
@Service
public class DeadLockLogic {
    private static long ONE_MINUTE = 1;

    /**
     * 可能触发死锁，死锁最大保持时间 1 分钟
     */
    public void deadLock() throws InterruptedException {
        Lock lockA = new ReentrantLock();
        Lock lockB = new ReentrantLock();
        String currentJettyThreadName = Thread.currentThread().getName();

        DeadLockSample t1 = new DeadLockSample(currentJettyThreadName + "_Thread1",
                lockA, lockB, ONE_MINUTE);
        DeadLockSample t2 = new DeadLockSample(currentJettyThreadName + "_Thread2",
                lockB, lockA, ONE_MINUTE);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
