package segmentedfilesystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Packet {
    public Packet(List<DataPack> dataPacks, List<HeaderPack> headerPacks){
        
    }
    public void assembleFile(List<DataPack> dataPacks, List<HeaderPack> headerPacks){
          // Go through Headers and use them to ID files
          for(int i=0; i<headerPacks.size(); i++){
            byte ID = headerPacks.get(i).getFileID();
            ArrayList<String> file = new ArrayList<String>();
            String filename = new String(headerPacks.get(i).getFilename());

            // Make Packet Manager to sort dataPacks for the specified file
            Map<Integer,String> datalist = new HashMap<Integer,String>();
            for(int j = 0; j<dataPacks.size(); j++){
                DataPack dpack = dataPacks.get(i);
                if(dpack.getFileID() == ID){
                    datalist.put(dpack.pnum, new String(dpack.info));
                }
            }
            PacketManager manager = new PacketManager(datalist);
            List<String> sorted = manager.sortPacks(datalist);
            System.out.println(filename);
            System.out.println(sorted);
        }
    }
    
}
