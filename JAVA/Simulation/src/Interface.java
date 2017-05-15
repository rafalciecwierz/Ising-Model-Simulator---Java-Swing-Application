import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class Interface extends JFrame {
	int count = 0;	
	int energySum = 0;
	public Interface(Web w)
	{
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Isign Model Symulation Interface by Rafał Ciećwierz");
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(new GridBagLayout());
		//setLayout(new GridLayout(1,3));
		setLocation(600, 100);
		String[] tempStrings = { "Choose temp", "1", "2", "2.2", "2.3", "2.4","2.6", "3", "4", "5" };
		JLabel label1 = new JLabel("Simulation steps:");
		label1.setFont(new Font("Arial", Font.BOLD, 32));
		JLabel energyStats = new JLabel("Magnetism: ");
		energyStats.setFont(new Font("Arial", Font.PLAIN, 28));
		JLabel energy = new JLabel("0");
		JLabel stepCount = new JLabel("Steps: ");
		stepCount.setFont(new Font("Arial", Font.PLAIN, 28));
		JLabel tempLabel = new JLabel("T*: ");
		tempLabel.setFont(new Font("Arial", Font.PLAIN, 28));
		
		JLabel steps = new JLabel("0");
		JButton button = new JButton("1");
		JButton button1 = new JButton("10");
		JButton button2 = new JButton("100");
		JButton button3 = new JButton("1000");
		JButton button4 = new JButton("5000");
		JButton button5 = new JButton("10000");
		JButton button6 = new JButton("Generate excel file for next 10000 steps");
		JComboBox box = new JComboBox(tempStrings);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth =6;
		add(label1,gbc);
		gbc.gridwidth =1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(button,gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(button1,gbc);
		gbc.gridx = 2;
		gbc.gridy = 2;
		add(button2,gbc);
		gbc.gridx = 3;
		gbc.gridy = 2;
		add(button3,gbc);
		gbc.gridx = 4;
		gbc.gridy = 2;
		add(button4,gbc);
		gbc.gridx = 5;
		gbc.gridy = 2;
		add(button5,gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth =3;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(energyStats,gbc);
		gbc.gridwidth =2;
		gbc.gridx = 3;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(energy,gbc);
		gbc.gridwidth =3;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(stepCount,gbc);
		gbc.gridwidth =2;
		gbc.gridx = 3;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(steps,gbc);
		gbc.gridwidth =3;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(tempLabel,gbc);
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(box,gbc);
		gbc.gridwidth =6;
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.CENTER;
		add(button6,gbc);
		
		//set buttons to disable at the beginning of the simulation
		button.setEnabled(false);
		button1.setEnabled(false);
		button2.setEnabled(false);
		button3.setEnabled(false);
		button4.setEnabled(false);
		button5.setEnabled(false);
		button6.setEnabled(false);
		
		//ComboBox Listener
		box.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JComboBox cb = (JComboBox)e.getSource();
				String wybor = (String)cb.getSelectedItem();
				System.out.println(wybor);
				if(wybor.equals("Choose temp"))
				{
					button.setEnabled(false);
					button1.setEnabled(false);
					button2.setEnabled(false);
					button3.setEnabled(false);
					button4.setEnabled(false);
					button5.setEnabled(false);
					button6.setEnabled(false);
				}
				else
				{
					button.setEnabled(true);
					button1.setEnabled(true);
					button2.setEnabled(true);
					button3.setEnabled(true);
					button4.setEnabled(true);
					button5.setEnabled(true);
					button6.setEnabled(true);
					w.temp = Double.parseDouble(wybor);
					
					
				}
			}
		
		});
		
		
		// button Listener
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int k=0; k<1;k++)
				{
					
					w.monteCarloStep();
					//System.out.println("Krok numer: " + k);
					
				}
				energy.setText(w.summary());
				w.colorUpdate();
				count +=1;
				steps.setText(Integer.toString(count));
			}
		});
		// button1 Listener
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int k=0; k<10;k++)
				{
					
					w.monteCarloStep();
					//System.out.println("Krok numer: " + k);
					
				}
				energy.setText(w.summary());
				w.colorUpdate();	
				count +=10;
				steps.setText(Integer.toString(count));
			}
		});
		// button2 Listener
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int k=0; k<100;k++)
				{
					
					w.monteCarloStep();
					//System.out.println("Krok numer: " + k);
					
				}
				energy.setText(w.summary());
				w.colorUpdate();	
				count +=100;
				steps.setText(Integer.toString(count));
			}
		});
		
		// button3 Listener
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int k=0; k<1000;k++)
				{
					
					w.monteCarloStep();
					//System.out.println("Krok numer: " + k);
					
				}
				energy.setText(w.summary());
				w.colorUpdate();
				count +=1000;
				steps.setText(Integer.toString(count));
			}
		});
		
		// button4 Listener
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int k=0; k<5000;k++)
				{
					
					w.monteCarloStep();
					//System.out.println("Krok numer: " + k);
					
				}
				energy.setText(w.summary());
				w.colorUpdate();	
				count +=5000;
				steps.setText(Integer.toString(count));
			}
		});
		// button5 Listener
		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int k=0; k<10000;k++)
				{
					
					w.monteCarloStep();
					//System.out.println("Krok numer: " + k);
					
				}
				energy.setText(w.summary());
				w.colorUpdate();	
				count +=10000;
				steps.setText(Integer.toString(count));
			}
		});
		
		// button6 Listener - GENERATE EXCEL FILE
		button6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				double summary =0;
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("Results");
				//Create heading
				Row rowheading[] = new Row[10];
				for(int i=0;i<10;i++)  rowheading[i] = sheet.createRow(i);
				rowheading[0].createCell(0).setCellValue("T=");
				rowheading[0].createCell(1).setCellValue(w.temp);
				rowheading[1].createCell(0).setCellValue("Step");	
				rowheading[2].createCell(0).setCellValue("Magnetization");
				rowheading[3].createCell(0).setCellValue("Summary");
				int step = 2;
				for(int k=0; k<10000;k++)
				{
					if(k%100==0)
						{
							rowheading[1].createCell(step).setCellValue(k);
							rowheading[2].createCell(step).setCellValue(Double.parseDouble(w.summary()));
							summary= summary + Double.parseDouble(w.summary());
							step++;
						}
					w.monteCarloStep();
					
				}
				energy.setText(w.summary());
				w.colorUpdate();	
				count +=10000;
				steps.setText(Integer.toString(count));
				
				rowheading[3].createCell(1).setCellValue(summary/100);

				
				FileOutputStream out;
				try {
					out = new FileOutputStream(new File("ResultsForT="+w.temp+".xls"), false);
					workbook.write(out);
					out.close();
					workbook.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				/*

				energy.setText(w.summary());
				w.colorUpdate();	
				count +=10000;
				steps.setText(Integer.toString(count));
				
				*/
			}
		});
		
		
		
		
		
		pack();
		setSize(400,400);
		setVisible(true);	
	}

}
