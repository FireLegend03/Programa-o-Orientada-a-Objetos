import java.time.LocalDate;

public class Nataçao extends Atividade{
    
    private int numPiscinas;
    private double cumprimentoPiscina;
    
    public Nataçao(){
        super();
        this.numPiscinas = 0;
        this.cumprimentoPiscina = 0;
    }

    public Nataçao(double duracao, LocalDate data, int numPiscinas,
                   double cumprimentoPiscina){
        
        super("Natação", duracao, data, (numPiscinas * cumprimentoPiscina * 0.5));
        this.numPiscinas = numPiscinas;
        this.cumprimentoPiscina = cumprimentoPiscina;
    }

    public Nataçao(Nataçao outra){
        super(outra);
        this.numPiscinas = outra.getNumPiscinas();
        this.cumprimentoPiscina = outra.getCumprimentoPiscina();
    }

    public int getNumPiscinas(){
        return this.numPiscinas;
    }

    public void setNumPiscinas(int numPiscinas){
        this.numPiscinas = numPiscinas;
    }

    public double getCumprimentoPiscina(){
        return this.cumprimentoPiscina;
    }

    public void setCumprimentoPiscina(double cumprimentoPiscina){
        this.cumprimentoPiscina = cumprimentoPiscina;
    }

    @Override
    public String toString(){
        return super.toString() + ", Número de Piscinas: " +
        this.numPiscinas + ", Cumprimento da Piscina: " + this.cumprimentoPiscina +"}";
    }

    @Override
    public String toStringSemData(){
        return super.toStringSemData() + ", Número de Piscinas: " +
        this.numPiscinas + ", Cumprimento da Piscina: " + this.cumprimentoPiscina +"}";
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }
        Nataçao n = (Nataçao) o;
        return super.equals(n) && this.numPiscinas == n.getNumPiscinas() &&
        this.cumprimentoPiscina == n.getCumprimentoPiscina();
    }

    @Override
    public Nataçao clone(){
        return new Nataçao(this);
    }

}
