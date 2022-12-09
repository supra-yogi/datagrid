package com.yogi.demodatagrid.web;

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
            log.error("Got error: {}", ex.getMessage());
            return "Something wrong happened";
        }
    }
}
