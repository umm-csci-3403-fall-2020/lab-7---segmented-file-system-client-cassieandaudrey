package segmentedfilesystem;
import java.io.IOException; 
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;


public class FileRetriever  {
        String server;
        int port;
        //Constructor 
	public FileRetriever(String server, int port) throws UnknownHostException {
                this.server=server;
                this.port=port;                
        }
        
        //method to start conversation with server
        public static void start(int port, String server ) throws IOException{
                InetAddress inetAddress = InetAddress.getByName(server);
                DatagramSocket socket = new DatagramSocket(port);
                byte[] buf = new byte[0]; 
                DatagramPacket dp = new DatagramPacket(buf, buf.length, inetAddress, port);
                socket.send(dp);
                
        }
        // method to retrive packets 
	public void downloadFiles() throws IOException {
                start(this.port, this.server);



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

