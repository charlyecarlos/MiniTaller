package es.charlye.coches.Modelo;

public class Averia {

	private Long id_averia=null;
	private Long id_vehi=null;
	private String fecha_averia;
	private String comen_averia;
	private double precio_repuesto;
	private double precio_cobrado;
	private int km;

	public Averia(Long id_averia, Long id_vehi, String fecha_averia, String comen_averia, double precio_repuesto,
			double precio_cobrado,int km) {
		super();
		this.id_averia = id_averia;
		this.id_vehi = id_vehi;
		this.fecha_averia = fecha_averia;
		this.comen_averia = comen_averia;
		this.precio_repuesto = precio_repuesto;
		this.precio_cobrado = precio_cobrado;
		this.km=km;
	}

	public Averia(Long id_vehi, String fecha_averia, String comen_averia, double precio_repuesto,
			double precio_cobrado,int km) {
		super();
		this.id_vehi = id_vehi;
		this.fecha_averia = fecha_averia;
		this.comen_averia = comen_averia;
		this.precio_repuesto = precio_repuesto;
		this.precio_cobrado = precio_cobrado;
		this.km=km;
	}



	public Long getId_averia() {
		return id_averia;
	}

	public void setId_averia(Long id_averia) {
		this.id_averia = id_averia;
	}

	public Long getId_vehi() {
		return id_vehi;
	}

	public void setId_vehi(Long id_vehi) {
		this.id_vehi = id_vehi;
	}

	public String getFecha_averia() {
		return fecha_averia;
	}

	public void setFecha_averia(String fecha_averia) {
		this.fecha_averia = fecha_averia;
	}

	public String getComen_averia() {
		return comen_averia;
	}

	public void setComen_averia(String comen_averia) {
		this.comen_averia = comen_averia;
	}

	public double getPrecio_repuesto() {
		return precio_repuesto;
	}

	public void setPrecio_repuesto(int precio_repuesto) {
		this.precio_repuesto = precio_repuesto;
	}

	public double getPrecio_cobrado() {
		return precio_cobrado;
	}

	public void setPrecio_cobrado(int precio_cobrado) {
		this.precio_cobrado = precio_cobrado;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	@Override
	public String toString() {
		return "Averia [id_averia=" + id_averia + ", id_vehi=" + id_vehi + ", fecha_averia=" + fecha_averia
				+ ", comen_averia=" + comen_averia + ", precio_repuesto=" + precio_repuesto + ", precio_cobrado="
				+ precio_cobrado + ", km=" + km + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comen_averia == null) ? 0 : comen_averia.hashCode());
		result = prime * result + ((fecha_averia == null) ? 0 : fecha_averia.hashCode());
		result = prime * result + ((id_averia == null) ? 0 : id_averia.hashCode());
		result = prime * result + ((id_vehi == null) ? 0 : id_vehi.hashCode());
		result = prime * result + km;
		long temp;
		temp = Double.doubleToLongBits(precio_cobrado);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(precio_repuesto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Averia other = (Averia) obj;
		if (comen_averia == null) {
			if (other.comen_averia != null)
				return false;
		} else if (!comen_averia.equals(other.comen_averia))
			return false;
		if (fecha_averia == null) {
			if (other.fecha_averia != null)
				return false;
		} else if (!fecha_averia.equals(other.fecha_averia))
			return false;
		if (id_averia == null) {
			if (other.id_averia != null)
				return false;
		} else if (!id_averia.equals(other.id_averia))
			return false;
		if (id_vehi == null) {
			if (other.id_vehi != null)
				return false;
		} else if (!id_vehi.equals(other.id_vehi))
			return false;
		if (km != other.km)
			return false;
		if (Double.doubleToLongBits(precio_cobrado) != Double.doubleToLongBits(other.precio_cobrado))
			return false;
		if (Double.doubleToLongBits(precio_repuesto) != Double.doubleToLongBits(other.precio_repuesto))
			return false;
		return true;
	}
}
