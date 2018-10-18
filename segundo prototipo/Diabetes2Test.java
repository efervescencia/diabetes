package packDiabetes;

import static org.junit.Assert.*;

import org.junit.Test;

public class Diabetes2Test {

	@Test
	public void testCalcularDosis() {
		
		Diabetes2 d = new Diabetes2();
		
		//caso aumentar dosis por consumir mas hidratos
		
		assertTrue(6.0 == d.CalcularDosis(3, 6, 120, 160, 20, 120, 12));
		assertTrue(4.0 == d.CalcularDosis(3, 6, 120, 160, 20, 120, 8));
		assertTrue(3.5 == d.CalcularDosis(3, 6, 120, 160, 20, 120, 7));
		
		//caso disminuir dosis por consumir menos hidratos
		
		assertTrue(1.5 == d.CalcularDosis(3, 6, 120, 160, 20, 120, 3));
		assertTrue(2.0 == d.CalcularDosis(3, 6, 120, 160, 20, 120, 4));
		assertTrue(2.5 == d.CalcularDosis(3, 6, 120, 160, 20, 120, 5));
		
		//caso misma dosis por ser mismos hidratos
		assertTrue(3.0 == d.CalcularDosis(3, 6, 120, 160, 20, 120, 6));
	}

	@Test
	public void testCalcularAccion() {
		
		Diabetes2 d = new Diabetes2();
		
		
		//PRIMEROS 9 CASOS GL PREV AYER ALTA
		
		//caso1: InsulinaAyer = 3, GlPrev Alta, GlPost Alta, GlAct Alta
		assertTrue(3.5 == d.CalcularAccion(3, 141, 181, 20, 142));
		//caso2: InsulinaAyer = 3, GlPrev Alta, GlPost Alta, GlAct ok
		assertTrue(3.0 == d.CalcularAccion(3, 141, 181, 20, 138));
		//caso3: InsulinaAyer = 3, GlPrev Alta, GlPost Alta, GlAct Baja
		assertTrue(2.5 == d.CalcularAccion(3, 141, 181, 20, 79));
		//caso4: InsulinaAyer = 3, GlPrev Alta, GlPost ok, GlAct Alta
		assertTrue(3.0 == d.CalcularAccion(3, 141, 179, 20, 141));
		//caso5: InsulinaAyer = 3, GlPrev Alta, GlPost ok, GlAct ok
		assertTrue(2.5 == d.CalcularAccion(3, 141, 179, 20, 139));
		//caso6: InsulinaAyer = 3, GlPrev Alta, GlPost ok, GlAct Baja
		assertTrue(2.0 == d.CalcularAccion(3, 141, 179, 20, 79));
		//caso7: InsulinaAyer = 3, GlPrev Alta, GlPost Baja, GlAct Alta
		assertTrue(2.5 == d.CalcularAccion(3, 141, 99, 20, 141));
		//caso8: InsulinaAyer = 3, GlPrev Alta, GlPost Baja, GlAct ok
		assertTrue(2.0 == d.CalcularAccion(3, 141, 99, 20, 81));
		//caso9: InsulinaAyer = 3, GlPrev Alta, GlPost Baja, GlAct Baja
		assertTrue(1.5 == d.CalcularAccion(3, 141, 99, 20, 79));
		
		
		//SEGUNDOS 9 CASOS GL PREV AYER OK
		
		//caso1: InsulinaAyer = 3, GlPrev ok, GlPost Alta, GlAct Alta
		assertTrue(4.0 == d.CalcularAccion(3, 139, 181, 20, 142));
		//caso2: InsulinaAyer = 3, GlPrev ok, GlPost Alta, GlAct ok
		assertTrue(3.5 == d.CalcularAccion(3, 139, 181, 20, 138));
		//caso3: InsulinaAyer = 3, GlPrev ok, GlPost Alta, GlAct Baja
		assertTrue(3.0 == d.CalcularAccion(3, 139, 181, 20, 79));
		//caso4: InsulinaAyer = 3, GlPrev ok, GlPost ok, GlAct Alta
		assertTrue(3.5 == d.CalcularAccion(3, 139, 179, 20, 141));
		//caso5: InsulinaAyer = 3, GlPrev ok, GlPost ok, GlAct ok
		assertTrue(3.0 == d.CalcularAccion(3, 139, 179, 20, 139));
		//caso6: InsulinaAyer = 3, GlPrev ok, GlPost ok, GlAct Baja
		assertTrue(2.5 == d.CalcularAccion(3, 139, 179, 20, 79));
		//caso7: InsulinaAyer = 3, GlPrev ok, GlPost Baja, GlAct Alta
		assertTrue(3.0 == d.CalcularAccion(3, 139, 99, 20, 141));
		//caso8: InsulinaAyer = 3, GlPrev ok, GlPost Baja, GlAct ok
		assertTrue(2.5 == d.CalcularAccion(3, 139, 99, 20, 81));
		//caso9: InsulinaAyer = 3, GlPrev ok, GlPost Baja, GlAct Baja
		assertTrue(2.0 == d.CalcularAccion(3, 139, 99, 20, 79));
		
		
		//TERCEROS CASOS GL PREV AYER BAJA
		System.out.println(d.CalcularAccion(3, 79, 99, 20, 79));
		
		//caso1: InsulinaAyer = 3, GlPrev Baja, GlPost Alta, GlAct Alta
		assertTrue(4.5 == d.CalcularAccion(3, 79, 181, 20, 142));
		//caso2: InsulinaAyer = 3, GlPrev Baja, GlPost Alta, GlAct ok
		assertTrue(4.0 == d.CalcularAccion(3, 79, 181, 20, 138));
		//caso3: InsulinaAyer = 3, GlPrev Baja, GlPost Alta, GlAct Baja
		assertTrue(3.5 == d.CalcularAccion(3, 79, 181, 20, 79));
		//caso4: InsulinaAyer = 3, GlPrev Baja, GlPost ok, GlAct Alta
		assertTrue(4.0 == d.CalcularAccion(3, 79, 179, 20, 141));
		//caso5: InsulinaAyer = 3, GlPrev Baja, GlPost ok, GlAct ok
		assertTrue(3.5 == d.CalcularAccion(3, 79, 179, 20, 139));
		//caso6: InsulinaAyer = 3, GlPrev Baja, GlPost ok, GlAct Baja
		assertTrue(3.0 == d.CalcularAccion(3, 79, 179, 20, 79));
		//caso7: InsulinaAyer = 3, GlPrev Baja, GlPost Baja, GlAct Alta
		assertTrue(3.5 == d.CalcularAccion(3, 79, 99, 20, 141));
		//caso8: InsulinaAyer = 3, GlPrev Baja, GlPost Baja, GlAct ok
		assertTrue(3.0 == d.CalcularAccion(3, 79, 99, 20, 81));
		//caso9: InsulinaAyer = 3, GlPrev Baja, GlPost Baja, GlAct Baja
		assertTrue(2.5 == d.CalcularAccion(3, 79, 99, 20, 79));
		
	}

}
