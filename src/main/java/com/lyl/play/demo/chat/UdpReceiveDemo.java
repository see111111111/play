package com.lyl.play.demo.chat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpReceiveDemo {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket=new DatagramSocket(6666);
        while (true){
            byte[] bytes = new byte[1024];
            DatagramPacket packet=new DatagramPacket(bytes,0,bytes.length);
            socket.receive(packet);

            byte[] data = packet.getData();
            String s=new String(data,0,data.length);
            System.out.println(s);
            if (s.equals("bye")){
                break;
            }

        }
        socket.close();













        /*DatagramSocket socket=new DatagramSocket(6666);
        while (true){
            byte[] bytes = new byte[1024];
            DatagramPacket packet=new DatagramPacket(bytes,0,bytes.length);
            socket.receive(packet);

            byte[] data = packet.getData();
            String s = new String(data, 0, data.length);
            System.out.println(s);
            if (s.equals("bye")){
                break;
            }
        }
        socket.close();*/
    }
}
