import java.time.LocalDate;

public class Corrida extends Atividade{
    private double distancia;

    public Corrida() {
        super();
        this.distancia = 0;
    }

    public Corrida(double duracao, LocalDate data, double distancia) {
        
        super("Corrida", duracao, data,
                 (distancia * duracao * 0.8));

        this.distancia = distancia;
    }

    public Corrida(Corrida outra) {
        super(outra);
        this.distancia = outra.getDistancia();
    }

    public double getDistancia() {
        return this.distancia;
    }
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }


    @Override
    public String toString() {
        return super.toString() + ", Distancia: " + this.distancia + "}";
    }

    @Override
    public String toStringSemData() {
        return super.toStringSemData() + ", Distancia: " + this.distancia + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Corrida c = (Corrida) o;
        return (super.equals(c) && this.distancia == c.getDistancia());
    }

    @Override
    public Corrida clone() {
        return new Corrida(this);
    }
}

