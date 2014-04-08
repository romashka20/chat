package com.tshap88.chat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConfFileTest {

    @Test
    public void testGetADDRESS () {
        String address = ConfFile.getADDRESS();
        assertEquals(address, "localhost");
    }

    @Test
    public void testGetPORT () {
        int port = ConfFile.getPORT();
        assertEquals(port, 5343);
    }

    @Test
    public void testConfFileTest()
    {
        assertTrue( true );
    }
}
