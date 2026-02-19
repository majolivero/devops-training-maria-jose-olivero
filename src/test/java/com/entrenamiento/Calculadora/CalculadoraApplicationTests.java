package com.entrenamiento.Calculadora;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.entrenamiento.Calculadora.controlador.controlador;
import com.entrenamiento.Calculadora.modelo.resultado;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CalculadoraApplicationTests {

	resultado assertresultado =new resultado();
	controlador controlador =new controlador();

	@Test
	void testsuma() {
		resultado localresultado = controlador.suma("159","18");

		assertresultado.setResultado(177);
		assertresultado.setEstado("ok");

		assertEquals(assertresultado.getEstado(),localresultado.getEstado());
		assertEquals(assertresultado.getResultado(), localresultado.getResultado());
	}
/*	@Test
	void testresta(){
		resultado localresultado =controlador.resta("15","7");
		assertresultado.setResultado(8);
		assertresultado.setEstado("ok");
		assertEquals(assertresultado.getEstado(),localresultado.getEstado());
		assertEquals(assertresultado.getResultado(),localresultado.getResultado());
	}
	@Test
	void testmultiplicar(){

		resultado mul = controlador.multiplicar("16","2");

		assertresultado.setResultado(32);
		assertresultado.setEstado("ok");

		assertEquals(assertresultado.getResultado(),mul.getResultado());
		assertEquals(assertresultado.getEstado(),mul.getEstado());

	}
	@Test
	void testDiv(){

		resultado res = controlador.dividir("27","3");

		assertresultado.setResultado(9);
		assertresultado.setEstado("ok");

		assertEquals(assertresultado.getEstado(),res.getEstado());
		assertEquals(assertresultado.getResultado(), res.getResultado());
	}

	/*@Test
	void testraiz(){
		resultado raiz = controlador.raiz("9");

		assertresultado.setResultado(3);

		assertEquals(assertresultado.getResultado(), raiz.getResultado_raiz());
	}*/


}