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
            byte ID = headerPacks.get(i).getFileID();
            ArrayList<String> file = new ArrayList<String>();
            String filename = new String(headerPacks.get(i).getFilename());

            // Make Packet Manager to sort dataPacks for the specified file
            Map<Integer,DataPack> datalist = new HashMap<Integer,DataPack>();
            for(int j = 0; j<dataPacks.size(); j++){
                DataPack dpack = dataPacks.get(i);
                if(dpack.getFileID() == ID){
                    datalist.put(dpack.pnum, (dpack));
                }
            }
            PacketManager manager = new PacketManager(datalist);
            List<DataPack> sorted = manager.sortPacks(datalist);
            writeToFile(sorted, filename);
            System.out.println(filename);
            System.out.println(sorted);
        }
    }
       public void writeToFile(List<DataPack> sorted, String filename) throws IOException {
            File file = new File(filename);
            for(int i = 0; i<sorted.size(); i++){
                FileOutputStream os = new FileOutputStream(file);
                BufferedOutputStream out = new BufferedOutputStream(os);
                for(int j = 0; j < sorted.size(); j++) {
                    System.out.println("writing");
                    out.write(sorted.get(i).info);
                }
                out.flush();
                out.close();
            }


        }
    
}
