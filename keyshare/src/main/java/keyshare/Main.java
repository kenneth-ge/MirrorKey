package keyshare;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    //keyboard poll time is based on the average keyboard polling rate -- 1000hz
    //therefore, the program should poll once per millisecond (approximately)
    
    static Main main;
    
    public static void startKeyListener() {
    	main.keys();
    }
    
    Stage s;
    Scene scene;
    StackPane root;
    
    public void keys() {
    	root.getChildren().clear();
    	
    	scene.setOnKeyPressed(this::pressed);
    	scene.setOnKeyReleased(this::released);
    	scene.setOnMouseMoved(this::mouseMoved);
    	scene.setOnMouseClicked(this::mouseClicked);
    }
    
    double oldX, oldY, newX, newY;
    boolean pressed = false;  

    public void mouseClicked(MouseEvent event) {
    	NetworkManager.send("c");
    }
    
    public void mouseMoved(MouseEvent event) {
		oldX = newX;
		oldY = newY;
		newX = event.getSceneX();
		newY = event.getSceneY();
		double dx = newX - oldX;
		double dy = newY - oldY;

		NetworkManager.send("m " + dx + " " + dy);
    }
    
    public void pressed(KeyEvent e) {
    	NetworkManager.send("p " + e.getCode().getCode());
    }
    
    public void released(KeyEvent e) {
    	NetworkManager.send("r " + e.getCode().getCode());
    }
    
    @Override
    public void stop() throws Exception {
    	super.stop();
    	NetworkManager.close();
    }
    
    @Override
    public void start(Stage primaryStage) {
    	Main.main = this;
    	
        primaryStage.setTitle("Keyshare");
        Button btn = new Button();
        
        btn.setText("Discover devices");
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                NetworkManager.sendDiscoverBroadcast();
            }
        });
        
        NetworkManager.init();
        
        root = new StackPane();
        root.getChildren().add(btn);
        this.s = primaryStage;
        primaryStage.setScene(scene = new Scene(root, 500, 500));
        primaryStage.show();
        
    	s.setFullScreen(true);
    	s.setMaximized(true);
    }
}