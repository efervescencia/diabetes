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
		//ratio en medias dosis
		ratioInsulinaPorHidratos = (insulinaAyer / hidratosAyer);
		double diferenciaHidratos = hidratosActual - hidratosAyer;
		double insulina = CalcularAccion(insulinaAyer, glucosaPreviaAyer, glucosaPosteriorAyer, totalInsulinaAyer, glucosaActual); 
		
		//según la accion calculamos el incremento o decremento de dosis
		
		if (diferenciaHidratos >0){
			//Hay que aumentar la dosis
			insulina+=(ratioInsulinaPorHidratos*diferenciaHidratos);
		}
		else if (diferenciaHidratos <0){
			//Hay que disminuir la dosis
			insulina+=(ratioInsulinaPorHidratos*diferenciaHidratos);
		}
		
		return insulina;
	}
	
	
	public double CalcularAccion(double insulinaAyer, double glucosaPreviaAyer, double glucosaPosteriorAyer, double totalInsulinaAyer, double glucosaActual){
		

		int accion = 0;
				
		//posibles 27 casos, reducidos a 20: los casos 0 no hace falta explicitar.
		
		//Primeros 7 casos cuando la glucosa previa de ayer era alta
		if(glucosaPreviaAyer > 140)
		{
			//Si la glucosa posterior tambien fue alta
			if(glucosaPosteriorAyer >180)
			{
				//Si la glucosa actual es alta
				if(glucosaActual>140){accion=1;}
				//Si la glucosa actual es baja
				else if(glucosaActual<100){accion=(-1);}
			}
			//Si la glucosa posterior fue correcta
			else if(glucosaPosteriorAyer<180 && glucosaPosteriorAyer>100)
				{
					//Si la glucosa actual es correcta
					if(glucosaActual > 80 && glucosaActual<140){accion=(-1);}
					else if (glucosaActual<80){accion=(-2);}
				}
			//Si la glucosa posterior fue baja
			else if(glucosaPosteriorAyer<100)
			{
				//Si la glucosa actual es alta
				if(glucosaActual >140){accion=(-1);}
				//Si la glucosa actual es correcta
				else if(glucosaActual >80 && glucosaActual<140){accion=(-2);}
				//si la glucosa actual es baja
				else if(glucosaActual <80){accion=(-3);}
			}
		}
		//6 casos cuando la glucosa previa de ayer era correcta
		else if (glucosaPreviaAyer <140 && glucosaPreviaAyer>80)
			{
			//si la glucosa posterior fue alta
			if(glucosaPosteriorAyer >180)
				{
				//si la glucosa actual es alta
				if(glucosaActual>140){accion=2;}
				//si la glucosaActual es correcta
				else if(glucosaActual>80 && glucosaActual<140){accion=1;}
				}
			//si la glucosa posterior fue correcta
			else if(glucosaPosteriorAyer<180 && glucosaPosteriorAyer>100)
			{
				//Si la glucosa actual es alta
				if(glucosaActual>140){accion=1;}
				//Si la glucosa actual es baja
				else if(glucosaActual<80){accion=(-1);}
			}
			//si la glucosa posterior fue baja
			else if (glucosaPosteriorAyer<100)
			{
				//si la glucosa actual es correcta
				if(glucosaActual<140 &&glucosaActual>80){accion=(-1);}
				//Si la glucosa actual es baja
				if(glucosaActual<80){accion=(-2);}
			}	
		}
		//ultimos 7 casos cuando la glucosa previa de ayer era baja
		else if(glucosaPreviaAyer<80)
			{
			//Cuando la glucosa posterior ayer era alta
				if(glucosaPosteriorAyer>180)
				{
					//si la glucosa actual es alta +3
					if(glucosaActual>140){accion=3;}
					//si la glucosa actual es correcta +2
					else if(glucosaActual>80 && glucosaActual<140){accion=2;}
					//si la glucosa actual es baja +1
					else if(glucosaActual<80){accion=1;}
				}
			//Cuando la glucosa posterior de ayer era correcta
				else if (glucosaPosteriorAyer<180 && glucosaPosteriorAyer>80)
				{
					//si la glucosa actual es alta +2
					if(glucosaActual>140){accion=2;}
					//si la glucosa actual es correcta +1
					else if(glucosaActual>80 && glucosaActual<140){accion=1;}
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
		
		return insulinaAyer + (accion / 2.0);
	}
	

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
