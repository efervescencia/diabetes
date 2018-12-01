package efervescencia.es.myapplication;

public class Lectura {

    String hora, ingesta, glucosaPrevia, glucosaPosterior, fecha, insulina;
    int id, hidratos;

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

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setInsulina(String insulina) {
        this.insulina = insulina;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHidratos(int hidratos) {
        this.hidratos = hidratos;
    }

    public String getFecha() {
        return fecha;
    }

    public String getInsulina() {
        return insulina;
    }

    public int getId() {
        return id;
    }

    public int getHidratos() {
        return hidratos;
    }
}
