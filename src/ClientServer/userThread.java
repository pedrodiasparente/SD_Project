package ClientServer;

import MediaCenter.MediaCenter;
import MediaCenter.MediaCenterRemoto;

import java.net.Socket;

public class userThread implements Runnable{
    Socket clSocket;
    MediaCenter mediaCenter;

    userThread(Socket socket, MediaCenter mediaCenter){
        this.clSocket = socket;
        this.mediaCenter = mediaCenter;
    }

    @Override
    public void run() {

    }
}
