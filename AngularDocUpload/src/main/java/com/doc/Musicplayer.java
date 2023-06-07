package com.doc;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class Musicplayer {

	public static void main(String[] args) {

		paysound("music/file_example_WAV_10MG.wav");
		JOptionPane.showMessageDialog(null, "Stop music play.");
	}

	public static void paysound(String filepath) {

		try {

			File path = new File(filepath);
			AudioInputStream ais = AudioSystem.getAudioInputStream(path);
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
