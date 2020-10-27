package segmentedfilesystem;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PacketManager {
    boolean full;
    List<DataPack> datalist;
    int MaxSize;
    byte fileID;

    // Constructors
    public PacketManager(byte fileID) {
        this.datalist = new ArrayList<DataPack>();
        this.full = false;
        this.MaxSize = 100000;
        this.fileID = fileID;

    }

    public void addToList(DataPack dpack) {
        datalist.add(dpack);
        if (datalist.size() == this.MaxSize) {
            this.full = true;
        }

    }

    public void setfileID(byte fileID) {
        this.fileID = fileID;
    }
    public void setMaxSize(int MaxSize){
        this.MaxSize = MaxSize;
    }

}
