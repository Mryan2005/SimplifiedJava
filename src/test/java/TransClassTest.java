import top.mryan2005.simplifiedjava.RAFile;
import org.junit.Test;
import top.mryan2005.simplifiedjava.Trans;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TransClassTest {
    @Test
    public void testTrans() throws Exception {
        Trans trIn = new Trans("test/TransClass/in.json");
        RAFile raIn = new RAFile("test/TransClass/out.dat");
        int i = 0;
        for(String key : trIn.object.keySet()) {
            if(trIn.tr(key).equals(raIn.content.get(i))) {
                i++;
            } else {
                fail();
            }
        }
    }
}
