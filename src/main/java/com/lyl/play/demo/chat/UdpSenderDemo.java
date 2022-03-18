package com.lyl.play.demo.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UdpSenderDemo {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket=new DatagramSocket(9999);
        while (true){

            BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
            String s1 = reader.readLine();
            byte[] bytes1 = s1.getBytes();
            DatagramPacket packet=new DatagramPacket(bytes1,0,bytes1.length,new InetSocketAddress("localhost",6666));
            socket.send(packet);
            if (s1.equals("bye")){
                break;
            }

        }
        socket.close();
    }
}
