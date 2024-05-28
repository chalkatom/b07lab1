import java.io.*;
import java.util.*;

public class Polynomial {
	//field
	double [] coefficients;
	int [] exponents;

	//methods
	
	//constructor with no arguments
	public Polynomial(){
		
		double [] Cplaceholder = new double[1];
		int [] Eplaceholder = new int[1];
		coefficients = Cplaceholder;
		exponents = Eplaceholder;
	
	}
	
	//constructor with coefficient and exponent arrays as arguments
	public Polynomial(double [] coff, int [] exp){
		
		double [] Cplaceholder = new double[coff.length];
		int [] Eplaceholder = new int[exp.length];
		
		for (int i = 0; i < coff.length; i++){
			Cplaceholder[i] = coff[i];
		}
		
		for (int i = 0; i < exp.length; i++){
			Eplaceholder[i] = exp[i];
		}
		
		coefficients = Cplaceholder;
		exponents = Eplaceholder;
	}
	
	public Polynomial(File file) throws FileNotFoundException{
		double [] coff;
		int [] exp;
		try{	
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String data = reader.readLine();
			reader.close();
			
			String [] terms = data.split("[+-]");
			coff = new double[terms.length];
			exp = new int[terms.length];
			
			for (int i = 0; i < terms.length; i++){
				String term = terms[i];
				
				if (term.indexOf("x") == -1){
					coff[i] = Double.parseDouble(term);
					exp[i] = 0;
				}
				else{
					String [] nums = term.split("x");
					coff[i] = Double.parseDouble(nums[0]);
					exp[i] = Integer.parseInt(nums[1]);
				}
			}
			
			
			
		}catch (IOException e){
		e.printStackTrace();
		coff = new double[0];
		exp = new int[0];
		}
		
		coefficients = coff;
		exponents = exp;
	}
	
	public void saveToFile(String name){
		try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(name));
		writer.write((int) coefficients[0] + "");
		for (int i = 1; i < coefficients.length; i++){
			if (coefficients[i] > 0){
				writer.write("+" + (int) coefficients[i]);
			}
			else{
			writer.write((int) coefficients[i] + "");
			}
			if (exponents[i] == 0){
				continue;
			}
			writer.write("x" + (int) exponents[i]);
		}
		writer.close();
		}catch (IOException e){
		e.printStackTrace();
		}
	}

	public double finalLength(Polynomial poly){
		double sameCount = 0;
		for (int i = 0; i < poly.exponents.length; i++){
			for (int j = 0; j < exponents.length; j++){
				if (poly.exponents[i] == exponents[j]){
					sameCount++;
					break;
				}
			}
		}
		return poly.exponents.length + (exponents.length - sameCount);
	}
	
	
	public Polynomial add(Polynomial poly){
		int size = (int) this.finalLength(poly);
		double [] coff = new double[size];
		int [] exp = new int[size];
		int newIndex = 0;
		
		for (int i = 0; i < poly.exponents.length; i++){
			coff[i] = poly.coefficients[i];
			exp[i] = poly.exponents[i];
			newIndex++;
		}
		
		for (int i = 0; i < exponents.length; i++) {
        		boolean found = false;
        		
        		for (int j = 0; j < newIndex; j++) {
            			if (exp[j] == exponents[i]) {
                			coff[j] += coefficients[i];
                			found = true;
                			break;
            			}
        		}
        		if (!found) {
            			exp[newIndex] = exponents[i];
            			coff[newIndex] = coefficients[i];
            			newIndex++;
        		}
    		}
		return new Polynomial(coff, exp);
	}
	
	public Polynomial multiply(Polynomial poly){
		int tempSize = poly.coefficients.length * coefficients.length;
		double [] coff = new double[tempSize];
		int [] exp = new int[tempSize];
		int tempIndex = 0;
		
		//multiply every term with eachother
		for (int i = 0; i < coefficients.length; i++){
			for (int j = 0; j < poly.coefficients.length; j++){
				coff[tempIndex] = coefficients[i] * poly.coefficients[j];
				exp[tempIndex] = exponents[i] + poly.exponents[j];
				tempIndex++;
			}
		}
		
		//add like terms
		for (int i = 0; i < tempSize; i++){
			for (int j = i + 1; j < tempSize; j++){
				if (exp[i] == exp[j]){
					coff[i] += coff[j];
					coff[j] = 0;
				}
			}
		}
		
		//find non-zero count
		int size = 0;
		for (int i = 0; i < tempSize; i++){
			if (coff[i] != 0){
				size++;
			}
		}
		
		//add each non-zero coefficient (and its respective exponent) to new arrays
		double [] finalCoff = new double[size];
		int [] finalExp = new int[size];
		tempIndex = 0;
		for (int i = 0; i < tempSize; i++){
			if (coff[i] != 0){
				finalCoff[tempIndex] = coff[i];
				finalExp[tempIndex] = exp[i];
				tempIndex++;
			}
		}
		
		return new Polynomial(finalCoff, finalExp);
	}

	public double evaluate(double x){
		double total = 0;
		for (int i = 0; i < coefficients.length; i++){
			total += coefficients[i] * (Math.pow(x, exponents[i]));
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
