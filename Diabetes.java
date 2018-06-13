package packDiabetes;

public class Diabetes {
	
	int BARRERA_HIPO = 80;
	int BARRERA_HIPER = 180;
	int SUPER_HIPO = 70;
	int SUPER_HIPER = 250;
	
	double ratioInsulina = 0;
	double ratioInsulinaPorHidratos = 0;
	
	
	public double CalcularDosis(double insulinaAyer, double hidratosAyer, double glucosaPreviaAyer, double glucosaPosteriorAyer, double totalInsulinaAyer, double glucosaActual, double hidratosActual){
		
		//Formulas basicas
		ratioInsulina = 1800 / totalInsulinaAyer;
		ratioInsulinaPorHidratos = insulinaAyer / hidratosAyer;
		
		int accion = 0;
				
		//posibles 27 casos
		
		//Primeros 8 casos cuando la glucosa previa de ayer era alta
		if(glucosaPreviaAyer > 140)
		{
			//Si la glucosa posterior tambien fue alta
			if(glucosaPosteriorAyer >180)
			{
				//Si la glucosa actual es alta
				if(glucosaActual>140){accion=1;}
				//Si la glucosa actual es baja
				else if(glucosaActual<80){accion=(-1);}
			}
			//Si la glucosa posterior fue correcta
			else if(glucosaPosteriorAyer<180 && glucosaPosteriorAyer>80)
				{
					//Si la glucosa actual no es alta
					if(glucosaActual<180){accion=(-1);}
				}
			//Si la glucosa posterior fue baja
			else if(glucosaPosteriorAyer<80)
			{
				//da igual como este la actual... hay que disminuir la dosis
				
			}
		}
		//7 casos cuando la glucosa previa de ayer era correcta
		else if (glucosaPreviaAyer <140 && glucosaPreviaAyer>80)
			{
			//si la glucosa posterior fue alta
			if(glucosaPosteriorAyer >180)
				{
				//si la glucosa actual no es baja
				if(glucosaActual>80){accion=1;}
				}
			//si la glucosa posterior fue correcta
			else if(glucosaPosteriorAyer<140 && glucosaPosteriorAyer>80)
			{
				//Si la glucosa actual es alta
				if(glucosaActual>140){accion=1;}
				//Si la glucosa actual es baja
				else if(glucosaActual<80){accion=(-1);}
			}
			//si la glucosa posterior fue baja
			else if (glucosaPosteriorAyer<80)
			{
				//Si la glucosa actual no es alta
				if(glucosaActual<180){accion=(-1);}
			}	
		}
		//ultimos 6 casos cuando la glucosa previa de ayer era baja
		else if(glucosaPreviaAyer<80)
			{
			//Cuando la glucosa posterior ayer era alta
				if(glucosaPosteriorAyer>180)
				{
					//en cualquier caso aumentamos la dosis
					accion=1;
				}
			//Cuando la glucosa posterior de ayer era correcta
				else if (glucosaPosteriorAyer<180 && glucosaPosteriorAyer>80)
				{
					//si la glucosa actual es mayor de 80 hay que aumentar la dosis
					if(glucosaActual>80){accion=1;}
				}
			//Cuando la glucosa posterior de ayer era baja
				else if (glucosaPosteriorAyer<80)
				{
					//Si la glucosa actual es alta
					if(glucosaActual>140){accion=1;}
					//Si la glucosa actual es baja
					else if(glucosaActual<80){accion=(-1);}
				}
			
			}
		
		return accion;
	}
	

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
