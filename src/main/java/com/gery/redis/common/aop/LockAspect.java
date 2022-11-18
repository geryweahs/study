package com.gery.redis.common.aop;

import cn.hutool.core.util.StrUtil;
import com.boss.common.constant.BossConstants;
import com.boss.common.exception.BossException;
import com.gery.redis.common.annotation.Lock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: LockAspect
 * @Author: YaoWenHua
 * @Date: 2022/10/20 14:27
 */
@Slf4j
@Aspect
@Component
public class LockAspect {

  //  @Autowired
   // private RedisKeyProperties redisKeyProperties;

    @Autowired
    private RedissonClient redisson;

    /**
     * 加锁、释放锁
     *
     * @param joinPoint
     * @param lock
     * @return
     * @throws Throwable
     */
//    @Around("@annotation(lock)")
//    public Object lock(ProceedingJoinPoint joinPoint, Lock lock) throws Throwable {
//        String accountNumber = LockContext.getAccountNumber();
//        if (StrUtil.isBlank(accountNumber)) {
//            log.error("account number does not exist,please check code");
//            throw new BossException(BossConstants.FAIL, PaymentConstant.SYSTEM_ERROR);
//        }
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(redisKeyProperties.getKeyPrefix());
//        stringBuilder.append(lock.target());
//        stringBuilder.append(PaymentConstant.SEPARATOR_0);
//        stringBuilder.append(accountNumber);
//        RLock rLock = redisson.getLock(stringBuilder.toString());
//        rLock.lock(lock.expire(), lock.timUnit());
//        Object result;
//        try {
//            result = joinPoint.proceed();
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            LockContext.clear();
//            rLock.unlock();
//        }
//        return result;
//    }


}
