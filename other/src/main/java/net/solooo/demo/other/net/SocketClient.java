package net.solooo.demo.other.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Description:
 * Author:Eric
 * Date:2017/9/18
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.1.6", 9999);
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.write("test");
        pw.flush();
        socket.shutdownOutput();

        InputStream in = socket.getInputStream();
        BufferedReader br = new BufferedReader((new InputStreamReader(in)));
        String str;
        while ((str = br.readLine()) != null) {
            System.out.println(str);
        }
        br.close();
        pw.close();
        in.close();
        os.close();

    }
}
