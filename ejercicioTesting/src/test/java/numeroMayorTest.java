import org.example.Main;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class numeroMayorTest {

    @Test
    public void test1()
    {
        int a = 5;
        int b = 3;
        int c = 7;
        Main instance = new Main();
        assertEquals(instance.numero_mayor(a,b,c), 7);
    }

    @Test
    public void test2()
    {
        int a = 5;
        int b = 3;
        int c = 4;
        Main main = new Main();
        int expResult = 5;
        int result = main.numero_mayor(a,b,c);
        assertEquals(expResult, result);
    }

    @Test
    public void test3()
    {
        int a = 5;
        int b = 7;
        int c = 6;
        Main main = new Main();
        assertTrue(main.numero_mayor(a,b,c)==7);

    }

    @Test
    public void test4()
    {
        int a = 5;
        int b = 7;
        int c = 9;
        Main main = new Main();
        assertEquals(main.numero_mayor(a,b,c), 9);
    }
}
