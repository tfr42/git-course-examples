package net.gfu.seminar.ci;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigourous Test :-)
     */
    @Test
    public void testApp()
    {
        App.main(null);
        assertTrue( true );
    }

    @Test @Ignore
    public void testFailed()
    {
        assertTrue( false );
    }
}
