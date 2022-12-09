package graphic;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.Panel;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class GuiInterface extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public GuiInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel List = new JPanel();
		List.setBackground(new Color(0, 128, 192));
		List.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		List.setBounds(0, 0, 436, 210);
		contentPane.add(List);
		List.setLayout(null);
		
		JButton btnNewButton = new JButton("Upload path");
		btnNewButton.setFont(new Font("Arial Narrow For CAE", btnNewButton.getFont().getStyle() | Font.BOLD, btnNewButton.getFont().getSize() + 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser=new JFileChooser();
				chooser.showSaveDialog(null);
				
					//File directory=new File(file_upload.getSelectedFile().getAbsolutePath());
					//System.out.println(directory);
			 File Dirctory=chooser.getCurrentDirectory();
			 String ditrectoruname=Dirctory.getAbsolutePath();
					
				
			}
		});
		btnNewButton.setBounds(104, 66, 231, 44);
		List.add(btnNewButton);
		
		JTextPane ResponseText = new JTextPane();
		ResponseText.setForeground(new Color(255, 255, 0));
		ResponseText.setFont(new Font("Bahnschrift", Font.BOLD, 27));
		ResponseText.setBackground(new Color(0, 0, 0));
		ResponseText.setBounds(0, 221, 444, 41);
		contentPane.add(ResponseText);
	}
}
