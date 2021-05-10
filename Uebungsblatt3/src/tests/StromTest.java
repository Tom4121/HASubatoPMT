package tests;

import classes.Strom;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StromTest {


  @Before
  public void init() {

  }

  @Test
  public void testReadBinary() {
    assertEquals(42,Strom.readBinary("101010"));
  }

  @Test
  public void testQuersumme() {
    assertEquals(12, Strom.quersumme(2424));
  }

  @Test
  public void testFactorial() {
    assertEquals(3628800,Strom.factorial(10));
  }
}