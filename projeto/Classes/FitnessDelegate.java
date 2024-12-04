import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FitnessDelegate {
    private Fitness model;
    public Scanner input = new Scanner(System.in);

    public FitnessDelegate() {
        try{
            this.model = Fitness.carrega("fitness.BIN");
        }
        catch(IOException | ClassNotFoundException e){
            this.model = new Fitness();
        }
    }

    public void run() {
        NewMenu menu = new NewMenu(new String[] {"Menu utilizadores",
                                                 "Login",
                                                 "Estatísticas",
                                                 "Gravar"});

        menu.setHandler(1, () ->menuUtilizadores());
        menu.setHandler(2, () ->login());
        menu.setHandler(3, () ->estatisticas());
        menu.setHandler(4, () ->gravar());

        menu.run();
        gravar();
    }

    private void menuUtilizadores() {
        NewMenu menu = new NewMenu(new String[] {"Novo utilizador",
                                                 "Existe utilizador",
                                                 "Lista de utilizadores"
                                                });

        menu.setHandler(1, () ->NovoUtilizador());
        menu.setHandler(2, () ->existeUtilizador());
        menu.setHandler(3, () ->this.model.printUtilizadores());

        menu.run();
    }

    private void NovoUtilizador() {
        Utilizador u = new Utilizador();

        // Solicitar informações do novo utilizador
        System.out.println("Digite o seu nome:");
        String nome = input.nextLine();
        u.setNome(nome);

        System.out.println("Digite a sua morada:");
        String morada = input.nextLine();
        u.setMorada(morada);

        System.out.println("Digite o seu email:");
        String email = input.nextLine();
        // Verificar se o email já exta a ser usado
        if (this.model.getUtilizador(email) != null) {
            System.out.println("O email introduzido já está a ser usado.");
            return;
        }
        u.setEmail(email);

        System.out.println("Digite a sua password:");
        String password = input.nextLine();
        u.setPassword(password);

        System.out.println("Qual o seu nível de praticante?"
                         + "\n1. Amador"
                         + "\n2. Ocasional"
                         + "\n3. Profissional"
                         + "\nOpção: ");
        int nivel = 0;
        while(nivel == 0){
            nivel = input.nextInt();
            input.nextLine(); // Limpar o enter no buffer
            switch (nivel) {
                case 1:
                    u.setNivelPraticante("Amador");
                    break;
                case 2:
                    u.setNivelPraticante("Ocasional");
                    break;
                case 3:
                    u.setNivelPraticante("Profissional");
                    break;
                default:
                    nivel = 0;
                    System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
            }
        }

        System.out.println("Digite a sua frequência cardíaca média:");
        double frequenciaCardiacaMedia = input.nextDouble();
        input.nextLine(); // Limpar o enter no buffer
        u.setFrequenciaCardiacaMedia(frequenciaCardiacaMedia);

        this.model.addUtilizador(u);
    }

    private void existeUtilizador() {
        
        System.out.println("Introduza o email do utilizador");
        String email = input.nextLine();
        if(this.model.getUtilizador(email) != null){
            System.out.println("Utilizador encontrado");
        }else{
            System.out.println("Utilizador não encontrado");
        }
    }

    private void login() {
        System.out.println("Introduza o seu email:");
        String email = input.nextLine();
        Utilizador u = this.model.getUtilizador(email);
        System.out.println("Introduza a sua password:");
        String password = input.nextLine();
        if(u == null){   
            System.out.println("Utilizador não registado");
        }else{
            if(!u.getPassword().equals(password)){
                System.out.println("Password incorreta");
            }else{
                NewMenu menu = new NewMenu(new String[] {"Consultar dados",
                                                         "Remover utilizador",
                                                         "Consultar atividades feitas",
                                                         "Adicionar atividade",
                                                         "Km percorridos",
                                                         "Total metros de altimetria percorridos",
                                                         "Ver plano de treino",
                                                         "Total de calorias gastas",
                                                         "Tempo total de treino",
                                                         "Atividade mais realizada",
                                                         "Alterar o meu nível de praticante"
                                                         });
                menu.setHandler(1, () ->System.out.println(u.toString()));
                menu.setHandler(2, () ->this.model.RemoveUtilizador(email));
                menu.setHandler(3, () ->System.out.println(u.AtividadesString()));
                menu.setHandler(4, () ->NovaAtividade(u));
                menu.setHandler(5, () ->System.out.println(u.getNome() + " percorreu " +
                                        u.getTotalDistancia() + " km desde sempre!"));

                menu.setHandler(6, () ->System.out.println(u.getNome() + " percorreu " +
                                        u.getTotalAltimetria() + " metros de altimetria desde sempre!"));
            
                menu.setHandler(7, () ->System.out.println(u.getPlanoTreino()));
                menu.setHandler(8, () ->System.out.println(u.getNome() + " queimou " +
                                    u.getTotalCalorias() + " calorias desde sempre!"));
                menu.setHandler(9, () ->System.out.println(u.getNome() + " treinou " +
                                    u.getTotalDuracao() + " minutos desde sempre!"));
                menu.setHandler(10, () ->u.AtividadeMaisRealizada()); 
                menu.setHandler(11, () ->AlteraNivelPraticante(u));

                menu.run();

            }
        }
    }

    private void NovaAtividade(Utilizador u) {
        System.out.println("Digite a duração da atividade (minutos):");
        double duracao = input.nextDouble();

        System.out.println("Qual a data da atividade (dd/MM/yyyy):");
        String data = input.next();
        input.nextLine(); // Limpar o enter no buffer
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataAtividade = LocalDate.parse(data, formatador);

        int opcao = 0;
        while (opcao == 0) {
            System.out.println("Selecione o tipo de atividade:" +
                              "\n1. Corrida" +
                              "\n2. Corrida de montanha" +
                              "\n3. Natação" +
                              "\n4. Calistenia" +
                              "\n5. Musculação" +
                              "\n6. Escalada");
            System.out.print("Opção: ");

            opcao = input.nextInt();
            input.nextLine(); // Limpar o enter no buffer
            switch (opcao) {
                case 1:
                    System.out.println("Digite a distância percorrida (km):");
                    double distancia = input.nextDouble();
                    input.nextLine(); // Limpar o enter no buffer
                    Corrida c = new Corrida(duracao, dataAtividade, distancia);
                    u.addAtividade(c);
                    u.addCalorias(c.getCalorias());
                    u.addDistancia(distancia);
                    u.addDuracao(duracao);
                    break;
                case 2:
                    System.out.println("Digite a distância percorrida (km):");
                    double distanciaMonte = input.nextDouble();
                    input.nextLine(); // Limpar o enter no buffer
                    System.out.println("Digite a altimetria (metros):");
                    double altimetria = input.nextDouble();
                    input.nextLine(); // Limpar o enter no buffer
                    CorridaMonte cm = new CorridaMonte(duracao, dataAtividade,
                                                       distanciaMonte, altimetria);
                    u.addAtividade(cm);
                    u.addCalorias(cm.getCalorias());
                    u.addDistancia(distanciaMonte);
                    u.addDuracao(duracao);
                    u.addAltimetria(altimetria);
                    break;
                case 3:
                    System.out.println("Digite o número de piscinas:");
                    int numPiscinas = input.nextInt();
                    input.nextLine(); // Limpar o enter no buffer
                    System.out.println("Digite o cumprimento da piscina (metros):");
                    double cumprimentoPiscina = input.nextDouble();
                    input.nextLine(); // Limpar o enter no buffer
                    Nataçao n = new Nataçao(duracao, dataAtividade,
                                            numPiscinas, cumprimentoPiscina);
                    u.addAtividade(n);
                    u.addCalorias(n.getCalorias());
                    u.addDistancia(numPiscinas * (cumprimentoPiscina / 1000));
                    u.addDuracao(duracao);
                    break;
                case 4:
                    System.out.println("Digite o número de séries:");
                    int series = input.nextInt();
                    input.nextLine(); // Limpar o enter no buffer
                    System.out.println("Digite o número de repetições:");
                    int repeticoes = input.nextInt();
                    input.nextLine(); // Limpar o enter no buffer
                    Calistenia cal = new Calistenia(duracao, dataAtividade,
                                                    series, repeticoes);
                    u.addAtividade(cal);
                    u.addCalorias(cal.getCalorias());
                    u.addDuracao(duracao);
                    break;
                case 5:
                    System.out.println("Digite o número de séries:");
                    int seriesMusculacao = input.nextInt();
                    input.nextLine(); // Limpar o enter no buffer
                    System.out.println("Digite o número de repetições:");
                    int repeticoesMusculacao = input.nextInt();
                    input.nextLine(); // Limpar o enter no buffer
                    System.out.println("Digite o peso:");
                    double peso = input.nextDouble();
                    input.nextLine(); // Limpar o enter no buffer
                    Pesos m = new Pesos(duracao, dataAtividade, peso,
                                        seriesMusculacao, repeticoesMusculacao);
                    u.addAtividade(m);
                    u.addCalorias(m.getCalorias());
                    u.addDuracao(duracao);
                    break;
                case 6:
                    System.out.println("Digite a distância escalada (km):");
                    double distanciaEscalada = input.nextDouble();
                    input.nextLine(); // Limpar o enter no buffer
                    Escalada e = new Escalada(duracao, dataAtividade,
                                              distanciaEscalada);
                    u.addAtividade(e);
                    u.addCalorias(e.getCalorias());
                    u.addDistancia(distanciaEscalada);
                    u.addDuracao(duracao);
                    break;
                default:
                    opcao = 0;
                    System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
            }
        }
    }

    private void AlteraNivelPraticante(Utilizador u) {
        System.out.println("Qual o seu nível de praticante?"
                         + "\n1. Amador"
                         + "\n2. Ocasional"
                         + "\n3. Profissional"
                         + "\nOpção: ");
        int nivel = 0;
        while(nivel == 0){
            nivel = input.nextInt();
            input.nextLine(); // Limpar o enter no buffer
            switch (nivel) {
                case 1:
                    u.setNivelPraticante("Amador");
                    break;
                case 2:
                    u.setNivelPraticante("Ocasional");
                    break;
                case 3:
                    u.setNivelPraticante("Profissional");
                    break;
                default:
                    nivel = 0;
                    System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
            }
        }
    }

    private void gravar() {
        try{
            this.model.grava("fitness.BIN");
            System.out.println("Dados gravados com sucesso");
        }
        catch(IOException e){
            System.out.println("Erro a gravar os dados");
        }
    }


    private void estatisticas() {
        NewMenu menu = new NewMenu(new String[]
                        {"Utilizador que gastou mais calorias",
                        "Utilizador que fez mais atividades",
                        "Atividade mais realizada",
                        "Utilizador que gastou mais tempo em atividades"});
        
        menu.setHandler(1, () ->UtilizadorMaisCalorias());
        menu.setHandler(2, () ->UtilizadorMaisAtividades());
        menu.setHandler(3, () ->AtividadeMaisPopular());
        menu.setHandler(4, () ->UtilizadorMaisTempoAtividade());

        menu.run();
    }

    private void AtividadeMaisPopular() {
        String[] atividades = {"Pesos", "Corrida", "Corrida no Monte", "Calistenia",
                                "Natação", "Escalada"};
        int maxcontador = 0;
        String atividadeMaisPopular = "";
        for(String atividade: atividades) {
            int contador = 0;
            for(Utilizador u: this.model.getUtilizadores().values()) {
                for(Atividade a: u.getAtividades()) {
                    if(a.getNome().equals(atividade)) {
                        contador++;
                    }
                }
            }
            if(contador > maxcontador) {
                maxcontador = contador;
                atividadeMaisPopular = atividade;
            }
        }
        System.out.println("A atividade mais popular entre os utilizadores é " +
        atividadeMaisPopular + " sendo realizada " + maxcontador + " vezes!");
    }

    private void UtilizadorMaisCalorias() {
        NewMenu menu = new NewMenu(new String[] 
                        {"Utilizador que gastou mais calorias desde sempre",
                         "Utilizador que gastou mais calorias num determinado período"});
        
        menu.setHandler(1, () ->UtilizadorMaisCaloriasSempre());
        menu.setHandler(2, () ->UtilizadorMaisCaloriasPeriodo());

        menu.run();
    }

    private void UtilizadorMaisCaloriasSempre() {
        double maxCalorias = 0;
        String email = "";
        for(Utilizador u: this.model.getUtilizadores().values()) {
            if(u.getTotalCalorias() > maxCalorias) {
                maxCalorias = u.getTotalCalorias();
                email = u.getEmail();
            }
        }
        System.out.println("O utilizador que gastou mais calorias desde sempre foi " +
                             email + " com " + maxCalorias + " calorias gastas!");
    }

    private void UtilizadorMaisCaloriasPeriodo() {
        System.out.println("Digite a data de início do período (dd/MM/yyyy):");
        String dataInicio = input.next();
        input.nextLine(); // Limpar o enter no buffer
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicioAtividade = LocalDate.parse(dataInicio, formatador);

        System.out.println("Digite a data de fim do período (dd/MM/yyyy):");
        String dataFim = input.next();
        input.nextLine(); // Limpar o enter no buffer
        LocalDate dataFimAtividade = LocalDate.parse(dataFim, formatador);

        double maxCalorias = 0;
        String email = "";
        for(Utilizador u: this.model.getUtilizadores().values()) {
            double calorias = 0;
            for(Atividade a: u.getAtividades()) {
                if((a.getData().isAfter(dataInicioAtividade) || a.getData().isEqual(dataInicioAtividade)) &&
                    (a.getData().isBefore(dataFimAtividade) || a.getData().isEqual(dataFimAtividade))) {
                    calorias += a.getCalorias();
                }
            }
            if(calorias > maxCalorias) {
                maxCalorias = calorias;
                email = u.getEmail();
            }
        }
        System.out.println("O utilizador que gastou mais calorias no período foi " + email +
                           " com " + maxCalorias + " calorias gastas!");
    }

    private void UtilizadorMaisAtividades() {
        NewMenu menu = new NewMenu(new String[] 
                        {"Utilizador que fez mais atividades desde sempre",
                         "Utilizador que fez mais atividades num determinado período"});
        
        menu.setHandler(1, () ->UtilizadorMaisAtividadesSempre());
        menu.setHandler(2, () ->UtilizadorMaisAtividadesPeriodo());

        menu.run();
    }

    private void UtilizadorMaisAtividadesSempre() {
        int maxAtividades = 0;
        String email = "";
        for(Utilizador u: this.model.getUtilizadores().values()) {
            if(u.getAtividades().size() > maxAtividades) {
                maxAtividades = u.getAtividades().size();
                email = u.getEmail();
            }
        }
        System.out.println("O utilizador que fez mais atividades desde sempre foi " +
                             email + " com " + maxAtividades + " atividades feitas!");
    }

    private void UtilizadorMaisAtividadesPeriodo() {
        System.out.println("Digite a data de início do período (dd/MM/yyyy):");
        String dataInicio = input.next();
        input.nextLine(); // Limpar o enter no buffer
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicioAtividade = LocalDate.parse(dataInicio, formatador);

        System.out.println("Digite a data de fim do período (dd/MM/yyyy):");
        String dataFim = input.next();
        input.nextLine(); // Limpar o enter no buffer
        LocalDate dataFimAtividade = LocalDate.parse(dataFim, formatador);

        int maxAtividades = 0;
        String email = "";
        for(Utilizador u: this.model.getUtilizadores().values()) {
            int atividades = 0;
            for(Atividade a: u.getAtividades()) {
                if((a.getData().isAfter(dataInicioAtividade) || a.getData().equals(dataInicioAtividade)) &&
                    (a.getData().isBefore(dataFimAtividade) || a.getData().equals(dataFimAtividade))) {
                    atividades++;
                }
            }
            if(atividades > maxAtividades) {
                maxAtividades = atividades;
                email = u.getEmail();
            }
        }
        System.out.println("O utilizador que fez mais atividades no período foi " + email +
                           " com " + maxAtividades + " atividades feitas!");
    }

    private void UtilizadorMaisTempoAtividade() {
        NewMenu menu = new NewMenu(new String[] 
                        {"Utilizador que gastou mais tempo em atividades desde sempre",
                         "Utilizador que gastou mais tempo em atividades num determinado período"});
        
        menu.setHandler(1, () ->UtilizadorMaisTempoAtividadeSempre());
        menu.setHandler(2, () ->UtilizadorMaisTempoAtividadePeriodo());

        menu.run();
    }

    private void UtilizadorMaisTempoAtividadeSempre() {
        double maxTempo = 0;
        String email = "";
        for(Utilizador u: this.model.getUtilizadores().values()) {
            if(u.getTotalDuracao() > maxTempo) {
                maxTempo = u.getTotalDuracao();
                email = u.getEmail();
            }
        }
        maxTempo = maxTempo / 60; // Converter para horas
        System.out.println("O utilizador que gastou mais tempo em atividades desde sempre foi " +
                             email + " com " + maxTempo + " horas gastas!");
    }

    private void UtilizadorMaisTempoAtividadePeriodo() {
        System.out.println("Digite a data de início do período (dd/MM/yyyy):");
        String dataInicio = input.next();
        input.nextLine(); // Limpar o enter no buffer
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicioAtividade = LocalDate.parse(dataInicio, formatador);

        System.out.println("Digite a data de fim do período (dd/MM/yyyy):");
        String dataFim = input.next();
        input.nextLine(); // Limpar o enter no buffer
        LocalDate dataFimAtividade = LocalDate.parse(dataFim, formatador);

        double maxTempo = 0;
        String email = "";
        for(Utilizador u: this.model.getUtilizadores().values()) {
            double tempo = 0;
            for(Atividade a: u.getAtividades()) {
                if((a.getData().isAfter(dataInicioAtividade) || a.getData().equals(dataInicioAtividade)) &&
                    (a.getData().isBefore(dataFimAtividade) || a.getData().equals(dataFimAtividade))) {
                    tempo += a.getDuracao();
                }
            }
            if(tempo > maxTempo) {
                maxTempo = tempo;
                email = u.getEmail();
            }
        }
        maxTempo = maxTempo / 60; // Converter para horas
        System.out.println("O utilizador que gastou mais tempo em atividades no período foi " + email +
                           " com " + maxTempo + " horas gastas!");
    }

}
