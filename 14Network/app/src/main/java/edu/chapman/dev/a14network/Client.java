package edu.chapman.dev.a14network;

import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client implements Runnable {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            // Retrieve the ServerName
            InetAddress serverAddr = InetAddress.getByName("127.0.0.1");

            Log.d("UDP", "C: Connecting...");
            /* Create new UDP-Socket */
            DatagramSocket socket = new DatagramSocket();

            /* Prepare some data to be sent. */
            byte[] buf = ("Hello from Client").getBytes();

            /* Create UDP-packet with
             * data & destination(url+port) */
            DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, Server.SERVERPORT);
            Log.d("UDP", "C: Sending: '" + new String(buf) + "'");

            /* Send out the packet */
            socket.send(packet);
            Log.d("UDP", "C: Sent.");
            Log.d("UDP", "C: Done.");



            buf = new byte[6];
            packet = new DatagramPacket(buf, buf.length, serverAddr, Server.SERVERPORT);

            socket.receive(packet);
            Log.d("UDP", "C: Received: '" + new String(packet.getData()) + "'");

        } catch (Exception e) {
            Log.e("UDP", "C: Error ", e);
        }
    }

}