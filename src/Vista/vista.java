package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.controlador;
import resource.configuracion;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.awt.event.ItemEvent;


public class vista extends JFrame {
	
	public JPanel contentPane,panel_Buscador,panel_Mapa;

	public JLabel labelNombreProvincia,labelIconoTiempo,labelTMax,labelTMin,labelImagenEspana, labelBuscador;
	public JComboBox <String>boxCA;
	public JComboBox <String>boxProvincia;
	public JComboBox <String>boxDia;
	public JButton boton;
	public String [] comunidadesAutonomas = new String [17] ;
	


	
	public ArrayList<String> CA = new ArrayList();
	public JLabel lblIconoAvila;
	public JLabel lblIconoCaceres;
	public JLabel lblIconoValencia;
	public JLabel lblIconoMurcia;
	public JLabel lblIconoMadrid;
	public JLabel lblIconoToledo;
	public JLabel lblIconoBadajoz;
	public JLabel lblIconoGuadalajara;
	public JLabel lblIconoCuenca;
	public JLabel lblIconoAlbacete;
	public JLabel lblIconoAlicante;
	public JLabel lblIconoCastellon;
	public JLabel lblIconoIslasBaleares;
	public JLabel lblIconoSantaCruzDeTenerife;
	public JLabel lblIconoLasPalmas;
	public JLabel lblIconoHuelva;
	public JLabel lblIconoSevilla;
	public JLabel lblIconoMalaga;
	public JLabel lblIconoCordoba;
	public JLabel lblIconoGranada;
	public JLabel lblIconoAlmeria;
	public JLabel lblIconoJaen;
	public JLabel lblIconoSalamanca;
	public JLabel lblIconoLaRioja;
	public JLabel lblIconoNavarra;
	public JLabel lblIconoVizcaya;
	public JLabel lblIconoAlava;
	public JLabel lblIconoTeruel;
	public JLabel lblIconoZaragoza;
	public JLabel lblIconoCantabria;
	public JLabel lblIconoAsturias;
	public JLabel lblIconoLugo;
	public JLabel lblIconoOrense;
	public JLabel lblIconoLaCoruña;
	public JLabel lblIconoPontevedra;
	public JLabel lblIconoTarragona;
	public JLabel lblIconoBarcelona;
	public JLabel lblIconoGirona;
	public JLabel lblIconoLerida;
	public JLabel lblIconoSoria;
	public JLabel lblIconoSegovia;
	public JLabel lblIconoBurgos;
	public JLabel lblIconoZamora;
	public JLabel lblIconoValladolid;
	public JLabel lblIconoLeon;
	public JLabel lblIconoPalencia;
	public JLabel lblIconoCiudadReal;
	public JLabel lblIconoCadiz;
	public JLabel lblIconoHuesca;
	private JComboBox<String> comboBoxFechas;
    
	
//github.com/maikel50/Eje_METEO.git
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vista frame = new vista();
					frame.setVisible(true);
					
					
					controlador control = new controlador(frame);
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
		
		 boton = new JButton("BUSCAR");
		boton.setBounds(102, 314, 85, 21);
		panel_Buscador.add(boton);
		
		 labelNombreProvincia = new JLabel("Provincia\r\n");
		 labelNombreProvincia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNombreProvincia.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombreProvincia.setBounds(48, 59, 197, 21);
		panel_Buscador.add(labelNombreProvincia);
		
		labelIconoTiempo = new JLabel(" ");
		labelIconoTiempo.setHorizontalAlignment(SwingConstants.CENTER);
		labelIconoTiempo.setBounds(115, 101, 66, 53);
		panel_Buscador.add(labelIconoTiempo);
		
		 labelTMax = new JLabel("Max: 0 ºC");
		labelTMax.setHorizontalAlignment(SwingConstants.CENTER);
		labelTMax.setBounds(48, 193, 197, 29);
		panel_Buscador.add(labelTMax);
		
		 labelTMin = new JLabel("Min: 0 ºC");
		labelTMin.setHorizontalAlignment(SwingConstants.CENTER);
		labelTMin.setBounds(48, 213, 197, 29);
		panel_Buscador.add(labelTMin);
		
		labelBuscador = new JLabel("BUSCADOR");
		labelBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		labelBuscador.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelBuscador.setForeground(Color.BLUE);
		labelBuscador.setBounds(30, 20, 228, 21);
		panel_Buscador.add(labelBuscador);
		
		 panel_Mapa = new JPanel();
		panel_Mapa.setBounds(386, 84, 569, 386);
		contentPane.add(panel_Mapa);
		panel_Mapa.setLayout(null);
		
		boxDia = new JComboBox();
		boxDia.setBounds(556, 480, 263, 21);
		contentPane.add(boxDia);
		
		lblIconoPalencia = new JLabel("");
		lblIconoPalencia.setIcon(null);
		lblIconoPalencia.setBounds(244, 44, 25, 25);
		panel_Mapa.add(lblIconoPalencia);
		
		lblIconoLeon = new JLabel("");
		lblIconoLeon.setIcon(null);
		lblIconoLeon.setBounds(196, 45, 25, 25);
		panel_Mapa.add(lblIconoLeon);
		
		lblIconoValladolid = new JLabel("");
		lblIconoValladolid.setIcon(null);
		lblIconoValladolid.setBounds(229, 91, 25, 25);
		panel_Mapa.add(lblIconoValladolid);
		
		lblIconoZamora = new JLabel("");
		lblIconoZamora.setIcon(null);
		lblIconoZamora.setBounds(194, 91, 25, 25);
		panel_Mapa.add(lblIconoZamora);
		
		lblIconoBurgos = new JLabel("");
		lblIconoBurgos.setIcon(null);
		lblIconoBurgos.setBounds(271, 66, 25, 25);
		panel_Mapa.add(lblIconoBurgos);
		
		lblIconoSegovia = new JLabel("");
		lblIconoSegovia.setIcon(null);
		lblIconoSegovia.setBounds(258, 115, 25, 25);
		panel_Mapa.add(lblIconoSegovia);
		
		lblIconoSoria = new JLabel("");
		lblIconoSoria.setIcon(null);
		lblIconoSoria.setBounds(318, 91, 25, 25);
		panel_Mapa.add(lblIconoSoria);
		
		lblIconoLerida = new JLabel("");
		lblIconoLerida.setIcon(null);
		lblIconoLerida.setBounds(439, 66, 25, 25);
		panel_Mapa.add(lblIconoLerida);
		
		lblIconoTarragona = new JLabel("");
		lblIconoTarragona.setIcon(null);
		lblIconoTarragona.setBounds(426, 115, 25, 25);
		panel_Mapa.add(lblIconoTarragona);
		
		lblIconoPontevedra = new JLabel("");
		lblIconoPontevedra.setIcon(null);
		lblIconoPontevedra.setBounds(110, 54, 25, 25);
		panel_Mapa.add(lblIconoPontevedra);
		
		lblIconoLaCoruña = new JLabel("");
		lblIconoLaCoruña.setIcon(null);
		lblIconoLaCoruña.setBounds(105, 21, 25, 25);
		panel_Mapa.add(lblIconoLaCoruña);
		
		lblIconoOrense = new JLabel("");
		lblIconoOrense.setIcon(null);
		lblIconoOrense.setBounds(140, 66, 25, 25);
		panel_Mapa.add(lblIconoOrense);
		
		lblIconoLugo = new JLabel("");
		lblIconoLugo.setIcon(null);
		lblIconoLugo.setBounds(140, 10, 25, 25);
		panel_Mapa.add(lblIconoLugo);
		
		lblIconoAsturias = new JLabel("");
		lblIconoAsturias.setIcon(null);
		lblIconoAsturias.setBounds(186, 10, 25, 25);
		panel_Mapa.add(lblIconoAsturias);
		
		lblIconoCantabria = new JLabel("");
		lblIconoCantabria.setIcon(null);
		lblIconoCantabria.setBounds(271, 10, 25, 25);
		panel_Mapa.add(lblIconoCantabria);
		
		lblIconoHuesca = new JLabel("");
		lblIconoHuesca.setIcon(null);
		lblIconoHuesca.setBounds(385, 66, 25, 25);
		panel_Mapa.add(lblIconoHuesca);
		
		lblIconoZaragoza = new JLabel("");
		lblIconoZaragoza.setIcon(null);
		lblIconoZaragoza.setBounds(375, 104, 25, 25);
		panel_Mapa.add(lblIconoZaragoza);
		
		lblIconoTeruel = new JLabel("");
		lblIconoTeruel.setIcon(null);
		lblIconoTeruel.setBounds(366, 141, 25, 25);
		panel_Mapa.add(lblIconoTeruel);
		
		lblIconoAlava = new JLabel("");
		lblIconoAlava.setIcon(null);
		lblIconoAlava.setBounds(299, 33, 25, 25);
		panel_Mapa.add(lblIconoAlava);
		
		lblIconoVizcaya = new JLabel("");
		lblIconoVizcaya.setIcon(null);
		lblIconoVizcaya.setBounds(318, 10, 25, 25);
		panel_Mapa.add(lblIconoVizcaya);
		
		lblIconoNavarra = new JLabel("");
		lblIconoNavarra.setIcon(null);
		lblIconoNavarra.setBounds(339, 44, 25, 25);
		panel_Mapa.add(lblIconoNavarra);
		
		lblIconoLaRioja = new JLabel("");
		lblIconoLaRioja.setIcon(null);
		lblIconoLaRioja.setBounds(318, 66, 25, 25);
		panel_Mapa.add(lblIconoLaRioja);
		
		lblIconoSalamanca = new JLabel("");
		lblIconoSalamanca.setIcon(null);
		lblIconoSalamanca.setBounds(194, 127, 25, 25);
		panel_Mapa.add(lblIconoSalamanca);
		
		lblIconoJaen = new JLabel("");
		lblIconoJaen.setIcon(null);
		lblIconoJaen.setBounds(283, 252, 25, 25);
		panel_Mapa.add(lblIconoJaen);
		
		lblIconoAlmeria = new JLabel("");
		lblIconoAlmeria.setIcon(null);
		lblIconoAlmeria.setBounds(318, 281, 25, 25);
		panel_Mapa.add(lblIconoAlmeria);
		
		lblIconoGranada = new JLabel("");
		lblIconoGranada.setIcon(null);
		lblIconoGranada.setBounds(283, 281, 25, 25);
		panel_Mapa.add(lblIconoGranada);
		
		lblIconoCordoba = new JLabel("");
		lblIconoCordoba.setIcon(null);
		lblIconoCordoba.setBounds(229, 252, 25, 25);
		panel_Mapa.add(lblIconoCordoba);
		
		lblIconoMalaga = new JLabel("");
		lblIconoMalaga.setIcon(null);
		lblIconoMalaga.setBounds(244, 296, 25, 25);
		panel_Mapa.add(lblIconoMalaga);
		
		lblIconoSevilla = new JLabel("");
		lblIconoSevilla.setIcon(null);
		lblIconoSevilla.setBounds(204, 281, 25, 25);
		panel_Mapa.add(lblIconoSevilla);
		
		lblIconoHuelva = new JLabel("");
		lblIconoHuelva.setIcon(null);
		lblIconoHuelva.setBounds(159, 265, 25, 25);
		panel_Mapa.add(lblIconoHuelva);
		
		lblIconoLasPalmas = new JLabel("");
		lblIconoLasPalmas.setIcon(null);
		lblIconoLasPalmas.setBounds(110, 351, 25, 25);
		panel_Mapa.add(lblIconoLasPalmas);
		
		lblIconoSantaCruzDeTenerife = new JLabel("");
		lblIconoSantaCruzDeTenerife.setIcon(null);
		lblIconoSantaCruzDeTenerife.setBounds(33, 340, 25, 25);
		panel_Mapa.add(lblIconoSantaCruzDeTenerife);
		
		lblIconoIslasBaleares = new JLabel("");
		lblIconoIslasBaleares.setIcon(null);
		lblIconoIslasBaleares.setBounds(503, 176, 25, 25);
		panel_Mapa.add(lblIconoIslasBaleares);
		
		lblIconoCastellon = new JLabel("");
		lblIconoCastellon.setIcon(null);
		lblIconoCastellon.setBounds(401, 157, 25, 25);
		panel_Mapa.add(lblIconoCastellon);
		
		lblIconoAlicante = new JLabel("");
		lblIconoAlicante.setIcon(null);
		lblIconoAlicante.setBounds(385, 226, 25, 25);
		panel_Mapa.add(lblIconoAlicante);
		
		lblIconoAlbacete = new JLabel("");
		lblIconoAlbacete.setIcon(null);
		lblIconoAlbacete.setBounds(318, 211, 25, 25);
		panel_Mapa.add(lblIconoAlbacete);
		
		lblIconoCuenca = new JLabel("");
		lblIconoCuenca.setIcon(null);
		lblIconoCuenca.setBounds(328, 176, 25, 25);
		panel_Mapa.add(lblIconoCuenca);
		
		lblIconoGuadalajara = new JLabel("");
		lblIconoGuadalajara.setIcon(null);
		lblIconoGuadalajara.setBounds(318, 141, 25, 25);
		panel_Mapa.add(lblIconoGuadalajara);
		
		lblIconoBadajoz = new JLabel("");
		lblIconoBadajoz.setIcon(null);
		lblIconoBadajoz.setBounds(186, 226, 25, 25);
		panel_Mapa.add(lblIconoBadajoz);
		
		lblIconoToledo = new JLabel("");
		lblIconoToledo.setIcon(null);
		lblIconoToledo.setBounds(258, 176, 25, 25);
		panel_Mapa.add(lblIconoToledo);
		
		lblIconoMadrid = new JLabel("");
		lblIconoMadrid.setIcon(null);
		lblIconoMadrid.setBounds(271, 141, 25, 25);
		panel_Mapa.add(lblIconoMadrid);
		
		lblIconoMurcia = new JLabel("");
		lblIconoMurcia.setIcon(null);
		lblIconoMurcia.setBounds(353, 252, 25, 25);
		panel_Mapa.add(lblIconoMurcia);
		
		lblIconoValencia = new JLabel("");
		lblIconoValencia.setIcon(null);
		lblIconoValencia.setBounds(375, 187, 25, 25);
		panel_Mapa.add(lblIconoValencia);
		
		lblIconoCadiz = new JLabel("");
		lblIconoCadiz.setIcon(null);
		lblIconoCadiz.setBounds(204, 316, 25, 25);
		panel_Mapa.add(lblIconoCadiz);
		
		lblIconoCaceres = new JLabel("");
		lblIconoCaceres.setIcon(null);
		lblIconoCaceres.setBounds(186, 176, 25, 25);
		panel_Mapa.add(lblIconoCaceres);
		
		lblIconoCiudadReal = new JLabel("");
		lblIconoCiudadReal.setIcon(null);
		lblIconoCiudadReal.setBounds(271, 211, 25, 25);
		panel_Mapa.add(lblIconoCiudadReal);
		
		lblIconoAvila = new JLabel("");
		lblIconoAvila.setIcon(null);
		lblIconoAvila.setBounds(229, 141, 25, 25);
		panel_Mapa.add(lblIconoAvila);
		
		lblIconoBarcelona = new JLabel("");
		lblIconoBarcelona.setIcon(null);
		lblIconoBarcelona.setBounds(471, 91, 25, 25);
		panel_Mapa.add(lblIconoBarcelona);
		
		lblIconoGirona = new JLabel("");
		lblIconoGirona.setIcon(null);
		lblIconoGirona.setBounds(489, 66, 25, 25);
		panel_Mapa.add(lblIconoGirona);
		
		labelImagenEspana = new JLabel("");
		labelImagenEspana.setHorizontalAlignment(SwingConstants.CENTER);
		labelImagenEspana.setIcon(new ImageIcon(vista.class.getResource("/img/mapaEspana-0000.jpg")));
		labelImagenEspana.setBounds(0, 0, 569, 386);
		panel_Mapa.add(labelImagenEspana);
	
		//Comunidades Autonomas
		
		String [] comunidadesAutonomas = {
	            "Andalucia", "Aragon", "Asturias", "Canarias", "Cantabria", "Castilla_y_Leon",
	            "Castilla-La_Mancha", "Cataluna", "Extremadura", "Galicia", "Islas_Baleares",
	            "La_Rioja", "Madrid", "Murcia", "Navarra", "Pais_Vasco", "Valencia"
	    };
		for (String comunidad : comunidadesAutonomas) {
	        boxCA.addItem(comunidad);
	    }
		
		boxCA.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String comunidadSeleccionada = (String) boxCA.getSelectedItem();
                    llenarProvincias(comunidadSeleccionada);
                }
            }
        });
		
		
		
       
        

	}
	 
	 private void llenarProvincias(String comunidadAutonoma) {
	        ArrayList<String> provincias = sacarProvincias(comunidadAutonoma);
	        
	        boxProvincia.removeAllItems();
	        for (String provincia : provincias) {
	            boxProvincia.addItem(provincia);
	        }
	    }
	 public ArrayList<String> sacarProvincias(String comunidadAutonoma) {
		    ArrayList<String> provincias = new ArrayList<>();

		    if ("Andalucia".equals(comunidadAutonoma)) {
		        provincias.add("Almeria");
		        provincias.add("Cadiz");
		        provincias.add("Cordoba");
		        provincias.add("Granada");
		        provincias.add("Huelva");
		        provincias.add("Jaen");
		        provincias.add("Malaga");
		        provincias.add("Sevilla");
		    } else if ("Aragon".equals(comunidadAutonoma)) {
		        provincias.add("Huesca");
		        provincias.add("Teruel");
		        provincias.add("Zaragoza");
		    } else if ("Asturias".equals(comunidadAutonoma)) {
		        provincias.add("Asturias");
		    } else if ("Canarias".equals(comunidadAutonoma)) {
		        provincias.add("Las_Palmas");
		        provincias.add("Santa_Cruz_de_Tenerife");
		    } else if ("Cantabria".equals(comunidadAutonoma)) {
		        provincias.add("Cantabria");
		    } else if ("Castilla_y_Leon".equals(comunidadAutonoma)) {
		        provincias.add("Avila");
		        provincias.add("Burgos");
		        provincias.add("Leon");
		        provincias.add("Palencia");
		        provincias.add("Salamanca");
		        provincias.add("Segovia");
		        provincias.add("Soria");
		        provincias.add("Valladolid");
		        provincias.add("Zamora");
		    } else if ("Castilla-La_Mancha".equals(comunidadAutonoma)) {
		        provincias.add("Albacete");
		        provincias.add("Ciudad_Real");
		        provincias.add("Cuenca");
		        provincias.add("Guadalajara");
		        provincias.add("Toledo");
		    } else if ("Cataluna".equals(comunidadAutonoma)) {
		        provincias.add("Barcelona");
		        provincias.add("Girona");
		        provincias.add("Lleida");
		        provincias.add("Tarragona");
		    } else if ("Extremadura".equals(comunidadAutonoma)) {
		        provincias.add("Badajoz");
		        provincias.add("Caceres");
		    } else if ("Galicia".equals(comunidadAutonoma)) {
		        provincias.add("A_Coruna");
		        provincias.add("Lugo");
		        provincias.add("Ourense");
		        provincias.add("Pontevedra");
		    } else if ("Islas_Baleares".equals(comunidadAutonoma)) {
		        provincias.add("Islas Baleares");
		    } else if ("La_Rioja".equals(comunidadAutonoma)) {
		        provincias.add("La Rioja");
		    } else if ("Madrid".equals(comunidadAutonoma)) {
		        provincias.add("Madrid");
		    } else if ("Murcia".equals(comunidadAutonoma)) {
		        provincias.add("Murcia");
		    } else if ("Navarra".equals(comunidadAutonoma)) {
		        provincias.add("Navarra");
		    } else if ("Pais_Vasco".equals(comunidadAutonoma)) {
		        provincias.add("Alava");
		        provincias.add("Vizcaya");
		        
		    } else if ("Valencia".equals(comunidadAutonoma)) {
		        provincias.add("Alicante");
		        provincias.add("Castellon");
		        provincias.add("Valencia");
		    }

		    return provincias;
		}

}
