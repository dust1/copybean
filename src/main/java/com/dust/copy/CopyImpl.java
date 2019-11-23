package com.dust.copy;

import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copy的实现类
 */
public class CopyImpl implements Copy {

    private volatile CopyFactory factory;

    private Lock lock;

    public CopyImpl() {
        this.lock = new ReentrantLock();
    }

    @Override
    public void copy(Object source, Object target) {
        CopyTask task = getTask(source, target);
        task.start();
    }

    /**
     * 根据给定的两个实体创建执行的复制任务
     */
    private CopyTask getTask(Object source, Object target) {
        if (Objects.isNull(factory)) {
            lock.lock();
            if (Objects.isNull(factory)) {
                factory = CopyFactory.getInstance();
            }
            lock.unlock();
        }

        return factory.getTask(source, target);
    }

}
