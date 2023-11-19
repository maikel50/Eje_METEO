package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Vista.vista;

public class controlador implements ActionListener{

	vista frame;
	
	public controlador(vista frame) {
		this.frame= frame;
		this.frame.boton.addActionListener(this);
		this.frame.boxCA.addActionListener(this);
		this.frame.boxProvincia.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == frame.boxCA) {
	       
	    }
	}






}
