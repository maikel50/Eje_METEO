package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import resource.configuracion;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import java.awt.event.ItemEvent;

public class vista extends JFrame {
	configuracion Configuracion = new configuracion();
	public JPanel contentPane,panel_Buscador,panel_Mapa;

	public JLabel labelNombreProvincia,labelIconoTiempo,labelTemperatura,labelTMax,labelTMin,labelImagenEspana, labelBuscador;
	public JComboBox <String>boxCA;
	public JComboBox <String>boxProvincia;
	public    JComboBox <String>boxDia;
	public JButton boton;
	public String [] comunidadesAutonomas = new String [17] ;
	


	public List<String> fechas = configuracion.devolverFechas();
	public ArrayList<String> CA = new ArrayList();
	public JLabel lblIconoAvila;
	public JLabel lblIconoCáceres;
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
		
		 labelIconoTiempo = new JLabel("");
		labelIconoTiempo.setHorizontalAlignment(SwingConstants.CENTER);
		labelIconoTiempo.setBounds(115, 101, 66, 53);
		panel_Buscador.add(labelIconoTiempo);
		
		 labelTemperatura = new JLabel("0 ºC");
		 labelTemperatura.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelTemperatura.setHorizontalAlignment(SwingConstants.CENTER);
		labelTemperatura.setBounds(48, 164, 197, 29);
		panel_Buscador.add(labelTemperatura);
		
		 labelTMax = new JLabel("Mín: 0 ºC");
		labelTMax.setHorizontalAlignment(SwingConstants.CENTER);
		labelTMax.setBounds(48, 193, 197, 29);
		panel_Buscador.add(labelTMax);
		
		 labelTMin = new JLabel("Máx: 0 ºC");
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
		lblIconoPalencia.setIcon(new ImageIcon(vista.class.getResource("/img/lluvia.png")));
		lblIconoPalencia.setBounds(244, 44, 25, 25);
		panel_Mapa.add(lblIconoPalencia);
		
		lblIconoLeon = new JLabel("");
		lblIconoLeon.setIcon(new ImageIcon(vista.class.getResource("/img/nieve.png")));
		lblIconoLeon.setBounds(196, 45, 25, 25);
		panel_Mapa.add(lblIconoLeon);
		
		lblIconoValladolid = new JLabel("");
		lblIconoValladolid.setIcon(new ImageIcon(vista.class.getResource("/img/sol.png")));
		lblIconoValladolid.setBounds(229, 91, 25, 25);
		panel_Mapa.add(lblIconoValladolid);
		
		lblIconoZamora = new JLabel("");
		lblIconoZamora.setIcon(new ImageIcon(vista.class.getResource("/img/sol.png")));
		lblIconoZamora.setBounds(194, 91, 25, 25);
		panel_Mapa.add(lblIconoZamora);
		
		lblIconoBurgos = new JLabel("");
		lblIconoBurgos.setIcon(new ImageIcon(vista.class.getResource("/img/lluvia.png")));
		lblIconoBurgos.setBounds(271, 66, 25, 25);
		panel_Mapa.add(lblIconoBurgos);
		
		lblIconoSegovia = new JLabel("");
		lblIconoSegovia.setIcon(new ImageIcon(vista.class.getResource("/img/sol.png")));
		lblIconoSegovia.setBounds(258, 115, 25, 25);
		panel_Mapa.add(lblIconoSegovia);
		
		lblIconoSoria = new JLabel("");
		lblIconoSoria.setIcon(new ImageIcon(vista.class.getResource("/img/lluvia.png")));
		lblIconoSoria.setBounds(318, 91, 25, 25);
		panel_Mapa.add(lblIconoSoria);
		
		lblIconoLerida = new JLabel("");
		lblIconoLerida.setIcon(new ImageIcon(vista.class.getResource("/img/sol.png")));
		lblIconoLerida.setBounds(439, 66, 25, 25);
		panel_Mapa.add(lblIconoLerida);
		
		lblIconoTarragona = new JLabel("");
		lblIconoTarragona.setIcon(new ImageIcon(vista.class.getResource("/img/sol.png")));
		lblIconoTarragona.setBounds(426, 115, 25, 25);
		panel_Mapa.add(lblIconoTarragona);
		
		lblIconoPontevedra = new JLabel("");
		lblIconoPontevedra.setIcon(new ImageIcon(vista.class.getResource("/img/nieve.png")));
		lblIconoPontevedra.setBounds(110, 54, 25, 25);
		panel_Mapa.add(lblIconoPontevedra);
		
		lblIconoLaCoruña = new JLabel("");
		lblIconoLaCoruña.setIcon(new ImageIcon(vista.class.getResource("/img/nieve.png")));
		lblIconoLaCoruña.setBounds(105, 21, 25, 25);
		panel_Mapa.add(lblIconoLaCoruña);
		
		lblIconoOrense = new JLabel("");
		lblIconoOrense.setIcon(new ImageIcon(vista.class.getResource("/img/nube.png")));
		lblIconoOrense.setBounds(140, 66, 25, 25);
		panel_Mapa.add(lblIconoOrense);
		
		lblIconoLugo = new JLabel("");
		lblIconoLugo.setIcon(new ImageIcon(vista.class.getResource("/img/lluvia.png")));
		lblIconoLugo.setBounds(140, 10, 25, 25);
		panel_Mapa.add(lblIconoLugo);
		
		lblIconoAsturias = new JLabel("");
		lblIconoAsturias.setIcon(new ImageIcon(vista.class.getResource("/img/sol.png")));
		lblIconoAsturias.setBounds(186, 10, 25, 25);
		panel_Mapa.add(lblIconoAsturias);
		
		lblIconoCantabria = new JLabel("");
		lblIconoCantabria.setIcon(new ImageIcon(vista.class.getResource("/img/lluvia.png")));
		lblIconoCantabria.setBounds(271, 10, 25, 25);
		panel_Mapa.add(lblIconoCantabria);
		
		lblIconoHuesca = new JLabel("");
		lblIconoHuesca.setIcon(new ImageIcon(vista.class.getResource("/img/lluvia.png")));
		lblIconoHuesca.setBounds(385, 66, 25, 25);
		panel_Mapa.add(lblIconoHuesca);
		
		lblIconoZaragoza = new JLabel("");
		lblIconoZaragoza.setIcon(new ImageIcon(vista.class.getResource("/img/sol.png")));
		lblIconoZaragoza.setBounds(375, 104, 25, 25);
		panel_Mapa.add(lblIconoZaragoza);
		
		lblIconoTeruel = new JLabel("");
		lblIconoTeruel.setIcon(new ImageIcon(vista.class.getResource("/img/sol.png")));
		lblIconoTeruel.setBounds(366, 141, 25, 25);
		panel_Mapa.add(lblIconoTeruel);
		
		lblIconoAlava = new JLabel("");
		lblIconoAlava.setIcon(new ImageIcon(vista.class.getResource("/img/sol.png")));
		lblIconoAlava.setBounds(299, 33, 25, 25);
		panel_Mapa.add(lblIconoAlava);
		
		lblIconoVizcaya = new JLabel("");
		lblIconoVizcaya.setIcon(new ImageIcon(vista.class.getResource("/img/nube.png")));
		lblIconoVizcaya.setBounds(318, 10, 25, 25);
		panel_Mapa.add(lblIconoVizcaya);
		
		lblIconoNavarra = new JLabel("");
		lblIconoNavarra.setIcon(new ImageIcon(vista.class.getResource("/img/lluvia.png")));
		lblIconoNavarra.setBounds(339, 44, 25, 25);
		panel_Mapa.add(lblIconoNavarra);
		
		lblIconoLaRioja = new JLabel("");
		lblIconoLaRioja.setIcon(new ImageIcon(vista.class.getResource("/img/nube.png")));
		lblIconoLaRioja.setBounds(318, 66, 25, 25);
		panel_Mapa.add(lblIconoLaRioja);
		
		lblIconoSalamanca = new JLabel("");
		lblIconoSalamanca.setIcon(new ImageIcon(vista.class.getResource("/img/lluvia.png")));
		lblIconoSalamanca.setBounds(194, 127, 25, 25);
		panel_Mapa.add(lblIconoSalamanca);
		
		lblIconoJaen = new JLabel("");
		lblIconoJaen.setIcon(new ImageIcon(vista.class.getResource("/img/sol.png")));
		lblIconoJaen.setBounds(283, 252, 25, 25);
		panel_Mapa.add(lblIconoJaen);
		
		lblIconoAlmeria = new JLabel("");
		lblIconoAlmeria.setIcon(new ImageIcon(vista.class.getResource("/img/sol.png")));
		lblIconoAlmeria.setBounds(318, 281, 25, 25);
		panel_Mapa.add(lblIconoAlmeria);
		
		lblIconoGranada = new JLabel("");
		lblIconoGranada.setIcon(new ImageIcon(vista.class.getResource("/img/sol.png")));
		lblIconoGranada.setBounds(283, 281, 25, 25);
		panel_Mapa.add(lblIconoGranada);
		
		lblIconoCordoba = new JLabel("");
		lblIconoCordoba.setIcon(new ImageIcon(vista.class.getResource("/img/nube.png")));
		lblIconoCordoba.setBounds(229, 252, 25, 25);
		panel_Mapa.add(lblIconoCordoba);
		
		lblIconoMalaga = new JLabel("");
		lblIconoMalaga.setIcon(new ImageIcon(vista.class.getResource("/img/lluvia.png")));
		lblIconoMalaga.setBounds(244, 296, 25, 25);
		panel_Mapa.add(lblIconoMalaga);
		
		lblIconoSevilla = new JLabel("");
		lblIconoSevilla.setIcon(new ImageIcon(vista.class.getResource("/img/lluvia.png")));
		lblIconoSevilla.setBounds(204, 281, 25, 25);
		panel_Mapa.add(lblIconoSevilla);
		
		lblIconoHuelva = new JLabel("");
		lblIconoHuelva.setIcon(new ImageIcon(vista.class.getResource("/img/nieve.png")));
		lblIconoHuelva.setBounds(159, 265, 25, 25);
		panel_Mapa.add(lblIconoHuelva);
		
		lblIconoLasPalmas = new JLabel("");
		lblIconoLasPalmas.setIcon(new ImageIcon(vista.class.getResource("/img/nube.png")));
		lblIconoLasPalmas.setBounds(110, 351, 25, 25);
		panel_Mapa.add(lblIconoLasPalmas);
		
		lblIconoSantaCruzDeTenerife = new JLabel("");
		lblIconoSantaCruzDeTenerife.setIcon(new ImageIcon(vista.class.getResource("/img/sol.png")));
		lblIconoSantaCruzDeTenerife.setBounds(33, 340, 25, 25);
		panel_Mapa.add(lblIconoSantaCruzDeTenerife);
		
		lblIconoIslasBaleares = new JLabel("");
		lblIconoIslasBaleares.setIcon(new ImageIcon(vista.class.getResource("/img/lluvia.png")));
		lblIconoIslasBaleares.setBounds(503, 176, 25, 25);
		panel_Mapa.add(lblIconoIslasBaleares);
		
		lblIconoCastellon = new JLabel("");
		lblIconoCastellon.setIcon(new ImageIcon(vista.class.getResource("/img/nube.png")));
		lblIconoCastellon.setBounds(401, 157, 25, 25);
		panel_Mapa.add(lblIconoCastellon);
		
		lblIconoAlicante = new JLabel("");
		lblIconoAlicante.setIcon(new ImageIcon(vista.class.getResource("/img/lluvia.png")));
		lblIconoAlicante.setBounds(385, 226, 25, 25);
		panel_Mapa.add(lblIconoAlicante);
		
		lblIconoAlbacete = new JLabel("");
		lblIconoAlbacete.setIcon(new ImageIcon(vista.class.getResource("/img/sol.png")));
		lblIconoAlbacete.setBounds(318, 211, 25, 25);
		panel_Mapa.add(lblIconoAlbacete);
		
		lblIconoCuenca = new JLabel("");
		lblIconoCuenca.setIcon(new ImageIcon(vista.class.getResource("/img/nube.png")));
		lblIconoCuenca.setBounds(328, 176, 25, 25);
		panel_Mapa.add(lblIconoCuenca);
		
		lblIconoGuadalajara = new JLabel("");
		lblIconoGuadalajara.setIcon(new ImageIcon(vista.class.getResource("/img/nube.png")));
		lblIconoGuadalajara.setBounds(318, 141, 25, 25);
		panel_Mapa.add(lblIconoGuadalajara);
		
		lblIconoBadajoz = new JLabel("");
		lblIconoBadajoz.setIcon(new ImageIcon(vista.class.getResource("/img/nube.png")));
		lblIconoBadajoz.setBounds(186, 226, 25, 25);
		panel_Mapa.add(lblIconoBadajoz);
		
		lblIconoToledo = new JLabel("");
		lblIconoToledo.setIcon(new ImageIcon(vista.class.getResource("/img/sol.png")));
		lblIconoToledo.setBounds(258, 176, 25, 25);
		panel_Mapa.add(lblIconoToledo);
		
		lblIconoMadrid = new JLabel("");
		lblIconoMadrid.setIcon(new ImageIcon(vista.class.getResource("/img/sol.png")));
		lblIconoMadrid.setBounds(271, 141, 25, 25);
		panel_Mapa.add(lblIconoMadrid);
		
		lblIconoMurcia = new JLabel("");
		lblIconoMurcia.setIcon(new ImageIcon(vista.class.getResource("/img/nieve.png")));
		lblIconoMurcia.setBounds(353, 252, 25, 25);
		panel_Mapa.add(lblIconoMurcia);
		
		lblIconoValencia = new JLabel("");
		lblIconoValencia.setIcon(new ImageIcon(vista.class.getResource("/img/sol.png")));
		lblIconoValencia.setBounds(375, 187, 25, 25);
		panel_Mapa.add(lblIconoValencia);
		
		lblIconoCadiz = new JLabel("");
		lblIconoCadiz.setIcon(new ImageIcon(vista.class.getResource("/img/nieve.png")));
		lblIconoCadiz.setBounds(204, 316, 25, 25);
		panel_Mapa.add(lblIconoCadiz);
		
		lblIconoCáceres = new JLabel("");
		lblIconoCáceres.setIcon(new ImageIcon(vista.class.getResource("/img/lluvia.png")));
		lblIconoCáceres.setBounds(186, 176, 25, 25);
		panel_Mapa.add(lblIconoCáceres);
		
		lblIconoCiudadReal = new JLabel("");
		lblIconoCiudadReal.setIcon(new ImageIcon(vista.class.getResource("/img/sol.png")));
		lblIconoCiudadReal.setBounds(271, 211, 25, 25);
		panel_Mapa.add(lblIconoCiudadReal);
		
		lblIconoAvila = new JLabel("");
		lblIconoAvila.setIcon(new ImageIcon(vista.class.getResource("/img/nube.png")));
		lblIconoAvila.setBounds(229, 141, 25, 25);
		panel_Mapa.add(lblIconoAvila);
		
		lblIconoBarcelona = new JLabel("");
		lblIconoBarcelona.setIcon(new ImageIcon(vista.class.getResource("/img/nube.png")));
		lblIconoBarcelona.setBounds(471, 91, 25, 25);
		panel_Mapa.add(lblIconoBarcelona);
		
		lblIconoGirona = new JLabel("");
		lblIconoGirona.setIcon(new ImageIcon(vista.class.getResource("/img/nube.png")));
		lblIconoGirona.setBounds(489, 66, 25, 25);
		panel_Mapa.add(lblIconoGirona);
		
		labelImagenEspana = new JLabel("");
		labelImagenEspana.setHorizontalAlignment(SwingConstants.CENTER);
		labelImagenEspana.setIcon(new ImageIcon(vista.class.getResource("/img/mapaEspana-0000.jpg")));
		labelImagenEspana.setBounds(0, 0, 569, 386);
		panel_Mapa.add(labelImagenEspana);
	
		//Comunidades Autonomas
		
		String [] comunidadesAutonomas = {
	            "Andalucía", "Aragón", "Asturias", "Canarias", "Cantabria", "Castilla y León",
	            "Castilla-La Mancha", "Cataluña", "Extremadura", "Galicia", "Islas Baleares",
	            "La Rioja", "Madrid", "Murcia", "Navarra", "País Vasco", "Valencia"
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
		
		fechasComboBOXDIA(fechas);
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

		    if ("Andalucía".equals(comunidadAutonoma)) {
		        provincias.add("Almería");
		        provincias.add("Cádiz");
		        provincias.add("Córdoba");
		        provincias.add("Granada");
		        provincias.add("Huelva");
		        provincias.add("Jaén");
		        provincias.add("Málaga");
		        provincias.add("Sevilla");
		    } else if ("Aragón".equals(comunidadAutonoma)) {
		        provincias.add("Huesca");
		        provincias.add("Teruel");
		        provincias.add("Zaragoza");
		    } else if ("Asturias".equals(comunidadAutonoma)) {
		        provincias.add("Asturias");
		    } else if ("Canarias".equals(comunidadAutonoma)) {
		        provincias.add("Las Palmas");
		        provincias.add("Santa Cruz de Tenerife");
		    } else if ("Cantabria".equals(comunidadAutonoma)) {
		        provincias.add("Cantabria");
		    } else if ("Castilla y León".equals(comunidadAutonoma)) {
		        provincias.add("Ávila");
		        provincias.add("Burgos");
		        provincias.add("León");
		        provincias.add("Palencia");
		        provincias.add("Salamanca");
		        provincias.add("Segovia");
		        provincias.add("Soria");
		        provincias.add("Valladolid");
		        provincias.add("Zamora");
		    } else if ("Castilla-La Mancha".equals(comunidadAutonoma)) {
		        provincias.add("Albacete");
		        provincias.add("Ciudad Real");
		        provincias.add("Cuenca");
		        provincias.add("Guadalajara");
		        provincias.add("Toledo");
		    } else if ("Cataluña".equals(comunidadAutonoma)) {
		        provincias.add("Barcelona");
		        provincias.add("Girona");
		        provincias.add("Lleida");
		        provincias.add("Tarragona");
		    } else if ("Extremadura".equals(comunidadAutonoma)) {
		        provincias.add("Badajoz");
		        provincias.add("Cáceres");
		    } else if ("Galicia".equals(comunidadAutonoma)) {
		        provincias.add("A Coruña");
		        provincias.add("Lugo");
		        provincias.add("Ourense");
		        provincias.add("Pontevedra");
		    } else if ("Islas Baleares".equals(comunidadAutonoma)) {
		        provincias.add("Islas Baleares");
		    } else if ("La Rioja".equals(comunidadAutonoma)) {
		        provincias.add("La Rioja");
		    } else if ("Madrid".equals(comunidadAutonoma)) {
		        provincias.add("Madrid");
		    } else if ("Murcia".equals(comunidadAutonoma)) {
		        provincias.add("Murcia");
		    } else if ("Navarra".equals(comunidadAutonoma)) {
		        provincias.add("Navarra");
		    } else if ("País Vasco".equals(comunidadAutonoma)) {
		        provincias.add("Álava");
		        provincias.add("Gipuzkoa");
		        provincias.add("Bizkaia");
		    } else if ("Valencia".equals(comunidadAutonoma)) {
		        provincias.add("Alicante");
		        provincias.add("Castellón");
		        provincias.add("Valencia");
		    }

		    return provincias;
		}

	 public  void fechasComboBOXDIA(List<String> fechas) {
		 int contadorFechas=0;
		    	for (String fecha : fechas ) {
						boxDia.addItem(fecha);
						contadorFechas++;
						
						if(contadorFechas >= 5) {
							break;
						}
		    	}
		 }
		 
		
	 
}
