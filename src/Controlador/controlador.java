package Controlador;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.Date;
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
        this.frame.boxDia.addActionListener(this);
   
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
        }  
     
    }

    public void mostrarInformacion(String ciudad) {
        String informacion = configuracion.obtenerInformacionCiudad(ciudad);
        Double tMin = configuracion.devolverTMin();
        String tempMin = String.valueOf(tMin);
        
        Double tMax = configuracion.devolverTMax();
        String tempMax = String.valueOf(tMax);
        if (informacion != null) {
           frame.labelNombreProvincia.setText(ciudad);
           frame.labelTMin.setText(tempMin);
           frame.labelTMax.setText(tempMax);
        } else {
            System.out.println("Información no encontrada para la ciudad " + ciudad + " en la fecha " + fechaSeleccionada);
        }
    }


public String getProvincia() {
    return provincia;
}
}