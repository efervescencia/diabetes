package packDiabetes;

public class Diabetes {
	
	int BARRERA_HIPO = 80;
	int BARRERA_HIPER = 180;
	int SUPER_HIPO = 70;
	int SUPER_HIPER = 250;
	
	double ratioInsulina = 0;
	double dosisDiariaTotal = 0;
	
	
	public double CalcularDosis(double dosisAnterior, double glucosaPrevia, double glucosaPosterior, double glucosaActual){
		
		double dosisProvisional = 0;
		
		dosisProvisional= ComprobarGlucosaDiaAnterior(dosisAnterior, glucosaPrevia, glucosaPosterior);
		
		

		return 0;
	}
	
	public double CalcularRatioInsulina (double dosisTotal)
	{
		return (1800/dosisDiariaTotal);
	}
	
	public double ComprobarGlucosaDiaAnterior(double dosisAnterior, double glucosaPrevia, double glucosaPosterior) {
		
		double dosisProvisional = 0;
		// Si la glucosa del dia previo fue correcta. la dosis adecuada es la misma.
		if (glucosaPrevia >=70 && glucosaPrevia <= 180) {
			dosisProvisional = dosisAnterior;
		}
		
		
		
		return dosisProvisional;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
