package ClientServer;

import Exceptions.DadosInexistentesException;
import MediaCenter.MediaCenterRemoto;
import java.util.Scanner;

public class Cliente {
    private MediaCenterRemoto mcr;

    public Cliente(){
        this.mcr = new MediaCenterRemoto();
    }

    public void clientStart(){
        Scanner scanner = new Scanner(System.in);
        String info, nome, pass;
        info = scanner.nextLine();
        switch (info){
            case "login":
                nome = scanner.nextLine();
                pass = scanner.nextLine();
                try {
                    mcr.login(nome, pass);
                } catch (DadosInexistentesException e){
                    System.out.println("Conta Invalida");
                }
                break;
        }
    }
}
