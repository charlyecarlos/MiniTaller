package es.charlye.coches.DAO;

import java.util.ArrayList;
import java.util.List;

import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Alarma;
import es.charlye.coches.Modelo.ModeloAlerta;

public interface AlarmaDAO extends DAO<Alarma, Alarma>{

	public List<ModeloAlerta> obtenerAlertas() throws DAOException;
	

	public Alarma encontrarAlarma(ArrayList<String> a) throws DAOException;
}
