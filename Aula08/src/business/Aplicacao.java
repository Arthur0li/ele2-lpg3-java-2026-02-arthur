package business;

public class Aplicacao implements IAplicacao {

    private float rendimento;

    @Override
    public void calcularRendimento(float valorAplicado, int prazo, float taxa) {

        float taxaDecimal = taxa / 100;

        rendimento = (float) (valorAplicado *
                Math.pow(1 + taxaDecimal, prazo));

    }

    public float getRendimento() {
        return rendimento;
    }

}