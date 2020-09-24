package com.xue.study.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server extends Thread
{
    private static Vector<Socket> vecClient = new Vector<Socket>();
    private PrintWriter out;
    private BufferedReader in;
    private Socket clientsocket;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(2014); // 创建
        System.out.println("启动服务器！ ");
        Socket sock;

        while (true) {
            sock = server.accept(); // 等待客户请求
            Server ser = new Server(sock);
            ser.start(); // 启动
        }
    }

    public Server(Socket socket) {
        this.clientsocket = socket;
        vecClient.addElement(socket); // 增加客户线程到数组
        try {
            // 获取服务端传来的字节流好，存到缓冲区
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            // printwriter，文本输出流打印对象的格式化表示形式，只有调用println等，自动刷新即true，强行把缓冲区的数据输出。
            out = new PrintWriter(new OutputStreamWriter(
                    socket.getOutputStream()), true);
            // input、OutputStreamWriter 是字符流通向字节流的桥梁
        } catch (IOException e) {
        }
    }

    public void run()
    {
        try
        {
            Newclient();          // 向新客户发出欢迎信息，通知所有在线客户有新客户连接了
            while(true)          // 处理与客户的交流
            {

                String msg=in.readLine();    //数据读取

                if(msg.equals("bye")|| msg.equals("拜拜"))//当输入拜拜或bye时候退出
                {
                    Xiaxian();
                    in.close();          // 关闭输入流
                    clientsocket.close(); // 关闭socket
                    break;
                }
                else if(msg.indexOf("@")==0&&msg.indexOf(" ")>0){
                    int end=msg.indexOf(" ");
                    String findAddr=msg.substring(1,end);
                    if(vecClient.isEmpty() == false) //判断为空
                    {
                        for(int i=0;i<vecClient.size();i++)
                        {
                            Socket socket = vecClient.get(i);
                            String addr = socket.getInetAddress().toString().replace("/", "") + ":"
                                    + socket.getPort();
                            if(findAddr.equals(addr)){
                                String addr2 = clientsocket.getInetAddress().toString().replace("/", "") + ":"
                                        + clientsocket.getPort();
                                //elementAt返回指定索引处的组件，而get返回列表中指定位置处的元素
                                PrintWriter pw = new PrintWriter(new OutputStreamWriter(vecClient.get(i).getOutputStream()),true);
                                pw.println("大神"+addr2+" 对你 说: "+msg.substring(end));
                            }
                        }
                    }
                }
                // 否则将接收到的信息向所有在线客户发出去
                else
                {
                    if(vecClient.isEmpty() == false)
                        for(int i=0;i<vecClient.size();i++)
                        {
                            //elementAt返回指定索引处的组件，而get返回列表中指定位置处的元素
                            PrintWriter pw = new PrintWriter(new OutputStreamWriter(vecClient.get(i).getOutputStream()),true);
                            pw.println("大神"+clientsocket.getInetAddress().toString()+":"+clientsocket.getPort()+" 说: "+msg);
                        }
                }
            }
        }
        catch(IOException e)
        { }
    }

    public void Newclient() throws IOException // 新来的，用此函数
    {
        String addr = clientsocket.getInetAddress().toString() + ":"
                + clientsocket.getPort();
        out.println("欢迎:" + addr+"加入！ ");
        try {
            if (vecClient.isEmpty() == false)
                for (int i = 0; i < vecClient.size(); i++)
                {
                    Socket socket = vecClient.get(i);
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(
                            socket.getOutputStream()), true);
                    String addr2 = socket.getInetAddress().toString() + ":"
                            + socket.getPort();

                    // TODO: 2019/11/15 0015 服务端连接通知
                    if (addr.equals(addr2)) {
                        pw.println("欢迎加入聊天，私聊请用@ip:port 内容");
                    } else {
                        // TODO: 2019/11/15 0015 公告新成员
                        pw.println("新成员:"
                                + clientsocket.getInetAddress().toString()
                                + ":" + clientsocket.getPort() + "  嗨起来吧！");
                    }
                }
        } catch (IOException e) {
        }
    }

    public void Xiaxian() throws IOException // 下线方法
    {
        out.println("再见，连接关闭！");
        if (vecClient.isEmpty() == false)
            for (int i = 0; i < vecClient.size(); i++) {
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(vecClient
                        .get(i).getOutputStream()), true);
                pw.println("--" + clientsocket.getInetAddress().toString()
                        + ":" + clientsocket.getPort() + " 已经下线");
            }
        vecClient.remove(clientsocket);
    }
}