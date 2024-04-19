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
import centroEduactivo.model.Estudiante;

public class EstudianteControlador extends Controlador {
	private static EstudianteControlador instance = null;

	public EstudianteControlador() {
		super("estudiante", Estudiante.class);
		// TODO Auto-generated constructor stub
	}

	public static EstudianteControlador getInstance() {
		if (instance == null) {
			instance = new EstudianteControlador();
		}
		return instance;
	}

}
