package net.gfu.seminar.ci;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AppIT {

    @Test
    public void testApp() {
        App.main(null);
        assertTrue( true );
    }
}
