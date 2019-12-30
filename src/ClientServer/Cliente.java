package ClientServer;

import Comparators.MusicaComparator;
import Exceptions.DadosInexistentesException;
import Exceptions.DadosJaExistemException;
import MediaCenter.MediaCenterRemoto;
import MediaCenter.Musica;

import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
    private MediaCenterRemoto mcr;
    boolean loggedIn;

    public Cliente(){
        this.mcr = new MediaCenterRemoto();
        loggedIn = false;
    }

    public void clientStart() {
        Scanner scanner = new Scanner(System.in);
        Musica musica;
        ArrayList<Musica> musicas;
        String info, nome, pass, tag, tags;
        String[] tagIndividual;

        info = "";

        while (!info.equals("quit")){
            System.out.println("Insira a próxima operação:");
            info = scanner.nextLine();
            switch (info) {
                case "registo":
                    System.out.print("Insira nome: ");
                    nome = scanner.nextLine();
                    System.out.print("Insira pass: ");
                    pass = scanner.nextLine();
                    try {
                        mcr.registo(nome, pass);
                    } catch (DadosJaExistemException e) {
                        System.out.println("Conta já existe");
                    }
                    break;

                case "login":
                    if(!loggedIn) {
                        System.out.print("Insira nome: ");
                        nome = scanner.nextLine();
                        System.out.print("Insira pass: ");
                        pass = scanner.nextLine();
                        try {
                            mcr.login(nome, pass);
                            mcr.setCurrentUser(nome);
                            loggedIn = true;
                        } catch (DadosInexistentesException e) {
                            System.out.println("Conta invalida");
                        }
                    } else{
                        System.out.println("Já conectado");
                    }
                    break;

                case "procura":
                    if(loggedIn) {
                        System.out.print("Insira a tag a procurar: ");
                        tag = scanner.nextLine();
                        musicas = mcr.search(tag);
                        musicas.sort(new MusicaComparator());
                        for (Musica m : musicas) {
                            System.out.print("[" + m.getIdentificador() + "] (" + m.getDescargas() + ") " + m.getTitulo() + " by " + m.getArtista() + " | ( ");
                            for (String t : m.getTags()) {
                                System.out.print(t + " ");
                            }
                            System.out.println(")");
                        }
                    } else{
                        System.out.println("Ainda não conectado");
                    }
                    break;

                case "insere":
                    if(loggedIn){
                        musica = new Musica();
                        System.out.print("Insira o nome da música a inserir:");
                        musica.setTitulo(scanner.nextLine());
                        System.out.print("Insira o nome do Artista da música:");
                        musica.setArtista(scanner.nextLine());
                        System.out.print("Insira o Ano da música:");
                        musica.setAno(Integer.parseInt(scanner.nextLine()));
                        System.out.print("Insira as tags da música que deseja adicionar (separadas por espaço):");
                        tags = scanner.nextLine();
                        tagIndividual = tags.split(" ");
                        for(String t : tagIndividual){
                            musica.getTags().add(t);
                        }
                        mcr.addMusica(musica);
                    } else{
                        System.out.println("Ainda não conectado");
                    }
                    break;

                case "quit":
                    mcr.quit();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e){
                    }
                    break;

                default:
                    System.out.println("Operação inválida");
                    break;
            }
        }
    }
}
