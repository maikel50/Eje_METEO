package resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class configuracion {

    public static void main(String[] args) {
        Properties configuracion = new Properties();

        try {
            // Cargar el archivo de configuración
        	configuracion.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));


            // Obtener la información climatológica para una ciudad específica (en este caso, Ciudad Real)
        	String nombreCiudad = configuracion.getProperty("ciudad");
        	String direccionWeb = configuracion.getProperty("web");


            if (direccionWeb != null) {
                String informacionClimatologica = obtenerInformacionClimatologica(direccionWeb);

                // Procesar la información climatológica y mostrar el pronóstico diario
                procesarInformacionClimatologica(informacionClimatologica);
            } else {
                System.out.println("La ciudad no está configurada en el archivo.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String obtenerInformacionClimatologica(String direccionWeb) {
        StringBuilder respuesta = new StringBuilder();
        BufferedReader leer;

        try {
            // Crear URL
            URL url = new URL(direccionWeb);

            // Abrir conexión HTTP
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

            // Configurar la solicitud
            conexion.setRequestMethod("GET");

            // Obtener la respuesta
            try {
                leer = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String linea;
                while ((linea = leer.readLine()) != null) {
                    respuesta.append(linea);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Cerrar la conexión
                conexion.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return respuesta.toString();
    }

    private static void procesarInformacionClimatologica(String informacionClimatologica) {
        try {
            // Parsear el JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(informacionClimatologica);

            // Obtener el nodo del pronóstico
            JsonNode forecastNode = rootNode.path("city").path("forecast").path("forecastDay");

            // Iterar sobre cada día del pronóstico
            for (JsonNode dayNode : forecastNode) {
                String fecha = dayNode.path("forecastDate").asText();
                String estadoTiempo = dayNode.path("weather").asText();
                double tempMin = dayNode.path("minTemp").asDouble();
                double tempMax = dayNode.path("maxTemp").asDouble();

                // Imprimir la información del pronóstico diario
                System.out.println("Fecha: " + fecha);
                System.out.println("Estado del tiempo: " + estadoTiempo);
                System.out.println("Temperatura mínima: " + tempMin);
                System.out.println("Temperatura máxima: " + tempMax);
                System.out.println("---------------------");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





