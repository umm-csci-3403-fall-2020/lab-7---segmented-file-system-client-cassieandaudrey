package segmentedfilesystem;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * This is just a stub test file. You should rename it to something meaningful
 * in your context and populate it with useful tests.
 */
public class DummyTest {
    FileRetriever fileRetriever;
    DatagramSocket socket;


    @Before
    public void setUp() throws IOException {
        this.fileRetriever = new FileRetriever("localhost", 6014);
        FileRetriever.start(fileRetriever.port, fileRetriever.server, fileRetriever.socket);
        this.socket = fileRetriever.socket;
    }

    @Test
    public void test() throws IndexOutOfBoundsException, IOException {
        byte[] buf = new byte[0];
        Map<Integer, DataPack> dpacklist = new HashMap<Integer, DataPack>();
        int numDp = 20;
        while (numDp > 20) {
            buf = new byte[1028];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            socket.receive(dp);
            if (!(dp.getData()[0] % 2 == 0)) {
                DataPack dpack = new DataPack(dp);
                dpacklist.put(dpack.pnum, dpack);
            }
            numDp --;
        }
        Packet test = new Packet();
        List<DataPack> sorted = test.sortPacks(dpacklist);
        for(int i=0; i<sorted.size()-1; i++){
            if(!(sorted.get(i).pnum <= sorted.get(i+1).pnum)){
                assertTrue(true);
            }
        }

    }


}
