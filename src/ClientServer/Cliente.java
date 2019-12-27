package ClientServer;

import Exceptions.DadosInexistentesException;
import Exceptions.DadosJaExistemException;
import MediaCenter.MediaCenterRemoto;
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
        String info, nome, pass;

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
                    System.out.print("Insira nome: ");
                    nome = scanner.nextLine();
                    System.out.print("Insira pass: ");
                    pass = scanner.nextLine();
                    try {
                        mcr.login(nome, pass);
                        loggedIn = true;
                    } catch (DadosInexistentesException e) {
                        System.out.println("Conta invalida");
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
