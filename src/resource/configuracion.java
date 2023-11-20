package resource;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class configuracion {

    public static void main(String[] args) {
        Properties configuracion = new Properties();
        int n=1;
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
                    System.out.println("La ciudad " + ciudad + " no tiene URL configurada.");
                }
            }

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
       double tempMin,tempMax;
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
    public static String obtenerFecha(String fecha) {
		
		return fecha;
	}

	public static String obtenerNombreCiudad(String ciudad) {
        return  ciudad;
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
