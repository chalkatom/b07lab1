import java.io.*;
import java.util.*;

public class Driver {
	public static void main(String [] args){
		Polynomial p = new Polynomial();
		System.out.println("p(3) = " + p.evaluate(3));
		double [] c1 = {6, 5};
		int [] i1 = {0, 3};
		Polynomial p1 = new Polynomial(c1, i1);
		double [] c2 = {-2, -9};
		int [] i2 = {1, 4};
		Polynomial p2 = new Polynomial(c2, i2);
		Polynomial m = p1.multiply(p2);
		System.out.println("p1 x p2 = [COEFFICIENTS]: " + Arrays.toString(m.coefficients) + " [EXPONENTS]: " + Arrays.toString(m.exponents));
		Polynomial s = p1.add(p2);
		System.out.println("s' coefficients: " + Arrays.toString(s.coefficients));
		System.out.println("and their respective exponents: " + Arrays.toString(s.exponents));
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if (s.hasRoot(1)) {
			System.out.println("1 is a root of s");
		}
		else {
			System.out.println("1 is not a root of s");
		}
		File file = new File("lab2_test.txt");
		try{
		Polynomial p3 = new Polynomial(file);
		}catch(IOException e){
		e.printStackTrace();
		}
		System.out.println("p3's COFF: " + Arrays.toString(s.coefficients) + " p3's EXP: " + Arrays.toString(s.exponents));
		p1.saveToFile("p1TEST");
	}
}

