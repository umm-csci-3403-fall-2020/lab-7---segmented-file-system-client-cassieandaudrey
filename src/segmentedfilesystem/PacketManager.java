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
    public PacketManager(Map<Integer, String> datalist){
        
   
    }
    public List<String> sortPacks(Map<Integer,String> datalist){ 
        // TreeMap to store values of HashMap 
        List<Integer> sortedKeys=new ArrayList<Integer>(datalist.keySet());
        Collections.sort(sortedKeys);
        List<String> sortedData = new ArrayList<String>(); 
        for (Integer key : sortedKeys){
            sortedData.add(datalist.get(key));
        }       
        return sortedData;
    }
    // public void writeToFile(List<DataPack> sortedPacks, HeaderPack head){
    //     String filename = new String(head.getFilename());
    //     File file = new File(filename);
    //     for(int i = 0; i<sortedPacks.size(); i++){
            

    //         FileOutputStream os = new FileOutputStream(file);
    //         ArrayList<DataPacket> dataPackets = box.getData();
    //         BufferedOutputStream out = new BufferedOutputStream(fos);
    //         for(int j = 0; j < dataPackets.size(); j++) {
                
    //             byte[] data = packet.getData();
    //             out.write(data);
    //         }
    //         out.flush();
    //         out.close();
            
    //     }
    //     }

    // }
}
