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

public class HeaderPack {
    String status = "header";
    DatagramPacket dp;
    byte[] filename;
    byte fileID;

    public HeaderPack(DatagramPacket dp){
        this.dp = dp;
        fileID = dp.getData()[1];
        int filenameLength = dp.getLength();
        filename = Arrays.copyOfRange(dp.getData(), 2, filenameLength);
    }

    public byte[] getFilename() {
        return filename;
    }
    public byte getFileID(){
        return fileID;

    }
    
}
