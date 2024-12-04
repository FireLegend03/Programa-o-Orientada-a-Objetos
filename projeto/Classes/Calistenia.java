import java.time.LocalDate;

public class Calistenia extends Atividade{
    private int series;
    private int repeticoes;

    public Calistenia() {
        super();
        this.series = 0;
        this.repeticoes = 0;
    }

    public Calistenia(double duracao, LocalDate data, int series, int repeticoes) {
        
        super("Calistenia", duracao, data, (series * repeticoes));
        this.series = series;
        this.repeticoes = repeticoes;
    }

    public Calistenia(Calistenia outra) {
        super(outra);
        this.series = outra.getSeries();
        this.repeticoes = outra.getRepeticoes();
    }

    // Métodos getters e setters

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }


    @Override
    public String toString() {
        return super.toString() +
                ", Series: " + series  +
                ", Repetições: " + repeticoes + "}";
    }

    @Override
    public String toStringSemData() {
        return super.toStringSemData() +
                ", Series: " + series  +
                ", Repetições: " + repeticoes + "}";
    }

    @Override
    public Calistenia clone() {
        return new Calistenia(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Calistenia outra = (Calistenia) obj;
        return super.equals(outra) &&
                this.series == outra.getSeries() &&
                this.repeticoes == outra.getRepeticoes();
    }

    
}
