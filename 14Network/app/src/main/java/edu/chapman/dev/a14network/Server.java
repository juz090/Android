package edu.chapman.dev.a14network;

import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server implements Runnable {

    public static final String SERVERIP = "127.0.0.1"; // 'Within' the emulator!
    public static final int SERVERPORT = 4444;

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            /* Retrieve the ServerName */
            InetAddress serverAddr = InetAddress.getByName(SERVERIP);

            Log.d("UDP", "S: Connecting...");
            /* Create new UDP-Socket */
            DatagramSocket socket = new DatagramSocket(SERVERPORT, serverAddr);

            /* By magic we know, how much data will be waiting for us */
            byte[] buf = new byte[17];
            /* Prepare a UDP-Packet that can
             * contain the data we want to receive */
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            Log.d("UDP", "S: Receiving...");

            /* Receive the UDP-Packet */
            socket.receive(packet);

            Log.d("UDP", "S: Received: '" + new String(packet.getData()) + "'");
            Log.d("UDP", "S: Done.");

            InetAddress clientAddr = packet.getAddress();
            int clientPort = packet.getPort();
            String s = "Thanks";
            buf = s.getBytes();
            packet = new DatagramPacket(buf, buf.length, clientAddr, clientPort);

            Log.d("UDP", "S: Sending: '" + new String(buf) + "'");
            socket.send(packet);

        } catch (Exception e) {
            Log.e("UDP", "S: Error", e);
        }
    }
}
