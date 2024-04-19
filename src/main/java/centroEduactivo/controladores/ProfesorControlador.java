package centroEduactivo.controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import centroEduactivo.model.Materia;
import centroEduactivo.model.Profesor;

public class ProfesorControlador extends Controlador {
	private static ProfesorControlador instance = null;

	public ProfesorControlador() {
		super("profesor", Profesor.class);
	}

	public static ProfesorControlador getInstance() {
		if (instance == null) {
			instance = new ProfesorControlador();
		}
		return instance;
	}

}
