package com.liqiang.gradle.gradleboot.util.model;

public class WangPo implements Women {

    private Women women;

    public WangPo(Women women) {
        this.women = women;
    }
    public WangPo() {
        this.women = new Panjinlian();
    }

    @Override
    public void flayEye() {
        this.women.flayEye();
    }

    @Override
    public void eat() {
        this.women.eat();
    }
}
