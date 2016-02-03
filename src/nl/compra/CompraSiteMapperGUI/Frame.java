package nl.compra.CompraSiteMapperGUI;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.PrintStream;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class Frame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldURL;
	private JTextArea textAreaLog;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
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
	public Frame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Frame.class.getResource("/nl/compra/CompraSiteMapperGUI/Resources/compra.png")));
		
		setTitle("Compra Sitemap Generator");
		setResizable(false);
		
		try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblUrl = new JLabel("URL:");
		lblUrl.setBounds(6, 11, 28, 16);
		getContentPane().add(lblUrl);
		
		textFieldURL = new JTextField();
		textFieldURL.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldURL.setBounds(32, 5, 499, 28);
		textFieldURL.setToolTipText("Enter a URL for the crawler to crawl");
		getContentPane().add(textFieldURL);
		textFieldURL.setColumns(10);
		
		JButton btnCrawl = new JButton("Crawl");
		btnCrawl.setBounds(541, 5, 108, 28);
		btnCrawl.setAction(action);
		getContentPane().add(btnCrawl);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 39, 643, 373);
		getContentPane().add(scrollPane_1);
		
		textAreaLog = new JTextArea();
		scrollPane_1.setViewportView(textAreaLog);
		textAreaLog.setDropMode(DropMode.INSERT);
		textAreaLog.setTabSize(4);
		
	}
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Crawl");
			putValue(SHORT_DESCRIPTION, "Crawl the requested URL to generate a sitemap.");
		}
		public void actionPerformed(ActionEvent e) {
			
			PrintStream ps = new PrintStream (new CustomOutputStream(textAreaLog));
			System.setOut(ps);
			System.setErr(ps);
			
			String URL = textFieldURL.getText().toString ();
			
			CrawlerBooter crawlerBooter = new CrawlerBooter ();
			crawlerBooter.SetURL (URL);
			crawlerBooter.start ();
			
		}
	}

}
