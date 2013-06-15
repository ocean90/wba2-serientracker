package de.fhkoeln.gm.serientracker.client;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CLayout {
	JFrame frame = new JFrame("Setting");
	JPanel panelCont = new JPanel();
	JPanel panelProfile = new JPanel();
	JPanel panelGenre = new JPanel();
	JPanel panelMessage = new JPanel();

	JButton buttonProfile = new JButton("Profile");
	JButton buttonProfile1 = new JButton("Profile");
	JButton buttonProfile2 = new JButton("Profile");

	
	JButton buttonGenre = new JButton("Genre");
	JButton buttonGenre1 = new JButton("Genre");
	JButton buttonGenre2 = new JButton("Genre");

	
	JButton buttonMessage = new JButton("Message");
	JButton buttonMessage1 = new JButton("Message");
	JButton buttonMessage2 = new JButton("Message");


	CardLayout cl = new CardLayout();

	
	
	public  CLayout() {
		panelCont.setLayout(cl);

		panelProfile.add(buttonProfile);
		panelProfile.add(buttonGenre);
		panelProfile.add(buttonMessage);

		panelGenre.add(buttonProfile1);
		panelGenre.add(buttonGenre1);
		panelGenre.add(buttonMessage1);
		
		panelMessage.add(buttonProfile2);
		panelMessage.add(buttonGenre2);
		panelMessage.add(buttonMessage2);

		panelProfile.setBackground(Color.BLUE);
		panelGenre.setBackground(Color.GREEN);
		panelMessage.setBackground(Color.RED);


		panelCont.add(panelProfile, "1");
		panelCont.add(panelGenre, "2");
		panelCont.add(panelMessage, "3");
		
		cl.show(panelCont, "1");

		buttonProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "1");
			}
		});
		buttonProfile1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "1");
			}
		});

		buttonProfile2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "1");
			}
		});


		buttonGenre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "2");
			}
		});
		buttonGenre1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "2");
			}
		});
		buttonGenre2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "2");
			}
		});
		
		buttonMessage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "3");
			}
		});
		buttonMessage1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "3");
			}
		});
		buttonMessage2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "3");
			}
		});

		frame.add(panelCont);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new  CLayout();
			}
		});
	}
}
