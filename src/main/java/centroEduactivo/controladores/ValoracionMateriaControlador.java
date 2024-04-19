package centroEduactivo.controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import centroEduactivo.model.Estudiante;
import centroEduactivo.model.Materia;
import centroEduactivo.model.Profesor;
import centroEduactivo.model.ValoracionMateria;

public class ValoracionMateriaControlador extends Controlador {

	public ValoracionMateriaControlador() {
		super("valoracionmateria", ValoracionMateria.class);
	}

}