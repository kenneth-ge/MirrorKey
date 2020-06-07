import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws Exception {
		DatagramSocket ds = new DatagramSocket(4545);
		System.out.println(ds);
		DatagramPacket dp = new DatagramPacket(new byte[1024], 1024);
		ds.receive(dp);
		ds.close();
		
		Socket socket = new Socket(dp.getAddress(), 4545);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		Robot r = new Robot();
		
		while(true) {
			String[] st = br.readLine().split(" ");
			
			System.out.println(Arrays.toString(st));
			
			String operation = st[0];
			
			if(operation.equals("p")) {
				int key = Integer.parseInt(st[1]);
				r.keyPress(key);
			}
			
			if(operation.equals("r")) {
				int key = Integer.parseInt(st[1]);
				r.keyRelease(key);
			}
			
			if(operation.equals("c")) {
				r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			}
			
			if(operation.contentEquals("rc")) {
				r.mousePress(InputEvent.BUTTON2_DOWN_MASK);
				r.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
			}
			
			if(operation.equals("m")) {
				double x = Double.parseDouble(st[1]);
				double y = Double.parseDouble(st[2]);
				
				var v = MouseInfo.getPointerInfo().getLocation();
				
				r.mouseMove((int) x + v.x, (int) y + v.y);
			}
		}
		
		//socket.close();
	}
	
}
