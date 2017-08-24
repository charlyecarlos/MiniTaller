package es.charlye.coches.Modelo;

public class ModeloAlerta {

	private String nombre_prop;
	private String telefono;
	private String email;
	private String marca;
	private String modelo;
	private String nombre_alarma;
	private String fecha;
	private String km;
	public ModeloAlerta(String nombre_prop, String telefono, String email, String marca, String modelo,
			String nombre_alarma, String fecha, String km) {
		super();
		this.nombre_prop = nombre_prop;
		this.telefono = telefono;
		this.email = email;
		this.marca = marca;
		this.modelo = modelo;
		this.nombre_alarma = nombre_alarma;
		this.fecha = fecha;
		this.km = km;
	}
	@Override
	public String toString() {
		return "ModeloAlerta [nombre_prop=" + nombre_prop + ", telefono=" + telefono + ", email=" + email + ", marca="
				+ marca + ", modelo=" + modelo + ", nombre_alarma=" + nombre_alarma + ", fecha=" + fecha + ", km=" + km
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((km == null) ? 0 : km.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((nombre_alarma == null) ? 0 : nombre_alarma.hashCode());
		result = prime * result + ((nombre_prop == null) ? 0 : nombre_prop.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
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
		ModeloAlerta other = (ModeloAlerta) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (km == null) {
			if (other.km != null)
				return false;
		} else if (!km.equals(other.km))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (nombre_alarma == null) {
			if (other.nombre_alarma != null)
				return false;
		} else if (!nombre_alarma.equals(other.nombre_alarma))
			return false;
		if (nombre_prop == null) {
			if (other.nombre_prop != null)
				return false;
		} else if (!nombre_prop.equals(other.nombre_prop))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}
	public String getNombre_prop() {
		return nombre_prop;
	}
	public void setNombre_prop(String nombre_prop) {
		this.nombre_prop = nombre_prop;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getNombre_alarma() {
		return nombre_alarma;
	}
	public void setNombre_alarma(String nombre_alarma) {
		this.nombre_alarma = nombre_alarma;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getKm() {
		return km;
	}
	public void setKm(String km) {
		this.km = km;
	}
}
