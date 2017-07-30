package es.charlye.coches.Modelo;

public class Vehiculo {

	private Long id_vehi;
	private String marca;
	private String modelo;
	private int anho;
	private String motor;
	private String chasis;
	private Long id_prop;
	
	public Vehiculo(Long id_vehi, String marca, String modelo, int anho, String motor, String chasis, Long id_prop) {
		super();
		this.id_vehi = id_vehi;
		this.marca = marca;
		this.modelo = modelo;
		this.anho = anho;
		this.motor = motor;
		this.chasis = chasis;
		this.id_prop = id_prop;
	}

	public Vehiculo(String marca, String modelo, int anho, String motor, String chasis, Long id_prop) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.anho = anho;
		this.motor = motor;
		this.chasis = chasis;
		this.id_prop = id_prop;
	}

	@Override
	public String toString() {
		return "Vehiculo [id_vehi=" + id_vehi + ", marca=" + marca + ", modelo=" + modelo + ", anho=" + anho
				+ ", motor=" + motor + ", chasis=" + chasis + ", id_prop=" + id_prop + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anho;
		result = prime * result + ((chasis == null) ? 0 : chasis.hashCode());
		result = prime * result + ((id_prop == null) ? 0 : id_prop.hashCode());
		result = prime * result + ((id_vehi == null) ? 0 : id_vehi.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((motor == null) ? 0 : motor.hashCode());
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
		Vehiculo other = (Vehiculo) obj;
		if (anho != other.anho)
			return false;
		if (chasis == null) {
			if (other.chasis != null)
				return false;
		} else if (!chasis.equals(other.chasis))
			return false;
		if (id_prop == null) {
			if (other.id_prop != null)
				return false;
		} else if (!id_prop.equals(other.id_prop))
			return false;
		if (id_vehi == null) {
			if (other.id_vehi != null)
				return false;
		} else if (!id_vehi.equals(other.id_vehi))
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
		if (motor == null) {
			if (other.motor != null)
				return false;
		} else if (!motor.equals(other.motor))
			return false;
		return true;
	}

	public Long getId_vehi() {
		return id_vehi;
	}

	public void setId_vehi(Long id_vehi) {
		this.id_vehi = id_vehi;
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

	public int getAnho() {
		return anho;
	}

	public void setAnho(int anho) {
		this.anho = anho;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public String getChasis() {
		return chasis;
	}

	public void setChasis(String chasis) {
		this.chasis = chasis;
	}

	public Long getId_prop() {
		return id_prop;
	}

	public void setId_prop(Long id_prop) {
		this.id_prop = id_prop;
	}
	

}