package test;
import object.Board;
import org.testng.annotations.Test;

import static junit.framework.Assert.*;

/**
 * Created by francica on 2/5/16.
 */
public class BoardTest {

    @Test
    public void create() {
        assertEquals(1 > 2, false);

        Board tmp = new Board();
        //check whether  pieces are on their right position

        assertEquals(tmp.grids[0][4].name, "King");
        assertEquals(tmp.grids[7][4].name, "King");
        assertEquals(tmp.grids[7][1].name, "Knight");
        assertEquals(tmp.grids[0][6].name, "Knight");
    }
}