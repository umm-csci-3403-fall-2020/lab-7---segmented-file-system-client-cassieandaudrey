package segmentedfilesystem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class FileRetriever {
        String server;
        int port;

        DatagramSocket socket;

        // Constructor
        public FileRetriever(String server, int port) throws UnknownHostException, SocketException {
                this.server = server;
                this.port = port;
                DatagramSocket socket = new DatagramSocket();
                this.socket = socket;
        }

        // method to start conversation with server
        public static void start(int port, String server, DatagramSocket socket) throws IOException {
                InetAddress inetAddress = InetAddress.getByName(server);
                // DatagramSocket socket = new DatagramSocket(port);
                byte[] buf = new byte[0];
                DatagramPacket dp = new DatagramPacket(buf, buf.length, inetAddress, port);
                socket.send(dp);
        }

        public void getPackets(String server, int port, DatagramSocket socket) throws IOException {
                byte[] buf = new byte[0];
                int numFiles = 3; // Three here becasue this is number in writeup

                while (numFiles > 0) {
                        int count = 0;
                        int numPacks = 100000;
                        List<DataPack> datalist = new ArrayList<DataPack>();
                        ArrayList<HeaderPack> headers = new ArrayList<HeaderPack>();
                        while (count < numPacks) { // Count +1 here because header pack must be accounted for
                                buf = new byte[1028];
                                DatagramPacket dp = new DatagramPacket(buf, buf.length);
                                socket.receive(dp);
                                if (dp.getData()[0] % 2 == 0) {
                                        HeaderPack head = new HeaderPack(dp);
                                        headers.add(head);
                                        count++;
                                }
                                // otherwise it is data
                                else {
                                        DataPack data = new DataPack(dp);
                                        datalist.add(data);
                                        count++;
                                        if ((data.statusID % 4) == 3) {
                                                numPacks = data.pnum;
                                        }

                                }

                        }
                        Packet packer = new Packet(datalist, headers);
                        packer.assembleFile(datalist, headers);
                        numFiles--;
                        System.out.println("FILE COMPLETED");
                }

        }

        // method to retrive packets
        public void downloadFiles() throws IOException, IndexOutOfBoundsException {
                start(this.port, this.server, socket);
                getPackets(this.server, this.port, socket);

        }

}
