package com.liqiang.gradle.gradleboot.util;

import org.springframework.boot.actuate.endpoint.Endpoint;

public class CustomEndpoint implements Endpoint {

    @Override
    public String getId() {
        return "custom";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isSensitive() {
        return false;
    }

    @Override
    public Object invoke() {
        return "hello endpoint";
    }
}
