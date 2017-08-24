package es.charlye.coches.Modelo;

public class Alarma {

	private Long id_vehi;
	private String nombre;
	private String fecha;
	private int km_actual;
	
	public Alarma(Long id_vehi,String nombre,String fecha,int km_actual) {
		super();
		this.id_vehi = id_vehi;
		this.nombre = nombre;
		this.fecha = fecha;
		this.km_actual = km_actual;
	}

	@Override
	public String toString() {
		return "Alarma [id_vehi=" + id_vehi + ", nombre=" + nombre + ", fecha=" + fecha + ", km_actual=" + km_actual
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((id_vehi == null) ? 0 : id_vehi.hashCode());
		result = prime * result + km_actual;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alarma other = (Alarma) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (id_vehi == null) {
			if (other.id_vehi != null)
				return false;
		} else if (!id_vehi.equals(other.id_vehi))
			return false;
		if (km_actual != other.km_actual)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	public Long getId_vehi() {
		return id_vehi;
	}

	public void setId_vehi(Long id_vehi) {
		this.id_vehi = id_vehi;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getKm_actual() {
		return km_actual;
	}

	public void setKm_actual(int km_actual) {
		this.km_actual = km_actual;
	}
}
