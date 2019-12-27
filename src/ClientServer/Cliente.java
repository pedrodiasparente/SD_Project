package ClientServer;

import Exceptions.DadosInexistentesException;
import Exceptions.DadosJaExistemException;
import MediaCenter.MediaCenterRemoto;
import java.util.Scanner;

public class Cliente {
    private MediaCenterRemoto mcr;

    public Cliente(){
        this.mcr = new MediaCenterRemoto();
    }

    public void clientStart() {
        Scanner scanner = new Scanner(System.in);
        String info, nome, pass;

        while ((info = scanner.nextLine()) != null) {
            switch (info) {
                case "login":
                    nome = scanner.nextLine();
                    pass = scanner.nextLine();
                    try {
                        mcr.login(nome, pass);
                    } catch (DadosInexistentesException e) {
                        System.out.println("Conta Invalida");
                    }
                    break;
                case "registo":
                    nome = scanner.nextLine();
                    pass = scanner.nextLine();
                    try {
                        mcr.registo(nome, pass);
                    } catch (DadosJaExistemException e) {
                        System.out.println("Conta Invalida");
                    }

            }
        }
    }
}
