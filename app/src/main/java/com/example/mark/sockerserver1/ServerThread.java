package com.example.mark.sockerserver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class ServerThread extends Thread {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void run(){

        try{
            // Open a server socket listening on port 8080
            InetAddress addr = InetAddress.getByName(getLocalIpAddress());
            serverSocket = new ServerSocket(8080, 0, addr);
            clientSocket = serverSocket.accept();

            // Client established connection.
            // Create input and output streams
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read received data and echo it back
            String input = in.readLine();
            out.println("received: " + input);

            // Perform cleanup
            in.close();
            out.close();

        } catch(Exception e) {
            // Omitting exception handling for clarity
        }
    }

    /*private String getLocalIpAddress() throws Exception {
        String resultIpv6 = "";
        String resultIpv4 = inetAddress.getLocalHost().getHostAddress();;


        return ((resultIpv4.length() > 0) ? resultIpv4 : resultIpv6);
    }*/

    public static String getLocalIpAddress() {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress().toUpperCase();
                                return sAddr;
                    }
                }
            }
        } catch (Exception ex) { } // for now eat exceptions
        return "";
    }
}