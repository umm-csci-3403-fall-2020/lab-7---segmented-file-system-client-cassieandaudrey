package segmentedfilesystem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class FileRetriever  {
        String server;
        int port;
        Map<Byte, byte[]> headers;
        Map<Integer, byte[]> datalist;
        DatagramSocket socket;

        // Constructor
        public FileRetriever(String server, int port) throws UnknownHostException, SocketException {
                this.server = server;
                this.port = port;
                DatagramSocket socket = new DatagramSocket(port);
                this.socket = socket;
                Map<Byte, byte[]> headers = new HashMap<Byte, byte[]>();
                Map<Integer, byte[]> datalist = new HashMap<Integer, byte[]>();
                this.datalist = datalist;
                this.headers = headers;
        }

        // method to start conversation with server
        public static void start(int port, String server, DatagramSocket socket) throws IOException {
                InetAddress inetAddress = InetAddress.getByName(server);
                // DatagramSocket socket = new DatagramSocket(port);
                byte[] buf = new byte[0];
                DatagramPacket dp = new DatagramPacket(buf, buf.length, inetAddress, port);
                socket.send(dp);
                
        }
        public void getPackets(String server, int port, DatagramSocket socket) throws IOException{
              
                int MAX = 1000;
                int count =0;
                byte[] buf = new byte[0];

                while(count < MAX){
                        buf = new byte[1028];
                        DatagramPacket dp = new DatagramPacket(buf, buf.length);
                        socket.receive(dp);
                        PacketManager packer = new PacketManager(dp);
                        count++;
                        // if packet is a header
                        if(packer.status == "header" ){
                          headers.put(packer.statusID, packer.data);
                        }
                        // otherwise it is data
                        else{
                           datalist.put(packer.pnum, packer.data);
                           System.out.println(packer.pnum);
                           if( packer.isLast == true){
                                   
                                MAX = packer.pnum;
                           }

                        }
                        
                }
        }
        // method to retrive packets 
	public void downloadFiles() throws IOException {
                start(this.port, this.server, socket);
                getPackets(this.server, this.port, socket);
                socket.close();
                System.out.println(datalist.get(115));



        // Do all the heavy lifting here.
        // This should
        //   * Connect to the server
        //   * Download packets in some sort of loop
        //   * Handle the packets as they come in by, e.g.,
        //     handing them to some PacketManager class
        // Your loop will need to be able to ask someone
        // if you've received all the packets, and can thus
        // terminate. You might have a method like
        // PacketManager.allPacketsReceived() that you could
        // call for that, but there are a bunch of possible
        // ways.
        }
       
      

}

