package segmentedfilesystem;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Packet {
    public Packet() {

    }

    public List<DataPack> sortPacks(Map<Integer, DataPack> datalist) {
        // TreeMap to store values of HashMap
        List<Integer> sortedKeys = new ArrayList<Integer>(datalist.keySet());
        Collections.sort(sortedKeys);
        List<DataPack> sortedData = new ArrayList<DataPack>();
        for (Integer key : sortedKeys) {
            sortedData.add(datalist.get(key));
        }

        return sortedData;
    }

    public void assembleFile(List<DataPack> dataPacks, HeaderPack header) throws IOException {
        // Go through Headers and use them to ID files
            String filename = new String(header.getFilename());
            Map<Integer, DataPack> dpacklist = new HashMap<Integer, DataPack>();
            for (int j = 0; j < dataPacks.size(); j++) {
                DataPack dpack = dataPacks.get(j);
                dpacklist.put(dpack.pnum, dpack);
            }
            List<DataPack> sorted = sortPacks(dpacklist);
            writeToFile(sorted, filename);
    }
    public void packagePackets(List<HeaderPack> headers, List<PacketManager> packyList) throws IOException {
        for (int i = 0; i < headers.size(); i++) {
            HeaderPack header = headers.get(i);
            byte ID = header.fileID;
            PacketManager chosenOne = new PacketManager((byte ) 1); 
            for(int j =0; j<packyList.size(); j++){
                    if(ID == packyList.get(j).fileID){
                            chosenOne = packyList.get(j);
                    }
            }
            System.out.println(chosenOne.datalist.size());
            Packet packer = new Packet();
            packer.assembleFile(chosenOne.datalist, header);
            System.out.println("FILE COMPLETED");

    }



    }

    public void writeToFile(List<DataPack> sorted, String filename) throws IOException {
        File file = new File(filename);
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        System.out.println("THE SIZE OF THE SORTED IS: " + sorted.size());
        for (int i = 0; i < sorted.size(); i++) {
            out.write(sorted.get(i).info);
            System.out.println(sorted.get(i).pnum);
            
        }
        out.flush();
        out.close();

    }
}
