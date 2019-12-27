package MediaCenter;

import Exceptions.DadosInexistentesException;
import Exceptions.DadosJaExistemException;

import java.io.IOException;
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
        this.socket = socket;
    }

    @Override
    public void registo(String nome, String pass) throws DadosJaExistemException {

    }

    @Override
    public void login(String nome, String pass) throws DadosInexistentesException {

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
}
