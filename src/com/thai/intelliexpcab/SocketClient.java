package com.thai.intelliexpcab;

import java.io.*;
import java.net.Socket;

public class SocketClient {
    public void send(String parm) {

        // 1、创建客户端Socket，指定服务器地址和端口
        try {
            Socket socket = new Socket("192.168.3.9", 2658);
            // 2、获取输出流，向服务器端发送信息
            OutputStream os = socket.getOutputStream();// 获取字节输出流
            // 将输出流包装为打印流
            PrintWriter pw = new PrintWriter(os);
            pw.write(parm);
            pw.flush();
            socket.shutdownOutput();//关闭输出流

            // 3、获取输入流，并读取服务器端的响应信息
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info = null;
            while ((info = br.readLine()) != null) {
                System.out.println("我是客户端，服务器跟我说：" + info);
            }

            // 4、关闭资源
            br.close();
            is.close();
            pw.close();
            os.close();
            socket.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
