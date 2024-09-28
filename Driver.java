import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main(String [] args) throws IOException {
    Polynomial p = new Polynomial();
    System.out.println(p.evaluate(3));
    double [] c1 = {6,1,5};
    int [] e1 = {2,4,1};
    Polynomial p1 = new Polynomial(c1, e1);
    double [] c2 = {5,-2,-9, 100};
    int [] e2 = {0,5,1, 100};
    Polynomial p2 = new Polynomial(c2, e2);
    Polynomial s = p1.multiply(p2);
    for (int i = 0; i < 11; i++) {
    	System.out.println("Coef " + i + " = " + s.coefficients[i]);
    	System.out.println("Exps " + i + " = " + s.exponents[i]);
    }
    Polynomial p3 = new Polynomial(new File("C:\\Users\\sepaz\\Downloads\\file.txt"));
    
    for (int i = 0; i < p3.coefficients.length; i++) {
    	System.out.print(p3.coefficients[i] + "(x^" + p3.exponents[i] + ") + ");
    }
    
    s.saveToFile("file9.txt");
    
    System.out.println("\ns(0.1) = " + s.evaluate(0.1));
    }
}
