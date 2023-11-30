package com.naukma.introductionspringproject.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyCacheManager implements CacheManager {
    private static final Logger logger = LoggerFactory.getLogger(MyCacheManager.class);
    private final Map<String, Cache> cacheMap = new HashMap<>();


    @Override
    public Cache getCache(String name) {
        logger.info("Requesting cache with name: {}", name);
        return cacheMap.computeIfAbsent(name, n -> {
            logger.info("Creating new cache with name: {}", n);
            return new ConcurrentMapCache(n);
        });
    }

    @Override
    public Collection<String> getCacheNames() {
        logger.info("Getting all cache names");
        return cacheMap.keySet();
    }

}

