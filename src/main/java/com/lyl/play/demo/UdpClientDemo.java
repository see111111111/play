package com.lyl.play.demo;

import java.io.IOException;
import java.net.*;

public class UdpClientDemo {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket=new DatagramSocket();

        String msg="我是服务器";
        InetAddress localhost = InetAddress.getByName("localhost");
        int port=9090;

        DatagramPacket packet=new DatagramPacket(msg.getBytes(),0,msg.getBytes().length,localhost,port);
        socket.send(packet);

        socket.close();
    }

}
