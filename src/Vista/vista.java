package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

public class vista extends JFrame {

	public JPanel contentPane,panel_Buscador,panel_Mapa;
	public JLabel labelNombreProvincia,labelIconoTiempo,labelTemperatura,labelTMax,labelTMin,labelImagenEspana;
	public JComboBox boxCA,boxProvincia,boxDia;
	public JButton boton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vista frame = new vista();
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
	public vista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 979, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("APP METEOROLOGICO");
		lblNewLabel.setForeground(new Color(72, 209, 204));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(149, 28, 302, 46);
		contentPane.add(lblNewLabel);
		
		 panel_Buscador = new JPanel();
		panel_Buscador.setBounds(10, 102, 302, 345);
		contentPane.add(panel_Buscador);
		panel_Buscador.setLayout(null);
		
		 boxCA = new JComboBox();
		boxCA.setBounds(48, 252, 197, 21);
		panel_Buscador.add(boxCA);
		
		 boxProvincia = new JComboBox();
		boxProvincia.setBounds(48, 283, 197, 21);
		panel_Buscador.add(boxProvincia);
		
		 boton = new JButton("New button");
		boton.setBounds(102, 314, 85, 21);
		panel_Buscador.add(boton);
		
		 labelNombreProvincia = new JLabel("New label");
		labelNombreProvincia.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombreProvincia.setBounds(48, 21, 197, 21);
		panel_Buscador.add(labelNombreProvincia);
		
		 labelIconoTiempo = new JLabel("New label");
		labelIconoTiempo.setHorizontalAlignment(SwingConstants.CENTER);
		labelIconoTiempo.setBounds(48, 52, 197, 53);
		panel_Buscador.add(labelIconoTiempo);
		
		 labelTemperatura = new JLabel("New label");
		labelTemperatura.setHorizontalAlignment(SwingConstants.CENTER);
		labelTemperatura.setBounds(48, 115, 197, 29);
		panel_Buscador.add(labelTemperatura);
		
		 labelTMax = new JLabel("New label");
		labelTMax.setHorizontalAlignment(SwingConstants.CENTER);
		labelTMax.setBounds(48, 154, 197, 29);
		panel_Buscador.add(labelTMax);
		
		 labelTMin = new JLabel("New label");
		labelTMin.setHorizontalAlignment(SwingConstants.CENTER);
		labelTMin.setBounds(48, 193, 197, 29);
		panel_Buscador.add(labelTMin);
		
		 panel_Mapa = new JPanel();
		panel_Mapa.setBounds(386, 84, 569, 386);
		contentPane.add(panel_Mapa);
		panel_Mapa.setLayout(null);
		
		boxDia = new JComboBox();
		boxDia.setBounds(556, 480, 263, 21);
		contentPane.add(boxDia);
		
		labelImagenEspana = new JLabel("");
		labelImagenEspana.setHorizontalAlignment(SwingConstants.CENTER);
		labelImagenEspana.setIcon(new ImageIcon("C:\\Users\\Propietario\\Desktop\\Eclipse2\\METEO\\Eje_METEO\\src\\img\\mapaEspana-0000.jpg"));
		labelImagenEspana.setBounds(0, 0, 569, 386);
		panel_Mapa.add(labelImagenEspana);
		
		
	}
}
