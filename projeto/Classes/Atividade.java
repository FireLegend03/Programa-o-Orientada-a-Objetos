import java.io.Serializable;
import java.time.LocalDate;

public class Atividade implements Serializable{
    private String nome;
    private LocalDate data;
    private double duracao;
    private double calorias;

    public Atividade() {
        this.nome = "";
        this.data = LocalDate.now();
        this.duracao = 0;
        this.calorias = 0;
    }

    public Atividade(String nome, double duracao, LocalDate data, double calorias) {
        this.nome = nome;
        this.data = data;
        this.duracao = duracao;
        this.calorias = calorias;
    }

    public Atividade(Atividade outra) {
        this.nome = outra.getNome();
        this.data = outra.getData();
        this.duracao = outra.getDuracao();
        this.calorias = outra.getCalorias();
    }

    // Métodos getters e setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getDuracao() {
        return this.duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    public double getCalorias() {
        return this.calorias;
    }

    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

    @Override
    public String toString() {
        return  "\n{nome = " + this.nome +
                ", data = " + this.data +
                ", duração = " + duracao +
                ", calorias = " + this.calorias;
    }
    
    public String toStringSemData() {
        return  "\n{nome = " + this.nome +
                ", duração = " + duracao +
                ", calorias = " + this.calorias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atividade atividade = (Atividade) o;
        return (this.getNome().equals(atividade.getNome()) &&
                this.getData().isEqual(atividade.getData()) &&
                this.getDuracao() == atividade.getDuracao() &&
                this.getCalorias() == atividade.getCalorias());
    }
    @Override
    public Atividade clone(){
        return new Atividade(this);
    }
}
