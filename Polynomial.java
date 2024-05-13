public class Polynomial {
	//field
	double [] coefficients = new double [10];;

	//methods
	public Polynomial(){
	}

	public Polynomial(double [] coff){
		for (int i = 0; i < coff.length; i++){
			coefficients[i] = coff[i];
		}
	}

	public Polynomial add(Polynomial poly){
		if (poly.coefficients.length > coefficients.length){
			for (int i = 0; i < coefficients.length; i++){
				poly.coefficients[i] += coefficients[i];
			}
			return poly;
		}
		for (int i = 0; i < poly.coefficients.length; i++){
			poly.coefficients[i] += coefficients[i];
		}

		return poly;
	
	}

	public double evaluate(double x){
		double total = 0;
		for (int i = 0; i < coefficients.length; i++){
			total += coefficients[i] * (Math.pow(x, (double)i));
		}
		return total;
	}
	public boolean hasRoot(double x){
		if (evaluate(x) == 0){
			return true;
		}
		return false;
	}
}

