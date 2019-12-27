package ClientServer;

import Exceptions.DadosInexistentesException;
import MediaCenter.MediaCenterRemoto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private MediaCenterRemoto mcr;

    public Cliente(){
        this.mcr = new MediaCenterRemoto();
    }

    public void clientStart() throws IOException{
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
