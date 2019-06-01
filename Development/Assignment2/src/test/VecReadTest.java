package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import shapes.AbstractShape;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import shapes.Rectangle;
import shapes.ShapeException;
import vec.VecReader;

import java.awt.*;
import java.util.ArrayList;

/**
 * Unit test for VecRead class
 */
public class VecReadTest {
    private VecReader vecReader = new VecReader("TestFiles/VecTest.vec");

    /**
     * Clear object before each test
     */
    @BeforeEach
    void Init(){
        vecReader = null;
    }

    /**
     * Test Construction of object
     */
    @Test
    void TestConstruction(){
        vecReader = new VecReader("TestFiles/VecTest.vec");
    }

    /**
     * Test Type
     */
    @Test
    void TestShapeType(){
        vecReader = new VecReader("TestFiles/VecTest.vec");
        ArrayList<AbstractShape> shapes = vecReader.GetData();
        assertEquals(Rectangle.class, shapes.get(0).getClass());
    }

    /**
     * Test that object read with no fill is correct in the file
     * @throws ShapeException
     */
    @Test
    void TestNullFill() throws ShapeException {
        vecReader = new VecReader("TestFiles/VecTest.vec");
        ArrayList<AbstractShape> shapes = vecReader.GetData();
        assertNull(shapes.get(1).getFillColor());
    }

    /**
     * Test pen color is correct in the file
     * @throws ShapeException
     */
    @Test
    void TestPen() throws ShapeException {
        vecReader = new VecReader("TestFiles/VecTest.vec");
        ArrayList<AbstractShape> shapes = vecReader.GetData();
        assertEquals(Color.decode("#0000FF"), shapes.get(2).getPenColor());
    }

    /**
     * Test that fill is correct in te file
     * @throws ShapeException
     */
    @Test
    void TestFill() throws ShapeException {
        vecReader = new VecReader("TestFiles/VecTest.vec");
        ArrayList<AbstractShape> shapes = vecReader.GetData();
        assertEquals(Color.decode("#FFFF00"), shapes.get(shapes.size()-1).getFillColor());
    }

    /**
     * Test both pen and fill color of the shape whe reading the file
     * @throws ShapeException
     */
    @Test
    void TestPenFill() throws ShapeException {
        vecReader = new VecReader("TestFiles/VecTest.vec");
        ArrayList<AbstractShape> shapes = vecReader.GetData();
        assertEquals(Color.decode("#FF0000"), shapes.get(shapes.size()-1).getPenColor());
        assertEquals(Color.decode("#FFFF00"), shapes.get(shapes.size()-1).getFillColor());
    }
}
