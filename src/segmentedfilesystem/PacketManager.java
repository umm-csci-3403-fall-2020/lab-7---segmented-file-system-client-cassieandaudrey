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
  
    
    // Constructor
    public PacketManager(Map<Integer, DataPack> datalist){
        
   
    }
    public List<DataPack> sortPacks(Map<Integer, DataPack> datalist){ 
        // TreeMap to store values of HashMap 
        List<Integer> sortedKeys=new ArrayList<Integer>(datalist.keySet());
        Collections.sort(sortedKeys);
        List<DataPack> sortedData = new ArrayList<DataPack>(); 
        for (Integer key : sortedKeys){
            sortedData.add(datalist.get(key));
        }       
        System.out.println(sortedData.size()+ "SORTED DATA SIZE 33");
        return sortedData;
    }
 
}
