import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Utilizador implements Serializable{
    private String nome;
    private String morada;
    private String email;
    private String password;
    private String nivelPraticante;
    private double frequenciaCardiacaMedia;
    private List<Atividade> atividades;
    private double totalCalorias;
    private double totalDuracao;
    private double totalDistancia;
    private double totalAltimetria;
    

    public Utilizador() {
        this.nome = "";
        this.morada = "";
        this.email = "";
        this.nivelPraticante = "";
        this.password = "";
        this.frequenciaCardiacaMedia = 0;
        this.atividades = new ArrayList<>();
        this.totalCalorias = 0;
        this.totalDuracao = 0;
        this.totalDistancia = 0;
        this.totalAltimetria = 0;

    }

    public Utilizador(String nome, String morada, String email,
                      String password, String nivelPraticante, 
                      double frequenciaCardiacaMedia, List<Atividade> atividades,
                      double totalCalorias, double totalDuracao,
                      double totalDistancia, double totalAltimetria) {
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.password = password;
        this.nivelPraticante = nivelPraticante;
        this.frequenciaCardiacaMedia = frequenciaCardiacaMedia;
        this.atividades = atividades;
        this.totalCalorias = totalCalorias;
        this.totalDuracao = totalDuracao;
        this.totalDistancia = totalDistancia;
        this.totalAltimetria = totalAltimetria;
        
    }

    public Utilizador(Utilizador outra) {
        this.nome = outra.getNome();
        this.morada = outra.getMorada();
        this.email = outra.getEmail();
        this.password = outra.getPassword();
        this.nivelPraticante = outra.getNivelPraticante();
        this.frequenciaCardiacaMedia = outra.getFrequenciaCardiacaMedia();
        this.atividades = outra.getAtividades();
        this.totalCalorias = outra.getTotalCalorias();
        this.totalDuracao = outra.getTotalDuracao();
        this.totalDistancia = outra.getTotalDistancia();
        this.totalAltimetria = outra.getTotalAltimetria();
        
    }

    // Métodos getters e setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getNivelPraticante() {
        return nivelPraticante;
    }

    public void setNivelPraticante(String nivelPraticante) {
        this.nivelPraticante = nivelPraticante;
    }

    public double getFrequenciaCardiacaMedia() {
        return frequenciaCardiacaMedia;
    }

    public void setFrequenciaCardiacaMedia(double frequenciaCardiacaMedia) {
        this.frequenciaCardiacaMedia = frequenciaCardiacaMedia;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public void addAtividade(Atividade atividade) {
        this.atividades.add(atividade);
    }

    public void removeAtividade(Atividade atividade) {
        this.atividades.remove(atividade);
    }

    public double getTotalCalorias() {
        return this.totalCalorias;
    }

    public void setTotalCalorias(double totalCalorias) {
        this.totalCalorias = totalCalorias;
    }

    public void addCalorias(double calorias) {
        this.totalCalorias += calorias;
    }

    public double getTotalDuracao() {
        return this.totalDuracao;
    }

    public void setTotalDuracao(double totalDuracao) {
        this.totalDuracao = totalDuracao;
    }

    public void addDuracao(double duracao) {
        this.totalDuracao += duracao;
    }

    public double getTotalDistancia() {
        return this.totalDistancia;
    }

    public void setTotalDistancia(double totalDistancia) {
        this.totalDistancia = totalDistancia;
    }

    public void addDistancia(double distancia) {
        this.totalDistancia += distancia;
    }

    public double getTotalAltimetria() {
        return this.totalAltimetria;
    }

    public void setTotalAltimetria(double totalAltimetria) {
        this.totalAltimetria = totalAltimetria;
    }

    public void addAltimetria(double altimetria) {
        this.totalAltimetria += altimetria;
    }
    

    public void AtividadeMaisRealizada() {
        String[] atividades = {"Pesos", "Corrida", "Corrida no Monte", "Calistenia",
                                "Natação", "Escalada"};
        String atividadeMaisRealizada = "";
        int maxcontador = 0;
        for(String atividade : atividades) {
            int contador = 0;
            for(Atividade a: this.getAtividades()) {
                if(a.getNome().equals(atividade)) {
                    contador++;
                }
            }
            
            if(contador > maxcontador) {
                maxcontador = contador;
                atividadeMaisRealizada = atividade;
            }
        }
        System.out.println("A sua atividade mais realizada foi " + atividadeMaisRealizada +
                           " sendo realizada " + maxcontador + " vezes!");
    }

    public String getPlanoTreino() {
        switch(this.nivelPraticante) {
            case "Amador":
                Pesos p_amador = new Pesos(60,  LocalDate.now(),
                                    5, 15, 12);
                Corrida c_amador = new Corrida(60, LocalDate.now(),
                                        5);
                Calistenia cal_amador = new Calistenia(60,LocalDate.now(),
                                                        12, 15);
                return "Plano de treino para amador:" +
                        "\nSegunda:" + p_amador.toStringSemData() +
                        "\nTerça: descanso" +
                        "\nQuarta:" + c_amador.toStringSemData() +
                        "\nQuinta: descanso" +
                        "\nSexta:" + cal_amador.toStringSemData() +
                        "\nSábado: descanso" + "\nDomingo: descanso";
            case "Ocasional":
                Pesos p_ocasional = new Pesos(75,  LocalDate.now(),
                                        7.5, 15, 12);
                CorridaMonte cm_ocasional = new CorridaMonte(50,LocalDate.now(),
                                                            7.5, 50);
                Calistenia cal_ocasional = new Calistenia(75, LocalDate.now(),
                                                    12, 15);
                Nataçao nat_ocasional = new Nataçao(45, LocalDate.now(),
                                                10, 50);
                return "Plano de treino para ocasional:" +
                        "\nSegunda:" + p_ocasional.toStringSemData() +
                        "\nTerça:" + cm_ocasional.toStringSemData() +
                        "\nQuarta: descanso" + 
                        "\nQuinta:" + cal_ocasional.toStringSemData() +
                        "\nSexta:" + nat_ocasional.toStringSemData() +
                        "\nSábado: descanso" + "\nDomingo: descanso";
            case "Profissional":
                Pesos p_profissional = new Pesos(90,  LocalDate.now(),
                                            10, 15, 12);
                CorridaMonte cm_profissional = new CorridaMonte( 60, LocalDate.now(),
                                                10, 60);
                Calistenia cal_profissional = new Calistenia(90, LocalDate.now(),
                                                12, 15);
                Nataçao nat_profissional = new Nataçao(60, LocalDate.now(),
                                                10, 50);
                Escalada e_profissional = new Escalada(60, LocalDate.now(),
                                     0.5);
                return "Plano de treino para profissional:" +
                        "\nSegunda:" + p_profissional.toStringSemData() +
                        "\nTerça:" + cm_profissional.toStringSemData() +
                        "\nQuarta:" + cal_profissional.toStringSemData() +
                        "\nQuinta:" + nat_profissional.toStringSemData() +
                        "\nSexta:" + e_profissional.toStringSemData() +
                        "\nSábado: descanso" + "\nDomingo: descanso";
            default:
                return "Não foi possível encontrar o seu nível de praticante.";
        }
    }

    public String AtividadesString() {
        StringBuilder sb = new StringBuilder();
        for(Atividade a : this.atividades){
            sb.append(a.toString());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "\nUtilizador " + this.nome + ":" +
                " morada = " + this.morada +
                ", email = " + this.email +
                ", nivelPraticante = " + this.nivelPraticante +
                ", frequência cardiaca média = " + this.frequenciaCardiacaMedia +
                ", atividades = " + this.atividades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilizador p = (Utilizador) o;
        return (p.getFrequenciaCardiacaMedia() == this.getFrequenciaCardiacaMedia() &&
                this.getNome().equals(p.getNome()) &&
                this.getMorada().equals(p.getMorada()) &&
                this.getEmail().equals(p.getEmail()) &&
                this.getPassword().equals(p.getPassword()) &&
                this.getNivelPraticante().equals(p.getNivelPraticante()) &&
                this.getAtividades().equals(p.getAtividades()) &&
                this.getTotalCalorias() == p.getTotalCalorias() &&
                this.getTotalDuracao() == p.getTotalDuracao() &&
                this.getTotalDistancia() == p.getTotalDistancia());
    }
    @Override
    public Utilizador clone() {
        return new Utilizador(this);
    }

}
