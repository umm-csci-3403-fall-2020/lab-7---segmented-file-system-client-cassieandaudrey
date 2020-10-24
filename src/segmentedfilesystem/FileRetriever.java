package segmentedfilesystem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileRetriever  {
        String server;
        int port;
        ArrayList<HeaderPack> headers;
        List<DataPack> datalist;
        DatagramSocket socket;

        // Constructor
        public FileRetriever(String server, int port) throws UnknownHostException, SocketException {
                this.server = server;
                this.port = port;
                DatagramSocket socket = new DatagramSocket(port);
                this.socket = socket;
                ArrayList<HeaderPack> headers = new ArrayList<HeaderPack>();
                List<DataPack>datalist = new ArrayList<DataPack>();
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
                        count++;
                        // if packet is a header
                        if(dp.getData()[0]%2 ==0){
                          HeaderPack head = new HeaderPack(dp);
                          headers.add(head);
                        }
                        // otherwise it is data
                        else{
                           DataPack data = new DataPack(dp);
                           datalist.add(data);
                           if(data.isLast == true){
                             MAX = data.pnum;
                           }

                        }
                        
                }
        } 
        // method to retrive packets 
	public void downloadFiles() throws IOException, IndexOutOfBoundsException {
                start(this.port, this.server, socket);
                getPackets(this.server, this.port, socket);
                for(int i=0; i<headers.size(); i++){
                  System.out.println(new String(headers.get(0).getFilename()));
                }
                Packet packer = new  Packet(datalist,headers);
                packer.assembleFile(datalist, headers);
                socket.close();
                


                
                




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

