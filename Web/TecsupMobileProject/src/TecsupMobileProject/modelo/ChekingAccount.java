package TecsupMobileProject.modelo;


import polideportivo.modelo.Reserva;

public class Cancha {
	private int idCancha;
	private Reserva reserva;
	private String nombre;
	private int estado;
	
	public Cancha() {
		super();
		this.idCancha = 0;
		this.reserva = null;
		this.nombre = "";
		this.estado = 0;
		
	}
	public Cancha(int idCancha, Reserva categoria, String nombre,int estado) {
		super();
		this.idCancha = idCancha;
		this.reserva = categoria;
		this.nombre = nombre;
		this.estado = estado;
	}
	public int getIdCancha() {
		return idCancha;
	}
	public void setIdCancha(int idCancha) {
		this.idCancha = idCancha;
	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
}