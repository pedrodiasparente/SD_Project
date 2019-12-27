package MediaCenter;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

public class Musica {
    private int identificador;
    private String titulo, artista;
    private ArrayList<String> tags;

    public Musica(String titulo, String artista, ArrayList<String> tags){
        this.identificador = 0;
        this.titulo = titulo;
        this.artista = artista;
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

    public String getTitulo() {
        return titulo;
    }

    public void setArtista(String artista) {
        this.artista = artista;
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
