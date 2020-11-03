package segmentedfilesystem;

import java.net.DatagramPacket;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class DataPack {
    byte statusID;
    byte[] data;
    int pnum;
    byte fileID;
    byte[] info;
    boolean added;

    // Constructor
    public DataPack(DatagramPacket dp) {
        this.data = dp.getData();
        this.setPnum();
        this.fileID = data[1];
        this.statusID = data[0];
        int length = dp.getLength();
        this.info = Arrays.copyOfRange(dp.getData(), 4, length);
        this.added = false;

    }

    public void setPnum() {
        int x = data[2];
        int y = data[3];
        if (x < 0) {
            x += 256;
        }
        if (y < 0) {
            y += 256;
        }
        this.pnum = (256 * x + y);
    }

    public byte getFileID() {
        return fileID;
    }

}
