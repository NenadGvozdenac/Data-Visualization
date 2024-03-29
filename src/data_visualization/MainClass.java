package data_visualization;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MainClass extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public static ArrayList<Integer> all_data = new ArrayList<Integer>();
	public static int MAX_ELEMENTS = 5;
	public static Scanner sc = new Scanner(System.in);
	
	public static JPanel create_panel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.black);
		panel.setLayout(null);
		
		return panel;
	}

	public ArrayList<Integer> data_input() {

		MAX_ELEMENTS = Integer.parseInt(JOptionPane.showInputDialog(null, "How many elements do you want", "ELEMENT COUNT", JOptionPane.QUESTION_MESSAGE));
		
		for(int i = 0; i < MAX_ELEMENTS; i++) {
			
			int c = Integer.parseInt(JOptionPane.showInputDialog(null, "Input next integer element:", "ELEMENT " + (i+1), JOptionPane.PLAIN_MESSAGE));
			all_data.add(c);
		}
		
		return all_data;
	}
	
	public void remove_components(JPanel panel) {
		for(Component c : panel.getComponents()) {
			if(c instanceof JLabel) {
				panel.remove(c);
				panel.revalidate();
				this.repaint();
			}
		}
		
	}
	
	public static Boolean add_data_to_panel(JPanel panel, Integer max_y, ArrayList<Integer> data) {
		
		for(int i = 0; i < data.size(); i++) {
			
			Integer k = data.get(i);
			
			if(k < 0 || k > 20) {
				return false;
			}
			
			JLabel label = new JLabel(k.toString(), JLabel.CENTER);
			label.setFont(new Font("Arial", Font.BOLD, 35));
		    label.setForeground(Color.WHITE);
		    label.setBackground(Color.ORANGE);
		    Border border = BorderFactory.createLineBorder(Color.WHITE);
		    label.setBorder(border);
		    label.setVerticalAlignment(JLabel.CENTER);
			label.setLocation(30 + i * 75, max_y - k * 50 + 20);
			label.setOpaque(true);
			
			label.setSize(new Dimension(60, k * 50));
			panel.add(label);
			
		}
		
		return true;
	}
	
	public static int get_max_from_data() {
		int max = all_data.get(0);
		
		for(int i = 1; i < all_data.size(); i++) {
			if(all_data.get(i) > max) {
				max = all_data.get(i);
			}
		}
		
		return max;
	}
	
	public void update(ArrayList<Integer> arr, JPanel panel) {
		remove_components(panel);
		add_data_to_panel(panel, get_max_from_data() * 50, arr);
	}
	
	
	public void sort_data(ArrayList<Integer> arr, JPanel panel){
		
		int n = arr.size();
		
        for(int i = 0; i < n; i++) {
        	for(int j = 0; j < n - i - 1; j++) {
        		
        		if(arr.get(j) > arr.get(j+1)) {
        			
        			int temp = arr.get(j);
        			arr.set(j, arr.get(j+1));
        			arr.set(j+1, temp);
        			
        			update(arr, panel);
	        	}
	        }
        }

	}
		
	
	public MainClass(JPanel panel) {
		
		all_data = data_input();
		
		super.setTitle("Data Visualization");
		
		this.getContentPane().add(panel);
		int max = get_max_from_data();
		
		panel.setPreferredSize(new Dimension(60 + MAX_ELEMENTS * 75, max * 50 + 80));
		
		if(add_data_to_panel(panel, max * 50, all_data) == false) {
			System.out.print("Error adding data, wrong input.");
			System.exit(0);
		};
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		JButton button = new JButton("SORT");
		button.setLocation(0, max * 50 + 40);
		button.setFont(new Font("Arial", Font.BOLD, 30));
		button.setSize(panel.getWidth(), 40);
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(49,221,225));
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sort_data(all_data, panel);
			}
			
		});
		
		panel.add(button);
		
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "Thank you for using this program.\nMade by Nenad Gvozdenac, FTN 2021.", "THANK YOU", JOptionPane.PLAIN_MESSAGE);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "Thank you for using this program.\nMade by Nenad Gvozdenac, FTN 2021.", "THANK YOU", JOptionPane.PLAIN_MESSAGE);
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			
		});
		
		//remove_components();
		this.setVisible(true);

	}
	
	
	public static void main(String[] args) {
		
		new MainClass(create_panel());

	}

}
