package com.lyl.play.demo;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    /*
    * InetSocketAddress  ip端口
    * */

    public static void main(String[] args) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);
        String hostName = localHost.getHostName();
        System.out.println(hostName);

        InetAddress byName = InetAddress.getByName("www.baidu.com");
        System.out.println(byName);
    }
}
