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
public class DataPack {
    String status = "data";
    byte statusID; 
    byte[] data;
    int pnum;
    byte fileID;
    byte[] info;

    
    //Constructor
    public DataPack(DatagramPacket dp){
        this.data = dp.getData();
        this.setPnum();
        this.fileID = data[1];
        this.statusID = data[0];
        int length = dp.getLength();
        this.info = Arrays.copyOfRange(dp.getData(), 4, length);

    }
    public void setPnum(){
        int x = data[2];
        int y = data[3];
        if (x < 0) {
            x += 256;
        }
        if (y < 0) {
            y += 256;
        }
        
        this.pnum = (256*x+y);
    }
    public byte getFileID(){
        return fileID;
    }
    
}
