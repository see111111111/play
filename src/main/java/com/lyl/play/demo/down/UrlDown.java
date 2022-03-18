package com.lyl.play.demo.down;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UrlDown {
    public static void main(String[] args) throws IOException {

        System.out.println(15<<2);
       /*  //下载地址
        URL url = new URL("https://m701.music.126.net/20211213172126/41c3c48af0336cc1ee0a0e596208106a/jdyyaac/obj/w5rDlsOJwrLDjj7CmsOj/11858827254/839c/479d/c797/5b2e139670c187c5391ef2e22d698d2d.m4a");
        //连接到这个资源
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        FileOutputStream file=new FileOutputStream("d.m4a");
        byte[] bytes = new byte[1024];
        int length;
        while ((length=inputStream.read(bytes))!=-1){
            file.write(bytes,0,bytes.length);
        }
        file.close();
        inputStream.close();
        urlConnection.disconnect();*/


    }

    /**
     *
     * @param name
     * @return
     */
    public void Test(String name ){

    }
}
