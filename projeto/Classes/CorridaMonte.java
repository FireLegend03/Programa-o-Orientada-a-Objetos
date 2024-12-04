import java.time.LocalDate;

public class CorridaMonte extends Atividade{
    private double distancia;
    private double altimetria;

    public CorridaMonte() {
        super();
        this.distancia = 0;
        this.altimetria = 0;
    }

    public CorridaMonte(double duracao, LocalDate data,
                        double distancia, double altimetria) {
        
        super("Corrida no Monte", duracao, data, (distancia * altimetria * 0.2));
        this.distancia = distancia;
        this.altimetria = altimetria;
    }

    public CorridaMonte(CorridaMonte outra) {
        super(outra);
        this.distancia = outra.getDistancia();
        this.altimetria = outra.getAltimetria();
    }

    public double getDistancia() {
        return this.distancia;
    }
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
    public double getAltimetria() {
        return this.altimetria;
    }
    public void setAltimetria(double altimetria) {
        this.altimetria = altimetria;
    }


    @Override
    public String toString() {
        return super.toString() + ", Distancia: " + this.distancia + ", Altimetria: "
        + this.altimetria + "}";
    }

    @Override
    public String toStringSemData() {
        return super.toStringSemData() + ", Distancia: " + this.distancia + ", Altimetria: "
        + this.altimetria + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        CorridaMonte c = (CorridaMonte) o;
        return super.equals(c) && this.distancia == c.getDistancia() &&
               this.altimetria == c.getAltimetria();
    }

    @Override
    public CorridaMonte clone() {
        return new CorridaMonte(this);
    }
}
