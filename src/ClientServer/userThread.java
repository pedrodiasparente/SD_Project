package ClientServer;

import Exceptions.DadosInexistentesException;
import Exceptions.DadosJaExistemException;
import MediaCenter.*;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class userThread implements Runnable{
    private Socket clSocket;
    private MediaCenter mediaCenter;
    //PATH PARA O PROJECTO
    private final String PATH_SD = "/home/nuno/Documents/Universidade/SD/trabalho/SD_Project/";


    userThread(Socket socket, MediaCenter mediaCenter){
        this.clSocket = socket;
        this.mediaCenter = mediaCenter;
    }

    @Override
    public void run() {
        byte[] musicaBytes = new byte[0];
        ArrayList<Musica> musicas;
        Musica musica;
        String numTags;
        int length;

        String info = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clSocket.getOutputStream());
            DataInputStream dIn = new DataInputStream(clSocket.getInputStream());


            while (clSocket.isConnected() && !info.equals("quit")) {

                info = in.readLine();
                String[] dataExe = info.split(" ");

                System.out.println(info + " [Executando...]");

                switch (dataExe[0]){
                    case "registo" :
                        try{
                            mediaCenter.registo(dataExe[1], dataExe[2]);
                        } catch (DadosJaExistemException e){
                            out.println("Erro");
                        }
                        out.println("Sucesso");
                        break;

                    case "login" :
                        try {
                            mediaCenter.login(dataExe[1], dataExe[2]);
                        } catch(DadosInexistentesException e){
                            out.println("Erro");
                        }
                        out.println("Sucesso");
                        break;

                    case "procura" :
                        musicas = mediaCenter.search(dataExe[1]);
                        out.println(musicas.size());
                        for(Musica m : musicas){
                            out.println(m.getIdentificador());
                            out.println(m.getTitulo());
                            out.println(m.getArtista());
                            out.println(m.getAno());
                            out.println(m.getDescargas());
                            out.println(m.getTags().size());
                            for(String t : m.getTags()){
                                out.println(t);
                            }
                        }
                        break;

                    case "insere" :
                        musica = new Musica();
                        musica.setIdentificador(Integer.parseInt(in.readLine()));
                        musica.setTitulo(in.readLine());
                        musica.setArtista(in.readLine());
                        musica.setAno(Integer.parseInt(in.readLine()));
                        numTags = in.readLine();
                        for(int j = 0; j < Integer.parseInt(numTags); j++){
                            musica.getTags().add(in.readLine());
                        }
                        length = dIn.readInt();
                        if(length>0) {
                            musicaBytes = new byte[length];
                            dIn.readFully(musicaBytes, 0, musicaBytes.length);
                        }
                        Files.write(Paths.get(PATH_SD + "userData/server/" + musica.getTitulo() + ".mp3"), musicaBytes);
                        mediaCenter.addMusica(musica);
                        out.println("Musica inserida!");
                        break;

                    default :
                        out.println("Op invalida");
                        break;
                }
                out.flush();
            }
            clSocket.shutdownOutput();
            clSocket.shutdownInput();
            clSocket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
