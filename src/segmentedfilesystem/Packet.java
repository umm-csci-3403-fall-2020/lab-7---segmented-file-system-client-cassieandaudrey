package segmentedfilesystem;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Packet {
    public Packet(List<DataPack> dataPacks, List<HeaderPack> headerPacks){
        
    }
    public void assembleFile(List<DataPack> dataPacks, List<HeaderPack> headerPacks) throws IOException {
        // Go through Headers and use them to ID files
        for(int i=0; i<headerPacks.size(); i++){
            String filename = new String(headerPacks.get(i).getFilename());
            byte ID = headerPacks.get(i).getFileID();
            Map<Integer,DataPack> dpacklist = new HashMap<Integer,DataPack>();
            for(int j=0; j<dataPacks.size(); j++){
                DataPack dpack = dataPacks.get(j);
                if(dpack.getFileID() == ID){
                    dpacklist.put(dpack.pnum, dpack);
                }
            }
            PacketManager manager = new PacketManager(dpacklist);
            List<DataPack> sorted = manager.sortPacks(dpacklist);
            writeToFile(sorted, filename);
        }
        
    
    }
       public void writeToFile(List<DataPack> sorted, String filename) throws IOException {
        File file = new File(filename); 
        BufferedOutputStream out= new BufferedOutputStream(new FileOutputStream(file));
        System.out.println("THE SIZE OF THE SORTED IS: " + sorted.size());                
        for(int i = 0; i < sorted.size(); i++) {
            System.out.println("writing");
            out.write(sorted.get(i).info);            
        }
        out.flush();
        out.close();
            
        }
}
