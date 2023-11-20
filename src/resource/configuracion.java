package resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Modelo.datos;
import Vista.vista;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class configuracion {
    private static List<String> fechasProcesadas = new ArrayList<>();

    public static void main(String[] args) {
        Properties configuracion = new Properties();
        
        try {

            configuracion.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));

            Set<String> ciudades = configuracion.stringPropertyNames();


            for (String ciudad : ciudades) {
                String direccionWeb = configuracion.getProperty(ciudad);
                if (direccionWeb != null) {

                    System.out.println(obtenerNombreCiudad(ciudad));

                    String informacionClimatologica = obtenerInformacionClimatologica(direccionWeb);
                    procesarInformacionClimatologica(informacionClimatologica);
                    System.out.println("---------------------");
                } else {
                    System.out.println("ERROR");
                }
            }

            
             devolverFechas();
          

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String obtenerInformacionClimatologica(String direccionWeb) {
        StringBuilder respuesta = new StringBuilder();

        try {
            // Crear URL
            URL url = new URL(direccionWeb);

            // Abrir conexión HTTP
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

            // Configurar la solicitud
            conexion.setRequestMethod("GET");

            // Verificar si la solicitud fue exitosa
            int codigoRespuesta = conexion.getResponseCode();
            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {

                try (BufferedReader leer = new BufferedReader(new InputStreamReader(conexion.getInputStream()))) {
                    String linea;
                    while ((linea = leer.readLine()) != null) {
                        respuesta.append(linea);
                    }
                }
            } else {
                System.out.println("Error en la solicitud HTTP. Código de respuesta: " + codigoRespuesta);
                return null;
            }
        } catch (MalformedURLException e) {
            System.out.println("URL malformada: " + direccionWeb);
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return respuesta.toString();
    }


    private static void procesarInformacionClimatologica(String informacionClimatologica) {
        String fecha, estadoTiempo;
        double tempMin, tempMax;
        datos datos;
      
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(informacionClimatologica);

            JsonNode forecastNode = rootNode.path("city").path("forecast").path("forecastDay");
            if (forecastNode.isArray()) {
                for (JsonNode dayNode : forecastNode) {
                    fecha = dayNode.path("forecastDate").asText();
                    estadoTiempo = dayNode.path("weather").asText();
                    tempMin = dayNode.path("minTemp").asDouble();
                    tempMax = dayNode.path("maxTemp").asDouble();
                    
                    datos = new datos()
                    // Agregar la fecha a la lista
                    fechasProcesadas.add(fecha);
                    Map<String, datos> mapa = new HashMap<>();
                    
                    System.out.println(obtenerFecha(fecha));
                    System.out.println(obtenerEstadoDelTiempo(estadoTiempo));
                    System.out.println(obtenerTemperaturaMinima(tempMin));
                    System.out.println(obtenerTemperaturaMaxima(tempMax));
                    
                    
                }
            } else {
                System.out.println("Estructura JSON incorrecta. No se encontró el nodo del pronóstico.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
   
    
 
    public static List<String> devolverFechas() {
        return fechasProcesadas;
    }


    public static String obtenerFecha(String fecha) {
        return fecha;
    }

    public static String obtenerNombreCiudad(String ciudad) {
        return ciudad;
    }

    public static Double obtenerTemperaturaMinima(double tempMin) {
        return tempMin;
    }

    public static Double obtenerTemperaturaMaxima(double tempMax) {
        return tempMax;
    }

    public static String obtenerEstadoDelTiempo(String estadoTiempo) {
        return estadoTiempo;
    }
    
    
}

