package Modelo;
import java.util.ArrayList;
import java.util.List;

import Controlador.controlador;

import Vista.vista;
import resource.configuracion;


public class modelo {
    public static void main(String[] args) {
       
        configuracion Configuracion = new configuracion();
        vista vista = new vista();
        controlador controlador = new controlador(vista,Configuracion);
        List<String> fechas;
        // Llamar al método main para procesar la información climatológica
        Configuracion.main(null);
        fechas = configuracion.devolverFechas();
        vista.fechasComboBOXDIA(fechas);
        vista.main(null);
        
    }
}

