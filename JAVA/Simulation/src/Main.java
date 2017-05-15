import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main extends JFrame {

	public static void main(String[] args) {
		try{
			Web siatka = new Web();
			new Interface(siatka);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}		

	}
	
}
