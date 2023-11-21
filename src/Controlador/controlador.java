package Controlador;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Vista.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        this.frame.boxDia.addActionListener(this);
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
        }
        if(e.getSource() == frame.boxDia) {
        	
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
                        
                        JsonNode forecastNode = jsonNode.path("city").path("forecast").path("forecastDay").get(0);
                        String fecha = forecastNode.path("forecastDate").asText();
                        String minTemp = forecastNode.path("minTemp").asText();
                        String maxTemp = forecastNode.path("maxTemp").asText();
                        String estadoTiempo = forecastNode.path("weather").asText();

                        // Imprimir la información
                        System.out.println("Temperatura Mínima: " + minTemp);
                        System.out.println("Temperatura Máxima: " + maxTemp);
                        System.out.println("Estado del Tiempo: " + estadoTiempo);
                    } catch (Exception ex) {
                        ex.printStackTrace();  // Manejar errores de procesamiento JSON
                    }
                }
            }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
            
        }
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

    