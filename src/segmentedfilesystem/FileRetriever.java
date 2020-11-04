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
                // Give 'dummy' values to packet list IDs to signify they are open for use
                List<PacketManager> packyList = new ArrayList<PacketManager>();
                for (int i = 0; i < numFiles; i++) {
                        PacketManager packy = new PacketManager(Byte.MAX_VALUE);
                        packyList.add(packy);
                }
                ArrayList<HeaderPack> headers = new ArrayList<HeaderPack>();
                while (numFiles > 0) {
                                buf = new byte[1028];
                                DatagramPacket dp = new DatagramPacket(buf, buf.length);
                                socket.receive(dp);
                                // Check if pack is header
                                if (dp.getData()[0] % 2 == 0) {
                                        HeaderPack head = new HeaderPack(dp);
                                        headers.add(head);
                                }
                                // otherwise it is dataPack
                                else {
                                        DataPack data = new DataPack(dp);
                                        for (int i = 0; i < packyList.size(); i++) {
                                                if (packyList.get(i).fileID == data.fileID) {
                                                        packyList.get(i).addToList(data);
                                                        data.added = true;
                                                        if ((data.statusID % 4) == 3) {
                                                                packyList.get(i).setMaxSize(data.pnum);
                                                        }
                                                        if (packyList.get(i).full == true) {
                                                                numFiles--;
                                                        }
                                                        
                                                }
                                        }
                                        // if data was not added, it belongs to new file
                                        if (data.added == false) {
                                                for (int i = 0; i < packyList.size(); i++) {
                                                        // if fileID is dummy number, it is free to be assigned new ID
                                                        if (packyList.get(i).fileID == Byte.MAX_VALUE) {
                                                                packyList.get(i).setfileID(data.fileID);
                                                                packyList.get(i).addToList(data);
                                                                data.added = true;
                                                                if ((data.statusID % 4) == 3) {
                                                                        // found last packet, update file max
                                                                        packyList.get(i).setMaxSize(data.pnum);                                                                        
                                                                }
                                                                if (packyList.get(i).full == true) {
                                                                        // Recieved all packets for given file
                                                                        numFiles--;
                                                                }
                                                                break;

                                                        }
                                                      
                                                }
                                        }
                                        

                                }

                }
                Packet packer = new Packet();
                packer.packagePackets(headers, packyList);
        }

        // method to retrive packets
        public void downloadFiles() throws IOException, IndexOutOfBoundsException {
                start(this.port, this.server, socket);
                getPackets(this.server, this.port, socket);

        }

}
