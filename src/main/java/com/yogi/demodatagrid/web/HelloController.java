package com.yogi.demodatagrid.web;

import com.yogi.demodatagrid.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/hello/v1")
public class HelloController {

    @Autowired
    private RemoteCacheManager remoteCacheManager;

    /**
     *  Saving strings into RHDG
     *
     * @param name
     * @return
     */
    @PostMapping("/greeting/{name}")
    public String greeting(@PathVariable("name") String name) {
        try {
            var cacheName = "test_datagrid";
            var cache = (String) remoteCacheManager.getCache(cacheName).get(name);
            log.info("Cache is {}", cache);

            if (cache == null) {
                remoteCacheManager.getCache(cacheName).put(name, name, 10L, TimeUnit.MINUTES);
                log.info("Success put to cache");
            }

            if (cache != null) {
                return String.format("Hello %s, data got from cache", cache);
            }
            return "Hello " + name;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return "Something wrong happened";
        }
    }

    /**
     *  Saving objects in RHDG
     *
     * @param name
     * @return
     */
    @PostMapping("/greeting-2/{name}")
    public String greetingWitbObject(@PathVariable("name") String name) {
        try {
            var cacheName = "lele";
            var user = (User) remoteCacheManager.getCache(cacheName).get(name);
            log.info("Cache is {}", user);

            if (user == null) {
                remoteCacheManager.getCache(cacheName).put(name, new User(name, "Jakarta"), 10L, TimeUnit.MINUTES);
                log.info("Success put to cache");
            }

            if (user != null) {
                return String.format("Hello %s, data got from cache, your address is %s", user.getName(), user.getAddress());
            }
            return "Hello " + name;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return "Something wrong happened";
        }
    }
}
