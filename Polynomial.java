public class Polynomial {

	double [] cofficients;

	public Polynomial() {
		cofficients = new double [] {0.0};
	}

	public Polynomial(double [] array) {
		cofficients = array;
	}

	public Polynomial add(Polynomial p) {
		int length = Math.max(cofficients.length, p.cofficients.length);

		double [] res_coefs = new double[length];

		for (int i = 0; i < cofficients.length; i++) {
			res_coefs[i] = cofficients[i];
		}

		for (int i = 0; i < p.cofficients.length; i++) {
			res_coefs[i] += p.cofficients[i];
		}

		Polynomial res = new Polynomial(res_coefs);
		return res;
	}

	public double evaluate(double x) {
		double res = 0.0;
		for (int i = 0; i < cofficients.length; i++) {
			res += Math.pow(x, i) * cofficients[i];
		}

		return res;
	}

	public boolean hasRoot(double x) {
		return evaluate(x) == 0;
	}

}
