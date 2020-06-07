package keyshare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class NetworkManager {

	static ServerSocket tcp;
	static DatagramSocket udp;
	
	static byte[] buffer = new byte[2048];
	static final int PORT = 4545;
	
	static void init() {
		try {
			udp = new DatagramSocket();
			tcp = new ServerSocket(PORT);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new Thread(NetworkManager::listen).start();
	}
	
	static BufferedReader br;
	static PrintWriter pw;
	static Socket s;
	
	public static void listen() {
		System.out.println("listen");
		try {
			s = tcp.accept();
			
			System.out.println("Connection");
			
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream());
			
			Platform.runLater(() -> {showDialog(s.getInetAddress().getHostName());});
			
			new Thread(NetworkManager::sendData).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void showDialog(String name) {
		System.out.println("Dialog");
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Send Key Presses");
		alert.setContentText("Are you sure you want to send your key presses to " + name + "?");
		alert.setResizable(true);
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			//start key listener
			Main.startKeyListener();
		} else {
		    try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	static boolean running = true;
	static StringBuffer sb = new StringBuffer();
	
	public static void sendData() {
		while(running) {
			if(sb.length() > 0) {
				pw.println(sb.toString());
				sb.delete(0, sb.length());
				pw.flush();
			}
			try {
				Thread.sleep(100);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void send(String s) {
		
		
		sb.append(s);
		sb.append('\n');
		
		// s.close();
	}
	
	public static void sendDiscoverBroadcast() {
		sendUDP("hello");
	}
	
	public static void sendUDP(String s) {
		byte[] buf = s.getBytes();
		try {
			DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("255.255.255.255"), PORT);
			
			udp.send(dp);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void close() {
		try {
			running = false;
			
			send("close");
			if(s != null)
				s.close();
			if(tcp != null)
				tcp.close();
			if(udp != null)
				udp.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
