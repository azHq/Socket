package cilent;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;

class sendingside extends Thread {

    Robot robot;
    Socket s;

    public sendingside() {
        try {
            robot = new Robot();
            System.out.println("Update Thread Created");
            
            s=new Socket("127.0.0.1",1111);
        } catch (Exception e) {

        }
    }

    @Override
    public void run() {
        System.out.println("Update Thread Running");
       
        Dimension screenSize;
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out;
        try {
            out = new DataOutputStream(s.getOutputStream());
        } catch (Exception e) {
            return;
        }
        if(s.isConnected()) {
        	
        	 while (s.isConnected()) {
                 //System.out.println("test");
                 BufferedImage bi = robot.createScreenCapture(screenRectangle);
                 try {

                     ImageIO.write(bi, "PNG", baos);
                     baos.flush();
                     byte[] bytes = baos.toByteArray();
                     out.flush();
                     out.writeInt(bytes.length);
                     out.flush();
                     out.write(bytes);
                     System.out.println("Image sent");
                 } catch (Exception e) {
                 }
                 
             }

        }
        else {
        	
        	 System.out.println("Connection failed");
        }
       
        try {
            out.close();
        } catch (Exception e) {
        }
       
    }

}