package com.liqiang.gradle.gradleboot.util;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSourceConfig {
    String key();
}
