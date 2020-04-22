package com.qian.io_demo;
import java.io.*;
class iodemo1{
    public static void main (String args[])throws  IOException{
        char c;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.printl("q to quit");
        //读字符串
        do{
            c = (char) bufferedReader.read();
            System.out.println(c);
        }while (c !='q');
    }
}