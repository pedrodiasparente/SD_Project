package MediaCenter;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

public class Musica {
    private int identificador, ano, descargas;
    private String titulo, artista;
    private ArrayList<String> tags;

    public Musica(){
        this.identificador = 0;
        this.descargas = 0;
        this.titulo = "";
        this.artista = "";
        this.ano = 0;
        this.tags = new ArrayList<>();
    }

    public Musica(String titulo, String artista, int ano, ArrayList<String> tags){
        this.identificador = 0;
        this.descargas = 0;
        this.titulo = titulo;
        this.artista = artista;
        this.ano = ano;
        this.tags = tags;
        tags.add(titulo);
        tags.add(artista);
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getArtista() {
        return artista;
    }

    public int getAno() {
        return ano;
    }

    public int getDescargas() {
        return descargas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
