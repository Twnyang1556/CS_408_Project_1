import org.junit.Before;
import org.junit.Test;

public class TestM {
	private M m;
	// Setup variable for testing
	@Before
	public void setUp() {
		m = new M();
	}
    // Node coverage


	//N1
	@Test
	public void testN1() {

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
