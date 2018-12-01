package efervescencia.es.myapplication;

public class Lectura {

    String hora, ingesta, glucosaPrevia, glucosaPosterior;

    public Lectura(String pHora, String pIngesta, String pGLucosaPrevia, String pGLucosaPosterior){
        hora = pHora;
        ingesta = pIngesta;
        glucosaPrevia = pGLucosaPrevia;
        glucosaPosterior = pGLucosaPosterior;
    }

    public String getHora() {
        return hora;
    }

    public String getIngesta() {
        return ingesta;
    }


    public String getGlucosaPrevia() {
        return glucosaPrevia;
    }


    public String getGlucosaPosterior() {
        return glucosaPosterior;
    }

}
