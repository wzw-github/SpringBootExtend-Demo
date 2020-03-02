package com.wzw.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
    int num=0;
    /**
     * second 秒, minute 分, hour 时, day of month 日, month 月, and day of week 周几
     * 0 * * * * MON-FRI：周一到周五，每月每日每小时每分都执行一次
     * * * * * * MON-FRI：周一到周五，每月每日每小时每分都执行一次
     *  从左到右，second, minute, hour, day of month, month, and day of week，*代表所有
     *  需要注意的是，每周是从周日开始，不然报错Invalid inverted range: '1-0' in expression "* * * * * MON-SUN"
     *  应该是* * * * * SUN-MON，SUN 在前面
     *  @Scheduled(cron = "0,1,2,3,4 * * * * SUN-MON"):在第0,1,2，3,4秒的时候运行，枚举的写法
     *  @Scheduled(cron = "0-4 * * * * SUN-MON")：在0到4秒之间，每秒都运行
     *  @Scheduled(cron = "0/4 * * * * SUN-MON"):每四秒执行一次
     */
//    @Scheduled(cron = "* * * * * SUN-MON")
    @Scheduled(cron = "0/4 * * * * SUN-MON")
    public void hello(){

        num++;
        System.out.println("定时任务执行第"+num+"次");
    }
}
