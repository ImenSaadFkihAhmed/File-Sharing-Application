package graphic;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Rectangle;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class Gui extends JFrame {

	private JPanel Home;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		setTitle("Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		Home = new JPanel();
		Home.setBackground(new Color(0, 0, 0));
		Home.setBorder(new MatteBorder(2, 1, 1, 1, (Color) new Color(0, 0, 0)));

		setContentPane(Home);
		Home.setLayout(null);
		
		JPanel Home2 = new JPanel();
		Home2.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		Home2.setBounds(59, 0, 422, 390);
		Home2.setBackground(new Color(0, 128, 192));
		Home.add(Home2);
		Home2.setLayout(null);
		
		JLabel ApplicationTitle = new JLabel("File Shared Application");
		ApplicationTitle.setBounds(28, 7, 365, 47);
		ApplicationTitle.setForeground(new Color(255, 255, 0));
		ApplicationTitle.setFont(new Font("Arial Narrow", Font.BOLD, 40));
		Home2.add(ApplicationTitle);
		
		JButton RegisterButton = new JButton("Register File");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiInterface RegisterInterface=new GuiInterface();
				RegisterInterface.setVisible(true);
			}
			});
		RegisterButton.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		RegisterButton.setBounds(84, 89, 258, 56);
		Home2.add(RegisterButton);
		
		JButton btnSearchFile = new JButton("Search File");
		btnSearchFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DownloadInterface searchInterface=new DownloadInterface ();
				searchInterface.setVisible(true);
			}
		});
		btnSearchFile.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		btnSearchFile.setBounds(84, 170, 258, 56);
		Home2.add(btnSearchFile);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		btnExit.setBounds(84, 261, 258, 56);
		
		btnExit.addActionListener(e -> {
	         this.dispose();
	      });
	      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	      this.pack();
	      this.setVisible(true);
		Home2.add(btnExit);
	}
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
