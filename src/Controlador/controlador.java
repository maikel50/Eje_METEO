package Controlador;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import resource.configuracion;
import Vista.vista;

public class controlador implements ActionListener {

    vista frame;
    configuracion configuracion; // Asegúrate de tener una instancia de configuracion
    public String provincia;
    public List<String> ciudadesConfig;
    String fechaSeleccionada;
    public controlador(vista frame, configuracion configuracion) {
        this.frame = frame;
        this.configuracion = configuracion;
        this.frame.boton.addActionListener(this);
        this.frame.boxCA.addActionListener(this);
        this.frame.boxProvincia.addActionListener(this);
   
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frame.boxProvincia) {
            provincia = (String) frame.boxProvincia.getSelectedItem();
        }
       if (e.getSource() == frame.boton) {
        	
            Object objetoCiudad = frame.boxProvincia.getSelectedItem();
            ciudadesConfig = configuracion.devolverCiudades();

            if (objetoCiudad != null) {
                String ciudad = objetoCiudad.toString();
                System.out.println("Texto seleccionado: " + ciudad);

               
                if (ciudadesConfig.contains(ciudad)) {
                	
                    mostrarInformacion(ciudad);
                    
                } else {
                    System.out.println("La ciudad no está en la lista.");
                }
            } else {
                System.out.println("Error: No se seleccionó ninguna ciudad.");
            }
        } else if (e.getSource() == frame.boxDia) {
            
             fechaSeleccionada = (String) frame.boxDia.getSelectedItem();
            System.out.println("Fecha seleccionada: " + fechaSeleccionada);
        }
    }




public void mostrarInformacion(String ciudad) {
		String informacion = configuracion.obtenerInformacionCiudad(ciudad,fechaSeleccionada);
		if(informacion != null) {
			frame.labelNombreProvincia.setText(ciudad);
			frame.labelTMax.setText(informacion);
		}
		
	}

public String getProvincia() {
    return provincia;
}
}