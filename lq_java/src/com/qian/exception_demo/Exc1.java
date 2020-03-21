package com.qian.exception_demo;

/**
 * @author lq
 * @date 2020/03/22
 * 异常练习
 */

class Exc1 {
    static void subroutin(){
        int d = 0;
        int a = 10 / d;
    }
    public static void main(String[] args){
        Exc1.subroutin();
    }
}
