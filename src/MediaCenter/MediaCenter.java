package MediaCenter;

import Exceptions.DadosJaExistemException;
import Exceptions.DadosInexistentesException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MediaCenter implements MediaCenterAPI{
    private HashMap<String, Utilizador> utilizadores;
    private HashMap<Integer, Musica> musicas;
    private int idMusicas;
    //PATH PARA O PROJETO
    private final String PATH_SD = "/home/nuno/Documents/Universidade/SD/trabalho/SD_Project/";


    public MediaCenter(){
        Musica star;

        this.utilizadores = new HashMap<String, Utilizador>();
        this.musicas = new HashMap<Integer, Musica>();
        idMusicas = 0;

        star = new Musica("star", "Mozart", 1519, new ArrayList<>());
        this.addMusica(star);
    }

    @Override
    public synchronized void registo(String nome, String pass) throws DadosJaExistemException {
        if(!utilizadores.containsKey(nome)) {
            utilizadores.put(nome, new Utilizador(nome, pass));
            File f = new File(PATH_SD + "userData/users/" + nome);
            if(!f.exists())
                f.mkdir();
        } else {
            throw new DadosJaExistemException();
        }
    }

    @Override
    public synchronized void login(String nome, String pass) throws DadosInexistentesException{
        if(utilizadores.containsKey(nome) && utilizadores.get(nome).getPass().equals(pass)){
            return;
        }
        throw new DadosInexistentesException();
    }

    @Override
    public synchronized void addMusica(Musica musica){
        musica.setIdentificador(idMusicas);
        musicas.put(idMusicas++, musica);
    }

    @Override
    public synchronized Musica getMusica(int id) throws DadosInexistentesException{
        int descargas;
        if(musicas.containsKey(id)){
            descargas = musicas.get(id).getDescargas();
            musicas.get(id).setDescargas(descargas + 1);
            return(musicas.get(id));
        }
        throw new DadosInexistentesException();
    }

    @Override
    public synchronized ArrayList<Musica> search(String tag){
        ArrayList<Musica> ret = new ArrayList<Musica>();
        for(Musica m : musicas.values()){
            if(m.getTags().contains(tag))
                ret.add(m);
        }
        return ret;
    }
}
