package AE01;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vista1 {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnNewButton;
	private JTextPane textPane;
	private JScrollBar scrollBar;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista1 window = new Vista1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vista1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cantidad de proteinas");
		lblNewLabel.setBounds(219, 33, 196, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Proteína 1");
		lblNewLabel_1.setBounds(219, 71, 86, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(219, 96, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Proteína 2");
		lblNewLabel_2.setBounds(329, 69, 86, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(329, 96, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Proteína 3");
		lblNewLabel_3.setBounds(219, 127, 86, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(219, 152, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Proteína 4");
		lblNewLabel_4.setBounds(329, 127, 86, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(329, 152, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		btnNewButton = new JButton("Enviar");
		btnNewButton.setBounds(272, 197, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		// Crear el JTextPane
	    textPane = new JTextPane();
	    textPane.setEditable(false); // El texto no es editable

	    // Crear el JScrollPane y envolver el JTextPane dentro de él
	    JScrollPane scrollPane = new JScrollPane(textPane);
	    scrollPane.setBounds(192, 265, 250, 135); // Establecer tamaño y posición del JScrollPane
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	    // Agregar el JScrollPane al JFrame
	    frame.getContentPane().add(scrollPane);
	    
	    btnNewButton_1 = new JButton("Limpiar");
	    btnNewButton_1.setBounds(272, 231, 89, 23);
	    frame.getContentPane().add(btnNewButton_1);
	}

	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}

	public JTextPane getTextPane() {
		return textPane;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public JTextField getTextField_2() {
		return textField_2;
	}

	public JTextField getTextField_3() {
		return textField_3;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}
	
	public void mostrar() {
        frame.setVisible(true);
    }

    public void ocultar() {
        frame.setVisible(false);
    }
}
