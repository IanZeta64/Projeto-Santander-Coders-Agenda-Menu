package br.com.ada.agenda;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
public class Agenda {
    private final String CAMINHO = ("agenda_contatos\\Contato_%d.txt");
    private List<Contato> contatos;

    public Agenda() {
        this.contatos = new ArrayList<>();
    }

    public List<Contato> getContatos() {
        return Collections.unmodifiableList(contatos);
    }

    public void adicionarContato() {
        String nome = ConsoleUIHelper.askSimpleInput("Nome do Contato:");
        String sobreNome = ConsoleUIHelper.askSimpleInput("Sobrenome do Contato:");
        String email = ConsoleUIHelper.askSimpleInput("E-mail do Contato:");
        String empresa = ConsoleUIHelper.askSimpleInput("Empresa do Contato:");

        Contato novoContato = new Contato(nome, sobreNome, empresa, email);

        if (!this.contatos
                .stream().anyMatch(contato -> contato.equals(novoContato))) this.contatos.add(novoContato);
        else
            System.out.println("Não é possivel adicionar contatos ja existentes/duplicados, tente novamente com\noutras informações.");
    }

//    public void listarContatos() {
//        if (!this.contatos.isEmpty()) {
//            ConsoleUIHelper.drawHeader(String.format("%-13s%-15s%-19s%-29s",
//                    "INDICE",
//                    "NOME",
//                    "SOBRENOME",
//                    "E-MAIL"), 80);
//
//            this.contatos.forEach(contato -> {
//                ConsoleUIHelper.drawWithPadding(String.format(("%-13s%-15s%-19s%-29s"),
//                        (this.contatos.indexOf(contato) + 1),
//                        contato.getNome(),
//                        contato.getSobreNome(),
//                        contato.getEmail()), 80);
//            });
//            ConsoleUIHelper.drawLine(80);
//
//        } else {
//            System.out.println("Não há contatos na agenda.");
//        }
//    }

    public void listarContatos() {
        int pg = 1;
        int skip = 0;
        boolean continuar;
        if (!this.contatos.isEmpty()) {
            ConsoleUIHelper.drawHeader(String.format("%-13s%-15s%-19s%-29s",
                    "INDICE",
                    "NOME",
                    "SOBRENOME",
                    "E-MAIL"), 80);
            do {
                this.contatos.stream().skip(skip).limit(5).forEach(contato -> {
                    ConsoleUIHelper.drawWithPadding(String.format(("%-13s%-15s%-19s%-29s"),
                            (this.contatos.indexOf(contato) + 1),
                            contato.getNome(),
                            contato.getSobreNome(),
                            contato.getEmail()), 80);
                });
                if(skip < 5){
                       pg = ConsoleUIHelper.askChooseOption("Deseja sair ou passar de pagina?", "Sair", "Proxima pagina");
                       if(pg ==1){
                           continuar = false;
                       }
                       else {
                           continuar = true;
                           skip = 5;
                       }
                }else if(this.contatos.size() - skip > 5 ){
                        pg = ConsoleUIHelper.askChooseOption("\"Deseja sair ou passar de pagina?", "Sair", "Proxima pagina", "Pagina anterior");
                        if(pg ==1){
                            continuar = false;
                        }
                        else if (pg == 2) {
                            continuar = true;
                            skip += 5;
                        }
                        else {
                            continuar = true;
                            skip -=5;
                        }
                    }else {
                    pg = ConsoleUIHelper.askChooseOption("Deseja sair ou passar de pagina?", "Sair", "Pagina anterior");

                    if(pg ==1){
                        continuar = false;
                    }
                    else {
                        continuar = true;
                        skip -= 5;
                    }


                }
            }while (continuar);
            ConsoleUIHelper.drawLine(80);

        } else {
            System.out.println("Não há contatos na agenda.");
        }
    }

    public void buscarContato(Scanner entrada) {
            switch (ConsoleUIHelper.askChooseOption("Como deseja fazer a busca?", "Nome", "Sobrenome")) {
                case 1:
                    buscarContatoNome(entrada);
                    break;
                case 2:
                    buscarContatoSobreNome(entrada);
                    break;
                default:
                    System.out.println("Digite um valor valido!");
            }
    }

    private void buscarContatoNome(Scanner entrada) {
//            String busca = ConsoleUIHelper.askSimpleInput("Digite o sobrenome do contato para pesquisa:").toUpperCase();
//
//            List<Contato> listaBusca = contatos.stream().filter(contato -> contato.getNome().toUpperCase().contains(busca)).toList();
//            listaBusca.forEach(contato -> System.out.println("Indice: "
//                    + (this.contatos.indexOf(contato) + 1) + "\n" + contato));
//            if (listaBusca.isEmpty()) System.out.printf("Nenhum contato '%s' encontrado.%n", busca);

            String busca = ConsoleUIHelper.askSimpleInput("Digite o nome do contato para pesquisa:").toUpperCase();
            int contador = 0;
            int tamanho = busca.length();
            for (int i = 0; i < getContatos().size(); i++) {
                String contato = getContatos().get(i).getNome();
                if (busca.length() <= contato.length()) {
                    if (busca.equalsIgnoreCase(contato.substring(0, tamanho))) {
                        System.out.print((i + 1) + " - Nome: " + getContatos().get(i).toString() + "\n");
                        System.out.println();
                    } else {
                        contador++;
                    }
                }
            }
            if (contador == getContatos().size()) {
                System.out.println("Contato não encontrado!");
                System.out.println();
            }

    }

    private void buscarContatoSobreNome(Scanner entrada) {
//            String busca = ConsoleUIHelper.askSimpleInput("Digite o sobrenome do contato para pesquisa:").toUpperCase();
//            List<Contato> listaBusca = contatos.stream().filter(contato -> contato.getSobreNome().toUpperCase().contains(busca)).toList();
//            listaBusca.forEach(contato -> System.out.println("Indice: "
//                    + (this.contatos.indexOf(contato) + 1) + "\n" + contato));
//            if (listaBusca.isEmpty()) System.out.printf("Nenhum contato '%s' encontrado.%n", busca);

            String busca = ConsoleUIHelper.askSimpleInput("Digite o sobrenome do contato para pesquisa:").toUpperCase();
            int contador = 0;
            int tamanho = busca.length();
            for (int i = 0; i < getContatos().size(); i++) {
                String contato = getContatos().get(i).getSobreNome();
                if (busca.length() <= contato.length()) {
                    if (busca.equalsIgnoreCase(contato.substring(0, tamanho))) {
                        System.out.print((i + 1) + " - Nome: " + getContatos().get(i).toString() + "\n");
                        System.out.println();
                    } else {
                        contador++;
                    }
                }
            }
            if (contador == getContatos().size()) {
                System.out.println("Contato não encontrado!");
                System.out.println();
            }
    }

    public void removerContato(Scanner entrada) {
        boolean continuar = false;
        int indice = 0;
        listarContatos();
        if (!this.contatos.isEmpty()) {
            do {
                try {
                    indice = Integer.parseInt(ConsoleUIHelper.askSimpleInput("Digite o indice do contato que deseja remover da agenda:"));
                    if (indice > 0 && indice <= contatos.size()) {
                        this.contatos.remove((indice - 1));
                        System.out.println("Contato removido!");
                        continuar = false;
                    } else {
                        System.out.println("Impossivel remover contato fora do indice da agenda.");
                        continuar = true;
                    }
                } catch (IndexOutOfBoundsException e) {

                }
            } while (continuar);
        }
    }

    public void removerTodosContatos(Scanner entrada) {
        listarContatos();
        if (!this.contatos.isEmpty()) {
            boolean confirmar = ConsoleUIHelper.askConfirm("Deseja realmente remover todos os contatos da agenda?", "Sim", "Não");
            if (confirmar) {
                this.contatos.clear();
                System.out.println("Todos os contatos da agenda foram removidos.");
            } else {
                System.out.println("saindo do menu sem remover contatos.");
            }
        }
    }
    public void adicionarInformacaoContato(Scanner entrada) {
        listarContatos();
        int indice = -9;
        boolean continuar;
        if (!getContatos().isEmpty()) {
            do {
                try {
                    indice = Integer.parseInt(ConsoleUIHelper.askSimpleInput("Digite o indice do contato para adiconar a informação:"));
                    if (indice < 1 || indice > contatos.size()) {
                        System.out.println("Valor fora do indice.");
                        continuar = true;
                    } else {
                        continuar = false;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Digite um valor numerico inteiro.");
                    continuar = true;
                }
            } while (continuar);
            System.out.printf("voce selecionou o contato %d: \n", indice);
            System.out.println(this.contatos.get((indice - 1)));

            int resposta = ConsoleUIHelper.askChooseOption("Qual informacao voce deseja adicionar?", "Telefone", "Endereco");
            if (resposta == 1) adicionarTelefoneContato(entrada, indice);
            else if (resposta == 2) adicionarEnderecoContato(entrada, indice);
            else System.out.println("Digite um valor valido!");
        }
    }

    private void editarInfoContato(Scanner entrada){
        listarContatos();
        if(!this.contatos.isEmpty()){
            listarContatos();
            int indice = -9;
            boolean continuar;
            if (!getContatos().isEmpty()) {
                do {
                    try {
                        indice = Integer.parseInt(ConsoleUIHelper.askSimpleInput("Digite o indice do contato para editar as informações:"));
                        if (indice < 1 || indice > contatos.size()) {
                            System.out.println("Valor fora do indice.");
                            continuar = true;
                        } else {
                            continuar = false;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Digite um valor numerico inteiro.");
                        continuar = true;
                    }
                } while (continuar);
                System.out.printf("voce selecionou o contato %d: \n", indice);
                System.out.println("Nome completo: " + this.contatos.get((indice - 1)).getNome() +
                        this.contatos.get((indice - 1)).getSobreNome() + " E-mail: " + this.contatos.get((indice - 1)).getEmail());

                int resposta = ConsoleUIHelper.askChooseOption("Que tipo de informação voce deseja editar?",
                        "Pessoais", "Telefone", "Endereco");
                if (resposta == 1) editarInfoPessoal(entrada, indice);
                else if (resposta == 2) editarTelefone(entrada, indice);
                else if (resposta == 3) editarEndereco(entrada, indice);
                else System.out.println("Digite um valor valido!");
            }
        }
    }

    private void editarInfoPessoal(Scanner entrada, int indice){
        String nome = ConsoleUIHelper.askSimpleInput("Novo nome do contato:");
                this.contatos.get((indice - 1)).setNome(nome);
        String sobrenome = ConsoleUIHelper.askSimpleInput("Novo sobrenome do contato:");
        this.contatos.get((indice - 1)).setSobreNome(sobrenome);
        String email = ConsoleUIHelper.askSimpleInput("Novo email do contato:");
        this.contatos.get((indice - 1)).setEmail(email);
        String emp = ConsoleUIHelper.askSimpleInput("Nova empresa do contato:");
        this.contatos.get((indice - 1)).setEmpresa(emp);
        System.out.println("As novas informações do contato " + nome + ", são:\nNome Completo: " +
                nome + " " + sobrenome + " E-mail: " + email);
    }
    private void editarTelefone(Scanner entrada, int indice){
        if (!this.contatos.get(indice - 1).getTelefones().isEmpty()) {
            String nome = this.getContatos().get((indice - 1)).getNome();
            String sobrenome = this.getContatos().get(indice - 1).getSobreNome();
            System.out.println("As informações do contato " + nome + ", são:\nNome Completo: " +
                    nome + " " + sobrenome + "\nTelefone(s):");
            this.getContatos().get((indice - 1)).getTelefones().forEach(telefone ->
                    System.out.println((this.contatos.get(indice - 1).getTelefones().indexOf(telefone) + 1)
                            + " - " + telefone.getDdd() + telefone.getNumero()));
            int indExpandir = Integer.parseInt(ConsoleUIHelper.askSimpleInput("Digite o indice do telefone que deseja editar:"));
            int resposta = ConsoleUIHelper.askChooseOption("Digite o novo tipo de telefone:", "Residencial", "Comercial", "Celular");
            String tipo;
            if (resposta == 1) tipo = "RESIDENCIAL";
            else if (resposta == 2) tipo = "COMERCIAL";
            else tipo = "CELULAR";
            String ddi = ConsoleUIHelper.askSimpleInput("Digite o novo DDI:");
            String ddd = ConsoleUIHelper.askSimpleInput("Digite o novo DDD:");
            String numero = ConsoleUIHelper.askSimpleInput("Digite o novo numero:");
            this.contatos.get(indice - 1).getTelefones().get(indExpandir-1).setTipo(TipoTelefone.valueOf(tipo));
            this.contatos.get(indice - 1).getTelefones().get(indExpandir-1).setDdi(ddi);
            this.contatos.get(indice - 1).getTelefones().get(indExpandir-1).setDdd(ddd);
            this.contatos.get(indice - 1).getTelefones().get(indExpandir-1).setNumero(numero);
            System.out.println("Telefone Editado!");
        }else System.out.println("Esse contato nao tem telefone cadastrado.");
    }
    private void editarEndereco(Scanner entrada, int indice){
        if (!this.contatos.get(indice - 1).getEnderecos().isEmpty()) {
            String nome = this.contatos.get((indice - 1)).getNome();
            String sobrenome = this.contatos.get(indice - 1).getSobreNome();
            System.out.println("As informações do contato " + nome + ", são:\nNome Completo: " +
                    nome + " " + sobrenome + "\nEndereço(s):");
            this.contatos.get((indice - 1)).getEnderecos().forEach(endereco ->
                    System.out.println((this.contatos.get(indice - 1).getEnderecos().indexOf(endereco) + 1)
                            + " - " + endereco.getLogradouro() + ", " + endereco.getNumero()));
            int indExpandir = Integer.parseInt(ConsoleUIHelper.askSimpleInput("Digite o indice do endereço que deseja editar:"));

            int resposta = ConsoleUIHelper.askChooseOption("Digite o tipo de endereco:", "Residencial", "Comercial", "Caixa-postal");
            String tipo;
            if (resposta == 2) tipo = "COMERCIAL";
            else if (resposta == 3) tipo = "CAIXA_POSTAL";
            else tipo = "RESIDENCIAL";
            String logradouro = ConsoleUIHelper.askSimpleInput("Digite o novo logradouro:");
            String bairro = ConsoleUIHelper.askSimpleInput("Digite o novo Bairro:");
            String numero = ConsoleUIHelper.askSimpleInput("Digite o novo numero:");
            String cep = ConsoleUIHelper.askSimpleInput("Digite o novo CEP:");
            String comp = ConsoleUIHelper.askSimpleInput("Digite o novo complemento(caso nao tenha, deixar vazio):");
            if (comp.equals("")) comp = "(sem complemento)";
            String cidade = ConsoleUIHelper.askSimpleInput("Digite o nome da nova cidade:");
            String uf = ConsoleUIHelper.askSimpleInput("Digite o novo estado (sigla - UF):").toUpperCase().trim();
            uf = verificarENUM(uf);
            this.contatos.get(indice - 1).getEnderecos().get(indExpandir-1).setTipo(TipoEndereco.valueOf(tipo));
            this.contatos.get(indice - 1).getEnderecos().get(indExpandir-1).setLogradouro(logradouro);
            this.contatos.get(indice - 1).getEnderecos().get(indExpandir-1).setBairro(bairro);
            this.contatos.get(indice - 1).getEnderecos().get(indExpandir-1).setNumero(numero);
            this.contatos.get(indice - 1).getEnderecos().get(indExpandir-1).setCep(cep);
            this.contatos.get(indice - 1).getEnderecos().get(indExpandir-1).setComplemento(comp);
            this.contatos.get(indice - 1).getEnderecos().get(indExpandir-1).setCidade(cidade);
            this.contatos.get(indice - 1).getEnderecos().get(indExpandir-1).setUf(Estado.valueOf(uf.toUpperCase()));
            if (uf.equals("ERRO")) System.out.println("Erro ao cadastrar UF, edite as informações com valor valido");
            System.out.println("Endereço Editado!");

        }else System.out.println("Esse contato nao tem endereço cadastrado.");
    }

    private void adicionarEnderecoContato(Scanner entrada, int indice) {
        int resposta = ConsoleUIHelper.askChooseOption("Digite o tipo de endereco:", "Residencial", "Comercial", "Caixa-postal");
        String tipo;
        if (resposta == 2) tipo = "COMERCIAL";
        else if (resposta == 3) tipo = "CAIXA_POSTAL";
        else tipo = "RESIDENCIAL";
        String logradouro = ConsoleUIHelper.askSimpleInput("Digite o logradouro:");
        String bairro = ConsoleUIHelper.askSimpleInput("Digite o Bairro:");
        String numero = ConsoleUIHelper.askSimpleInput("Digite o numero:");
        String cep = ConsoleUIHelper.askSimpleInput("Digite o CEP:");
        String comp = ConsoleUIHelper.askSimpleInput("Digite o complemento(caso nao tenha, deixar vazio):");
        if (comp.equals("")) comp = "(sem complemento)";
        String cidade = ConsoleUIHelper.askSimpleInput("Digite o nome da cidade:");
        String uf = ConsoleUIHelper.askSimpleInput("Digite o estado (sigla UF):").toUpperCase().trim();
        uf = verificarENUM(uf);
        Endereco novoEndereco = new Endereco(TipoEndereco.valueOf(tipo), logradouro, bairro, numero, cep, comp, cidade, Estado.valueOf(uf.toUpperCase()));
        if (!contatos.get(indice - 1).getEnderecos().stream().anyMatch(endereco -> endereco.equals(novoEndereco))) {
                this.contatos.get((indice - 1)).getEnderecos()
                        .add(new Endereco(TipoEndereco.valueOf(tipo), logradouro, bairro, numero, comp, cep, cidade, Estado.valueOf(uf.toUpperCase())));
               if (uf.equals("ERRO")) System.out.println("Erro ao cadastrar UF, edite as informações com valor valido");
               System.out.println("Endereço adicionado!");
            } else{
                System.out.println("Não é possivel adicionar novo endereço ja existentes/duplicados, tente novamente com outras informações.");

        }
    }
    private void adicionarTelefoneContato(Scanner entrada, int indice) {
        int resposta = ConsoleUIHelper.askChooseOption("Digite o tipo de telefone:", "Residencial", "Comercial", "Celular");
        String tipo;
        if (resposta == 1) tipo = "RESIDENCIAL";
        else if (resposta == 2) tipo = "COMERCIAL";
        else tipo = "CELULAR";
        String ddi = ConsoleUIHelper.askSimpleInput("Digite o DDI:");
        String ddd = ConsoleUIHelper.askSimpleInput("Digite o DDD:");
        String numero = ConsoleUIHelper.askSimpleInput("Digite o numero:");

        Telefone novoTelefone = new Telefone(TipoTelefone.valueOf(tipo), ddi, ddd, numero);
        if (!this.contatos.get(indice - 1).getTelefones().stream().anyMatch(telefone -> telefone.equals(novoTelefone))) {
            this.contatos.get((indice - 1)).getTelefones()
                    .add(new Telefone(TipoTelefone.valueOf(tipo), ddi, ddd, numero));
            System.out.println("Numero Adicionado!");
        } else {
            System.out.println("Não é possivel adicionar novo telefone ja existentes/duplicados, tente novamente com outras informações.");
        }
    }
    public void exibirEnderecosContato(Scanner entrada) {
        listarContatos();
        if (!getContatos().isEmpty()) {
            int indice = Integer.parseInt(ConsoleUIHelper.askSimpleInput("Digite o indice do contato que deseja exibir os endereços: "));
            if (!getContatos().get(indice - 1).getEnderecos().isEmpty()) {
                String nome = this.getContatos().get((indice - 1)).getNome();
                String sobrenome = this.getContatos().get(indice - 1).getSobreNome();
                System.out.println("As informações do contato " + nome + ", são:\nNome Completo: " +
                        nome + " " + sobrenome + "\nEndereço(s):");
                this.contatos.get((indice - 1)).getEnderecos().forEach(endereco ->
                        System.out.println((this.contatos.get(indice - 1).getEnderecos().indexOf(endereco) + 1)
                                + " - " + endereco.getLogradouro() + ", " + endereco.getNumero()));
                String resp = Integer.toString(ConsoleUIHelper.askChooseOption("Deseja detalhar endereços?","Sim", "Não"));
                if (resp.equals("1")) {
                    int pg = 1;
                    int skip = 0;
                    boolean continuar;

                    do {
                        this.contatos.get(indice - 1).getEnderecos().stream().skip(skip).limit(1)
                                .forEach(System.out::println);
                        if (skip < 1) {
                            pg = ConsoleUIHelper.askChooseOption("Deseja sair ou passar de pagina?", "Sair", "Proxima pagina");
                            if (pg == 1) {
                                continuar = false;
                            } else {
                                continuar = true;
                                skip = 1;
                            }
                        } else if (this.contatos.get(indice-1).getEnderecos().size() - skip > 1) {
                            pg = ConsoleUIHelper.askChooseOption("\"Deseja sair ou passar de pagina?", "Sair", "Proxima pagina", "Pagina anterior");
                            if (pg == 1) {
                                continuar = false;
                            } else if (pg == 2) {
                                continuar = true;
                                skip += 1;
                            } else {
                                continuar = true;
                                skip -= 1;
                            }
                        } else {
                            pg = ConsoleUIHelper.askChooseOption("Deseja sair ou passar de pagina?", "Sair", "Pagina anterior");

                            if (pg == 1) {
                                continuar = false;
                            } else {
                                continuar = true;
                                skip -= 1;
                            }


                        }
                    } while (continuar);
                }
            }else System.out.println("Esse contato nao tem endereço cadastrado.");
        }
    }
    public void exibirTelefonesContato(Scanner entrada) {
        listarContatos();
        if (!getContatos().isEmpty()) {
            int indice = Integer.parseInt(ConsoleUIHelper.askSimpleInput("Digite o indice do contato que deseja exibir as informações: "));
            if (!getContatos().get(indice - 1).getTelefones().isEmpty()) {
                String nome = this.getContatos().get((indice - 1)).getNome();
                String sobrenome = this.getContatos().get(indice - 1).getSobreNome();
                System.out.println("As informações do contato " + nome + ", são:\nNome Completo: " + nome + " " + sobrenome + "\nTelefone(s):");
                this.getContatos().get((indice - 1)).getTelefones().forEach(telefone ->
                        System.out.println((this.contatos.get(indice - 1).getTelefones().indexOf(telefone) + 1)
                                + " - " + telefone.getDdd() + telefone.getNumero()));
                String resp = Integer.toString(ConsoleUIHelper.askChooseOption("Deseja detalhar endereços?","Sim", "Não"));
                if (resp.equals("1")) {
                    int pg = 1;
                    int skip = 0;
                    boolean continuar;

                    do {
                        this.contatos.get(indice - 1).getTelefones().stream().skip(skip).limit(1)
                                .forEach(System.out::println);
                        if (skip < 1) {
                            pg = ConsoleUIHelper.askChooseOption("Deseja sair ou passar de pagina?", "Sair", "Proxima pagina");
                            if (pg == 1) {
                                continuar = false;
                            } else {
                                continuar = true;
                                skip = 1;
                            }
                        } else if (this.contatos.get(indice-1).getTelefones().size() - skip > 1) {
                            pg = ConsoleUIHelper.askChooseOption("\"Deseja sair ou passar de pagina?", "Sair", "Proxima pagina", "Pagina anterior");
                            if (pg == 1) {
                                continuar = false;
                            } else if (pg == 2) {
                                continuar = true;
                                skip += 1;
                            } else {
                                continuar = true;
                                skip -= 1;
                            }
                        } else {
                            pg = ConsoleUIHelper.askChooseOption("Deseja sair ou passar de pagina?", "Sair", "Pagina anterior");

                            if (pg == 1) {
                                continuar = false;
                            } else {
                                continuar = true;
                                skip -= 1;
                            }


                        }
                    } while (continuar);
                }
            }else System.out.println("Esse contato nao tem telefone cadastrado.");
        }
    }
    public void exibirInfosContato(Scanner entrada) {
        listarContatos();
        if (!getContatos().isEmpty()) {
            int indice = Integer.parseInt(ConsoleUIHelper.askSimpleInput("Digite o indice do contato que deseja exibir as informações: "));
            String nome = this.getContatos().get((indice - 1)).getNome();
            String sobrenome = this.getContatos().get((indice - 1)).getSobreNome();
            String empresa = this.getContatos().get((indice - 1)).getEmpresa();
            String email = this.getContatos().get((indice - 1)).getEmail();
            System.out.println("As informações do contato " +
                    (this.getContatos().indexOf(this.getContatos().get(indice - 1))+1) +
                    ", são:\nNome Completo: " + nome + " " + sobrenome + "\nTelefone(s):");
            this.getContatos().get((indice - 1)).getTelefones().forEach(telefone ->
                    System.out.println((this.contatos.get(indice - 1).getTelefones().indexOf(telefone) + 1)
                            + " - " + telefone.getDdd() + telefone.getNumero()));
            System.out.println("Endereços:");
            this.contatos.get((indice - 1)).getEnderecos().forEach(endereco ->
                    System.out.println((this.contatos.get(indice - 1).getEnderecos().indexOf(endereco) + 1)
                            + " - " + endereco.getLogradouro() + ", " + endereco.getNumero()));

            int indExpandir = ConsoleUIHelper.askChooseOption("Deseja expandir informação de contato?", "Sim", "Não");
            if (indExpandir == 1) System.out.println(this.getContatos().get((indice - 1)).toString());

        }
    }
    public void removerTelefoneContato(Scanner entrada) {
        listarContatos();
        if (!getContatos().isEmpty()) {
            int indice = Integer.parseInt(ConsoleUIHelper.askSimpleInput("Digite o indice do contato que deseja excluir o telefone: "));
            System.out.printf("Os telefones do contato %s, são: \n", this.getContatos().get((indice - 1)).getNome());

            if (getContatos().get((indice - 1)).getTelefones().stream().count() > 0) {
                getContatos().get(indice - 1).getTelefones().stream().forEach(telefone ->
                        System.out.println("Indice: " +
                                (this.getContatos().get(indice - 1).getTelefones().indexOf(telefone) + 1) +
                                "\n" + telefone.toString()));
                int indiceTelefone = Integer.parseInt(ConsoleUIHelper.askSimpleInput("Qual deseja remover?"));
                this.getContatos().get((indice - 1)).getTelefones().remove(indiceTelefone - 1);
                System.out.println("Telefone removido!");
            } else {
                System.out.println("Sem telefones salvos no contato.");
            }
        }
    }
    public void removerEnderecoContato(Scanner entrada) {
        listarContatos();
        if (!getContatos().isEmpty()) {
            int indice = Integer.parseInt(ConsoleUIHelper.askSimpleInput("Digite o indice do contato que deseja excluir o endereço: "));
            System.out.printf("Os endereços do contato %s, são: \n", this.getContatos().get((indice - 1)).getNome());

            if (getContatos().get((indice - 1)).getEnderecos().stream().count() > 0) {
                this.getContatos().get(indice - 1).getEnderecos().stream().forEach(endereco ->
                        System.out.println("Indice: " +
                                (this.getContatos().get(indice - 1).getEnderecos().indexOf(endereco) + 1) +
                                "\n" + endereco.toString()));
                int indiceEndereco = Integer.parseInt(ConsoleUIHelper.askSimpleInput("Qual deseja remover?"));
                this.getContatos().get((indice - 1)).getEnderecos().remove(indiceEndereco - 1);
                System.out.println("Endereço removido!");
            } else {
                System.out.println("Sem endereços salvos no contato.");
            }
        }
    }
    public void exibirMenuAgenda(Scanner entrada) {
        boolean continuar;
        do {
            ConsoleUIHelper.drawHeader("MENU AGENDA DE CONTATOS", 80);

            switch (Integer.toString(ConsoleUIHelper.askChooseOption("# ESCOLHA UM INDICE DO MENU:",
                    "Adicionar Contato",
                    "Listar Contatos com paginas",
                    "Buscar Contato",
                    "Remover contato",
                    "Remover todos os contatos",
                    "Adicionar informação de contato",
                    "Remover telefone de contato",
                    "Remover endereço de contato",
                    "Exibir todas as informações de um contato",
                    "Exibir todos os telefones de um contato",
                    "Exibir todos os endereços de um contato",
                    "Editar informação de contato",
                    "Sair do menu"))) {
                case "1":
                    do {
                        adicionarContato();
                        continuar = ConsoleUIHelper.askConfirm("Deseja continuar adicionando contatos?", "Sim", "Não");
                    } while (continuar);
                    break;

                case "2":

                    listarContatos();
                    break;

                case "3":
                    do {
                        if (!this.contatos.isEmpty()) {
                            buscarContato(entrada);
                            continuar = ConsoleUIHelper.askConfirm("Deseja continuar pesquisando contatos?", "Sim", "Não");
                        } else {
                            System.out.println("Não há contatos salvo na agenda.");
                            continuar = false;
                        }
                    } while (continuar);
                    break;

                case "4":
                    do {
                        removerContato(entrada);
                        continuar = ConsoleUIHelper.askConfirm("Deseja continuar removendo contatos?", "Sim", "Não");
                    } while (continuar);
                    break;

                case "5":
                    removerTodosContatos(entrada);
                    break;

                case "6":
                    do {
                        adicionarInformacaoContato(entrada);
                        continuar = ConsoleUIHelper.askConfirm("Deseja continuar adicionando informações de contatos?", "Sim", "Não");
                    } while (continuar);
                    break;

                case "7":
                    do {
                        removerTelefoneContato(entrada);
                        continuar = ConsoleUIHelper.askConfirm("Deseja continuar removendo telefone de contatos?", "Sim", "Não");
                    } while (continuar);
                    break;

                case "8":
                    do {
                        removerEnderecoContato(entrada);
                        continuar = ConsoleUIHelper.askConfirm("Deseja continuar removendo endereço de contatos?", "Sim", "Não");
                    } while (continuar);
                    break;

                case "9":
                    do {
                        exibirInfosContato(entrada);
                        continuar = ConsoleUIHelper.askConfirm("Deseja exibir informação de outro contato?", "Sim", "Não");
                    } while (continuar);
                    break;

                case "10":
                    do {
                        exibirTelefonesContato(entrada);
                        continuar = ConsoleUIHelper.askConfirm("Deseja exibir telefone de outro contato?", "Sim", "Não");
                    } while (continuar);
                    break;

                case "11":
                    do {
                        exibirEnderecosContato(entrada);
                        continuar = ConsoleUIHelper.askConfirm("Deseja exibir endereço de outro contato?", "Sim", "Não");
                    } while (continuar);
                    break;

                case "12":
                    do {
                        editarInfoContato(entrada);
                        continuar = ConsoleUIHelper.askConfirm("Deseja editar infomação de outro contato?", "Sim", "Não");
                    } while (continuar);
                break;

                case "13":
                    try {
                        salvarContatos();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    ConsoleUIHelper.drawLine(80);
                    System.exit(0);
                default:
                    System.out.println("Opção inválida...");
                    break;
            }
            continuar = ConsoleUIHelper.askConfirm("Deseja retornar ao menu principal?", "Sim", "Não");
            ConsoleUIHelper.clearScreen();
        } while (continuar);
    }
    public void salvarContatos() throws Exception {
        int cont = contagemArquivos();
        for (int i = 0; i < cont; i++) {
            if (Files.exists(Path.of(String.format(CAMINHO, (i+1))))) {
                Files.delete(Path.of(String.format(CAMINHO, (i + 1))));
            }
        }
        for (int i = 0; i < this.contatos.size(); i++) {
            String agendaJSON = new ObjectMapper().writeValueAsString(this.contatos.get(i));
            Files.write(Path.of(String.format(CAMINHO, (i+1) )), agendaJSON.getBytes(),
                    StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
        }
    }
    public void lerLista() throws IOException {
        int cont = contagemArquivos();
        final var objectMapper = new ObjectMapper();
        String agendaJSON = null;
        try {
            for (int i = 0; i < cont; i++) {
                agendaJSON = Files.readString(Path.of(String.format(CAMINHO, (i+1))));
                Contato rec = objectMapper.readValue(agendaJSON, Contato.class);
                Contato contato = new Contato(rec.getNome(), rec.getSobreNome(),
                        rec.getEmpresa(), rec.getEmail() , rec.getTelefones(), rec.getEnderecos());
                this.contatos.add(contato);
            }
        } catch (IOException e) {
          throw new RuntimeException();
        }
    }
    private int contagemArquivos(){

        boolean continuar;
        int cont= 0;
        do {
            if (Files.exists(Path.of(String.format(CAMINHO, (cont+1))))){
                cont++;
                continuar = true;
            }
            else continuar = false;
        }while(continuar);
        return cont;
    }
    public String verificarENUM(String estado){
        if ("AC".equals(estado)) return estado;
        else if ("AL".equals(estado)) return estado;
        else if ("AM".equals(estado)) return estado;
        else if ("AP".equals(estado)) return estado;
        else if ("BA".equals(estado)) return estado;
        else if ("CE".equals(estado)) return estado;
        else if ("DF".equals(estado)) return estado;
        else if ("ES".equals(estado)) return estado;
        else if ("GO".equals(estado)) return estado;
        else if ("MA".equals(estado)) return estado;
        else if ("MG".equals(estado)) return estado;
        else if ("MS".equals(estado)) return estado;
        else if ("MT".equals(estado)) return estado;
        else if ("PA".equals(estado)) return estado;
        else if ("PB".equals(estado)) return estado;
        else if ("PE".equals(estado)) return estado;
        else if ("PI".equals(estado)) return estado;
        else if ("PR".equals(estado)) return estado;
        else if ("RJ".equals(estado)) return estado;
        else if ("RN".equals(estado)) return estado;
        else if ("RO".equals(estado)) return estado;
        else if ("RR".equals(estado)) return estado;
        else if ("RS".equals(estado)) return estado;
        else if ("TO".equals(estado)) return estado;
        else if ("SC".equals(estado)) return estado;
        else if ("SE".equals(estado)) return estado;
        else if ("SP".equals(estado)) return estado;
        else return "ERRO";
    }
    }