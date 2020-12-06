package com.mystudy;

public class TestMain {
    public static void main(String[] args) {
        Dept dept = new Dept(10,"策划部","成都");
        System.out.println(dept.getId());
        System.out.println(dept.getName());
        System.out.println(dept.getLoc());
    }
}
