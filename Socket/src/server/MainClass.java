package server;

import java.io.IOException;

public class MainClass {

	public static void main(String[] args) throws IOException {
		
		recievingside r=new recievingside();
		r.start();

	}

}
