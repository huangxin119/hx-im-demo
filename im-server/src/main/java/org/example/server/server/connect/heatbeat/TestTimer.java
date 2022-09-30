package org.example.server.server.connect.heatbeat;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.06.09
 */
@Slf4j
public class TestTimer {
    public static void main(String[] args) {
        Timer timer = new HashedWheelTimer();
        TimerTask timerTask01 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                log.info("执行第一个任务");
            }
        };
        TimerTask timerTask02 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                log.info("执行第2个任务");
            }
        };
        timer.newTimeout(timerTask01,3, TimeUnit.SECONDS);
        timer.newTimeout(timerTask02,5,TimeUnit.SECONDS);

    }
}
