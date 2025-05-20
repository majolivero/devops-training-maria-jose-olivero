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

public class controlador {
    @RequestMapping(value = "/sumar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public resultado suma(@RequestParam(name = "op1", required = false, defaultValue = "0") String op1,
                          @RequestParam(name = "op2", required = false, defaultValue = "0") String op2) {

        resultado exported = new resultado();

        try {

            float castop1 = Float.parseFloat(op1);
            float castop2 = Float.parseFloat(op2);

            float resultado_int = castop1 + castop2;

            exported.setResultado(resultado_int);
            exported.setEstado("ok");
        } catch (NumberFormatException Ex) {
            exported.setEstado("Error en un de los datos enviados ");

        }
        return exported;
    }

    @RequestMapping(value = "/restar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public resultado resta(@RequestParam(name = "op1", required = false, defaultValue = "0") String op1,
                           @RequestParam(name = "op2", required = false, defaultValue = "0") String op2) {

        resultado exported = new resultado();

        try {

            float castop1 = Float.parseFloat(op1);
            float castop2 = Float.parseFloat(op2);

            float resultado_int = castop1 - castop2;

            exported.setResultado(resultado_int);
            exported.setEstado("ok");
        } catch (NumberFormatException ex) {
            exported.setEstado("Error en un de los datos enviados ");

        }
        return exported;
    }

    @RequestMapping(value = "/multiplicar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public resultado multiplicar(@RequestParam(name = "op1", required = false, defaultValue = "0") String op1,
                                 @RequestParam(name = "op2", required = false, defaultValue = "0") String op2) {

        resultado exported = new resultado();

        try {

            float castop1 = Float.parseFloat(op1);
            float castop2 = Float.parseFloat(op2);

            float resultado_int = castop1 * castop2;

            exported.setResultado(resultado_int);
            exported.setEstado("ok");
        } catch (NumberFormatException ex) {
            exported.setEstado("Error en un de los datos enviados ");

        }
        return exported;
    }

    @RequestMapping(value = "/dividir", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public resultado dividir(@RequestParam(name = "op1", required = false, defaultValue = "0") String op1,
                             @RequestParam(name = "op2", required = false, defaultValue = "0") String op2) {

        resultado exported = new resultado();
        try {

            float castop1 = Float.parseFloat(op1);
            float castop2 = Float.parseFloat(op2);

            if (castop2 == 0) {
                throw new ArithmeticException();
            }
            float resultado_int = castop1 / castop2;

            exported.setResultado(resultado_int);
            exported.setEstado("ok");
        } catch (NumberFormatException ex) {
            exported.setEstado("Error en un de los datos enviados ");
        } catch (ArithmeticException zeroEx) {
            exported.setEstado("No es posible realizar la operacion");
        }

        return exported;

    }

    @RequestMapping(value = "/raiz", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public resultado raiz(@RequestParam(name = "op1", required = false, defaultValue = "0") String op1) {
        resultado export = new resultado();
        try {
            int valor = Integer.parseInt(op1);

            if (valor < 0) {
                throw new ArithmeticException();
            }

            float resultadoFloat = (float) Math.sqrt(valor); // Conversión explícita
            export.setResultado(resultadoFloat);
            export.setEstado("full");

        } catch (ArithmeticException negativoEx) {
            export.setEstado("El número es negativo");
        } catch (NumberFormatException ex) {
            export.setEstado("Error en uno de los datos enviados");
        }

        return export;
    }
    @RequestMapping(value = "/areaCircle", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public resultado circle(@RequestParam(name = "radio", required = false, defaultValue = "0") String radio) {
        resultado area_circle = new resultado();
        try {
            int valor = Integer.parseInt(radio);

            if (valor < 0) {
                throw new ArithmeticException();
            }

            float resultadoFloat =(float) (Math.PI*(valor * valor) );
            area_circle.setResultado(resultadoFloat);
            area_circle.setEstado("ok");

        } catch (ArithmeticException negativoEx) {
            area_circle.setEstado("El número es negativo");
        } catch (NumberFormatException ex) {
            area_circle.setEstado("Error en uno de los datos enviados");
        }

        return area_circle;
    }

}


