package game;

import impl.FieldTile;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WaterTileTest {

    @Test
    public void WaterTile_InstanceOfTile_True() {
        FieldTile fieldTile = new FieldTile();

        assertTrue(fieldTile instanceof Tile);
    }
}
