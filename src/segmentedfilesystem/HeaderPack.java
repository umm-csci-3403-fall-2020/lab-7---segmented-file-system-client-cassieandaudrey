package segmentedfilesystem;

import java.net.DatagramPacket;
import java.util.Arrays;

public class HeaderPack {
    String status = "header";
    DatagramPacket dp;
    byte[] filename;
    byte fileID;

    public HeaderPack(DatagramPacket dp) {
        this.dp = dp;
        fileID = dp.getData()[1];
        int filenameLength = dp.getLength();
        filename = Arrays.copyOfRange(dp.getData(), 2, filenameLength);
    }

    public byte[] getFilename() {
        return filename;
    }

    public byte getFileID() {
        return fileID;

    }

}
