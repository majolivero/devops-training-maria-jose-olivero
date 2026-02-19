package com.entrenamiento.Calculadora.modelo;

public class resultado {
    private float resultado;
    private String estado;

    //private Double resultado_raiz;

   /* public void setResultado_raiz(Double resultado_raiz) {

        this.resultado_raiz = resultado_raiz;
    }*/

    public void setResultado(float resultado) {

        this.resultado = resultado;
    }

    public void setEstado(String estado) {

        this.estado = estado;
    }

    public float getResultado() {

        return resultado;
    }

    /*public Double getResultado_raiz() {
        return resultado_raiz;
    }*/

    public String getEstado() {

        return estado;
    }

    public resultado() {
        setResultado(0);
        setEstado("pending");
       // setResultado_raiz(0.0);
    }
}
