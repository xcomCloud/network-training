package com.xue.study.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

class Client
{
    public static void main(String[] args)
    {
        try
        {
            Socket socket=new Socket("192.168.3.67",2014);
            ClientSend send=new ClientSend(socket);            // 创建发送线程
            ClientReceive receive=new ClientReceive(socket); // 创建接收线程
            send.start();                                       // 启动发送线程
            receive.start();                                  // 启动接收线程
        }
        catch(Exception e)
        {
            System.out.println("服务器没有开启呢！");
            e.printStackTrace();
        }

    }
}

class ClientSend extends Thread
{
    private Socket socket;
    private PrintWriter out;

    public ClientSend(Socket socket)
    {
        this.socket=socket;
        try
        {
            out=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
            //返回一个服务器与客户端的输出流,true强行把缓冲区的数据输出
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void run()  // 发送信息到服务器
    {
        String msg;
        Scanner input=new Scanner(System.in);//输入
        msg = input.nextLine();                //nextLine方法返回的是回车之前的所有字符
        while(true)
        {
            //用equalsIgnoreCase可忽略大小写
            if(msg.equals("bye") || msg.equals("拜拜"))
            {
                out.println(msg);
                break;
            }
            out.println(msg);
            msg = input.nextLine();
        }
    }
}

class ClientReceive extends Thread            //接收
{
    private Socket socket;
    private BufferedReader in;

    public ClientReceive(Socket socket)
    {
        this.socket= socket;
        try
        {
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void run() // 接收服务器发来的信息
    {
        try
        {
            String msg=in.readLine();
            while(msg.equals("bye") == false)
            {
                System.out.println(msg);
                msg=in.readLine();
            }
            in.close();
            socket.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}