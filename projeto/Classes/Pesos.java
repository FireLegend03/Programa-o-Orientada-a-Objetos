import java.time.LocalDate;

public class Pesos extends Atividade{
    private double peso;
    private int repeticoes;
    private int series;

    public Pesos() {
        super();
        this.peso = 0;
        this.repeticoes = 0;
        this.series = 0;
    }

    public Pesos(double duracao, LocalDate data, double peso, int series, int repeticoes) {
        
        super("Pesos", duracao, data, (peso * series * repeticoes * 0.5));
        this.peso = peso;
        this.repeticoes = repeticoes;
        this.series = series;
    }

    public Pesos(Pesos outra) {
        super(outra);
        this.peso = outra.getPeso();
        this.repeticoes = outra.getRepeticoes();
        this.series = outra.getSeries();
    }

    // Métodos getters e setters

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }


    @Override
    public String toString() {
        return super.toString() +
                ", Peso: " + peso  +
                ", Repetições: " + repeticoes  +
                ", Séries: " + series  + "}";
    }

    @Override
    public String toStringSemData() {
        return super.toStringSemData() +
                ", Peso: " + peso  +
                ", Repetições: " + repeticoes  +
                ", Séries: " + series  + "}";
    }

    @Override
    public Pesos clone() {
        return new Pesos(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Pesos outra = (Pesos) obj;
        return  this.peso == outra.getPeso() &&
                this.repeticoes == outra.getRepeticoes() &&
                this.series == outra.getSeries() &&
                super.equals(outra);
    }

    
}
