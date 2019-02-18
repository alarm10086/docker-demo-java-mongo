/*
 * DeadLockSample.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package io.daocloud.domains.problem;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author alarm10086
 *
 */
public class DeadLockSample extends Thread {
    private Lock firstLock;
    private Lock secondLock;
    private long holdLockMaxTime;

    public DeadLockSample(String name, Lock firstLock, Lock secondLock, long holdLockMaxTime) {
        super(name);
        this.firstLock = firstLock;
        this.secondLock = secondLock;
        this.holdLockMaxTime = holdLockMaxTime;
    }

    @Override
    public void run() {
        try {
            firstLock.tryLock(holdLockMaxTime, TimeUnit.MINUTES);
            System.out.println(this.getName() + " obtained: firstLock");
            try {
                Thread.sleep(1000L);
                try {
                    secondLock.tryLock(holdLockMaxTime, TimeUnit.MINUTES);
                    System.out.println(this.getName() + " obtained: secondLock");
                } catch (InterruptedException e) {
                    // Do nothing
                } finally {
                    secondLock.unlock();
                }
            } catch (InterruptedException e) {
                // Do nothing
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            firstLock.unlock();
        }
    }

}
