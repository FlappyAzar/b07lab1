import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Polynomial {

	double [] coefficients;
	int [] exponents;

	public Polynomial() {
		this.coefficients = new double[0];
		this.exponents = new int[0];
	}

	public Polynomial(double [] coefs, int [] exps) {
		this.coefficients = coefs;
		this.exponents = exps;
	}
	
	public Polynomial(File file) throws IOException {
		Scanner input = new Scanner(file);
		String poly_str = input.nextLine();
		input.close();
		
		String [] poly_arr = poly_str.split("(?=[+-])");
		
		double [] coefs = new double[poly_arr.length];
		int [] exps = new int[poly_arr.length];
		
		int i = 0;
		for (String str: poly_arr) {
			String [] element_arr = str.split("x");
			double coef = Double.parseDouble(element_arr[0]);
			coefs[i] = coef;
			if (element_arr.length == 2) {
				int exp = Integer.parseInt(element_arr[1]);
				exps[i] = exp;
			} else {
				exps[i] = 0;
			}
			
			i++;
		}
		
		this.coefficients = coefs;
		this.exponents = exps;
			
	}

	public Polynomial add(Polynomial p) {
		ArrayList<Double> res_coefs = new ArrayList<Double>();
		ArrayList<Integer> res_exps = new ArrayList<Integer>();

		for (int i = 0; i < this.coefficients.length; i++) {
			res_coefs.add(this.coefficients[i]);
			res_exps.add(this.exponents[i]);
		}

		for (int i = 0; i < p.coefficients.length; i++) {
			int index = res_exps.indexOf(p.exponents[i]);
			
			if (index == -1) {
				res_coefs.add(p.coefficients[i]);
				res_exps.add(p.exponents[i]);
			}
			
			else {
				res_coefs.set(index, p.coefficients[i] + res_coefs.get(index));
			}
		}
		
		for (Double x: res_coefs) {
			if (x == 0) {
				res_coefs.remove(res_coefs.indexOf(x));
				res_exps.remove(res_coefs.indexOf(x));
			}
		}
		
		double [] coefs = res_coefs.stream().mapToDouble(i -> i).toArray();
		int [] exps = res_exps.stream().mapToInt(i -> i).toArray(); 

		Polynomial res = new Polynomial(coefs, exps);
		return res;
	}
	
	public Polynomial multiply(Polynomial p) {
		
		Polynomial res = new Polynomial();
		
		for (int i = 0; i < this.coefficients.length; i++) {
			double [] coefs = new double[p.coefficients.length];
			int [] exps = new int[p.coefficients.length];
			for (int j = 0; j < p.coefficients.length; j++) {
				coefs[j] = this.coefficients[i] * p.coefficients[j];
				exps[j] = this.exponents[i] + p.exponents[j];
			}
			
			res = res.add(new Polynomial(coefs, exps));
		}
		
		return res;
	}

	public double evaluate(double x) {
		double res = 0.0;
		for (int i = 0; i < this.coefficients.length; i++) {
			res += Math.pow(x, this.exponents[i]) * this.coefficients[i];
		}

		return res;
	}

	public boolean hasRoot(double x) {

		return evaluate(x) == 0;
	}
	
	public void saveToFile(String file_name) throws IOException {
		String res = "";
		
		for (int i = 0; i < this.coefficients.length; i++) {
			double coef = this.coefficients[i];
			int exp = this.exponents[i];
			if (coef == 0.0) {
				continue;
			} else {
				if (coef > 0 && i != 0) {
					res += "+";
				}
				res += Double.toString(coef);
				if (exp != 0) {
					res += "x";
					if (exp != 1) {
						res += Integer.toString(exp);
					}
				}
			}
		}
		
		PrintWriter writer = new PrintWriter(new File(file_name));
		writer.println(res);
		writer.close();
	}
}
