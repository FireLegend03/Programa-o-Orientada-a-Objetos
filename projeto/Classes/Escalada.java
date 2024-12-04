import java.time.LocalDate;

public class Escalada extends Atividade{
    
    private double distanciaEscalada;

    public Escalada(){
        super();
        this.distanciaEscalada = 0;
    }

    public Escalada(double duracao, LocalDate data, double distanciaEscalada){
        
        super("Escalada", duracao, data, (distanciaEscalada * 1000 * duracao * 0.5));
        this.distanciaEscalada = distanciaEscalada;
    }

    public Escalada(Escalada outra){
        super(outra);
        this.distanciaEscalada = outra.getDistanciaEscalada();
    }

    public double getDistanciaEscalada(){
        return this.distanciaEscalada;
    }

    public void setDistanciaEscalada(double distanciaEscalada){
        this.distanciaEscalada = distanciaEscalada;
    }


    @Override
    public String toString(){
        return super.toString() + ", Distancia Escalada: " 
        + this.distanciaEscalada + "}";
    }

    @Override
    public String toStringSemData(){
        return super.toStringSemData() + ", Distancia Escalada: " 
        + this.distanciaEscalada + "}";
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }
        if(!super.equals(o)){
            return false;
        }
        Escalada escalada = (Escalada) o;
        return this.distanciaEscalada == escalada.getDistanciaEscalada();
    }

    @Override
    public Escalada clone(){
        return new Escalada(this);
    }
}
