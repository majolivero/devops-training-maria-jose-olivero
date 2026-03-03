package com.entrenamiento.Calculadora;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import com.entrenamiento.Calculadora.controlador.controlador;
import com.entrenamiento.Calculadora.modelo.resultado;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Tests de la Calculadora - Operación Suma")
class CalculadoraApplicationTests {

	private controlador calc;

	@BeforeEach  //Indica que este método se ejecutará antes de cada prueba, asegurando que cada test tenga una instancia fresca del controlador
	void setUp() {
		calc = new controlador();  //calc tiene un objeto controlador listo para usar
	}

	// ==================== TESTS DE SUMA ====================
	// ==================== PRUEBAS DE ENTRADA DE DATOS ====================

	@Test
	@DisplayName("Suma: Aceptar números enteros positivos")
	void testSumaEnterosPositivos() {
		// Arrange (Organizar): Preparacion del escenario de prueba (variables y datos de entrada)
		String op1 = "159";
		String op2 = "18";
		float resultadoEsperado = 177.0f;
		String estadoEsperado = "ok";

		// Act (Actuar): Ejecutar la acción o método que se desea probar
		resultado resultado = calc.suma(op1, op2);

		// Assert (Afirmar): Verificar que los resultados sean los esperados
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe retornar estado 'ok' con enteros positivos");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe calcular correctamente: 159 + 18 = 177");
	}

	@Test
	@DisplayName("Suma: Aceptar números decimales con punto")
	void testSumaDecimalesConPunto() {
		// Arrange
		String op1 = "5.5";
		String op2 = "3.2";
		float resultadoEsperado = 8.7f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.suma(op1, op2);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe aceptar decimales con punto");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe sumar decimales correctamente: 5.5 + 3.2 = 8.7");
	}

	@Test
	@DisplayName("Suma: Aceptar números negativos")
	void testSumaConNumerosNegativos() {
		// Arrange
		String op1 = "-10";
		String op2 = "5";
		float resultadoEsperado = -5.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.suma(op1, op2);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe aceptar números negativos");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe calcular correctamente: -10 + 5 = -5");
	}

	@Test
	@DisplayName("Suma: Ambos números negativos")
	void testSumaAmbosNegativos() {
		// Arrange
		String op1 = "-15";
		String op2 = "-25";
		float resultadoEsperado = -40.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.suma(op1, op2);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe aceptar ambos números negativos");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe calcular correctamente: -15 + (-25) = -40");
	}

	// ==================== PRUEBAS DE CÁLCULO Y LÓGICA ====================

	@Test
	@DisplayName("Suma: Correcta aplicación de lógica matemática (mismo signo positivo)")
	void testSumaLogicaMatematicaSignoPositivo() {
		// Arrange - Números con mismo signo (positivos)
		String sumandoA = "100";
		String sumandoB = "50";
		float resultadoEsperado = 150.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.suma(sumandoA, sumandoB);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Suma de números con mismo signo: 100 + 50 = 150");
	}

	@Test
	@DisplayName("Suma: Correcta aplicación de lógica matemática (signo diferente)")
	void testSumaLogicaMatematicaSignoDiferente() {
		// Arrange - Números con signo diferente
		String sumandoA = "20";
		String sumandoB = "-7";
		float resultadoEsperado = 13.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.suma(sumandoA, sumandoB);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Suma de números con signo diferente: 20 + (-7) = 13");
	}

	// ==================== PRUEBAS DE PRECISIÓN Y ALMACENAMIENTO ====================

	/*
	@Test
	@DisplayName("Suma: Resultado con precisión de 1 decimal")
	void testSumaPrecisionUnDecimal() {
		// Arrange
		String op1 = "10.33";
		String op2 = "5.67";
		float resultadoEsperado = 16.0f; // 10.33 + 5.67 = 16.00 redondeado a 1 decimal
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.suma(op1, op2);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 0.1f,
			"El resultado debe redondearse a 1 decimal máximo");
	}
	*/

	@Test
	@DisplayName("Suma: El resultado se almacena correctamente")
	void testSumaAlmacenamientoResultado() {
		// Arrange
		String op1 = "25";
		String op2 = "75";
		float resultadoEsperado = 100.0f;

		// Act
		resultado resultado = calc.suma(op1, op2);

		// Assert
		assertEquals(resultadoEsperado, resultado.getResultado(),
			"El resultado debe ser almacenado correctamente");
	}

	// ==================== PRUEBAS DE VALIDACIÓN Y ERRORES ====================

	@Test
	@DisplayName("Suma: Campo vacío op1 retorna error")
	void testSumaCampoVacioOp1() {
		// Arrange
		String op1 = "";
		String op2 = "10";
		String estadoEsperado = "Ambos campos son obligatorios";

		// Act
		resultado resultado = calc.suma(op1, op2);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe mostrar advertencia cuando op1 está vacío");
	}

	@Test
	@DisplayName("Suma: Campo vacío op2 retorna error")
	void testSumaCampoVacioOp2() {
		// Arrange
		String op1 = "10";
		String op2 = "";
		String estadoEsperado = "Ambos campos son obligatorios";

		// Act
		resultado resultado = calc.suma(op1, op2);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe mostrar advertencia cuando op2 está vacío");
	}

	@Test
	@DisplayName("Suma: Ambos campos vacíos retornan error")
	void testSumaAmbosVacios() {
		// Arrange
		String op1 = "";
		String op2 = "";
		String estadoEsperado = "Ambos campos son obligatorios";

		// Act
		resultado resultado = calc.suma(op1, op2);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe mostrar advertencia cuando ambos campos están vacíos");
	}

	/*
	@Test
	@DisplayName("Suma: Valor alfanumérico en op1 retorna error")
	void testSumaCaracterInvalidoOp1() {
		// Arrange
		String op1 = "abc";
		String op2 = "10";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.suma(op1, op2);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error cuando op1 contiene caracteres inválidos");
	}
	*/

	/*
	@Test
	@DisplayName("Suma: Valor alfanumérico en op2 retorna error")
	void testSumaCaracterInvalidoOp2() {
		// Arrange
		String op1 = "10";
		String op2 = "xyz";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.suma(op1, op2);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error cuando op2 contiene caracteres inválidos");
	}
	*/

	/*
	@Test
	@DisplayName("Suma: Caracteres especiales retornan error")
	void testSumaCaracteresEspeciales() {
		// Arrange
		String op1 = "10@";
		String op2 = "5$";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.suma(op1, op2);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error con caracteres especiales");
	}
	*/

	// ==================== TESTS DE RESTA ====================
	// ==================== PRUEBAS DE ENTRADA DE DATOS ====================

	@Test
	@DisplayName("Resta: Aceptar números enteros positivos")
	void testRestaEnterosPositivos() {
		// Arrange
		String minuendo = "159";
		String sustraendo = "18";
		float resultadoEsperado = 141.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.resta(minuendo, sustraendo);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe retornar estado 'ok' con enteros positivos");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe calcular correctamente: 159 - 18 = 141");
	}

	/*
	@Test
	@DisplayName("Resta: Aceptar numbers decimales con punto")
	void testRestaDecimalesConPunto() {
		// Arrange
		String minuendo = "10.5";
		String sustraendo = "3.2";
		float resultadoEsperado = 7.3f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.resta(minuendo, sustraendo);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe aceptar decimales con punto");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe restar decimales correctamente: 10.5 - 3.2 = 7.3");
	}
	*/

	@Test
	@DisplayName("Resta: Aceptar minuendo negativo")
	void testRestaMinuendoNegativo() {
		// Arrange
		String minuendo = "-20";
		String sustraendo = "5";
		float resultadoEsperado = -25.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.resta(minuendo, sustraendo);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe aceptar minuendo negativo");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe calcular correctamente: -20 - 5 = -25");
	}

	/*
	@Test
	@DisplayName("Resta: Aceptar sustraendo negativo")
	void testRestasustraendoNegativo() {
		// Arrange
		String minuendo = "15";
		String sustraendo = "-10";
		float resultadoEsperado = 25.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.resta(minuendo, sustraendo);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe aceptar sustraendo negativo");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe calcular correctamente: 15 - (-10) = 25");
	}
	*/

	@Test
	@DisplayName("Resta: Ambos números negativos")
	void testRestaAmbosNegativos() {
		// Arrange
		String minuendo = "-15";
		String sustraendo = "-25";
		float resultadoEsperado = 10.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.resta(minuendo, sustraendo);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe aceptar ambos números negativos");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe calcular correctamente: -15 - (-25) = 10");
	}

	// ==================== PRUEBAS DE CÁLCULO Y LÓGICA ====================

	@Test
	@DisplayName("Resta: Correcta aplicación de lógica matemática (resultado positivo)")
	void testRestaLogicaMatematicaPositiva() {
		// Arrange - Minuendo mayor que sustraendo
		String minuendo = "100";
		String sustraendo = "30";
		float resultadoEsperado = 70.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.resta(minuendo, sustraendo);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Resta con resultado positivo: 100 - 30 = 70");
	}

	@Test
	@DisplayName("Resta: Correcta aplicación de lógica matemática (resultado negativo)")
	void testRestaLogicaMatematicaNegativa() {
		// Arrange - Sustraendo mayor que minuendo
		String minuendo = "20";
		String sustraendo = "50";
		float resultadoEsperado = -30.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.resta(minuendo, sustraendo);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Resta con resultado negativo: 20 - 50 = -30");
	}

	// ==================== PRUEBAS DE PRECISIÓN Y ALMACENAMIENTO ====================

	@Test
	@DisplayName("Resta: Resultado con precisión de 1 decimal")
	void testRestaPrecisionUnDecimal() {
		// Arrange
		String minuendo = "10.75";
		String sustraendo = "3.33";
		float resultadoEsperado = 7.4f; // 10.75 - 3.33 = 7.42 redondeado a 7.4
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.resta(minuendo, sustraendo);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 0.1f,
			"El resultado debe redondearse a 1 decimal máximo");
	}

	@Test
	@DisplayName("Resta: El resultado se almacena correctamente")
	void testRestaAlmacenamientoResultado() {
		// Arrange
		String minuendo = "100";
		String sustraendo = "25";
		float resultadoEsperado = 75.0f;

		// Act
		resultado resultado = calc.resta(minuendo, sustraendo);

		// Assert
		assertEquals(resultadoEsperado, resultado.getResultado(),
			"El resultado debe ser almacenado correctamente");
	}

	// ==================== PRUEBAS DE VALIDACIÓN Y ERRORES ====================

	@Test
	@DisplayName("Resta: Minuendo vacío retorna error")
	void testRestaminuendoVacio() {
		// Arrange
		String minuendo = "";
		String sustraendo = "10";
		String estadoEsperado = "Ambos campos son obligatorios";

		// Act
		resultado resultado = calc.resta(minuendo, sustraendo);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe mostrar advertencia cuando minuendo está vacío");
	}

	@Test
	@DisplayName("Resta: Sustraendo vacío retorna error")
	void testRestasustraendoVacio() {
		// Arrange
		String minuendo = "10";
		String sustraendo = "";
		String estadoEsperado = "Ambos campos son obligatorios";

		// Act
		resultado resultado = calc.resta(minuendo, sustraendo);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe mostrar advertencia cuando sustraendo está vacío");
	}

	@Test
	@DisplayName("Resta: Ambos campos vacíos retornan error")
	void testRestaAmbosVacios() {
		// Arrange
		String minuendo = "";
		String sustraendo = "";
		String estadoEsperado = "Ambos campos son obligatorios";

		// Act
		resultado resultado = calc.resta(minuendo, sustraendo);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe mostrar advertencia cuando ambos campos están vacíos");
	}

	/*
	@Test
	@DisplayName("Resta: Carácter alfanumérico en minuendo retorna error")
	void testRestaCaracterInvalidoMinuendo() {
		// Arrange
		String minuendo = "abc";
		String sustraendo = "10";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.resta(minuendo, sustraendo);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error cuando minuendo contiene caracteres inválidos");
	}
	*/

	/*
	@Test
	@DisplayName("Resta: Carácter alfanumérico en sustraendo retorna error")
	void testRestaCaracterInvalidoSustraendo() {
		// Arrange
		String minuendo = "10";
		String sustraendo = "xyz";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.resta(minuendo, sustraendo);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error cuando sustraendo contiene caracteres inválidos");
	}
	*/

	/*
	@Test
	@DisplayName("Resta: Caracteres especiales retornan error")
	void testRestaCaracteresEspeciales() {
		// Arrange
		String minuendo = "20#";
		String sustraendo = "5&";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.resta(minuendo, sustraendo);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error con caracteres especiales");
	}
	*/

	// ==================== TESTS DE MULTIPLICACIÓN ====================
	// ==================== PRUEBAS DE ENTRADA DE DATOS ====================

	@Test
	@DisplayName("Multiplicación: Aceptar números enteros positivos")
	void testMultiplicacionEnterosPositivos() {
		// Arrange
		String multiplicando = "12";
		String multiplicador = "5";
		float resultadoEsperado = 60.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.multiplicar(multiplicando, multiplicador);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe retornar estado 'ok' con enteros positivos");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe calcular correctamente: 12 * 5 = 60");
	}

	/*
	@Test
	@DisplayName("Multiplicación: Aceptar números decimales con punto")
	void testMultiplicacionDecimalesConPunto() {
		// Arrange
		String multiplicando = "2.5";
		String multiplicador = "4.0";
		float resultadoEsperado = 10.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.multiplicar(multiplicando, multiplicador);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe aceptar decimales con punto");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe multiplicar decimales correctamente: 2.5 * 4 = 10.0");
	}
	*/

	@Test
	@DisplayName("Multiplicación: Aceptar multiplicando negativo")
	void testMultiplicacionmultiplicandoNegativo() {
		// Arrange
		String multiplicando = "-6";
		String multiplicador = "3";
		float resultadoEsperado = -18.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.multiplicar(multiplicando, multiplicador);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe aceptar multiplicando negativo");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe calcular correctamente: -6 * 3 = -18");
	}

	@Test
	@DisplayName("Multiplicación: Aceptar multiplicador negativo")
	void testMultiplicacionmultiplicadorNegativo() {
		// Arrange
		String multiplicando = "7";
		String multiplicador = "-4";
		float resultadoEsperado = -28.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.multiplicar(multiplicando, multiplicador);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe aceptar multiplicador negativo");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe calcular correctamente: 7 * -4 = -28");
	}

	@Test
	@DisplayName("Multiplicación: Ambos números negativos")
	void testMultiplicacionAmbosNegativos() {
		// Arrange
		String multiplicando = "-5";
		String multiplicador = "-3";
		float resultadoEsperado = 15.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.multiplicar(multiplicando, multiplicador);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe aceptar ambos números negativos");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe calcular correctamente: -5 * -3 = 15");
	}

	// ==================== PRUEBAS DE CÁLCULO Y LÓGICA ====================

	/*
	@Test
	@DisplayName("Multiplicación: Cualquier número multiplicado por cero es cero")
	void testMultiplicacionPorCero() {
		// Arrange - Caso especial: cualquier número * 0 = 0
		String multiplicando = "150";
		String multiplicador = "0";
		float resultadoEsperado = 0.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.multiplicar(multiplicando, multiplicador);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Cualquier número multiplicado por 0 debe ser 0");
	}
	*/

	/*
	@Test
	@DisplayName("Multiplicación: Signos iguales dan resultado positivo")
	void testMultiplicacionSignosIgualesPositivo() {
		// Arrange - Números con signo igual (ambos positivos)
		String multiplicando = "8";
		String multiplicador = "6";
		float resultadoEsperado = 48.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.multiplicar(multiplicando, multiplicador);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Signos iguales (positivos): 8 * 6 = 48");
	}
	*/

	@Test
	@DisplayName("Multiplicación: Signos iguales negativos dan resultado positivo")
	void testMultiplicacionSignosIgualesNegativo() {
		// Arrange - Números con signo igual (ambos negativos)
		String multiplicando = "-9";
		String multiplicador = "-5";
		float resultadoEsperado = 45.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.multiplicar(multiplicando, multiplicador);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Signos iguales (negativos): -9 * -5 = 45");
	}

	/*
	@Test
	@DisplayName("Multiplicación: Signos diferentes dan resultado negativo")
	void testMultiplicacionSignosDiferentes() {
		// Arrange - Números con signo diferente
		String multiplicando = "10";
		String multiplicador = "-7";
		float resultadoEsperado = -70.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.multiplicar(multiplicando, multiplicador);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Signos diferentes: 10 * -7 = -70");
	}
	*/

	// ==================== PRUEBAS DE PRECISIÓN Y ALMACENAMIENTO ====================

	/*
	@Test
	@DisplayName("Multiplicación: Resultado con precisión de 1 decimal")
	void testMultiplicacionPrecisionUnDecimal() {
		// Arrange
		String multiplicando = "3.5";
		String multiplicador = "2.6";
		float resultadoEsperado = 9.1f; // 3.5 * 2.6 = 9.1 redondeado a 1 decimal
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.multiplicar(multiplicando, multiplicador);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 0.1f,
			"El resultado debe redondearse a 1 decimal máximo");
	}
	*/

	/*
	@Test
	@DisplayName("Multiplicación: El resultado se almacena correctamente")
	void testMultiplicacionAlmacenamientoResultado() {
		// Arrange
		String multiplicando = "15";
		String multiplicador = "6";
		float resultadoEsperado = 90.0f;

		// Act
		resultado resultado = calc.multiplicar(multiplicando, multiplicador);

		// Assert
		assertEquals(resultadoEsperado, resultado.getResultado(),
			"El resultado debe ser almacenado correctamente");
	}
	*/

	// ==================== PRUEBAS DE VALIDACIÓN Y ERRORES ====================

	/*
	@Test
	@DisplayName("Multiplicación: Multiplicando vacío retorna error")
	void testMultiplicacionmultiplicandoVacio() {
		// Arrange
		String multiplicando = "";
		String multiplicador = "10";
		String estadoEsperado = "Ambos campos son obligatorios";

		// Act
		resultado resultado = calc.multiplicar(multiplicando, multiplicador);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe mostrar advertencia cuando multiplicando está vacío");
	}
	*/

	@Test
	@DisplayName("Multiplicación: Multiplicador vacío retorna error")
	void testMultiplicacionmultiplicadorVacio() {
		// Arrange
		String multiplicando = "10";
		String multiplicador = "";
		String estadoEsperado = "Ambos campos son obligatorios";

		// Act
		resultado resultado = calc.multiplicar(multiplicando, multiplicador);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe mostrar advertencia cuando multiplicador está vacío");
	}

	@Test
	@DisplayName("Multiplicación: Ambos campos vacíos retornan error")
	void testMultiplicacionAmbosVacios() {
		// Arrange
		String multiplicando = "";
		String multiplicador = "";
		String estadoEsperado = "Ambos campos son obligatorios";

		// Act
		resultado resultado = calc.multiplicar(multiplicando, multiplicador);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe mostrar advertencia cuando ambos campos están vacíos");
	}

	/*
	@Test
	@DisplayName("Multiplicación: Carácter alfanumérico en multiplicando retorna error")
	void testMultiplicacionCaracterInvalidomultiplicando() {
		// Arrange
		String multiplicando = "abc";
		String multiplicador = "10";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.multiplicar(multiplicando, multiplicador);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error cuando multiplicando contiene caracteres inválidos");
	}
	*/

	@Test
	@DisplayName("Multiplicación: Carácter alfanumérico en multiplicador retorna error")
	void testMultiplicacionCaracterInvalidomultiplicador() {
		// Arrange
		String multiplicando = "10";
		String multiplicador = "xyz";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.multiplicar(multiplicando, multiplicador);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error cuando multiplicador contiene caracteres inválidos");
	}

	/*
	@Test
	@DisplayName("Multiplicación: Caracteres especiales retornan error")
	void testMultiplicacionCaracteresEspeciales() {
		// Arrange
		String multiplicando = "15*";
		String multiplicador = "3/";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.multiplicar(multiplicando, multiplicador);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error con caracteres especiales");
	}
	*/

	// ==================== TESTS DE DIVISIÓN ====================
	// ==================== PRUEBAS DE ENTRADA DE DATOS ====================

	@Test
	@DisplayName("División: Aceptar números enteros positivos")
	void testDivisionEnterosPositivos() {
		// Arrange
		String dividendo = "20";
		String divisor = "4";
		float resultadoEsperado = 5.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.dividir(dividendo, divisor);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe retornar estado 'ok' con enteros positivos");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe calcular correctamente: 20 / 4 = 5");
	}

	/*
	@Test
	@DisplayName("División: Aceptar números decimales con punto")
	void testDivisionDecimalesConPunto() {
		// Arrange
		String dividendo = "10.5";
		String divisor = "2.5";
		float resultadoEsperado = 4.2f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.dividir(dividendo, divisor);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe aceptar decimales con punto");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe dividir decimales correctamente: 10.5 / 2.5 = 4.2");
	}
	*/

	@Test
	@DisplayName("División: Aceptar dividendo negativo")
	void testDivisionDividendoNegativo() {
		// Arrange
		String dividendo = "-30";
		String divisor = "5";
		float resultadoEsperado = -6.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.dividir(dividendo, divisor);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe aceptar dividendo negativo");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe calcular correctamente: -30 / 5 = -6");
	}

	@Test
	@DisplayName("División: Aceptar divisor negativo")
	void testDivisionDivisorNegativo() {
		// Arrange
		String dividendo = "24";
		String divisor = "-3";
		float resultadoEsperado = -8.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.dividir(dividendo, divisor);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe aceptar divisor negativo");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe calcular correctamente: 24 / -3 = -8");
	}

	@Test
	@DisplayName("División: Ambos números negativos")
	void testDivisionAmbosNegativos() {
		// Arrange
		String dividendo = "-45";
		String divisor = "-9";
		float resultadoEsperado = 5.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.dividir(dividendo, divisor);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe aceptar ambos números negativos");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe calcular correctamente: -45 / -9 = 5");
	}

	// ==================== PRUEBAS DE CÁLCULO Y LÓGICA ====================

	@Test
	@DisplayName("División: Aplicar lógica matemática estándar")
	void testDivisionLogicaEstandar() {
		// Arrange
		String dividendo = "100";
		String divisor = "5";
		float resultadoEsperado = 20.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.dividir(dividendo, divisor);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe aplicar: Cociente = Dividendo / Divisor");
	}

	@Test
	@DisplayName("División: Signos iguales dan resultado positivo")
	void testDivisionSignosIgualesPositivo() {
		// Arrange
		String dividendo = "40";
		String divisor = "8";
		float resultadoEsperado = 5.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.dividir(dividendo, divisor);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Signos iguales (positivos): 40 / 8 = 5");
	}

	@Test
	@DisplayName("División: Signos iguales negativos dan resultado positivo")
	void testDivisionSignosIgualesNegativo() {
		// Arrange
		String dividendo = "-36";
		String divisor = "-6";
		float resultadoEsperado = 6.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.dividir(dividendo, divisor);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Signos iguales (negativos): -36 / -6 = 6");
	}

	/*
	@Test
	@DisplayName("División: Signos diferentes dan resultado negativo")
	void testDivisionSignosDiferentes() {
		// Arrange
		String dividendo = "50";
		String divisor = "-5";
		float resultadoEsperado = -10.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.dividir(dividendo, divisor);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Signos diferentes: 50 / -5 = -10");
	}
	*/

	// ==================== PRUEBAS DE VALIDACIÓN DE DIVISIÓN POR CERO ====================

	@Test
	@DisplayName("División: Divisor cero retorna error específico")
	void testDivisionPorCero() {
		// Arrange - Caso especial: No es posible dividir por cero
		String dividendo = "100";
		String divisor = "0";
		String estadoEsperado = "Error: No es posible dividir por cero";

		// Act
		resultado resultado = calc.dividir(dividendo, divisor);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error específico cuando divisor es cero");
	}

	@Test
	@DisplayName("División: Divisor cero negativo retorna error")
	void testDivisionPorCeroNegativo() {
		// Arrange - Caso donde divisor es -0 (técnicamente 0)
		String dividendo = "50";
		String divisor = "-0";
		String estadoEsperado = "Error: No es posible dividir por cero";

		// Act
		resultado resultado = calc.dividir(dividendo, divisor);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error cuando divisor es cero");
	}

	// ==================== PRUEBAS DE PRECISIÓN Y ALMACENAMIENTO ====================

	/*
	/*
	@Test
	@DisplayName("División: Resultado con precisión de 1 decimal")
	void testDivisionPrecisionUnDecimal() {
		// Arrange
		String dividendo = "10.0";
		String divisor = "3.0";
		float resultadoEsperado = 3.3f; // 10 / 3 = 3.333... redondeado a 3.3
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.dividir(dividendo, divisor);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 0.1f,
			"El resultado debe redondearse a 1 decimal máximo");
	}
	*/

	@Test
	@DisplayName("División: El resultado se almacena correctamente")
	void testDivisionAlmacenamientoResultado() {
		// Arrange
		String dividendo = "84";
		String divisor = "7";
		float resultadoEsperado = 12.0f;

		// Act
		resultado resultado = calc.dividir(dividendo, divisor);

		// Assert
		assertEquals(resultadoEsperado, resultado.getResultado(),
			"El resultado debe ser almacenado correctamente");
	}

	// ==================== PRUEBAS DE VALIDACIÓN Y ERRORES ====================

	@Test
	@DisplayName("División: Dividendo vacío retorna error")
	void testDivisionDividendoVacio() {
		// Arrange
		String dividendo = "";
		String divisor = "10";
		String estadoEsperado = "Ambos campos son obligatorios";

		// Act
		resultado resultado = calc.dividir(dividendo, divisor);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe mostrar advertencia cuando dividendo está vacío");
	}

	@Test
	@DisplayName("División: Divisor vacío retorna error")
	void testDivisionDivisorVacio() {
		// Arrange
		String dividendo = "10";
		String divisor = "";
		String estadoEsperado = "Ambos campos son obligatorios";

		// Act
		resultado resultado = calc.dividir(dividendo, divisor);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe mostrar advertencia cuando divisor está vacío");
	}

	@Test
	@DisplayName("División: Ambos campos vacíos retornan error")
	void testDivisionAmbosVacios() {
		// Arrange
		String dividendo = "";
		String divisor = "";
		String estadoEsperado = "Ambos campos son obligatorios";

		// Act
		resultado resultado = calc.dividir(dividendo, divisor);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe mostrar advertencia cuando ambos campos están vacíos");
	}

	@Test
	@DisplayName("División: Carácter alfanumérico en dividendo retorna error")
	void testDivisionCaracterInvalidoDividendo() {
		// Arrange
		String dividendo = "abc";
		String divisor = "10";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.dividir(dividendo, divisor);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error cuando dividendo contiene caracteres inválidos");
	}

	@Test
	@DisplayName("División: Carácter alfanumérico en divisor retorna error")
	void testDivisionCaracterInvalidoDivisor() {
		// Arrange
		String dividendo = "10";
		String divisor = "xyz";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.dividir(dividendo, divisor);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error cuando divisor contiene caracteres inválidos");
	}

	/*
	@Test
	@DisplayName("División: Caracteres especiales retornan error")
	void testDivisionCaracteresEspeciales() {
		// Arrange
		String dividendo = "20@";
		String divisor = "4#";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.dividir(dividendo, divisor);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error con caracteres especiales");
	}
	*/

	// ==================== TESTS DE RAÍZ CUADRADA ====================
	// ==================== PRUEBAS DE ENTRADA DE DATOS ====================

	@Test
	@DisplayName("Raíz Cuadrada: Aceptar números enteros positivos")
	void testRaizEnterosPositivos() {
		// Arrange
		String radicando = "16";
		float resultadoEsperado = 4.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.raiz(radicando);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe retornar estado 'ok' con enteros positivos");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe calcular correctamente: √16 = 4");
	}

	/*
	@Test
	@DisplayName("Raíz Cuadrada: Aceptar números decimales con punto")
	void testRaizDecimalesConPunto() {
		// Arrange
		String radicando = "6.25";
		float resultadoEsperado = 2.5f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.raiz(radicando);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe aceptar decimales con punto");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe calcular correctamente: √6.25 = 2.5");
	}
	*/

	/*
	@Test
	@DisplayName("Raíz Cuadrada: Raíz de cero es cero")
	void testRaizDeCero() {
		// Arrange
		String radicando = "0";
		float resultadoEsperado = 0.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.raiz(radicando);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe aceptar cero como radicando");
		assertEquals(resultadoEsperado, resultado.getResultado(),
			"La raíz de cero debe ser cero: √0 = 0");
	}
	*/

	// ==================== PRUEBAS DE VALIDACIÓN NÚMEROS NEGATIVOS ====================

	/*
	@Test
	@DisplayName("Raíz Cuadrada: Número negativo retorna error")
	void testRaizNumeroNegativo() {
		// Arrange - No se puede calcular raíz cuadrada de números negativos
		String radicando = "-9";
		String estadoEsperado = "Error: No se puede calcular la raíz cuadrada de un número negativo";

		// Act
		resultado resultado = calc.raiz(radicando);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error cuando radicando es negativo");
	}
	*/

	/*
	@Test
	@DisplayName("Raíz Cuadrada: Número decimal negativo retorna error")
	void testRaizDecimalNegativo() {
		// Arrange
		String radicando = "-2.5";
		String estadoEsperado = "Error: No se puede calcular la raíz cuadrada de un número negativo";

		// Act
		resultado resultado = calc.raiz(radicando);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error con decimal negativo");
	}
	*/

	// ==================== PRUEBAS DE CÁLCULO Y LÓGICA ====================

	/*
	@Test
	@DisplayName("Raíz Cuadrada: Aplicar lógica matemática estándar")
	void testRaizLogicaEstandar() {
		// Arrange
		String radicando = "25";
		float resultadoEsperado = 5.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.raiz(radicando);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(),
			"Debe aplicar: Resultado = √Radicando");
	}
	*/

	/*
	@Test
	@DisplayName("Raíz Cuadrada: Resultado perfecto con decimales")
	void testRaizResultadoPerfectoDecimal() {
		// Arrange
		String radicando = "100.0";
		float resultadoEsperado = 10.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.raiz(radicando);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(),
			"Debe calcular raíz de decimal: √100.0 = 10.0");
	}
	*/

	// ==================== PRUEBAS DE PRECISIÓN ====================

	@Test
	@DisplayName("Raíz Cuadrada: Precisión de al menos 4 decimales para raíz irracional")
	void testRaizPrecisionCuatroDecimales() {
		// Arrange - Raíz de 2 es irracional: 1.4142135...
		String radicando = "2";
		float resultadoEsperado = 1.4142f; // Al menos 4 decimales
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.raiz(radicando);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		// Verificar con tolerancia de 0.00015 para pequeñas variaciones de redondeo
		assertEquals(resultadoEsperado, resultado.getResultado(), 0.00015f,  //diferencia maxima permitida entre resultado esperado y resultado actual
			"Debe mostrar al menos 4 decimales para resultados irracionales");
	}

	/*
	@Test
	@DisplayName("Raíz Cuadrada: Precisión en raíz de 3")
	void testRaizPrecisionTres() {
		// Arrange - √3 ≈ 1.7320508... redondeado a 1.7321
		String radicando = "3";
		float resultadoEsperado = 1.7321f; // Redondeado a 4 decimales
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.raiz(radicando);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 0.00015f,
			"Debe mantener precisión de 4 decimales");
	}
	*/

	/*
	@Test
	@DisplayName("Raíz Cuadrada: El resultado se almacena correctamente")
	void testRaizAlmacenamientoResultado() {
		// Arrange
		String radicando = "49";
		float resultadoEsperado = 7.0f;

		// Act
		resultado resultado = calc.raiz(radicando);

		// Assert
		assertEquals(resultadoEsperado, resultado.getResultado(),
			"El resultado debe ser almacenado correctamente");
	}
	*/

	// ==================== PRUEBAS DE VALIDACIÓN Y ERRORES ====================

	/*
	@Test
	@DisplayName("Raíz Cuadrada: Campo vacío retorna error")
	void testRaizCampoVacio() {
		// Arrange
		String radicando = "";
		String estadoEsperado = "El campo de entrada debe contener un valor numérico";

		// Act
		resultado resultado = calc.raiz(radicando);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe mostrar advertencia cuando campo está vacío");
	}
	*/

	/*
	@Test
	@DisplayName("Raíz Cuadrada: Carácter alfanumérico retorna error")
	void testRaizCaracterAlfanumerico() {
		// Arrange
		String radicando = "abc";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.raiz(radicando);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error con caracteres alfabéticos");
	}
	*/

	/*
	@Test
	@DisplayName("Raíz Cuadrada: Caracteres especiales retornan error")
	void testRaizCaracteresEspeciales() {
		// Arrange
		String radicando = "16@";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.raiz(radicando);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error con caracteres especiales");
	}
	*/

	// ==================== TESTS DE PERÍMETRO DE CIRCUNFERENCIA ====================
	// ==================== PRUEBAS DE ENTRADA DE DATOS ====================

	/*
	@Test
	@DisplayName("Perímetro Círculo: Aceptar radio entero positivo")
	void testPerimetroCirculoRadioEnterPositivo() {
		// Arrange
		String radio = "5";
		// Perímetro = 2 * Pi * 5 = 31.4159... redondeado a 31.4
		float resultadoEsperado = 31.4f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.perimetroCircle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe retornar estado 'ok' con radio entero positivo");
		assertEquals(resultadoEsperado, resultado.getResultado(), 0.1f,
			"Debe calcular correctamente: Perímetro = 2 * π * r");
	}
	*/

	/*
	@Test
	@DisplayName("Perímetro Círculo: Aceptar radio decimal positivo")
	void testPerimetroCirculoRadioDecimalPositivo() {
		// Arrange
		String radio = "2.5";
		// Perímetro = 2 * Pi * 2.5 = 15.7079... redondeado a 15.7
		float resultadoEsperado = 15.7f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.perimetroCircle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe aceptar radio decimal positivo");
		assertEquals(resultadoEsperado, resultado.getResultado(), 0.1f,
			"Debe calcular correctamente con decimales");
	}
	*/

	// ==================== PRUEBAS DE VALIDACIÓN RADIO ====================

	/*
	@Test
	@DisplayName("Perímetro Círculo: Radio cero retorna error")
	void testPerimetroCirculoRadioCero() {
		// Arrange - Radio debe ser > 0
		String radio = "0";
		String estadoEsperado = "El radio debe ser un valor positivo y diferente de cero";

		// Act
		resultado resultado = calc.perimetroCircle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error cuando radio es cero");
	}
	*/

	/*
	@Test
	@DisplayName("Perímetro Círculo: Radio negativo retorna error")
	void testPerimetroCirculoRadioNegativo() {
		// Arrange
		String radio = "-3";
		String estadoEsperado = "El radio debe ser un valor positivo y diferente de cero";

		// Act
		resultado resultado = calc.perimetroCircle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error cuando radio es negativo");
	}
	*/

	/*
	@Test
	@DisplayName("Perímetro Círculo: Radio decimal negativo retorna error")
	void testPerimetroCirculoRadioDecimalNegativo() {
		// Arrange
		String radio = "-1.5";
		String estadoEsperado = "El radio debe ser un valor positivo y diferente de cero";

		// Act
		resultado resultado = calc.perimetroCircle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error con radio decimal negativo");
	}
	*/

	// ==================== PRUEBAS DE CÁLCULO Y LÓGICA ====================

	/*
	@Test
	@DisplayName("Perímetro Círculo: Aplicar fórmula matemática estándar")
	void testPerimetroCirculoFormulaEstandar() {
		// Arrange
		String radio = "10";
		// Perímetro = 2 * Pi * 10 = 62.8318... redondeado a 62.8
		float resultadoEsperado = 62.8f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.perimetroCircle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 0.1f,
			"Debe aplicar: Perímetro = 2 * π * r");
	}
	*/

	/*
	@Test
	@DisplayName("Perímetro Círculo: Radio 1 retorna 2*Pi aproximado")
	void testPerimetroCirculoRadioUno() {
		// Arrange - Radio = 1: Perímetro = 2 * Pi ≈ 6.2831... redondeado a 6.3
		String radio = "1";
		float resultadoEsperado = 6.3f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.perimetroCircle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 0.1f,
			"Perímetro de círculo con radio 1: 2π ≈ 6.3");
	}
	*/

	// ==================== PRUEBAS DE PRECISIÓN Y ALMACENAMIENTO ====================

	/*
	@Test
	@DisplayName("Perímetro Círculo: Resultado con precisión de 1 decimal")
	void testPerimetroCirculoPrecisionUnDecimal() {
		// Arrange
		String radio = "7.5";
		// Perímetro = 2 * Pi * 7.5 = 47.1238... redondeado a 47.1
		float resultadoEsperado = 47.1f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.perimetroCircle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 0.1f,
			"El resultado debe redondearse a 1 decimal máximo");
	}
	*/

	/*
	@Test
	@DisplayName("Perímetro Círculo: El resultado se almacena correctamente")
	void testPerimetroCirculoAlmacenamientoResultado() {
		// Arrange
		String radio = "3";
		// Perímetro = 2 * Pi * 3 = 18.8495... 
		float resultadoEsperado = 18.8f;

		// Act
		resultado resultado = calc.perimetroCircle(radio);

		// Assert
		assertEquals(resultadoEsperado, resultado.getResultado(), 0.1f,
			"El resultado debe ser almacenado correctamente");
	}
	*/

	// ==================== PRUEBAS DE VALIDACIÓN Y ERRORES ====================

	/*
	@Test
	@DisplayName("Perímetro Círculo: Campo vacío retorna error")
	void testPerimetroCirculoCampoVacio() {
		// Arrange
		String radio = "";
		String estadoEsperado = "El campo de entrada debe contener un valor numérico";

		// Act
		resultado resultado = calc.perimetroCircle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe mostrar advertencia cuando campo está vacío");
	}
	*/

	/*
	@Test
	@DisplayName("Perímetro Círculo: Carácter alfanumérico retorna error")
	void testPerimetroCirculoCaracterAlfanumerico() {
		// Arrange
		String radio = "abc";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.perimetroCircle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error con caracteres alfabéticos");
	}
	*/

	/*
	@Test
	@DisplayName("Perímetro Círculo: Caracteres especiales retornan error")
	void testPerimetroCirculoCaracteresEspeciales() {
		// Arrange
		String radio = "5@";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.perimetroCircle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error con caracteres especiales");
	}
	*/

	// ==================== TESTS DE ÁREA DE CIRCUNFERENCIA ====================
	// ==================== PRUEBAS DE ENTRADA DE DATOS ====================

	@Test
	@DisplayName("Área Círculo: Aceptar radio entero positivo")
	void testAreaCirculoRadioEntePositivo() {
		// Arrange
		String radio = "5";
		// Área = Pi * 5 * 5 = 78.5398... redondeado a 78.5
		float resultadoEsperado = 78.5f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.circle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe retornar estado 'ok' con radio entero positivo");
		assertEquals(resultadoEsperado, resultado.getResultado(), 0.1f,
			"Debe calcular correctamente: Área = π * r²");
	}

	/*
	@Test
	@DisplayName("Área Círculo: Aceptar radio decimal positivo")
	void testAreaCirculoRadioDecimalPositivo() {
		// Arrange
		String radio = "2.5";
		// Área = Pi * 2.5 * 2.5 = 19.6349... redondeado a 19.6
		float resultadoEsperado = 19.6f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.circle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe aceptar radio decimal positivo");
		assertEquals(resultadoEsperado, resultado.getResultado(), 0.1f,
			"Debe calcular correctamente con decimales");
	}
	*/

	// ==================== PRUEBAS DE VALIDACIÓN RADIO ====================

	/*
	@Test
	@DisplayName("Área Círculo: Radio cero retorna error")
	void testAreaCirculoRadioCero() {
		// Arrange - Radio debe ser > 0
		String radio = "0";
		String estadoEsperado = "El radio debe ser un valor positivo";

		// Act
		resultado resultado = calc.circle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error cuando radio es cero");
	}
	*/

	/*
	@Test
	@DisplayName("Área Círculo: Radio negativo retorna error")
	void testAreaCirculoRadioNegativo() {
		// Arrange
		String radio = "-3";
		String estadoEsperado = "El radio debe ser un valor positivo";

		// Act
		resultado resultado = calc.circle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error cuando radio es negativo");
	}
	*/

	/*
	@Test
	@DisplayName("Área Círculo: Radio decimal negativo retorna error")
	void testAreaCirculoRadioDecimalNegativo() {
		// Arrange
		String radio = "-1.5";
		String estadoEsperado = "El radio debe ser un valor positivo";

		// Act
		resultado resultado = calc.circle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error con radio decimal negativo");
	}
	*/

	// ==================== PRUEBAS DE CÁLCULO Y LÓGICA ====================

	/*
	@Test
	@DisplayName("Área Círculo: Aplicar fórmula matemática estándar")
	void testAreaCirculoFormulaEstandar() {
		// Arrange
		String radio = "10";
		// Área = Pi * 10 * 10 = 314.159... redondeado a 314.2
		float resultadoEsperado = 314.2f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.circle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 0.1f,
			"Debe aplicar: Área = π * r²");
	}
	*/

	/*
	@Test
	@DisplayName("Área Círculo: Radio 1 retorna Pi aproximado")
	void testAreaCirculoRadioUno() {
		// Arrange - Radio = 1: Área = Pi ≈ 3.1415... redondeado a 3.1
		String radio = "1";
		float resultadoEsperado = 3.1f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.circle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 0.1f,
			"Área de círculo con radio 1: π ≈ 3.1");
	}
	*/

	// ==================== PRUEBAS DE PRECISIÓN Y ALMACENAMIENTO ====================

	@Test
	@DisplayName("Área Círculo: Resultado con precisión de 1 decimal")
	void testAreaCirculoPrecisionUnDecimal() {
		// Arrange
		String radio = "7.5";
		// Área = Pi * 7.5 * 7.5 = 176.714... redondeado a 176.7
		float resultadoEsperado = 176.7f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.circle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 0.1f,
			"El resultado debe redondearse a 1 decimal máximo");
	}

	@Test
	@DisplayName("Área Círculo: El resultado se almacena correctamente")
	void testAreaCirculoAlmacenamientoResultado() {
		// Arrange
		String radio = "3";
		// Área = Pi * 3 * 3 = 28.274... 
		float resultadoEsperado = 28.3f;

		// Act
		resultado resultado = calc.circle(radio);

		// Assert
		assertEquals(resultadoEsperado, resultado.getResultado(), 0.1f,
			"El resultado debe ser almacenado correctamente");
	}

	// ==================== PRUEBAS DE VALIDACIÓN Y ERRORES ====================

	@Test
	@DisplayName("Área Círculo: Campo vacío retorna error")
	void testAreaCirculoCampoVacio() {
		// Arrange
		String radio = "";
		String estadoEsperado = "El campo de entrada debe contener un valor numérico";

		// Act
		resultado resultado = calc.circle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe mostrar advertencia cuando campo está vacío");
	}

	@Test
	@DisplayName("Área Círculo: Carácter alfanumérico retorna error")
	void testAreaCirculoCaracterAlfanumerico() {
		// Arrange
		String radio = "abc";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.circle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error con caracteres alfabéticos");
	}

	/*
	@Test
	@DisplayName("Área Círculo: Caracteres especiales retornan error")
	void testAreaCirculoCaracteresEspeciales() {
		// Arrange
		String radio = "5@";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.circle(radio);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error con caracteres especiales");
	}
	*/

	// ==================== TESTS DE PERÍMETRO DE CUADRADO ====================
	// ==================== PRUEBAS DE ENTRADA DE DATOS ====================

	@Test
	@DisplayName("Perímetro Cuadrado: Aceptar lado entero positivo")
	void testPerimetroCuadradoLadoEnteroPositivo() {
		// Arrange
		String lado = "5";
		// Perímetro = 4 * 5 = 20
		float resultadoEsperado = 20.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.perimetroCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe retornar estado 'ok' con lado entero positivo");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe calcular correctamente: Perímetro = 4 * l");
	}

	/*
	@Test
	@DisplayName("Perímetro Cuadrado: Aceptar lado decimal positivo")
	void testPerimetroCuadradoLadoDecimalPositivo() {
		// Arrange
		String lado = "2.5";
		// Perímetro = 4 * 2.5 = 10
		float resultadoEsperado = 10.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.perimetroCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe aceptar lado decimal positivo");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe calcular correctamente con decimales");
	}
	*/

	// ==================== PRUEBAS DE VALIDACIÓN LADO ====================

	@Test
	@DisplayName("Perímetro Cuadrado: Lado cero retorna error")
	void testPerimetroCuadradoLadoCero() {
		// Arrange - Lado debe ser > 0
		String lado = "0";
		String estadoEsperado = "El valor ingresado debe ser mayor que cero";

		// Act
		resultado resultado = calc.perimetroCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error cuando lado es cero");
	}

	@Test
	@DisplayName("Perímetro Cuadrado: Lado negativo retorna error")
	void testPerimetroCuadradoLadoNegativo() {
		// Arrange
		String lado = "-3";
		String estadoEsperado = "El valor ingresado debe ser mayor que cero";

		// Act
		resultado resultado = calc.perimetroCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error cuando lado es negativo");
	}

	@Test
	@DisplayName("Perímetro Cuadrado: Lado decimal negativo retorna error")
	void testPerimetroCuadradoLadoDecimalNegativo() {
		// Arrange
		String lado = "-1.5";
		String estadoEsperado = "El valor ingresado debe ser mayor que cero";

		// Act
		resultado resultado = calc.perimetroCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error con lado decimal negativo");
	}

	// ==================== PRUEBAS DE CÁLCULO Y LÓGICA ====================

	@Test
	@DisplayName("Perímetro Cuadrado: Aplicar fórmula matemática estándar")
	void testPerimetroCuadradoFormulaEstandar() {
		// Arrange
		String lado = "10";
		// Perímetro = 4 * 10 = 40
		float resultadoEsperado = 40.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.perimetroCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(),
			"Debe aplicar: Perímetro = 4 * l");
	}

	@Test
	@DisplayName("Perímetro Cuadrado: Lado 1 retorna 4")
	void testPerimetroCuadradoLadoUno() {
		// Arrange - Lado = 1: Perímetro = 4 * 1 = 4
		String lado = "1";
		float resultadoEsperado = 4.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.perimetroCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(),
			"Perímetro de cuadrado con lado 1: 4 * 1 = 4");
	}

	// ==================== PRUEBAS DE PRECISIÓN Y ALMACENAMIENTO ====================

	@Test
	@DisplayName("Perímetro Cuadrado: Resultado con precisión de 1 decimal")
	void testPerimetroCuadradoPrecisionUnDecimal() {
		// Arrange
		String lado = "7.5";
		// Perímetro = 4 * 7.5 = 30.0
		float resultadoEsperado = 30.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.perimetroCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(),
			"El resultado debe redondearse a 1 decimal máximo");
	}

	@Test
	@DisplayName("Perímetro Cuadrado: El resultado se almacena correctamente")
	void testPerimetroCuadradoAlmacenamientoResultado() {
		// Arrange
		String lado = "3";
		// Perímetro = 4 * 3 = 12
		float resultadoEsperado = 12.0f;

		// Act
		resultado resultado = calc.perimetroCuadrado(lado);

		// Assert
		assertEquals(resultadoEsperado, resultado.getResultado(),
			"El resultado debe ser almacenado correctamente");
	}

	// ==================== PRUEBAS DE VALIDACIÓN Y ERRORES ====================

	@Test
	@DisplayName("Perímetro Cuadrado: Campo vacío retorna error")
	void testPerimetroCuadradoCampoVacio() {
		// Arrange
		String lado = "";
		String estadoEsperado = "El campo de entrada debe contener un valor numérico";

		// Act
		resultado resultado = calc.perimetroCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe mostrar advertencia cuando campo está vacío");
	}

	@Test
	@DisplayName("Perímetro Cuadrado: Carácter alfanumérico retorna error")
	void testPerimetroCuadradoCaracterAlfanumerico() {
		// Arrange
		String lado = "abc";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.perimetroCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error con caracteres alfabéticos");
	}

	/*
	@Test
	@DisplayName("Perímetro Cuadrado: Caracteres especiales retornan error")
	void testPerimetroCuadradoCaracteresEspeciales() {
		// Arrange
		String lado = "5@";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.perimetroCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error con caracteres especiales");
	}
	*/

	// ==================== TESTS DE ÁREA DE CUADRADO ====================
	// ==================== PRUEBAS DE ENTRADA DE DATOS ====================

	@Test
	@DisplayName("Área Cuadrado: Aceptar lado entero positivo")
	void testAreaCuadradoLadoEnteroPositivo() {
		// Arrange
		String lado = "5";
		// Área = 5 * 5 = 25
		float resultadoEsperado = 25.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.areaCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe retornar estado 'ok' con lado entero positivo");
		assertEquals(resultadoEsperado, resultado.getResultado(), 
			"Debe calcular correctamente: Área = l²");
	}

	/*
	@Test
	@DisplayName("Área Cuadrado: Aceptar lado decimal positivo")
	void testAreaCuadradoLadoDecimalPositivo() {
		// Arrange
		String lado = "2.5";
		// Área = 2.5 * 2.5 = 6.25
		float resultadoEsperado = 6.3f; // Redondeado a 1 decimal
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.areaCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(), 
			"Debe aceptar lado decimal positivo");
		assertEquals(resultadoEsperado, resultado.getResultado(), 0.1f,
			"Debe calcular correctamente con decimales");
	}
	*/

	// ==================== PRUEBAS DE VALIDACIÓN LADO ====================

	@Test
	@DisplayName("Área Cuadrado: Lado cero retorna error")
	void testAreaCuadradoLadoCero() {
		// Arrange - Lado debe ser > 0
		String lado = "0";
		String estadoEsperado = "El valor ingresado debe ser mayor que cero";

		// Act
		resultado resultado = calc.areaCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error cuando lado es cero");
	}

	@Test
	@DisplayName("Área Cuadrado: Lado negativo retorna error")
	void testAreaCuadradoLadoNegativo() {
		// Arrange
		String lado = "-3";
		String estadoEsperado = "El valor ingresado debe ser mayor que cero";

		// Act
		resultado resultado = calc.areaCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error cuando lado es negativo");
	}

	@Test
	@DisplayName("Área Cuadrado: Lado decimal negativo retorna error")
	void testAreaCuadradoLadoDecimalNegativo() {
		// Arrange
		String lado = "-1.5";
		String estadoEsperado = "El valor ingresado debe ser mayor que cero";

		// Act
		resultado resultado = calc.areaCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error con lado decimal negativo");
	}

	// ==================== PRUEBAS DE CÁLCULO Y LÓGICA ====================

	@Test
	@DisplayName("Área Cuadrado: Aplicar fórmula matemática estándar")
	void testAreaCuadradoFormulaEstandar() {
		// Arrange
		String lado = "10";
		// Área = 10 * 10 = 100
		float resultadoEsperado = 100.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.areaCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(),
			"Debe aplicar: Área = l²");
	}

	@Test
	@DisplayName("Área Cuadrado: Lado 1 retorna 1")
	void testAreaCuadradoLadoUno() {
		// Arrange - Lado = 1: Área = 1 * 1 = 1
		String lado = "1";
		float resultadoEsperado = 1.0f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.areaCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(),
			"Área de cuadrado con lado 1: 1 * 1 = 1");
	}

	// ==================== PRUEBAS DE PRECISIÓN Y ALMACENAMIENTO ====================

	@Test
	@DisplayName("Área Cuadrado: Resultado con precisión de 1 decimal")
	void testAreaCuadradoPrecisionUnDecimal() {
		// Arrange
		String lado = "7.5";
		// Área = 7.5 * 7.5 = 56.25 redondeado a 56.3
		float resultadoEsperado = 56.3f;
		String estadoEsperado = "ok";

		// Act
		resultado resultado = calc.areaCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado());
		assertEquals(resultadoEsperado, resultado.getResultado(), 0.1f,
			"El resultado debe redondearse a 1 decimal máximo");
	}

	@Test
	@DisplayName("Área Cuadrado: El resultado se almacena correctamente")
	void testAreaCuadradoAlmacenamientoResultado() {
		// Arrange
		String lado = "3";
		// Área = 3 * 3 = 9
		float resultadoEsperado = 9.0f;

		// Act
		resultado resultado = calc.areaCuadrado(lado);

		// Assert
		assertEquals(resultadoEsperado, resultado.getResultado(),
			"El resultado debe ser almacenado correctamente");
	}

	// ==================== PRUEBAS DE VALIDACIÓN Y ERRORES ====================

	@Test
	@DisplayName("Área Cuadrado: Campo vacío retorna error")
	void testAreaCuadradoCampoVacio() {
		// Arrange
		String lado = "";
		String estadoEsperado = "El campo de entrada debe contener un valor numérico";

		// Act
		resultado resultado = calc.areaCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe mostrar advertencia cuando campo está vacío");
	}

	@Test
	@DisplayName("Área Cuadrado: Carácter alfanumérico retorna error")
	void testAreaCuadradoCaracterAlfanumerico() {
		// Arrange
		String lado = "abc";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.areaCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error con caracteres alfabéticos");
	}

	/*
	@Test
	@DisplayName("Área Cuadrado: Caracteres especiales retornan error")
	void testAreaCuadradoCaracteresEspeciales() {
		// Arrange
		String lado = "5@";
		String estadoEsperado = "Carácter invalido, debe ingresar un valor de tipo numérico";

		// Act
		resultado resultado = calc.areaCuadrado(lado);

		// Assert
		assertEquals(estadoEsperado, resultado.getEstado(),
			"Debe retornar error con caracteres especiales");
	}
	*/

}

