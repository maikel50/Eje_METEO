package Modelo;

public class datos {
	private String ciudad;
	private String tiempo;
	private double tempMin;
	private double tempMax;
	
	public datos(String ciudad, String tiempo, double tempMin, double tempMax){
		this.ciudad = ciudad;
		this.tiempo = tiempo;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public double getTempMin() {
		return tempMin;
	}

	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
	}

	public double getTempMax() {
		return tempMax;
	}

	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}

	@Override
	public String toString() {
		return "datos [ciudad=" + ciudad + ", tiempo=" + tiempo + ", tempMin=" + tempMin + ", tempMax=" + tempMax + "]";
	}
	
	
}
