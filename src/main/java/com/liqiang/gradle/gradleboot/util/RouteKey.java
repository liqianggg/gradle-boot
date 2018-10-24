package com.liqiang.gradle.gradleboot.util;

import java.util.HashMap;
import java.util.Map;

public enum RouteKey {
    CURRENT,
    HISTORY;
    private static Map<String, RouteKey> map = new HashMap<>(values().length);

    static {
        for (RouteKey routeKey : values()) {
            map.put(routeKey.name(), routeKey);
        }
    }

    public static RouteKey converRoutekey(String key) {
        return map.get(key);
    }
}
