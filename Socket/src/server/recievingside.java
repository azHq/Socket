package server;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;

class recievingside extends Thread {
	 Socket connection ;
	public recievingside() throws IOException {

    	ServerSocket socket = new ServerSocket(1111);
        connection = socket.accept();
        
    }

    @Override
    public void run() {
        System.out.println("Receive Thread Start");
        DataInputStream in;
        try {

        } catch (Exception e) {
            return;
        }
        try {
            in = new DataInputStream(connection.getInputStream());
            while (true) {
                if (!connection.isConnected()) {
                    System.out.println("Connection not connected");
                    break;
                }
                try {

                    int len = in.readInt();
                    byte[] data = new byte[len];
                    System.out.println("Image size: " + len);
                    if (len > 0) {
                        in.readFully(data, 0, len);
                        BufferedImage bi = ImageIO.read(new ByteArrayInputStream(data));
                        System.out.println(bi);
                    }
                    
                } catch (Exception e) {
                }
                pause(100);
            }
			
			in.close();
			
        } catch (Exception e) {

        }
    }

    public void pause(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {

        }
    }
}

