package com.liqiang.gradle.gradleboot.controller;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.alibaba.fastjson.JSONObject;
import com.liqiang.gradle.gradleboot.service.CustomerService;
import com.liqiang.gradle.gradleboot.util.RedissonService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RFuture;
import org.redisson.api.RLock;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

@Slf4j
@Controller
@RequestMapping(value = "/redisson")
public class RedissonController {

    @Autowired
    private RedissonService redissonService;

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/{recordId}")
    @ResponseBody
    public JSONObject getRedissonByKey(@PathVariable(value = "recordId") String recordId) {
        JSONObject jsonObject = new JSONObject();
        RLock lock = redissonService.getRLock(recordId);
        try {
            boolean bs = lock.tryLock(5, 6, TimeUnit.SECONDS);
            if (bs) {
                // 业务代码
                log.info("进入业务代码: " + recordId);
                final RAtomicLong testRedisson = redissonService.getRAtomicLong("ratomicLong");
                final RFuture<Long> booleanRFuture = testRedisson.incrementAndGetAsync();
                booleanRFuture.whenComplete((aLong, throwable) -> {
                    log.debug(aLong.intValue() + "");
                });
                lock.unlock();
            } else {
                Thread.sleep(300);
            }
        } catch (Exception e) {
            log.error("", e);
            lock.unlock();
        }
        jsonObject.put("code", "1");
        jsonObject.put("msg", "success");
        return jsonObject;
    }

    @GetMapping(value = "/loggerInfo")
    @ResponseBody
    public void loggerLevelTest() {
        log.debug("debug");
        log.info("info");
        log.error("error");
    }

    @GetMapping(value = "/getLoglevel/{packageName}")
    @ResponseBody
    public String getLoglevel(@PathVariable(value = "packageName") String packageName) {

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        return loggerContext.getLogger(packageName).getLevel().levelStr;
    }

    /**
     * 动态设置指定路径下日志打印level
     *
     * @param packageName
     */
    @GetMapping(value = "/setLoglevel/{packageName}/{level}")
    @ResponseBody
    public void setLoglevel(@PathVariable(value = "packageName") String packageName, @PathVariable(value = "level") String level) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.getLogger(packageName).setLevel(Level.valueOf(level));
    }

    public static java.util.List<Thread> list_threads() {
        int tc = Thread.activeCount();
        Thread[] ts = new Thread[tc];
        Thread.enumerate(ts);
        return java.util.Arrays.asList(ts);
    }

    @GetMapping(value = "/api/stackinfo")
    @ResponseBody
    public String stackinfo() {
        String str = "<strong>Memory:</strong>";
        str += "<ol>";
        str += "<li>freeMemory=" + Runtime.getRuntime().freeMemory() / (1024 * 1024) + "M</li>";
        str += "<li>totalMemory=" + Runtime.getRuntime().totalMemory() / (1024 * 1024) + "M</li>";
        str += "<li>maxMemory=" + Runtime.getRuntime().maxMemory() / (1024 * 1024) + "M</li>";
        str += "</ol>";
        str += "<br/>";
        str += "<strong>Thread:</strong>";
        str += "<ol>";
        for (Thread t : list_threads()) {
            str += "<li>" + t.getName() + "," + t.getState() + ":" + t.getClass().getName() + "</li>";
            StackTraceElement[] elems = t.getStackTrace();
            str += "<ol>";
            for (StackTraceElement elem : elems) {
                str += "<li>    " + elem.toString() + "</li>";
            }
            str += "</ol>";
        }
        str += "</ol>";
        return str;
    }


}
