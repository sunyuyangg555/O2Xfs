package at.o2xfs.win32;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LPWORDTest {
    @Test
    public final void test() {
        LPWORD lpword = new LPWORD((short)5);
        assertEquals(5, lpword.get().intValue());
    }
}
