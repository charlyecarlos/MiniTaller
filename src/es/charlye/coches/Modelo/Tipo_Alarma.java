package es.charlye.coches.Modelo;

public class Tipo_Alarma {
	
	private String nombre;
	private int meses_exp;
	private Integer km_exp;
	
	public Tipo_Alarma(String nombre, int meses_exp,Integer km_exp) {
		super();
		this.nombre = nombre;
		this.meses_exp = meses_exp;
		this.km_exp = km_exp;
	}

	@Override
	public String toString() {
		return "Tipo_Alarma [nombre=" + nombre + ", meses_exp=" + meses_exp + ", km_exp=" + km_exp + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((km_exp == null) ? 0 : km_exp.hashCode());
		result = prime * result + meses_exp;
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
		Tipo_Alarma other = (Tipo_Alarma) obj;
		if (km_exp == null) {
			if (other.km_exp != null)
				return false;
		} else if (!km_exp.equals(other.km_exp))
			return false;
		if (meses_exp != other.meses_exp)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getMeses_exp() {
		return meses_exp;
	}

	public void setMeses_exp(int meses_exp) {
		this.meses_exp = meses_exp;
	}

	public Integer getKm_exp() {
		return km_exp;
	}

	public void setKm_exp(Integer km_exp) {
		this.km_exp = km_exp;
	}
}
