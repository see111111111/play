package com.lyl.play.demo;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpServiceDemo {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket=new DatagramSocket(9090);
        byte[] bytes = new byte[1024];
        DatagramPacket packet=new DatagramPacket(bytes,0,bytes.length);
        socket.receive(packet);
        System.out.println(packet.getAddress().getHostName());
        System.out.println(new String(packet.getData(),0,packet.getLength()));
        socket.close();
    }
}
