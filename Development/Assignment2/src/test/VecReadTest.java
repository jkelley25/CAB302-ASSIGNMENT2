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

public class VecReadTest {
    private VecReader vecReader = new VecReader("TestFiles/VecTest.vec");

    @BeforeEach
    void Init(){
        vecReader = null;
    }

    @Test
    void TestConstruction(){
        vecReader = new VecReader("TestFiles/VecTest.vec");
    }

    @Test
    void TestShapeType(){
        vecReader = new VecReader("TestFiles/VecTest.vec");
        ArrayList<AbstractShape> shapes = vecReader.GetData();
        assertEquals(Rectangle.class, shapes.get(0).getClass());
    }

    @Test
    void TestNullFill() throws ShapeException {
        vecReader = new VecReader("TestFiles/VecTest.vec");
        ArrayList<AbstractShape> shapes = vecReader.GetData();
        assertNull(shapes.get(1).getFillColor());
    }

    @Test
    void TestPen() throws ShapeException {
        vecReader = new VecReader("TestFiles/VecTest.vec");
        ArrayList<AbstractShape> shapes = vecReader.GetData();
        assertEquals(Color.decode("#0000FF"), shapes.get(2).getPenColor());
    }

    @Test
    void TestFill() throws ShapeException {
        vecReader = new VecReader("TestFiles/VecTest.vec");
        ArrayList<AbstractShape> shapes = vecReader.GetData();
        assertEquals(Color.decode("#FFFF00"), shapes.get(shapes.size()-1).getFillColor());
    }

    @Test
    void TestPenFill() throws ShapeException {
        vecReader = new VecReader("TestFiles/VecTest.vec");
        ArrayList<AbstractShape> shapes = vecReader.GetData();
        assertEquals(Color.decode("#FF0000"), shapes.get(shapes.size()-1).getPenColor());
        assertEquals(Color.decode("#FFFF00"), shapes.get(shapes.size()-1).getFillColor());
    }
}
