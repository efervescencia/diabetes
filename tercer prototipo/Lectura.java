package efervescencia.es.myapplication;

public class Lectura {


    private int id;
    private int glucosaPrevia;
    private int glucosaPosterior;
    private String insulina;
    private int hidratos;
    private String fecha;
    private String hora;


    void Lectura(int pId, String pFecha, String pHora, int pGPrevia, int pGPost, int pHidratos, String pInsulina){
        id = pId;
        glucosaPrevia = pGPrevia;
        glucosaPosterior = pGPost;
        insulina = pInsulina;
        hidratos = pHidratos;
        fecha = pFecha;
        hora = pHora;
    }

    public int getId(){
        return id;
    }

    public int getGlucosaPrevia() {
        return glucosaPrevia;
    }

    public void setGlucosaPrevia(int glucosaPrevia) {
        this.glucosaPrevia = glucosaPrevia;
    }

    public int getGlucosaPosterior() {
        return glucosaPosterior;
    }

    public void setGlucosaPosterior(int glucosaPosterior) {
        this.glucosaPosterior = glucosaPosterior;
    }

    public String getInsulina() {
        return insulina;
    }

    public void setInsulina(String insulina) {
        this.insulina = insulina;
    }

    public int getHidratos() {
        return hidratos;
    }

    public void setHidratos(int hidratos) {
        this.hidratos = hidratos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }



}
