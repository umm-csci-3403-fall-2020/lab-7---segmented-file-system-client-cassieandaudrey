package segmentedfilesystem;
import java.io.IOException; 
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress; 
import java.util.Arrays;

public class PacketManager {
    String status;
    DatagramPacket dp;
    boolean isLast = false;
    byte[] bytes = this.dp.getData();
    byte statusID = bytes[0];
    int offset = (status.equals("header") ? 4 : 3); 
    byte[] data = Arrays.copyOfRange(bytes, offset, bytes.length);
    int pnum;
    
    // Constructor
    public PacketManager(DatagramPacket dp){
        setStatus();
        setFileID(this.bytes);
    }
  
    // Getters
    public void setStatus(){
        byte[] bytes = this.dp.getData();
        byte statusID = bytes[0];
        this.statusID = statusID;
        // if even, packet is header
        if(statusID%2 == 0){
            this.status = "header";
        }
        else{
            this.status = "data";
            // is packet final packet?
            if(statusID%4 == 3){
                this.isLast = true;
            }
        }

        
    }
    public void setFileID(byte[] bytes){
        int x = bytes[2];
        int y = bytes[3];
        if (x < 0) {
            x += 256;
        }
        if (y < 0) {
            y += 256;
        }
        
        this.pnum = (256*x+y);
    }
 
    // method to decided weither a dp is a header or data packet
    public void classify(DatagramPacket dp){

        
    }
    public void constructHeader(DatagramPacket header){

    }
    public void constructData(DatagramPacket dp){

    }
    public void sortPackets(){

    }
}
