package MediaCenter;

import Exceptions.DadosInexistentesException;
import Exceptions.DadosJaExistemException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class MediaCenterRemoto implements MediaCenterAPI {
    private Socket socket;

    public MediaCenterRemoto(){
        try {
            socket = new Socket("127.0.0.1", 12345);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void registo(String nome, String pass) throws DadosJaExistemException {
        String infoServer;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            if (socket.isConnected()) {
                out.println("registo " + nome + " " + pass);
                out.flush();
                infoServer = in.readLine();
                if(infoServer.equals("Erro"))
                    throw new DadosJaExistemException();
                System.out.println(infoServer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void login(String nome, String pass) throws DadosInexistentesException {
        String infoServer;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            if (socket.isConnected()) {
                out.println("login " + nome + " " + pass);
                out.flush();
                infoServer = in.readLine();
                if(infoServer.equals("Erro"))
                    throw new DadosInexistentesException();
                System.out.println(infoServer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Musica getMusica(int id) throws DadosInexistentesException {
        return null;
    }

    @Override
    public ArrayList<Musica> search(String tag) {
        return null;
    }

    @Override
    public void addMusica(Musica musica) {

    }

    public void quit(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            if (socket.isConnected()) {
                out.println("quit");
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
