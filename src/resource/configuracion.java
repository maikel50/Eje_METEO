package resource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class configuracion {

private Properties properties;

public configuracion () {
	this.properties=properties;
	cargarConfiguracion();
}

private  void cargarConfiguracion() {
	FileInputStream input;
	try {
		input = new FileInputStream("src/resources/config.properties");
		properties.load(input);
	}catch(IOException e) {
		e.printStackTrace();
	}
}

public String obtenerValor(String clave) {
    return properties.getProperty(clave);
}

public void establecerValor(String clave, String valor) {
    properties.setProperty(clave, valor);
    guardarConfiguracion();
}

private void guardarConfiguracion() {
    try (FileOutputStream output = new FileOutputStream("src/resources/config.properties")) {
        properties.store(output, null);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
