package es.charlye.coches.DAO;

import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Tipo_Alarma;

public interface Tipo_AlarmaDAO extends DAO<Tipo_Alarma, String>{

	public boolean existe(String nombre)throws DAOException;
}
