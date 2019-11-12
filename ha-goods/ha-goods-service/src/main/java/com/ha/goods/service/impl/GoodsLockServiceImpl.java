package com.ha.goods.service.impl;

import com.ha.goods.service.GoodsLockService;
import com.nepxion.aquarius.lock.LockExecutor;
import com.nepxion.aquarius.lock.entity.LockType;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *    分布式锁实现类
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/11/8
 */
@Service
@Slf4j
public class GoodsLockServiceImpl implements GoodsLockService {
    @Autowired
    private LockExecutor<InterProcessMutex> lockExecutor;

    @Override
    public void printString(String name,int i) {
        String className = this.getClass().getSimpleName();
        InterProcessMutex lock = null;

        try {
            lock = lockExecutor.tryLock(LockType.LOCK, "lock", className+name, 5000L, 60000L, false, false);
            if (lock == null) {
                throw new RuntimeException("并发操作异常，请稍后重试！");
            }
            System.out.println("线程"+name+":---"+i);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                lockExecutor.unlock(lock);
            } catch (Exception e) {
                log.error("分布式锁解锁失败。Lock key: {}", className);
            }
        }
    }
}
