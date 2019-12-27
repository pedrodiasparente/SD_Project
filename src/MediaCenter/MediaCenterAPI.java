package MediaCenter;

import Exceptions.DadosInexistentesException;
import Exceptions.DadosJaExistemException;

import java.util.ArrayList;

public interface MediaCenterAPI {

    public void registo(String nome, String pass) throws DadosJaExistemException;

    public void login(String nome, String pass) throws DadosInexistentesException;

    public void addMusica(Musica musica);

    public Musica getMusica(int id) throws DadosInexistentesException;

    public ArrayList<Musica> search(String tag);
}
