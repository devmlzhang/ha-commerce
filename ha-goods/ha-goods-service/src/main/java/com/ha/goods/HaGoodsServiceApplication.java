package com.ha.goods;

import com.ha.common.interceptor.FeignClientInterceptor;
import com.ha.goods.service.GoodsLockService;
import com.nepxion.aquarius.lock.LockExecutor;
import com.nepxion.aquarius.lock.annotation.EnableLock;
import com.nepxion.aquarius.lock.entity.LockType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *    
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/10/21 
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.ha.goods.mapper")
@EnableLock
@ComponentScan(basePackages = { "com.ha.goods.service" })
public class HaGoodsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaGoodsServiceApplication.class, args);
       /* ConfigurableApplicationContext applicationContext = SpringApplication.run(HaGoodsServiceApplication.class, args);

        LockExecutor<Object> lockExecutor = applicationContext.getBean(LockExecutor.class);

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Object lock = null;
                    try {
                        lock = lockExecutor.tryLock(LockType.LOCK, "lock", "X-Y", 5000L, 60000L, false, false);
                        if (lock != null) {
                            try {
                                TimeUnit.MILLISECONDS.sleep(2000L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("doA - lock is got");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            lockExecutor.unlock(lock);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Object lock = null;
                    try {
                        lock = lockExecutor.tryLock(LockType.LOCK, "lock", "X-Y", 5000L, 60000L, false, false);
                        if (lock != null) {
                            try {
                                TimeUnit.MILLISECONDS.sleep(2000L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("doC - lock is got");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            lockExecutor.unlock(lock);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }*/
    }

    /**
     * 服务与服务之间调用拦截处理
     * @return
     */
    @Bean
    public FeignClientInterceptor getFeignClientInterceptor(){
        return new FeignClientInterceptor();
    }
}
