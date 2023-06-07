package com.doc;
import java.io.File;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;


public class VedioPlayer extends Application {
	
		@Override
		public void start(Stage primaryStage) throws Exception{
			String path = "music/file_example_MP4_1280_10MG.mp4";
			Media media = new Media(new File(path).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			MediaView mv = new MediaView(mediaPlayer);
			mediaPlayer.setAutoPlay(true);
			  Group root = new Group();  
		      root.getChildren().add(mv);  
		      Scene scene = new Scene(root,720,480);
		      primaryStage.setScene(scene);  
		      primaryStage.setTitle("HD video player ");  
		      primaryStage.show();

		}
		
		public static void main(String[] args) {
			launch(args);
		}
}