package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vista.vista;

public class controlador implements ActionListener{

	vista frame = new vista();
	
	public controlador(vista frame) {
		this.frame= frame;
		this.frame.boton.addActionListener(this);
		this.frame.boxCA.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
