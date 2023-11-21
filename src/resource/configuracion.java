package resource;

import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;

import Controlador.controlador;
import Modelo.datos;
import Vista.vista;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import java.util.*;
import java.io.*;
import java.net.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class configuracion {
    private static Map<String, List<Double>> temperaturasPorCiudad = new LinkedHashMap<>();
    private static List<String> ciudades;
    private static List<String> fechasProcesadas = new ArrayList<>();

    public static void main(String[] args) {
        Properties config = new Properties();
        try {
            config.load(new InputStreamReader(new FileInputStream("src/resource/config.properties"), "UTF-8"));
            
            ciudades = new ArrayList<>(config.stringPropertyNames());
            LinkedHashMap<String, String> ciudadesMap = new LinkedHashMap<>();

            for (Map.Entry<Object, Object> entry : config.entrySet()) {
                ciudadesMap.put((String) entry.getKey(), (String) entry.getValue());
            }

            // Recorrer las ciudades en el orden correcto
            for (Map.Entry<String, String> entry : ciudadesMap.entrySet()) {
                String ciudad = entry.getKey();
                String direccionWeb = entry.getValue();

                if (direccionWeb != null) {
                    direccionWeb = direccionWeb.trim();
                    String informacionClimatologica = obtenerInformacionClimatologica(direccionWeb);
                    procesarInformacionClimatologica(ciudad, informacionClimatologica);

                    System.out.println("---------------------");
                    String informacionCiudad = obtenerInformacionCiudad(ciudad);
                    System.out.println("Información de la ciudad: " + informacionCiudad);
                } else {
                    System.out.println("ERROR");
                }
            }

            devolverFechas();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String obtenerInformacionCiudad(String ciudad) {
        for (String info : ciudades) {
            if (info.contains(ciudad)) {
                return info;
            }
        }
        return "Información no encontrada para la ciudad: " + ciudad;
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
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    conexion.disconnect(); // Cerrar la conexión HTTP
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

    private static void procesarInformacionClimatologica(String ciudad, String informacionClimatologica) {
        String fecha;
        double tempMin, tempMax;
        ArrayList temperaturasMinimas = new ArrayList<>();
        ArrayList temperaturasMaximas = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(informacionClimatologica);

            JsonNode forecastNode = rootNode.path("city").path("forecast").path("forecastDay");
            if (forecastNode.isArray()) {
                for (JsonNode dayNode : forecastNode) {
                    fecha = dayNode.path("forecastDate").asText();
                    tempMin = dayNode.path("minTemp").asDouble();
                    tempMax = dayNode.path("maxTemp").asDouble();

                    // Agregar la fecha a la lista
                    fechasProcesadas.add(fecha);

                    // Almacenar temperaturas por ciudad
                    temperaturasPorCiudad.computeIfAbsent(ciudad, k -> new ArrayList<>()).add(tempMin);
                    temperaturasPorCiudad.computeIfAbsent(ciudad, k -> new ArrayList<>()).add(tempMax);
                    
                    temperaturasMinimas.add(tempMin);
                    temperaturasMaximas.add(tempMax);
                    System.out.println(obtenerFecha(fecha));
                    System.out.println(devolverTMin(ciudad, fecha));
                    System.out.println(devolverTMax(ciudad, fecha));
                }
            } else {
                System.out.println("Estructura JSON incorrecta. No se encontró el nodo del pronóstico.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Double devolverTMin(String ciudad, String fecha) {
        int indexCiudad = ciudades.indexOf(ciudad);
        int indexFecha = fechasProcesadas.indexOf(fecha);

        if (indexCiudad != -1 && indexFecha != -1) {
            int indexTemperatura = indexFecha + indexCiudad * fechasProcesadas.size();
            return temperaturasPorCiudad.get(ciudad).get(indexTemperatura);
        } else {
            System.out.println("Información no encontrada para la ciudad: " + ciudad + " y fecha: " + fecha);
            return null;
        }
    }

    public static Double devolverTMax(String ciudad, String fecha) {
        int indexCiudad = ciudades.indexOf(ciudad);
        int indexFecha = fechasProcesadas.indexOf(fecha);

        if (indexCiudad != -1 && indexFecha != -1) {
            int indexTemperatura = indexFecha + indexCiudad * fechasProcesadas.size() + fechasProcesadas.size();
            return temperaturasPorCiudad.get(ciudad).get(indexTemperatura);
        } else {
            System.out.println("Información no encontrada para la ciudad: " + ciudad + " y fecha: " + fecha);
            return null;
        }
    }

    public static List<String> devolverCiudades() {
        return ciudades;
    }

    public static List<String> devolverFechas() {
        return fechasProcesadas;
    }

    public static String obtenerFecha(String fecha) {
        return fecha;
    }
}
