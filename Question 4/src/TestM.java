import org.junit.After;import org.junit.Assert;import org.junit.Before;import org.junit.Test;import java.io.ByteArrayOutputStream;import java.io.PrintStream;public class TestM {	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();	private final PrintStream originalOut = System.out;	private final PrintStream originalErr = System.err;	@Before	public void setUpStreams() {		System.setOut(new PrintStream(outContent));		System.setErr(new PrintStream(errContent));	}	private M m;	// Setup variable for testing	@Before	public void setUp() {		m = new M();	}    // Node coverage	/*  Node coverage:		TR: {1,2,3,4,5,6,7,8,9,10,11}		Test path: {[1,2,3,4,8,10,11], [1,2,3,5,8,9,11], [1,2,3,6,7,8,9,11]}	 */	// Test when arg = "" and arg.length == 0	@Test	public void testLen0i0() {		String [] testArr = {""};		m.m(testArr[0], testArr.length);		Assert.assertEquals("zero\n", outContent.toString());	}	//lol	// Test when arg = "a" and arg.length == 1	@Test	public void testLen1i0() {		String [] testArr = {"a"};		m.m(testArr[0], testArr.length);		Assert.assertEquals("a\n", outContent.toString());	}	// Test when arg = "ab" and arg.length == 2	@Test	public void testLen2i0() {		String [] testArr = {"ab"};		m.m(testArr[0], testArr.length);		Assert.assertEquals("b\n", outContent.toString());	}	// End node coverage	/* Edge coverage:	   TR: {(1,2),(1,3),(2,3),(3,4),(3,5),(3,6),(3,7),(6,7),(4,8),(5,8),(7,8),	   (8,10),(8,9),(10,11),(9,11)}	   Test Path: {[1,2,3,4,8,10,11],  [1,2,3,5,8,9,11], [1,2,3,6,7,8,9,11],	   [1,2,3,7,8,9,11], [1,3,7,8,9,11]}	 */	//TODO: Add test case for EC	//End EC	/* Edge-Pair Coverage	   TR:{[1,2,3], [1,3,4], [1,3,5], [1,3,6], [1,3,7], [2,3,4], [2,3,5],	   [2,3,6], [2,3,7], [3,4,8], [3,5,8], [3,6,9], [3,6,7], [3,7,8], [4,8,9],	   [4,8,10], [5,8,9], [5,8,10], [6,7,8], [7,8,9], [7,8,10], [8,9,11],	   [8,10,11]}	 */	//TODO: Add test cases	//End EPC	@After	public void restoreStreams() {		System.setOut(originalOut);		System.setErr(originalErr);	}    }class M {	public static void main(String [] argv){		M obj = new M();		if (argv.length > 0)			obj.m(argv[0], argv.length);	}		public void m(String arg, int i) {		int q = 1;		A o = null;		Impossible nothing = new Impossible();		if (i == 0)			q = 4;		q++;		switch (arg.length()) {			case 0: q /= 2; break;			case 1: o = new A(); new B(); q = 25; break;			case 2: o = new A(); q = q * 100;			default: o = new B(); break; 		}		if (arg.length() > 0) {			o.m();		} else {			System.out.println("zero");		}		nothing.happened();	}}class A {	public void m() { 		System.out.println("a");	}}class B extends A {	public void m() { 		System.out.println("b");	}}class Impossible{	public void happened() {		// "2b||!2b?", whatever the answer nothing happens here	}}