import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {
    private Socket socket;
    private BancoRemoto bancoRemoto;

    public Cliente(){
        try {
            socket = new Socket("127.0.0.1", 12345);
        } catch(IOException e){
            e.printStackTrace();
        }
        this.bancoRemoto = new BancoRemoto(socket);
    }

    public void clientStart() throws IOException{
        String info, infoServer;
        int[] contas = {0,1,3,4,5,6};
        //Scanner scanner = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bancoRemoto.criar(2000);
        bancoRemoto.criar(1000);
        bancoRemoto.consultar(0);
        bancoRemoto.consultar(1);
        bancoRemoto.levantar(0,500.0);
        bancoRemoto.depositar(1,1500.0);
        bancoRemoto.consultar(0);
        bancoRemoto.consultar(1);
        bancoRemoto.criar(34);
        bancoRemoto.consultar(2);
        bancoRemoto.transferir(2, 0, 34.0);
        bancoRemoto.consultar(0);
        bancoRemoto.consultar(1);
        bancoRemoto.consultar(2);
        bancoRemoto.fechar(2);
        bancoRemoto.consultar(0);
        bancoRemoto.consultar(2);
        bancoRemoto.criar(4500);
        bancoRemoto.criar(5500);
        bancoRemoto.criar(6500);
        bancoRemoto.criar(7500);
        bancoRemoto.consultarTudo(contas);
        while(socket.isConnected()){
            //info = scanner.nextLine();
            infoServer = in.readLine();
            System.out.println(infoServer);
        }
        socket.shutdownOutput();
        socket.shutdownInput();
        socket.close();
    }
}
