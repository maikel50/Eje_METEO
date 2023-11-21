package Controlador;

import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;

import Vista.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

public class controlador implements ActionListener {

    private vista frame;
    String provinciaSeleccionada;
    Properties config = new Properties();
    public controlador(vista frame) {
        this.frame = frame;
        this.frame.boton.addActionListener(this);
        this.frame.boxCA.addActionListener(this);
        this.frame.boxProvincia.addActionListener(this);
      
    }
    private void cargarConfiguracion() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("src/Controlador/config.properties")) {
            if (input == null) {
                System.out.println("Lo siento, no se puede encontrar el archivo config.properties");
                return;
            }

           
            config.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frame.boxProvincia) {
        	 provinciaSeleccionada = (String) frame.boxProvincia.getSelectedItem();
        	 System.out.println(provinciaSeleccionada);
        	 obtenerFechas(provinciaSeleccionada);
        }
        
        if (e.getSource() == frame.boton) {
        	try {
        		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty(provinciaSeleccionada);

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 System.out.println("presionado");
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	String minTemp = selectedDayNode.path("minTemp").asText();
                        	String maxTemp = selectedDayNode.path("maxTemp").asText();
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
                        frame.labelTMax.setText("Temperatura Maxima: " +maxTemp + "ºC");
                        frame.labelTMin.setText("Temperatura Minima: " + minTemp + "ºC");
                
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.labelIconoTiempo.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                            frame.labelIconoTiempo.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                            frame.labelIconoTiempo.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                            frame.labelIconoTiempo.setIcon(iconoSoleado);
                        }
                        // Imprimir la información
                        System.out.println("Temperatura Mínima: " + minTemp);
                        System.out.println("Temperatura Máxima: " + maxTemp);
                        System.out.println("Estado del Tiempo: " + estadoTiempo);
                        System.out.println("fecha: " + fecha);
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
        	
            metodoPanelEspana();
        }
    }

	private void metodoPanelEspana() {
		//Andalucia
		try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Almeria");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 System.out.println("presionado");
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoAlmeria.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoAlmeria.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoAlmeria.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoAlmeria.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Cadiz");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 System.out.println("presionado");
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoCadiz.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoCadiz.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoCadiz.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoCadiz.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Cordoba");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 System.out.println("presionado");
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoCordoba.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoCordoba.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoCordoba.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoCordoba.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Huelva");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 System.out.println("presionado");
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoHuelva.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoHuelva.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoHuelva.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoHuelva.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		
		try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Sevilla");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 System.out.println("presionado");
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoSevilla.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoSevilla.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoSevilla.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoSevilla.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Jaen");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 System.out.println("presionado");
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoJaen.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoJaen.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoJaen.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoJaen.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Granada");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoGranada.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoGranada.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoGranada.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoGranada.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Malaga");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoMalaga.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoMalaga.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoMalaga.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoMalaga.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		//------FIN ANDALUCIA----------
		//CANARIAS
		try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Santa_Cruz_de_Tenerife");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoSantaCruzDeTenerife.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoSantaCruzDeTenerife.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoSantaCruzDeTenerife.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoSantaCruzDeTenerife.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Las_Palmas");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoLasPalmas.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoLasPalmas.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoLasPalmas.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoLasPalmas.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		//FIN CANARIAS
		//EXTREMADURA
try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Badajoz");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoBadajoz.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoBadajoz.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoBadajoz.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoBadajoz.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Caceres");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoCaceres.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoCaceres.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoCaceres.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoCaceres.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		//FIN EXTREMADURA
		//Murcia
		try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Murcia");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoMurcia.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoMurcia.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoMurcia.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoMurcia.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		//FIN MURCIA
		//CASTILLA LA MANCHA
try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Guadalajara");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 System.out.println("presionado");
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoGuadalajara.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoGuadalajara.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoGuadalajara.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoGuadalajara.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Cuenca");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 System.out.println("presionado");
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoCuenca.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoCuenca.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoCuenca.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoCuenca.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Albacete");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 System.out.println("presionado");
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoAlbacete.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoAlbacete.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoAlbacete.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoAlbacete.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Ciudad_Real");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 System.out.println("presionado");
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoCiudadReal.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoCiudadReal.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoCiudadReal.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoCiudadReal.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		
		try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Toledo");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 System.out.println("presionado");
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoToledo.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoToledo.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoToledo.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoToledo.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
			//FIN CASTILLA LA MANCHA
		//VALENCIA
		try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Castellon");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 System.out.println("presionado");
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoCastellon.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoCastellon.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoCastellon.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoCastellon.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Alicante");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 System.out.println("presionado");
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoAlicante.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoAlicante.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoAlicante.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoAlicante.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		
		try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Valencia");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 System.out.println("presionado");
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoValencia.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoValencia.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoValencia.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoValencia.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		//FIN VALENCIA
		//MADRID
		try {
    		
        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            String url = config.getProperty("Madrid");

            // Realizar la solicitud HTTP y procesar la respuesta JSON
            if (url != null) {
            	 System.out.println("presionado");
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
                      
                        // Obtener la fecha seleccionada
                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
                     // Buscar el nodo correspondiente a la fecha seleccionada
                        JsonNode selectedDayNode = null;
                        for (JsonNode dayNode : forecastNode) {
                            String fecha = dayNode.path("forecastDate").asText();
                            if (fecha.equals(fechaSeleccionada)) {
                                selectedDayNode = dayNode;
                                break;
                            }
                        }
                        if (selectedDayNode != null) {
                        	String fecha = selectedDayNode.path("forecastDate").asText();
                        	
                        	String estadoTiempo = selectedDayNode.path("weather").asText();
                        	
                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
 
                        if(estadoTiempo.equals("Soleado")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
                            frame.lblIconoMadrid.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoMadrid.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Cubierto")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
                        	frame.lblIconoMadrid.setIcon(iconoSoleado);
                        }else if(estadoTiempo.equals("Lluvia débil")) {
                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
                        	frame.lblIconoMadrid.setIcon(iconoSoleado);
                        }
                     
                    }else {
                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
                    }
                        }catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
		//FIN MADRID
		//GALICIA
		try{
			config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			            String url = config.getProperty("A_Coruna");

			            // Realizar la solicitud HTTP y procesar la respuesta JSON
			            if (url != null) {
			            	 System.out.println("presionado");
			                String respuesta = realizarSolicitudHTTP(url);

			                // Procesar la respuesta JSON
			                if (respuesta != null && !respuesta.isEmpty()) {
			                    try {
			                        ObjectMapper objectMapper = new ObjectMapper();
			                        JsonNode jsonNode = objectMapper.readTree(respuesta);

			                        // Obtener información del pronóstico
			                        
			                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                      
			                        // Obtener la fecha seleccionada
			                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                     // Buscar el nodo correspondiente a la fecha seleccionada
			                        JsonNode selectedDayNode = null;
			                        for (JsonNode dayNode : forecastNode) {
			                            String fecha = dayNode.path("forecastDate").asText();
			                            if (fecha.equals(fechaSeleccionada)) {
			                                selectedDayNode = dayNode;
			                                break;
			                            }
			                        }
			                        if (selectedDayNode != null) {
			                        	String fecha = selectedDayNode.path("forecastDate").asText();
			                        	
			                        	String estadoTiempo = selectedDayNode.path("weather").asText();
			                        	
			                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
			 
			                        if(estadoTiempo.equals("Soleado")) {
			                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                            frame.lblIconoLaCoruña.setIcon(iconoSoleado);
			                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                        	frame.lblIconoLaCoruña.setIcon(iconoSoleado);
			                        }else if(estadoTiempo.equals("Cubierto")) {
			                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                        	frame.lblIconoLaCoruña.setIcon(iconoSoleado);
			                        }else if(estadoTiempo.equals("Lluvia débil")) {
			                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                        	frame.lblIconoLaCoruña.setIcon(iconoSoleado);
			                        }
			                     
			                    }else {
			                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                    }
			                        }catch (Exception ex) {
			                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                    }
			                }
			            }
			            } catch(Exception ex) {
			            	ex.printStackTrace();
			            }
			try {
				
				config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			    String url = config.getProperty("Pontevedra");

			    // Realizar la solicitud HTTP y procesar la respuesta JSON
			    if (url != null) {
			    	 System.out.println("presionado");
			        String respuesta = realizarSolicitudHTTP(url);

			        // Procesar la respuesta JSON
			        if (respuesta != null && !respuesta.isEmpty()) {
			            try {
			                ObjectMapper objectMapper = new ObjectMapper();
			                JsonNode jsonNode = objectMapper.readTree(respuesta);

			                // Obtener información del pronóstico
			                
			                JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			              
			                // Obtener la fecha seleccionada
			                String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			             // Buscar el nodo correspondiente a la fecha seleccionada
			                JsonNode selectedDayNode = null;
			                for (JsonNode dayNode : forecastNode) {
			                    String fecha = dayNode.path("forecastDate").asText();
			                    if (fecha.equals(fechaSeleccionada)) {
			                        selectedDayNode = dayNode;
			                        break;
			                    }
			                }
			                if (selectedDayNode != null) {
			                	String fecha = selectedDayNode.path("forecastDate").asText();
			                	
			                	String estadoTiempo = selectedDayNode.path("weather").asText();
			                	
			                frame.labelNombreProvincia.setText(provinciaSeleccionada);

			                if(estadoTiempo.equals("Soleado")) {
			                	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                    frame.lblIconoPontevedra.setIcon(iconoSoleado);
			                }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                	frame.lblIconoPontevedra.setIcon(iconoSoleado);
			                }else if(estadoTiempo.equals("Cubierto")) {
			                	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                	frame.lblIconoPontevedra.setIcon(iconoSoleado);
			                }else if(estadoTiempo.equals("Lluvia débil")) {
			                	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                	frame.lblIconoPontevedra.setIcon(iconoSoleado);
			                }
			             
			            }else {
			            	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			            }
			                }catch (Exception ex) {
			                ex.printStackTrace();  // Manejar errores de procesamiento JSON
			            }
			        }
			    }
			    } catch(Exception ex) {
			    	ex.printStackTrace();
			    }try {
					
			    	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			        String url = config.getProperty("Lugo");

			        // Realizar la solicitud HTTP y procesar la respuesta JSON
			        if (url != null) {
			        	 System.out.println("presionado");
			            String respuesta = realizarSolicitudHTTP(url);

			            // Procesar la respuesta JSON
			            if (respuesta != null && !respuesta.isEmpty()) {
			                try {
			                    ObjectMapper objectMapper = new ObjectMapper();
			                    JsonNode jsonNode = objectMapper.readTree(respuesta);

			                    // Obtener información del pronóstico
			                    
			                    JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                  
			                    // Obtener la fecha seleccionada
			                    String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                 // Buscar el nodo correspondiente a la fecha seleccionada
			                    JsonNode selectedDayNode = null;
			                    for (JsonNode dayNode : forecastNode) {
			                        String fecha = dayNode.path("forecastDate").asText();
			                        if (fecha.equals(fechaSeleccionada)) {
			                            selectedDayNode = dayNode;
			                            break;
			                        }
			                    }
			                    if (selectedDayNode != null) {
			                    	String fecha = selectedDayNode.path("forecastDate").asText();
			                    	
			                    	String estadoTiempo = selectedDayNode.path("weather").asText();
			                    	
			                    frame.labelNombreProvincia.setText(provinciaSeleccionada);

			                    if(estadoTiempo.equals("Soleado")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                        frame.lblIconoLugo.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                    	frame.lblIconoLugo.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Cubierto")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                    	frame.lblIconoLugo.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Lluvia débil")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                    	frame.lblIconoLugo.setIcon(iconoSoleado);
			                    }
			                 
			                }else {
			                	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                }
			                    }catch (Exception ex) {
			                    ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                }
			            }
			        }
			        } catch(Exception ex) {
			        	ex.printStackTrace();
			        }
			    try {
					
			    	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			        String url = config.getProperty("Orense");

			        // Realizar la solicitud HTTP y procesar la respuesta JSON
			        if (url != null) {
			        	 System.out.println("presionado");
			            String respuesta = realizarSolicitudHTTP(url);

			            // Procesar la respuesta JSON
			            if (respuesta != null && !respuesta.isEmpty()) {
			                try {
			                    ObjectMapper objectMapper = new ObjectMapper();
			                    JsonNode jsonNode = objectMapper.readTree(respuesta);

			                    // Obtener información del pronóstico
			                    
			                    JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                  
			                    // Obtener la fecha seleccionada
			                    String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                 // Buscar el nodo correspondiente a la fecha seleccionada
			                    JsonNode selectedDayNode = null;
			                    for (JsonNode dayNode : forecastNode) {
			                        String fecha = dayNode.path("forecastDate").asText();
			                        if (fecha.equals(fechaSeleccionada)) {
			                            selectedDayNode = dayNode;
			                            break;
			                        }
			                    }
			                    if (selectedDayNode != null) {
			                    	String fecha = selectedDayNode.path("forecastDate").asText();
			                    	
			                    	String estadoTiempo = selectedDayNode.path("weather").asText();
			                    	
			                    frame.labelNombreProvincia.setText(provinciaSeleccionada);

			                    if(estadoTiempo.equals("Soleado")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                        frame.lblIconoOrense.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                    	frame.lblIconoOrense.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Cubierto")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                    	frame.lblIconoOrense.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Lluvia débil")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                    	frame.lblIconoOrense.setIcon(iconoSoleado);
			                    }
			                 
			                }else {
			                	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                }
			                    }catch (Exception ex) {
			                    ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                }
			            }
			        }
			        } catch(Exception ex) {
			        	ex.printStackTrace();
			        }
			    //FIN GALICIA
			    //ASTURIAS
			    try {
					
			    	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			        String url = config.getProperty("Asturias");

			        // Realizar la solicitud HTTP y procesar la respuesta JSON
			        if (url != null) {
			        	 System.out.println("presionado");
			            String respuesta = realizarSolicitudHTTP(url);

			            // Procesar la respuesta JSON
			            if (respuesta != null && !respuesta.isEmpty()) {
			                try {
			                    ObjectMapper objectMapper = new ObjectMapper();
			                    JsonNode jsonNode = objectMapper.readTree(respuesta);

			                    // Obtener información del pronóstico
			                    
			                    JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                  
			                    // Obtener la fecha seleccionada
			                    String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                 // Buscar el nodo correspondiente a la fecha seleccionada
			                    JsonNode selectedDayNode = null;
			                    for (JsonNode dayNode : forecastNode) {
			                        String fecha = dayNode.path("forecastDate").asText();
			                        if (fecha.equals(fechaSeleccionada)) {
			                            selectedDayNode = dayNode;
			                            break;
			                        }
			                    }
			                    if (selectedDayNode != null) {
			                    	String fecha = selectedDayNode.path("forecastDate").asText();
			                    	
			                    	String estadoTiempo = selectedDayNode.path("weather").asText();
			                    	
			                    frame.labelNombreProvincia.setText(provinciaSeleccionada);

			                    if(estadoTiempo.equals("Soleado")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                        frame.lblIconoAsturias.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                    	frame.lblIconoAsturias.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Cubierto")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                    	frame.lblIconoAsturias.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Lluvia débil")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                    	frame.lblIconoAsturias.setIcon(iconoSoleado);
			                    }
			                 
			                }else {
			                	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                }
			                    }catch (Exception ex) {
			                    ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                }
			            }
			        }
			        } catch(Exception ex) {
			        	ex.printStackTrace();
			        }
			    //FIN ASTURIAS
			    //CANTABRIA
			    try {
					
			    	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			        String url = config.getProperty("Cantabria");

			        // Realizar la solicitud HTTP y procesar la respuesta JSON
			        if (url != null) {
			        	 System.out.println("presionado");
			            String respuesta = realizarSolicitudHTTP(url);

			            // Procesar la respuesta JSON
			            if (respuesta != null && !respuesta.isEmpty()) {
			                try {
			                    ObjectMapper objectMapper = new ObjectMapper();
			                    JsonNode jsonNode = objectMapper.readTree(respuesta);

			                    // Obtener información del pronóstico
			                    
			                    JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                  
			                    // Obtener la fecha seleccionada
			                    String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                 // Buscar el nodo correspondiente a la fecha seleccionada
			                    JsonNode selectedDayNode = null;
			                    for (JsonNode dayNode : forecastNode) {
			                        String fecha = dayNode.path("forecastDate").asText();
			                        if (fecha.equals(fechaSeleccionada)) {
			                            selectedDayNode = dayNode;
			                            break;
			                        }
			                    }
			                    if (selectedDayNode != null) {
			                    	String fecha = selectedDayNode.path("forecastDate").asText();
			                    	
			                    	String estadoTiempo = selectedDayNode.path("weather").asText();
			                    	
			                    frame.labelNombreProvincia.setText(provinciaSeleccionada);

			                    if(estadoTiempo.equals("Soleado")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                        frame.lblIconoCantabria.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                    	frame.lblIconoCantabria.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Cubierto")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                    	frame.lblIconoCantabria.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Lluvia débil")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                    	frame.lblIconoCantabria.setIcon(iconoSoleado);
			                    }
			                 
			                }else {
			                	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                }
			                    }catch (Exception ex) {
			                    ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                }
			            }
			        }
			        } catch(Exception ex) {
			        	ex.printStackTrace();
			        }
			    //FIN CANTABRIA
			    //PAIS_VASCO
			    try {
					
			    	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			        String url = config.getProperty("Vizcaya");

			        // Realizar la solicitud HTTP y procesar la respuesta JSON
			        if (url != null) {
			        	 System.out.println("presionado");
			            String respuesta = realizarSolicitudHTTP(url);

			            // Procesar la respuesta JSON
			            if (respuesta != null && !respuesta.isEmpty()) {
			                try {
			                    ObjectMapper objectMapper = new ObjectMapper();
			                    JsonNode jsonNode = objectMapper.readTree(respuesta);

			                    // Obtener información del pronóstico
			                    
			                    JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                  
			                    // Obtener la fecha seleccionada
			                    String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                 // Buscar el nodo correspondiente a la fecha seleccionada
			                    JsonNode selectedDayNode = null;
			                    for (JsonNode dayNode : forecastNode) {
			                        String fecha = dayNode.path("forecastDate").asText();
			                        if (fecha.equals(fechaSeleccionada)) {
			                            selectedDayNode = dayNode;
			                            break;
			                        }
			                    }
			                    if (selectedDayNode != null) {
			                    	String fecha = selectedDayNode.path("forecastDate").asText();
			                    	
			                    	String estadoTiempo = selectedDayNode.path("weather").asText();
			                    	
			                    frame.labelNombreProvincia.setText(provinciaSeleccionada);

			                    if(estadoTiempo.equals("Soleado")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                        frame.lblIconoVizcaya.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                    	frame.lblIconoVizcaya.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Cubierto")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                    	frame.lblIconoVizcaya.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Lluvia débil")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                    	frame.lblIconoVizcaya.setIcon(iconoSoleado);
			                    }
			                 
			                }else {
			                	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                }
			                    }catch (Exception ex) {
			                    ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                }
			            }
			        }
			        } catch(Exception ex) {
			        	ex.printStackTrace();
			        }
			  
			    try {
					
			    	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			        String url = config.getProperty("Alava");

			        // Realizar la solicitud HTTP y procesar la respuesta JSON
			        if (url != null) {
			        	 System.out.println("presionado");
			            String respuesta = realizarSolicitudHTTP(url);

			            // Procesar la respuesta JSON
			            if (respuesta != null && !respuesta.isEmpty()) {
			                try {
			                    ObjectMapper objectMapper = new ObjectMapper();
			                    JsonNode jsonNode = objectMapper.readTree(respuesta);

			                    // Obtener información del pronóstico
			                    
			                    JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                  
			                    // Obtener la fecha seleccionada
			                    String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                 // Buscar el nodo correspondiente a la fecha seleccionada
			                    JsonNode selectedDayNode = null;
			                    for (JsonNode dayNode : forecastNode) {
			                        String fecha = dayNode.path("forecastDate").asText();
			                        if (fecha.equals(fechaSeleccionada)) {
			                            selectedDayNode = dayNode;
			                            break;
			                        }
			                    }
			                    if (selectedDayNode != null) {
			                    	String fecha = selectedDayNode.path("forecastDate").asText();
			                    	
			                    	String estadoTiempo = selectedDayNode.path("weather").asText();
			                    	
			                    frame.labelNombreProvincia.setText(provinciaSeleccionada);

			                    if(estadoTiempo.equals("Soleado")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                        frame.lblIconoAlava.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                    	frame.lblIconoAlava.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Cubierto")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                    	frame.lblIconoAlava.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Lluvia débil")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                    	frame.lblIconoAlava.setIcon(iconoSoleado);
			                    }
			                 
			                }else {
			                	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                }
			                    }catch (Exception ex) {
			                    ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                }
			            }
			        }
			        } catch(Exception ex) {
			        	ex.printStackTrace();
			        }
			    //FIN PAIS_VASCO
			    //NAVARRA
			    try {
					
			    	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			        String url = config.getProperty("Navarra");

			        // Realizar la solicitud HTTP y procesar la respuesta JSON
			        if (url != null) {
			        	 System.out.println("presionado");
			            String respuesta = realizarSolicitudHTTP(url);

			            // Procesar la respuesta JSON
			            if (respuesta != null && !respuesta.isEmpty()) {
			                try {
			                    ObjectMapper objectMapper = new ObjectMapper();
			                    JsonNode jsonNode = objectMapper.readTree(respuesta);

			                    // Obtener información del pronóstico
			                    
			                    JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                  
			                    // Obtener la fecha seleccionada
			                    String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                 // Buscar el nodo correspondiente a la fecha seleccionada
			                    JsonNode selectedDayNode = null;
			                    for (JsonNode dayNode : forecastNode) {
			                        String fecha = dayNode.path("forecastDate").asText();
			                        if (fecha.equals(fechaSeleccionada)) {
			                            selectedDayNode = dayNode;
			                            break;
			                        }
			                    }
			                    if (selectedDayNode != null) {
			                    	String fecha = selectedDayNode.path("forecastDate").asText();
			                    	
			                    	String estadoTiempo = selectedDayNode.path("weather").asText();
			                    	
			                    frame.labelNombreProvincia.setText(provinciaSeleccionada);

			                    if(estadoTiempo.equals("Soleado")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                        frame.lblIconoNavarra.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                    	frame.lblIconoNavarra.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Cubierto")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                    	frame.lblIconoNavarra.setIcon(iconoSoleado);
			                    }else if(estadoTiempo.equals("Lluvia débil")) {
			                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                    	frame.lblIconoNavarra.setIcon(iconoSoleado);
			                    }
			                 
			                }else {
			                	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                }
			                    }catch (Exception ex) {
			                    ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                }
			            }
			        }
			        } catch(Exception ex) {
			        	ex.printStackTrace();
			        }
			    //FIN NAVARRA
			//ARAGON
			        try {
			    		
			        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			            String url = config.getProperty("Huesca");

			            // Realizar la solicitud HTTP y procesar la respuesta JSON
			            if (url != null) {
			            	 System.out.println("presionado");
			                String respuesta = realizarSolicitudHTTP(url);

			                // Procesar la respuesta JSON
			                if (respuesta != null && !respuesta.isEmpty()) {
			                    try {
			                        ObjectMapper objectMapper = new ObjectMapper();
			                        JsonNode jsonNode = objectMapper.readTree(respuesta);

			                        // Obtener información del pronóstico
			                        
			                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                      
			                        // Obtener la fecha seleccionada
			                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                     // Buscar el nodo correspondiente a la fecha seleccionada
			                        JsonNode selectedDayNode = null;
			                        for (JsonNode dayNode : forecastNode) {
			                            String fecha = dayNode.path("forecastDate").asText();
			                            if (fecha.equals(fechaSeleccionada)) {
			                                selectedDayNode = dayNode;
			                                break;
			                            }
			                        }
			                        if (selectedDayNode != null) {
			                        	String fecha = selectedDayNode.path("forecastDate").asText();
			                        	
			                        	String estadoTiempo = selectedDayNode.path("weather").asText();
			                        	
			                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
			 
			                        if(estadoTiempo.equals("Soleado")) {
			                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                            frame.lblIconoHuesca.setIcon(iconoSoleado);
			                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                        	frame.lblIconoHuesca.setIcon(iconoSoleado);
			                        }else if(estadoTiempo.equals("Cubierto")) {
			                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                        	frame.lblIconoHuesca.setIcon(iconoSoleado);
			                        }else if(estadoTiempo.equals("Lluvia débil")) {
			                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                        	frame.lblIconoHuesca.setIcon(iconoSoleado);
			                        }
			                     
			                    }else {
			                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                    }
			                        }catch (Exception ex) {
			                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                    }
			                }
			            }
			            } catch(Exception ex) {
			            	ex.printStackTrace();
			            }try {
			        		
			            	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			                String url = config.getProperty("Zaragoza");

			                // Realizar la solicitud HTTP y procesar la respuesta JSON
			                if (url != null) {
			                	 System.out.println("presionado");
			                    String respuesta = realizarSolicitudHTTP(url);

			                    // Procesar la respuesta JSON
			                    if (respuesta != null && !respuesta.isEmpty()) {
			                        try {
			                            ObjectMapper objectMapper = new ObjectMapper();
			                            JsonNode jsonNode = objectMapper.readTree(respuesta);

			                            // Obtener información del pronóstico
			                            
			                            JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                          
			                            // Obtener la fecha seleccionada
			                            String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                         // Buscar el nodo correspondiente a la fecha seleccionada
			                            JsonNode selectedDayNode = null;
			                            for (JsonNode dayNode : forecastNode) {
			                                String fecha = dayNode.path("forecastDate").asText();
			                                if (fecha.equals(fechaSeleccionada)) {
			                                    selectedDayNode = dayNode;
			                                    break;
			                                }
			                            }
			                            if (selectedDayNode != null) {
			                            	String fecha = selectedDayNode.path("forecastDate").asText();
			                            	
			                            	String estadoTiempo = selectedDayNode.path("weather").asText();
			                            	
			                            frame.labelNombreProvincia.setText(provinciaSeleccionada);
			     
			                            if(estadoTiempo.equals("Soleado")) {
			                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                                frame.lblIconoZaragoza.setIcon(iconoSoleado);
			                            }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                            	frame.lblIconoZaragoza.setIcon(iconoSoleado);
			                            }else if(estadoTiempo.equals("Cubierto")) {
			                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                            	frame.lblIconoZaragoza.setIcon(iconoSoleado);
			                            }else if(estadoTiempo.equals("Lluvia débil")) {
			                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                            	frame.lblIconoZaragoza.setIcon(iconoSoleado);
			                            }
			                         
			                        }else {
			                        	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                        }
			                            }catch (Exception ex) {
			                            ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                        }
			                    }
			                }
			                } catch(Exception ex) {
			                	ex.printStackTrace();
			                }
			            try {
			            		
			                	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			                    String url = config.getProperty("Teruel");

			                    // Realizar la solicitud HTTP y procesar la respuesta JSON
			                    if (url != null) {
			                    	 System.out.println("presionado");
			                        String respuesta = realizarSolicitudHTTP(url);

			                        // Procesar la respuesta JSON
			                        if (respuesta != null && !respuesta.isEmpty()) {
			                            try {
			                                ObjectMapper objectMapper = new ObjectMapper();
			                                JsonNode jsonNode = objectMapper.readTree(respuesta);

			                                // Obtener información del pronóstico
			                                
			                                JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                              
			                                // Obtener la fecha seleccionada
			                                String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                             // Buscar el nodo correspondiente a la fecha seleccionada
			                                JsonNode selectedDayNode = null;
			                                for (JsonNode dayNode : forecastNode) {
			                                    String fecha = dayNode.path("forecastDate").asText();
			                                    if (fecha.equals(fechaSeleccionada)) {
			                                        selectedDayNode = dayNode;
			                                        break;
			                                    }
			                                }
			                                if (selectedDayNode != null) {
			                                	String fecha = selectedDayNode.path("forecastDate").asText();
			                                	
			                                	String estadoTiempo = selectedDayNode.path("weather").asText();
			                                	
			                                frame.labelNombreProvincia.setText(provinciaSeleccionada);
			         
			                                if(estadoTiempo.equals("Soleado")) {
			                                	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                                    frame.lblIconoTeruel.setIcon(iconoSoleado);
			                                }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                                	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                	frame.lblIconoTeruel.setIcon(iconoSoleado);
			                                }else if(estadoTiempo.equals("Cubierto")) {
			                                	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                	frame.lblIconoTeruel.setIcon(iconoSoleado);
			                                }else if(estadoTiempo.equals("Lluvia débil")) {
			                                	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                                	frame.lblIconoTeruel.setIcon(iconoSoleado);
			                                }
			                             
			                            }else {
			                            	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                            }
			                                }catch (Exception ex) {
			                                ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                            }
			                        }
			                    }
			                    } catch(Exception ex) {
			                    	ex.printStackTrace();
			                    }
			            //FIN TERUEL
			                //CATALUNYA
			                try {
			                		
			                    	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			                        String url = config.getProperty("Lerida");

			                        // Realizar la solicitud HTTP y procesar la respuesta JSON
			                        if (url != null) {
			                        	 System.out.println("presionado");
			                            String respuesta = realizarSolicitudHTTP(url);

			                            // Procesar la respuesta JSON
			                            if (respuesta != null && !respuesta.isEmpty()) {
			                                try {
			                                    ObjectMapper objectMapper = new ObjectMapper();
			                                    JsonNode jsonNode = objectMapper.readTree(respuesta);

			                                    // Obtener información del pronóstico
			                                    
			                                    JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                                  
			                                    // Obtener la fecha seleccionada
			                                    String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                                 // Buscar el nodo correspondiente a la fecha seleccionada
			                                    JsonNode selectedDayNode = null;
			                                    for (JsonNode dayNode : forecastNode) {
			                                        String fecha = dayNode.path("forecastDate").asText();
			                                        if (fecha.equals(fechaSeleccionada)) {
			                                            selectedDayNode = dayNode;
			                                            break;
			                                        }
			                                    }
			                                    if (selectedDayNode != null) {
			                                    	String fecha = selectedDayNode.path("forecastDate").asText();
			                                    	
			                                    	String estadoTiempo = selectedDayNode.path("weather").asText();
			                                    	
			                                    frame.labelNombreProvincia.setText(provinciaSeleccionada);
			             
			                                    if(estadoTiempo.equals("Soleado")) {
			                                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                                        frame.lblIconoLerida.setIcon(iconoSoleado);
			                                    }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                    	frame.lblIconoLerida.setIcon(iconoSoleado);
			                                    }else if(estadoTiempo.equals("Cubierto")) {
			                                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                    	frame.lblIconoLerida.setIcon(iconoSoleado);
			                                    }else if(estadoTiempo.equals("Lluvia débil")) {
			                                    	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                                    	frame.lblIconoLerida.setIcon(iconoSoleado);
			                                    }
			                                 
			                                }else {
			                                	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                                }
			                                    }catch (Exception ex) {
			                                    ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                                }
			                            }
			                        }
			                        } catch(Exception ex) {
			                        	ex.printStackTrace();
			                        }try {
			                    		
			                        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			                            String url = config.getProperty("Girona");

			                            // Realizar la solicitud HTTP y procesar la respuesta JSON
			                            if (url != null) {
			                            	 System.out.println("presionado");
			                                String respuesta = realizarSolicitudHTTP(url);

			                                // Procesar la respuesta JSON
			                                if (respuesta != null && !respuesta.isEmpty()) {
			                                    try {
			                                        ObjectMapper objectMapper = new ObjectMapper();
			                                        JsonNode jsonNode = objectMapper.readTree(respuesta);

			                                        // Obtener información del pronóstico
			                                        
			                                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                                      
			                                        // Obtener la fecha seleccionada
			                                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                                     // Buscar el nodo correspondiente a la fecha seleccionada
			                                        JsonNode selectedDayNode = null;
			                                        for (JsonNode dayNode : forecastNode) {
			                                            String fecha = dayNode.path("forecastDate").asText();
			                                            if (fecha.equals(fechaSeleccionada)) {
			                                                selectedDayNode = dayNode;
			                                                break;
			                                            }
			                                        }
			                                        if (selectedDayNode != null) {
			                                        	String fecha = selectedDayNode.path("forecastDate").asText();
			                                        	
			                                        	String estadoTiempo = selectedDayNode.path("weather").asText();
			                                        	
			                                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
			                 
			                                        if(estadoTiempo.equals("Soleado")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                                            frame.lblIconoGirona.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                        	frame.lblIconoGirona.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Cubierto")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                        	frame.lblIconoGirona.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Lluvia débil")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                                        	frame.lblIconoGirona.setIcon(iconoSoleado);
			                                        }
			                                     
			                                    }else {
			                                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                                    }
			                                        }catch (Exception ex) {
			                                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                                    }
			                                }
			                            }
			                            } catch(Exception ex) {
			                            	ex.printStackTrace();
			                            }
			                        try {
			                    		
			                        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			                            String url = config.getProperty("Barcelona");

			                            // Realizar la solicitud HTTP y procesar la respuesta JSON
			                            if (url != null) {
			                            	 System.out.println("presionado");
			                                String respuesta = realizarSolicitudHTTP(url);

			                                // Procesar la respuesta JSON
			                                if (respuesta != null && !respuesta.isEmpty()) {
			                                    try {
			                                        ObjectMapper objectMapper = new ObjectMapper();
			                                        JsonNode jsonNode = objectMapper.readTree(respuesta);

			                                       // Obtener información del pronóstico
			                                        
			                                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                                      
			                                        // Obtener la fecha seleccionada
			                                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                                     // Buscar el nodo correspondiente a la fecha seleccionada
			                                        JsonNode selectedDayNode = null;
			                                        for (JsonNode dayNode : forecastNode) {
			                                            String fecha = dayNode.path("forecastDate").asText();
			                                            if (fecha.equals(fechaSeleccionada)) {
			                                                selectedDayNode = dayNode;
			                                                break;
			                                            }
			                                        }
			                                        if (selectedDayNode != null) {
			                                        	String fecha = selectedDayNode.path("forecastDate").asText();
			                                        	
			                                        	String estadoTiempo = selectedDayNode.path("weather").asText();
			                                        	
			                                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
			                 
			                                        if(estadoTiempo.equals("Soleado")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                                            frame.lblIconoBarcelona.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                        	frame.lblIconoBarcelona.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Cubierto")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                        	frame.lblIconoBarcelona.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Lluvia débil")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                                        	frame.lblIconoBarcelona.setIcon(iconoSoleado);
			                                        }
			                                     
			                                    }else {
			                                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                                    }
			                                        }catch (Exception ex) {
			                                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                                    }
			                                }
			                            }
			                            } catch(Exception ex) {
			                            	ex.printStackTrace();
			                            }
			                        try {
			                    		
			                        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			                            String url = config.getProperty("Tarragona");

			                            // Realizar la solicitud HTTP y procesar la respuesta JSON
			                            if (url != null) {
			                            	 System.out.println("presionado");
			                                String respuesta = realizarSolicitudHTTP(url);

			                                // Procesar la respuesta JSON
			                                if (respuesta != null && !respuesta.isEmpty()) {
			                                    try {
			                                        ObjectMapper objectMapper = new ObjectMapper();
			                                        JsonNode jsonNode = objectMapper.readTree(respuesta);

			                                        // Obtener información del pronóstico
			                                        
			                                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                                      
			                                        // Obtener la fecha seleccionada
			                                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                                     // Buscar el nodo correspondiente a la fecha seleccionada
			                                        JsonNode selectedDayNode = null;
			                                        for (JsonNode dayNode : forecastNode) {
			                                            String fecha = dayNode.path("forecastDate").asText();
			                                            if (fecha.equals(fechaSeleccionada)) {
			                                                selectedDayNode = dayNode;
			                                                break;
			                                            }
			                                        }
			                                        if (selectedDayNode != null) {
			                                        	String fecha = selectedDayNode.path("forecastDate").asText();
			                                        	
			                                        	String estadoTiempo = selectedDayNode.path("weather").asText();
			                                        	
			                                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
			                 
			                                        if(estadoTiempo.equals("Soleado")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                                            frame.lblIconoTarragona.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                        	frame.lblIconoTarragona.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Cubierto")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                        	frame.lblIconoTarragona.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Lluvia débil")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                                        	frame.lblIconoTarragona.setIcon(iconoSoleado);
			                                        }
			                                     
			                                    }else {
			                                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                                    }
			                                        }catch (Exception ex) {
			                                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                                    }
			                                }
			                            }
			                            } catch(Exception ex) {
			                            	ex.printStackTrace();
			                            }
			                        //FIN CATALUNYA
			                       //CASTILLA Y LEON
			                        try {
			                    		
			                        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			                            String url = config.getProperty("Leon");

			                            // Realizar la solicitud HTTP y procesar la respuesta JSON
			                            if (url != null) {
			                            	 System.out.println("presionado");
			                                String respuesta = realizarSolicitudHTTP(url);

			                                // Procesar la respuesta JSON
			                                if (respuesta != null && !respuesta.isEmpty()) {
			                                    try {
			                                        ObjectMapper objectMapper = new ObjectMapper();
			                                        JsonNode jsonNode = objectMapper.readTree(respuesta);

			                                        // Obtener información del pronóstico
			                                        
			                                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                                      
			                                        // Obtener la fecha seleccionada
			                                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                                     // Buscar el nodo correspondiente a la fecha seleccionada
			                                        JsonNode selectedDayNode = null;
			                                        for (JsonNode dayNode : forecastNode) {
			                                            String fecha = dayNode.path("forecastDate").asText();
			                                            if (fecha.equals(fechaSeleccionada)) {
			                                                selectedDayNode = dayNode;
			                                                break;
			                                            }
			                                        }
			                                        if (selectedDayNode != null) {
			                                        	String fecha = selectedDayNode.path("forecastDate").asText();
			                                        	
			                                        	String estadoTiempo = selectedDayNode.path("weather").asText();
			                                        	
			                                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
			                 
			                                        if(estadoTiempo.equals("Soleado")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                                            frame.lblIconoLeon.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                        	frame.lblIconoLeon.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Cubierto")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                        	frame.lblIconoLeon.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Lluvia débil")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                                        	frame.lblIconoLeon.setIcon(iconoSoleado);
			                                        }
			                                     
			                                    }else {
			                                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                                    }
			                                        }catch (Exception ex) {
			                                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                                    }
			                                }
			                            }
			                            } catch(Exception ex) {
			                            	ex.printStackTrace();
			                            }
			                        try {
			                    		
			                        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			                            String url = config.getProperty("Palencia");

			                            // Realizar la solicitud HTTP y procesar la respuesta JSON
			                            if (url != null) {
			                            	 System.out.println("presionado");
			                                String respuesta = realizarSolicitudHTTP(url);

			                                // Procesar la respuesta JSON
			                                if (respuesta != null && !respuesta.isEmpty()) {
			                                    try {
			                                        ObjectMapper objectMapper = new ObjectMapper();
			                                        JsonNode jsonNode = objectMapper.readTree(respuesta);

			                                        // Obtener información del pronóstico
			                                        
			                                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                                      
			                                        // Obtener la fecha seleccionada
			                                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                                     // Buscar el nodo correspondiente a la fecha seleccionada
			                                        JsonNode selectedDayNode = null;
			                                        for (JsonNode dayNode : forecastNode) {
			                                            String fecha = dayNode.path("forecastDate").asText();
			                                            if (fecha.equals(fechaSeleccionada)) {
			                                                selectedDayNode = dayNode;
			                                                break;
			                                            }
			                                        }
			                                        if (selectedDayNode != null) {
			                                        	String fecha = selectedDayNode.path("forecastDate").asText();
			                                        	
			                                        	String estadoTiempo = selectedDayNode.path("weather").asText();
			                                        	
			                                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
			                 
			                                        if(estadoTiempo.equals("Soleado")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                                            frame.lblIconoPalencia.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                        	frame.lblIconoPalencia.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Cubierto")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                        	frame.lblIconoPalencia.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Lluvia débil")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                                        	frame.lblIconoPalencia.setIcon(iconoSoleado);
			                                        }
			                                     
			                                    }else {
			                                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                                    }
			                                        }catch (Exception ex) {
			                                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                                    }
			                                }
			                            }
			                            } catch(Exception ex) {
			                            	ex.printStackTrace();
			                            }
			                        try {
			                    		
			                        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			                            String url = config.getProperty("Burgos");

			                            // Realizar la solicitud HTTP y procesar la respuesta JSON
			                            if (url != null) {
			                            	 System.out.println("presionado");
			                                String respuesta = realizarSolicitudHTTP(url);

			                                // Procesar la respuesta JSON
			                                if (respuesta != null && !respuesta.isEmpty()) {
			                                    try {
			                                        ObjectMapper objectMapper = new ObjectMapper();
			                                        JsonNode jsonNode = objectMapper.readTree(respuesta);

			                                        // Obtener información del pronóstico
			                                        
			                                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                                      
			                                        // Obtener la fecha seleccionada
			                                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                                     // Buscar el nodo correspondiente a la fecha seleccionada
			                                        JsonNode selectedDayNode = null;
			                                        for (JsonNode dayNode : forecastNode) {
			                                            String fecha = dayNode.path("forecastDate").asText();
			                                            if (fecha.equals(fechaSeleccionada)) {
			                                                selectedDayNode = dayNode;
			                                                break;
			                                            }
			                                        }
			                                        if (selectedDayNode != null) {
			                                        	String fecha = selectedDayNode.path("forecastDate").asText();
			                                        	
			                                        	String estadoTiempo = selectedDayNode.path("weather").asText();
			                                        	
			                                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
			                 
			                                        if(estadoTiempo.equals("Soleado")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                                            frame.lblIconoBurgos.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                        	frame.lblIconoBurgos.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Cubierto")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                        	frame.lblIconoBurgos.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Lluvia débil")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                                        	frame.lblIconoBurgos.setIcon(iconoSoleado);
			                                        }
			                                     
			                                    }else {
			                                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                                    }
			                                        }catch (Exception ex) {
			                                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                                    }
			                                }
			                            }
			                            } catch(Exception ex) {
			                            	ex.printStackTrace();
			                            }
			                        try {
			                    		
			                        	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			                            String url = config.getProperty("La_Rioja");

			                            // Realizar la solicitud HTTP y procesar la respuesta JSON
			                            if (url != null) {
			                            	 System.out.println("presionado");
			                                String respuesta = realizarSolicitudHTTP(url);

			                                // Procesar la respuesta JSON
			                                if (respuesta != null && !respuesta.isEmpty()) {
			                                    try {
			                                        ObjectMapper objectMapper = new ObjectMapper();
			                                        JsonNode jsonNode = objectMapper.readTree(respuesta);

			                                        // Obtener información del pronóstico
			                                        
			                                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                                      
			                                        // Obtener la fecha seleccionada
			                                        String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                                     // Buscar el nodo correspondiente a la fecha seleccionada
			                                        JsonNode selectedDayNode = null;
			                                        for (JsonNode dayNode : forecastNode) {
			                                            String fecha = dayNode.path("forecastDate").asText();
			                                            if (fecha.equals(fechaSeleccionada)) {
			                                                selectedDayNode = dayNode;
			                                                break;
			                                            }
			                                        }
			                                        if (selectedDayNode != null) {
			                                        	String fecha = selectedDayNode.path("forecastDate").asText();
			                                        	
			                                        	String estadoTiempo = selectedDayNode.path("weather").asText();
			                                        	
			                                        frame.labelNombreProvincia.setText(provinciaSeleccionada);
			                 
			                                        if(estadoTiempo.equals("Soleado")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                                            frame.lblIconoLaRioja.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                        	frame.lblIconoLaRioja.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Cubierto")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                        	frame.lblIconoLaRioja.setIcon(iconoSoleado);
			                                        }else if(estadoTiempo.equals("Lluvia débil")) {
			                                        	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                                        	frame.lblIconoLaRioja.setIcon(iconoSoleado);
			                                        }
			                                     
			                                    }else {
			                                    	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                                    }
			                                        }catch (Exception ex) {
			                                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                                    }
			                                }
			                            }
			                            } catch(Exception ex) {
			                            	ex.printStackTrace();
			                            }try {
			                        		
			                            	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			                                String url = config.getProperty("Zamora");

			                                // Realizar la solicitud HTTP y procesar la respuesta JSON
			                                if (url != null) {
			                                	 System.out.println("presionado");
			                                    String respuesta = realizarSolicitudHTTP(url);

			                                    // Procesar la respuesta JSON
			                                    if (respuesta != null && !respuesta.isEmpty()) {
			                                        try {
			                                            ObjectMapper objectMapper = new ObjectMapper();
			                                            JsonNode jsonNode = objectMapper.readTree(respuesta);

			                                            // Obtener información del pronóstico
			                                            
			                                            JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                                          
			                                            // Obtener la fecha seleccionada
			                                            String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                                         // Buscar el nodo correspondiente a la fecha seleccionada
			                                            JsonNode selectedDayNode = null;
			                                            for (JsonNode dayNode : forecastNode) {
			                                                String fecha = dayNode.path("forecastDate").asText();
			                                                if (fecha.equals(fechaSeleccionada)) {
			                                                    selectedDayNode = dayNode;
			                                                    break;
			                                                }
			                                            }
			                                            if (selectedDayNode != null) {
			                                            	String fecha = selectedDayNode.path("forecastDate").asText();
			                                            	
			                                            	String estadoTiempo = selectedDayNode.path("weather").asText();
			                                            	
			                                            frame.labelNombreProvincia.setText(provinciaSeleccionada);
			                     
			                                            if(estadoTiempo.equals("Soleado")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                                                frame.lblIconoZamora.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                            	frame.lblIconoZamora.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Cubierto")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                            	frame.lblIconoZamora.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Lluvia débil")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                                            	frame.lblIconoZamora.setIcon(iconoSoleado);
			                                            }
			                                         
			                                        }else {
			                                        	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                                        }
			                                            }catch (Exception ex) {
			                                            ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                                        }
			                                    }
			                                }
			                                } catch(Exception ex) {
			                                	ex.printStackTrace();
			                                }
			                            try {
			                        		
			                            	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			                                String url = config.getProperty("Valladolid");

			                                // Realizar la solicitud HTTP y procesar la respuesta JSON
			                                if (url != null) {
			                                	 System.out.println("presionado");
			                                    String respuesta = realizarSolicitudHTTP(url);

			                                    // Procesar la respuesta JSON
			                                    if (respuesta != null && !respuesta.isEmpty()) {
			                                        try {
			                                            ObjectMapper objectMapper = new ObjectMapper();
			                                            JsonNode jsonNode = objectMapper.readTree(respuesta);

			                                            // Obtener información del pronóstico
			                                            
			                                            JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                                          
			                                            // Obtener la fecha seleccionada
			                                            String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                                         // Buscar el nodo correspondiente a la fecha seleccionada
			                                            JsonNode selectedDayNode = null;
			                                            for (JsonNode dayNode : forecastNode) {
			                                                String fecha = dayNode.path("forecastDate").asText();
			                                                if (fecha.equals(fechaSeleccionada)) {
			                                                    selectedDayNode = dayNode;
			                                                    break;
			                                                }
			                                            }
			                                            if (selectedDayNode != null) {
			                                            	String fecha = selectedDayNode.path("forecastDate").asText();
			                                            	
			                                            	String estadoTiempo = selectedDayNode.path("weather").asText();
			                                            	
			                                            frame.labelNombreProvincia.setText(provinciaSeleccionada);
			                     
			                                            if(estadoTiempo.equals("Soleado")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                                                frame.lblIconoValladolid.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                            	frame.lblIconoValladolid.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Cubierto")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                            	frame.lblIconoValladolid.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Lluvia débil")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                                            	frame.lblIconoValladolid.setIcon(iconoSoleado);
			                                            }
			                                         
			                                        }else {
			                                        	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                                        }
			                                            }catch (Exception ex) {
			                                            ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                                        }
			                                    }
			                                }
			                                } catch(Exception ex) {
			                                	ex.printStackTrace();
			                                }
			                            try {
			                        		
			                            	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			                                String url = config.getProperty("Soria");

			                                // Realizar la solicitud HTTP y procesar la respuesta JSON
			                                if (url != null) {
			                                	 System.out.println("presionado");
			                                    String respuesta = realizarSolicitudHTTP(url);

			                                    // Procesar la respuesta JSON
			                                    if (respuesta != null && !respuesta.isEmpty()) {
			                                        try {
			                                            ObjectMapper objectMapper = new ObjectMapper();
			                                            JsonNode jsonNode = objectMapper.readTree(respuesta);

			                                            // Obtener información del pronóstico
			                                            
			                                            JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                                          
			                                            // Obtener la fecha seleccionada
			                                            String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                                         // Buscar el nodo correspondiente a la fecha seleccionada
			                                            JsonNode selectedDayNode = null;
			                                            for (JsonNode dayNode : forecastNode) {
			                                                String fecha = dayNode.path("forecastDate").asText();
			                                                if (fecha.equals(fechaSeleccionada)) {
			                                                    selectedDayNode = dayNode;
			                                                    break;
			                                                }
			                                            }
			                                            if (selectedDayNode != null) {
			                                            	String fecha = selectedDayNode.path("forecastDate").asText();
			                                            	
			                                            	String estadoTiempo = selectedDayNode.path("weather").asText();
			                                            	
			                                            frame.labelNombreProvincia.setText(provinciaSeleccionada);
			                     
			                                            if(estadoTiempo.equals("Soleado")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                                                frame.lblIconoSoria.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                            	frame.lblIconoSoria.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Cubierto")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                            	frame.lblIconoSoria.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Lluvia débil")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                                            	frame.lblIconoSoria.setIcon(iconoSoleado);
			                                            }
			                                         
			                                        }else {
			                                        	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                                        }
			                                            }catch (Exception ex) {
			                                            ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                                        }
			                                    }
			                                }
			                                } catch(Exception ex) {
			                                	ex.printStackTrace();
			                                }
			                            try {
			                        		
			                            	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			                                String url = config.getProperty("Salamanca");

			                                // Realizar la solicitud HTTP y procesar la respuesta JSON
			                                if (url != null) {
			                                	 System.out.println("presionado");
			                                    String respuesta = realizarSolicitudHTTP(url);

			                                    // Procesar la respuesta JSON
			                                    if (respuesta != null && !respuesta.isEmpty()) {
			                                        try {
			                                            ObjectMapper objectMapper = new ObjectMapper();
			                                            JsonNode jsonNode = objectMapper.readTree(respuesta);

			                                            // Obtener información del pronóstico
			                                            
			                                            JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                                          
			                                            // Obtener la fecha seleccionada
			                                            String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                                         // Buscar el nodo correspondiente a la fecha seleccionada
			                                            JsonNode selectedDayNode = null;
			                                            for (JsonNode dayNode : forecastNode) {
			                                                String fecha = dayNode.path("forecastDate").asText();
			                                                if (fecha.equals(fechaSeleccionada)) {
			                                                    selectedDayNode = dayNode;
			                                                    break;
			                                                }
			                                            }
			                                            if (selectedDayNode != null) {
			                                            	String fecha = selectedDayNode.path("forecastDate").asText();
			                                            	
			                                            	String estadoTiempo = selectedDayNode.path("weather").asText();
			                                            	
			                                            frame.labelNombreProvincia.setText(provinciaSeleccionada);
			                     
			                                            if(estadoTiempo.equals("Soleado")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                                                frame.lblIconoSalamanca.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                            	frame.lblIconoSalamanca.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Cubierto")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                            	frame.lblIconoSalamanca.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Lluvia débil")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                                            	frame.lblIconoSalamanca.setIcon(iconoSoleado);
			                                            }
			                                         
			                                        }else {
			                                        	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                                        }
			                                            }catch (Exception ex) {
			                                            ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                                        }
			                                    }
			                                }
			                                } catch(Exception ex) {
			                                	ex.printStackTrace();
			                                }
			                            try {
			                        		
			                            	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			                                String url = config.getProperty("Avila");

			                                // Realizar la solicitud HTTP y procesar la respuesta JSON
			                                if (url != null) {
			                                	 System.out.println("presionado");
			                                    String respuesta = realizarSolicitudHTTP(url);

			                                    // Procesar la respuesta JSON
			                                    if (respuesta != null && !respuesta.isEmpty()) {
			                                        try {
			                                            ObjectMapper objectMapper = new ObjectMapper();
			                                            JsonNode jsonNode = objectMapper.readTree(respuesta);

			                                            // Obtener información del pronóstico
			                                            
			                                            JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                                          
			                                            // Obtener la fecha seleccionada
			                                            String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                                         // Buscar el nodo correspondiente a la fecha seleccionada
			                                            JsonNode selectedDayNode = null;
			                                            for (JsonNode dayNode : forecastNode) {
			                                                String fecha = dayNode.path("forecastDate").asText();
			                                                if (fecha.equals(fechaSeleccionada)) {
			                                                    selectedDayNode = dayNode;
			                                                    break;
			                                                }
			                                            }
			                                            if (selectedDayNode != null) {
			                                            	String fecha = selectedDayNode.path("forecastDate").asText();
			                                            	
			                                            	String estadoTiempo = selectedDayNode.path("weather").asText();
			                                            	
			                                            frame.labelNombreProvincia.setText(provinciaSeleccionada);
			                     
			                                            if(estadoTiempo.equals("Soleado")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                                                frame.lblIconoAvila.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                            	frame.lblIconoAvila.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Cubierto")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                            	frame.lblIconoAvila.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Lluvia débil")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                                            	frame.lblIconoAvila.setIcon(iconoSoleado);
			                                            }
			                                         
			                                        }else {
			                                        	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                                        }
			                                            }catch (Exception ex) {
			                                            ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                                        }
			                                    }
			                                }
			                                } catch(Exception ex) {
			                                	ex.printStackTrace();
			                                }
			                            try {
			                        		
			                            	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			                                String url = config.getProperty("Segovia");

			                                // Realizar la solicitud HTTP y procesar la respuesta JSON
			                                if (url != null) {
			                                	 System.out.println("presionado");
			                                    String respuesta = realizarSolicitudHTTP(url);

			                                    // Procesar la respuesta JSON
			                                    if (respuesta != null && !respuesta.isEmpty()) {
			                                        try {
			                                            ObjectMapper objectMapper = new ObjectMapper();
			                                            JsonNode jsonNode = objectMapper.readTree(respuesta);

			                                            // Obtener información del pronóstico
			                                            
			                                            JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                                          
			                                            // Obtener la fecha seleccionada
			                                            String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                                         // Buscar el nodo correspondiente a la fecha seleccionada
			                                            JsonNode selectedDayNode = null;
			                                            for (JsonNode dayNode : forecastNode) {
			                                                String fecha = dayNode.path("forecastDate").asText();
			                                                if (fecha.equals(fechaSeleccionada)) {
			                                                    selectedDayNode = dayNode;
			                                                    break;
			                                                }
			                                            }
			                                            if (selectedDayNode != null) {
			                                            	String fecha = selectedDayNode.path("forecastDate").asText();
			                                            	
			                                            	String estadoTiempo = selectedDayNode.path("weather").asText();
			                                            	
			                                            frame.labelNombreProvincia.setText(provinciaSeleccionada);
			                     
			                                            if(estadoTiempo.equals("Soleado")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                                                frame.lblIconoSegovia.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                            	frame.lblIconoSegovia.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Cubierto")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                            	frame.lblIconoSegovia.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Lluvia débil")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                                            	frame.lblIconoSegovia.setIcon(iconoSoleado);
			                                            }
			                                         
			                                        }else {
			                                        	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                                        }
			                                            }catch (Exception ex) {
			                                            ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                                        }
			                                    }
			                                }
			                                } catch(Exception ex) {
			                                	ex.printStackTrace();
			                                }
			                            
			                            try {
			                        		
			                            	config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			                                String url = config.getProperty("Asturias");

			                                // Realizar la solicitud HTTP y procesar la respuesta JSON
			                                if (url != null) {
			                                	 System.out.println("presionado");
			                                    String respuesta = realizarSolicitudHTTP(url);

			                                    // Procesar la respuesta JSON
			                                    if (respuesta != null && !respuesta.isEmpty()) {
			                                        try {
			                                            ObjectMapper objectMapper = new ObjectMapper();
			                                            JsonNode jsonNode = objectMapper.readTree(respuesta);

			                                            // Obtener información del pronóstico
			                                            
			                                            JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay");
			                                          
			                                            // Obtener la fecha seleccionada
			                                            String fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
			                                         // Buscar el nodo correspondiente a la fecha seleccionada
			                                            JsonNode selectedDayNode = null;
			                                            for (JsonNode dayNode : forecastNode) {
			                                                String fecha = dayNode.path("forecastDate").asText();
			                                                if (fecha.equals(fechaSeleccionada)) {
			                                                    selectedDayNode = dayNode;
			                                                    break;
			                                                }
			                                            }
			                                            if (selectedDayNode != null) {
			                                            	String fecha = selectedDayNode.path("forecastDate").asText();
			                                            	
			                                            	String estadoTiempo = selectedDayNode.path("weather").asText();
			                                            	
			                                            frame.labelNombreProvincia.setText(provinciaSeleccionada);
			                     
			                                            if(estadoTiempo.equals("Soleado")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/sol.png"));
			                                                frame.lblIconoAsturias.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Parcialmente nuboso")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                            	frame.lblIconoAsturias.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Cubierto")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/nube.png"));
			                                            	frame.lblIconoAsturias.setIcon(iconoSoleado);
			                                            }else if(estadoTiempo.equals("Lluvia débil")) {
			                                            	ImageIcon iconoSoleado = new ImageIcon(getClass().getResource("/img/lluvia.png"));
			                                            	frame.lblIconoAsturias.setIcon(iconoSoleado);
			                                            }
			                                         
			                                        }else {
			                                        	 System.err.println("Error: No se pudo encontrar el nodo para la fecha seleccionada.");
			                                        }
			                                            }catch (Exception ex) {
			                                            ex.printStackTrace();  // Manejar errores de procesamiento JSON
			                                        }
			                                    }
			                                }
			                                } catch(Exception ex) {
			                                	ex.printStackTrace();
			                                }
	
	}
	private void obtenerFechas(String provinciaSeleccionada) {
		System.out.println("Entrando al metodo");
		// Obtener el nodo de pronóstico
		JsonNode forecastNode = obtenerForecastNode(provinciaSeleccionada);

		// Verificar si el nodo de pronóstico no es nulo
		if (forecastNode != null) {
		    // Limpiar el comboBox antes de agregar nuevas fechas
		    frame.boxDia.removeAllItems();

		    // Iterar sobre las fechas en el nodo de pronóstico
		    for (JsonNode dayNode : forecastNode) {
		        // Obtener la fecha del nodo actual
		        String fecha = dayNode.path("forecastDate").asText();

		        // Agregar la fecha al comboBox
		        frame.boxDia.addItem(fecha);
		    }
		} else {
		    // Manejar el caso en que el nodo de pronóstico es nulo
		    System.err.println("Error: No se pudo obtener el nodo de pronóstico.");
		}

		
	}
	private JsonNode obtenerForecastNode(String provinciaSeleccionada) {
		System.out.println("haciendo obtenerForecastNode");
		try {
			config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
			 String url = config.getProperty(provinciaSeleccionada);
			 if (url != null) {
                String respuesta = realizarSolicitudHTTP(url);

                // Procesar la respuesta JSON
                if (respuesta != null && !respuesta.isEmpty()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(respuesta);

                        // Obtener información del pronóstico
                        
                        return jsonNode.path("city").path("forecast").path("forecastDay");
                    } catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
         
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	private String realizarSolicitudHTTP(String url) {
		try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();  // Manejar errores de conexión
        }

        return null;
    }
	
    }

    