package graphic;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DownloadInterface extends JFrame {

	private JPanel DownoadInterface;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public DownloadInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		DownoadInterface = new JPanel();
		DownoadInterface.setToolTipText("");
		DownoadInterface.setBackground(new Color(255, 255, 0));
		DownoadInterface.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(DownoadInterface);
		DownoadInterface.setLayout(null);
		
		JPanel List = new JPanel();
		List.setLayout(null);
		List.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		List.setBackground(new Color(0, 128, 192));
		List.setBounds(0, 0, 436, 210);
		DownoadInterface.add(List);
		
		JComboBox SubjectList = new JComboBox();
		SubjectList.setBackground(new Color(192, 192, 192));
		SubjectList.setModel(new DefaultComboBoxModel(new String[] {"Subject", "System Distributed", "System Real Time", "NestJs", "Optic", "Tx", "ML"}));
		SubjectList.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		SubjectList.setBounds(120, 45, 190, 37);
		List.add(SubjectList);
		
		JComboBox FileTypeList = new JComboBox();
		FileTypeList.setBackground(new Color(192, 192, 192));
		FileTypeList.setModel(new DefaultComboBoxModel(new String[] {"FileType", "Cour", "TD", "TP", "DS", "Exam"}));
		FileTypeList.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		FileTypeList.setBounds(120, 93, 190, 37);
		List.add(FileTypeList);
		
		JComboBox YearList = new JComboBox();
		YearList.setBackground(new Color(192, 192, 192));
		YearList.setModel(new DefaultComboBoxModel(new String[] {"Year", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018"}));
		YearList.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		YearList.setBounds(120, 141, 190, 37);
		List.add(YearList);
		
		JButton btnNewButton = new JButton("Download");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setFont(new Font("Arial Black", btnNewButton.getFont().getStyle() | Font.BOLD, btnNewButton.getFont().getSize() + 7));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(10, 221, 158, 23);
		DownoadInterface.add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExit.setFont(new Font("Arial Black", btnExit.getFont().getStyle() | Font.BOLD, btnExit.getFont().getSize() + 7));
		btnExit.setForeground(Color.BLACK);
		btnExit.setBackground(new Color(192, 192, 192));
		btnExit.setBounds(258, 221, 168, 23);
		DownoadInterface.add(btnExit);
	}

}
