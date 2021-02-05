import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.*;
import static org.junit.Assert.*;

import java.io.PrintStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

public class TestM {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	private M m;
	// Setup variable for testing
	@Before
	public void setUp() {
		m = new M();
	}
    // Node coverage


	//N1
	// Test when arg.length = 0 and i == 0
	@Test
	public void testLen0i0() {
		m.m("", 0);
		Assert.assertEquals("zero\n", outContent.toString());
	}

	// Test when arg.length = 1 and i != 0
	@Test
	public void testLen1i1() {
		m.m("a", 1);
		Assert.assertEquals("a\n", outContent.toString());
	}

	// Test when arg.length = 2 and i != 0
	@Test
	public void testLen2i1() {
		m.m("ab", 1);
		Assert.assertEquals("b\n", outContent.toString());
	}


	@After
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

    
}

class M {
	public static void main(String [] argv){
		M obj = new M();
		if (argv.length > 0)
			obj.m(argv[0], argv.length);
	}
	
	public void m(String arg, int i) {
		int q = 1;
		A o = null;
		Impossible nothing = new Impossible();
		if (i == 0)
			q = 4;
		q++;
		switch (arg.length()) {
			case 0: q /= 2; break;
			case 1: o = new A(); new B(); q = 25; break;
			case 2: o = new A(); q = q * 100;
			default: o = new B(); break; 
		}
		if (arg.length() > 0) {
			o.m();
		} else {
			System.out.println("zero");
		}
		nothing.happened();
	}
}

class A {
	public void m() { 
		System.out.println("a");
	}
}

class B extends A {
	public void m() { 
		System.out.println("b");
	}
}

class Impossible{
	public void happened() {
		// "2b||!2b?", whatever the answer nothing happens here
	}
}
