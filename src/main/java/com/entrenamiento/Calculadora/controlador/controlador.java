package com.entrenamiento.Calculadora.controlador;

import com.entrenamiento.Calculadora.modelo.resultado;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;

@CrossOrigin(origins = "*")
@RestController

//SUMA
public class controlador {
    @RequestMapping(value = "/sumar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public resultado suma(@RequestParam(name = "op1", required = false, defaultValue = "") String op1,
                          @RequestParam(name = "op2", required = false, defaultValue = "") String op2) {

        resultado exported = new resultado();

        try {
            // Validar que no estén vacíos
            if (op1.isEmpty() || op2.isEmpty()) {
                exported.setEstado("Ambos campos son obligatorios");
                return exported;
            }

            float castop1 = Float.parseFloat(op1);
            float castop2 = Float.parseFloat(op2);

            // Calcular la suma: op1 + op2
            float resultado_int = castop1 + castop2;

            // Redondear a 1 decimal
            resultado_int = Math.round(resultado_int * 10.0f) / 10.0f;

            exported.setResultado(resultado_int);
            exported.setEstado("ok");

        } catch (NumberFormatException Ex) {
            exported.setEstado("Carácter invalido, debe ingresar un valor de tipo numérico");
        }
        return exported;
    }


//RESTA
    @RequestMapping(value = "/restar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public resultado resta(@RequestParam(name = "minuendo", required = false, defaultValue = "") String minuendo,
                           @RequestParam(name = "sustraendo", required = false, defaultValue = "") String sustraendo) {

        resultado exported = new resultado();

        try {
            // Validar que no estén vacíos
            if (minuendo.isEmpty() || sustraendo.isEmpty()) {
                exported.setEstado("Ambos campos son obligatorios");
                return exported;
            }

            // Convertir a float (soporta tanto enteros como decimales)
            float castMinuendo = Float.parseFloat(minuendo);
            float castSustraendo = Float.parseFloat(sustraendo);

            // Calcular la resta: minuendo - sustraendo
            float resultado_int = castMinuendo - castSustraendo;

            // Redondear a un decimal
            resultado_int = Math.round(resultado_int * 10.0f) / 10.0f;

            exported.setResultado(resultado_int);
            exported.setEstado("ok");

        } catch (NumberFormatException Ex) {
            exported.setEstado("Carácter invalido, debe ingresar un valor de tipo numérico");
        }

        return exported;
    }


//MULTIPLICACION
    @RequestMapping(value = "/multiplicar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public resultado multiplicar(@RequestParam(name = "multiplicando", required = false, defaultValue = "") String multiplicando,
                                 @RequestParam(name = "multiplicador", required = false, defaultValue = "") String multiplicador) {

        resultado exported = new resultado();

        try {
            // Validar que no estén vacíos
            if (multiplicando.isEmpty() || multiplicador.isEmpty()) {
                exported.setEstado("Ambos campos son obligatorios");
                return exported;
            }

            // Convertir a float (soporta tanto enteros como decimales)
            float castMultiplicando = Float.parseFloat(multiplicando);
            float castMultiplicador = Float.parseFloat(multiplicador);

            // Calcular la multiplicación: multiplicando * multiplicador
            float resultado_int = castMultiplicando * castMultiplicador;

            // Redondear a un decimal
            resultado_int = Math.round(resultado_int * 10.0f) / 10.0f;

            exported.setResultado(resultado_int);
            exported.setEstado("ok");

        } catch (NumberFormatException Ex) {
            exported.setEstado("Carácter invalido, debe ingresar un valor de tipo numérico");
        }

        return exported;
    }


//DIVISION
    @RequestMapping(value = "/dividir", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public resultado dividir(@RequestParam(name = "dividendo", required = false, defaultValue = "") String dividendo,
                             @RequestParam(name = "divisor", required = false, defaultValue = "") String divisor) {

        resultado exported = new resultado();

        try {
            // Validar que no estén vacíos
            if (dividendo.isEmpty() || divisor.isEmpty()) {
                exported.setEstado("Ambos campos son obligatorios");
                return exported;
            }

            // Convertir a float (soporta tanto enteros como decimales)
            float castDividendo = Float.parseFloat(dividendo);
            float castDivisor = Float.parseFloat(divisor);

            // Validar que el divisor no sea cero
            if (castDivisor == 0) {
                exported.setEstado("Error: No es posible dividir por cero");
                return exported;
            }

            // Calcular la división: dividendo / divisor
            float resultado_int = castDividendo / castDivisor;

            // Redondear a un decimal
            resultado_int = Math.round(resultado_int * 10.0f) / 10.0f;

            exported.setResultado(resultado_int);
            exported.setEstado("ok");

        } catch (NumberFormatException Ex) {
            exported.setEstado("Carácter invalido, debe ingresar un valor de tipo numérico");
        }

        return exported;
    }

//RAIZ CUADRADA
    @RequestMapping(value = "/raiz", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public resultado raiz(@RequestParam(name = "radicando", required = false, defaultValue = "") String radicando) {

        resultado exported = new resultado();

        try {
            // Validar que no esté vacío
            if (radicando.isEmpty()) {
                exported.setEstado("El campo de entrada debe contener un valor numérico");
                return exported;
            }

            // Convertir a float (soporta tanto enteros como decimales)
            float castRadicando = Float.parseFloat(radicando);

            // Validar que no sea negativo
            if (castRadicando < 0) {
                exported.setEstado("Error: No se puede calcular la raíz cuadrada de un número negativo");
                return exported;
            }

            // Calcular la raíz cuadrada
            float resultado_raiz = (float) Math.sqrt(castRadicando);

            // Redondear a 4 decimales
            resultado_raiz = Math.round(resultado_raiz * 10000.0f) / 10000.0f;

            exported.setResultado(resultado_raiz);
            exported.setEstado("ok");

        } catch (NumberFormatException Ex) {
            exported.setEstado("Carácter invalido, debe ingresar un valor de tipo numérico");
        }

        return exported;
    }

//PERIMETRO DEL CIRCULO
    @RequestMapping(value = "/perimetroCircle", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public resultado perimetroCircle(@RequestParam(name = "radio", required = false, defaultValue = "") String radio) {

        resultado exported = new resultado();

        try {
            // Validar que no esté vacío
            if (radio.isEmpty()) {
                exported.setEstado("El campo de entrada debe contener un valor numérico");
                return exported;
            }

            // Convertir a float (soporta tanto enteros como decimales)
            float castRadio = Float.parseFloat(radio);

            // Validar que el radio sea positivo
            if (castRadio <= 0) {
                exported.setEstado("El radio debe ser un valor positivo y diferente de cero");
                return exported;
            }

            // Calcular el perímetro: Perímetro = 2 * Pi * r
            float resultado_perimetro = (float) (2 * Math.PI * castRadio);

            // Redondear a 1 decimal
            resultado_perimetro = Math.round(resultado_perimetro * 10.0f) / 10.0f;

            exported.setResultado(resultado_perimetro);
            exported.setEstado("ok");

        } catch (NumberFormatException Ex) {
            exported.setEstado("Carácter invalido, debe ingresar un valor de tipo numérico");
        }

        return exported;
    }

//AREA DE UN CUADRADO
    @RequestMapping(value = "/areaCuadrado", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public resultado areaCuadrado(@RequestParam(name = "lado", required = false, defaultValue = "") String lado) {

        resultado exported = new resultado();

        try {
            // Validar que no esté vacío
            if (lado.isEmpty()) {
                exported.setEstado("El campo de entrada debe contener un valor numérico");
                return exported;
            }

            // Convertir a float (soporta tanto enteros como decimales)
            float castLado = Float.parseFloat(lado);

            // Validar que el lado sea positivo
            if (castLado <= 0) {
                exported.setEstado("El valor ingresado debe ser mayor que cero");
                return exported;
            }

            // Calcular el área: Área = l * l
            float resultado_area = castLado * castLado;

            // Redondear a 1 decimal
            resultado_area = Math.round(resultado_area * 10.0f) / 10.0f;

            exported.setResultado(resultado_area);
            exported.setEstado("ok");

        } catch (NumberFormatException Ex) {
            exported.setEstado("Carácter invalido, debe ingresar un valor de tipo numérico");
        }

        return exported;
    }

//PERIMETRO DE UN CUADRADO
    @RequestMapping(value = "/perimetroCuadrado", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public resultado perimetroCuadrado(@RequestParam(name = "lado", required = false, defaultValue = "") String lado) {

        resultado exported = new resultado();

        try {
            // Validar que no esté vacío
            if (lado.isEmpty()) {
                exported.setEstado("El campo de entrada debe contener un valor numérico");
                return exported;
            }

            // Convertir a float (soporta tanto enteros como decimales)
            float castLado = Float.parseFloat(lado);

            // Validar que el lado sea positivo
            if (castLado <= 0) {
                exported.setEstado("El valor ingresado debe ser mayor que cero");
                return exported;
            }

            // Calcular el perímetro: Perímetro = 4 * l
            float resultado_perimetro = 4 * castLado;

            // Redondear a 1 decimal
            resultado_perimetro = Math.round(resultado_perimetro * 10.0f) / 10.0f;

            exported.setResultado(resultado_perimetro);
            exported.setEstado("ok");

        } catch (NumberFormatException Ex) {
            exported.setEstado("Carácter invalido, debe ingresar un valor de tipo numérico");
        }

        return exported;
    }

//AREA DE UN CIRCULO    
    @RequestMapping(value = "/areaCircle", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public resultado circle(@RequestParam(name = "radio", required = false, defaultValue = "0") String radio) {
        resultado area_circle = new resultado();
        
        try {
            // Validar que no esté vacío
            if (radio.isEmpty()) {
                area_circle.setEstado("El campo de entrada debe contener un valor numérico");
                return area_circle;
            }

            // Convertir a float (soporta tanto enteros como decimales)
            float valor = Float.parseFloat(radio);

            // Validar que el radio sea positivo
            if (valor <= 0) {
                area_circle.setEstado("El radio debe ser un valor positivo");
                return area_circle;
            }

            // Calcular el área: Área = Pi * r * r
            float resultadoFloat = (float) (Math.PI * valor * valor);
            
            // Redondear a 1 decimal
            resultadoFloat = Math.round(resultadoFloat * 10.0f) / 10.0f;
            
            area_circle.setResultado(resultadoFloat);
            area_circle.setEstado("ok");

        } catch (NumberFormatException ex) {
            area_circle.setEstado("Carácter invalido, debe ingresar un valor de tipo numérico");
        }

        return area_circle;
    }
}


