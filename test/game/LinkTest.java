package game;


import impl.Link;
import impl.BorderTile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LinkTest {

    private Link link;

    @Before
    public void setUp() throws Exception {
        link = new Link();
    }

    @Test
    public void Link_InstanceOfElement_True() {


        assertTrue(link instanceof Element);
    }

    @Test
    public void handleCollisionWithDeltaYOf10_y_doubleValueOf0() {
        BorderTile borderTile = new BorderTile();

        link.setDeltaY(10);
        double expectedYAfterCollision = link.getY()- link.getDeltaY();

        link.handleCollision(borderTile);

        assertTrue(link.getY() == expectedYAfterCollision);

    }
}
