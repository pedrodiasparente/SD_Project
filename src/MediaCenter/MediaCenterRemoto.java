package MediaCenter;

import Exceptions.DadosInexistentesException;
import Exceptions.DadosJaExistemException;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MediaCenterRemoto implements MediaCenterAPI {
    //PATH PARA O PROJECTO
    private final String PATH_SD = "/home/nuno/Documents/Universidade/SD/trabalho/SD_Project/";
    private Socket socket;
    String currentUser;

    public MediaCenterRemoto(){
        currentUser = null;
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
        Musica musica = new Musica();
        String numTags;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            if (socket.isConnected()) {
                out.println("download " + id);
                out.flush();
                musica.setIdentificador(Integer.parseInt(in.readLine()));
                musica.setTitulo(in.readLine());
                musica.setArtista(in.readLine());
                musica.setAno(Integer.parseInt(in.readLine()));
                musica.setDescargas(Integer.parseInt(in.readLine()));
                numTags = in.readLine();
                for(int j = 0; j < Integer.parseInt(numTags); j++){
                    musica.getTags().add(in.readLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return musica;
    }

    @Override
    public ArrayList<Musica> search(String tag) {
        String numMusica, numTags;
        ArrayList<Musica> musicas = new ArrayList<Musica>();
        Musica currentMusica;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            if (socket.isConnected()) {
                out.println("procura " + tag);
                out.flush();
                numMusica = in.readLine();
                for(int i = 0; i < Integer.parseInt(numMusica); i++){
                    currentMusica = new Musica();
                    currentMusica.setIdentificador(Integer.parseInt(in.readLine()));
                    currentMusica.setTitulo(in.readLine());
                    currentMusica.setArtista(in.readLine());
                    currentMusica.setAno(Integer.parseInt(in.readLine()));
                    currentMusica.setDescargas(Integer.parseInt(in.readLine()));
                    numTags = in.readLine();
                    for(int j = 0; j < Integer.parseInt(numTags); j++){
                        currentMusica.getTags().add(in.readLine());
                    }
                    musicas.add(currentMusica);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return musicas;
    }



    @Override
    public void addMusica(Musica m) {
        String infoServer;
        byte[] musicaBytes;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());

            if (socket.isConnected()) {
                out.println("insere");
                out.println(m.getIdentificador());
                out.println(m.getTitulo());
                out.println(m.getArtista());
                out.println(m.getAno());
                out.println(m.getTags().size());
                for(String t : m.getTags()){
                    out.println(t);
                }
                musicaBytes = Files.readAllBytes(Paths.get(PATH_SD + "userData/users/" + currentUser + "/" + m.getTitulo() +".mp3"));
                out.flush();
                dOut.writeInt(musicaBytes.length);
                dOut.write(musicaBytes);
                dOut.flush();
                infoServer = in.readLine();
                System.out.println(infoServer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downloadMusica(Musica m) {
        String infoServer;
        byte[] musicaBytes;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());

            if (socket.isConnected()) {
                out.println("download");
                out.println(m.getIdentificador());
                out.println(m.getTitulo());
                out.println(m.getArtista());
                out.println(m.getAno());
                out.println(m.getTags().size());
                for(String t : m.getTags()){
                    out.println(t);
                }
                musicaBytes = Files.readAllBytes(Paths.get(PATH_SD + "userData/server/" + m.getTitulo() +".mp3"));
                out.flush();
                dOut.writeInt(musicaBytes.length);
                dOut.write(musicaBytes);
                dOut.flush();
                infoServer = in.readLine();
                System.out.println(infoServer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void setCurrentUser(String currentUser){
        this.currentUser = currentUser;
    }
}
