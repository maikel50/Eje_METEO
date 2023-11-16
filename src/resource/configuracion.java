package resource;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;




public class configuracion {
	public static void main(String[] args) {
	Properties configuracion = new Properties();
	
	try {
		configuracion.load(new FileReader("src/resource/config.properties"));
		tiempoCiudad(configuracion);
	}catch(Exception e) {
		e.printStackTrace();	}
	
	}

	

	private static void tiempoCiudad(Properties configuracion) {
		 String ciudad = configuracion.getProperty("nombre");
	     String web = configuracion.getProperty("web");

	     // Realizar la solicitud a la dirección web y obtener la respuesta JSON
	     String respuestaJson = obtenerRespuestaWeb(web);
	     //Obtener informacion del tiempo
	    
	}



	private static String obtenerRespuestaWeb(String web) {
		StringBuilder respuesta = new StringBuilder();
		BufferedReader leer;
	    try {
	        // Crear URL
	        URL url = new URL(web);

	        // Abrir conexión HTTP
	        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

	        // Configurar la solicitud
	        conexion.setRequestMethod("GET");

	        // Obtener la respuesta
	        try  {
	        	leer =  new BufferedReader(new InputStreamReader(conexion.getInputStream()));
	            String linea;
	            while ((linea = leer.readLine()) != null) {
	                respuesta.append(linea);
	            }

	        // Cerrar la conexión
	        conexion.disconnect();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	    catch(Exception e) {
	    	e.printStackTrace();
	    }
	    return respuesta.toString();
}
}
