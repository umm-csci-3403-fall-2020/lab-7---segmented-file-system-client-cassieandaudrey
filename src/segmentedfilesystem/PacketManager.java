package segmentedfilesystem;
import java.util.ArrayList;
import java.util.List;


public class PacketManager {
    boolean full;
    List<DataPack> datalist;
    int MaxSize;
    byte fileID;

    // Constructors
    public PacketManager(byte fileID) {
        this.datalist = new ArrayList<DataPack>();
        this.full = false;
        this.MaxSize = 600;
        this.fileID = fileID;

    }

    public void addToList(DataPack dpack) {
        datalist.add(dpack);
        if (datalist.size() >= this.MaxSize) {
            this.full = true;
        }
        else{
            this.full = false;
        }

    }

    public void setfileID(byte fileID) {
        this.fileID = fileID;
    }
    public void setMaxSize(int MaxSize){
        this.MaxSize = MaxSize;
    }

}

