package efervescencia.es.myapplication;

public class Diabetes2 {
	
	int barreraHipo = 80;
	int barreraHiper = 180;
	int hipoGrave = 70;
	int hiperGrave = 250;
	
	double ratioInsulina = 0;
	double ratioInsulinaPorHidratos = 0;
	
	
	
	public int getBarreraHipo() {
		return barreraHipo;
	}


	public void setBarreraHipo(int barreraHipo) {
		this.barreraHipo = barreraHipo;
	}


	public int getBarreraHiper() {
		return barreraHiper;
	}


	public void setBarreraHiper(int barreraHiper) {
		this.barreraHiper = barreraHiper;
	}


	public int getHipoGrave() {
		return hipoGrave;
	}


	public void setHipoGrave(int hipoGrave) {
		this.hipoGrave = hipoGrave;
	}


	public int getHiperGrave() {
		return hiperGrave;
	}


	public void setHiperGrave(int hiperGrave) {
		this.hiperGrave = hiperGrave;
	}


	public double CalcularDosis(double insulinaAyer, double hidratosAyer, double glucosaPreviaAyer, double glucosaPosteriorAyer, double totalInsulinaAyer, double glucosaActual, double hidratosActual){
	
		//Formulas básicas
		ratioInsulina = 1800 / totalInsulinaAyer;
		//ratio insulina por hidratos
		ratioInsulinaPorHidratos = (insulinaAyer / hidratosAyer);
		double diferenciaHidratos = hidratosActual - hidratosAyer;
		double insulina = CalcularAccion(insulinaAyer, glucosaPreviaAyer, glucosaPosteriorAyer, totalInsulinaAyer, glucosaActual); 
		
		//según la acción calculamos el incremento o decremento de dosis
		
		if (diferenciaHidratos >0){
			//Hay que aumentar la dosis
			insulina+=(ratioInsulinaPorHidratos*diferenciaHidratos);
		}
		else if (diferenciaHidratos <0){
			//Hay que disminuir la dosis
			insulina+=(ratioInsulinaPorHidratos*diferenciaHidratos);
		}
		
		//evitamos los resultados negativos
		if(insulina<0){insulina=0;}
		
		//Redondeamos para no tener dosis imposibles
				insulina *=2;
				insulina = Math.floor(insulina);
				insulina /=2;
		
		return insulina;
	}
	
	
	public double CalcularAccion(double insulinaAyer, double glucosaPreviaAyer, double glucosaPosteriorAyer, double totalInsulinaAyer, double glucosaActual){
		

		int accion = 0;
				
		//posibles 27 casos, reducidos a 8, 3 comprobaciones de 2 posibilidades más 2 casos extras de hiperglucemias actuales severas.
		
		//Según la glucosa inicial del día anterior
		
		if(glucosaPreviaAyer > 140)
		{
			accion-=1;
			}
		else if (glucosaPreviaAyer < 80){
			accion+=1;
		}
		
		//según la glucosa posterior del día anterior
		
		if(glucosaPosteriorAyer >180)
				{
			accion+=1;
				}
		else if (glucosaPosteriorAyer < 100){
			accion-=1;
		}
		
		// según la glucosa actual
		if (glucosaActual > 140){
			accion+=1;
		}
		else if(glucosaActual<80){
			accion-=1;
			}
			else if (glucosaActual > 220){
			accion+=2;}
			else if (glucosaActual > 300){
			accion+=3;}
		
		return insulinaAyer + (accion / 2.0);
	}
	

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//comprobamos parámetros
		if(args.length !=7){
			System.out.println("El programa necesita 7 argumentos:\n");
			
			System.out.println("1.- Dosis de insulina suministrada ayer para el mismo tramo horario");
			System.out.println("2.- Raciones de hidratos ingeridos ayer en el mismo tramo horario");
			System.out.println("3.- Nivel de glucosa de ayer antes de la ingesta.");
			System.out.println("4.- Nivel de glucosa de ayer 2 horas después de inyectar la insulina.");
			System.out.println("5.- Insulina total inyectada ayer");
			System.out.println("6.- Nivel de glucosa actual");
			System.out.println("7.- Raciones de hidratos que va a ingerir");
		
			System.exit(0);
		}
		
		
		
	}

}
