package centroEduactivo.controladores;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import centroEduactivo.model.Entidad;
import centroEduactivo.model.Estudiante;
import centroEduactivo.model.Materia;
import centroEduactivo.model.Profesor;
import centroEduactivo.model.ValoracionMateria;



public class Controlador {

	private static String nombreTabla = "";
	private Class tipoEntidad;
	private static EntityManager em = null;

	public Controlador(String nombreTabla, Class tipoEntidad) {
		this.nombreTabla = nombreTabla;
		this.tipoEntidad = tipoEntidad;
	}

	private static void update(Entidad e) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.merge(e);
		em.getTransaction().commit();
	}

	public List<? extends Entidad> findAll() {
		return (List<Entidad>) getEntityManager()
				.createNativeQuery("SELECT * FROM " + nombreTabla + ";", this.tipoEntidad).getResultList();

	}
	public Entidad find(int id) {
		EntityManager em;
		try {
			em = getEntityManager();
			Entidad entidad = (Entidad) em.find(this.tipoEntidad, id);
			return entidad;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	protected static EntityManager getEntityManager() {
		if (em == null) {
			em = Persistence.createEntityManagerFactory("CentroEducativo").createEntityManager();
		}
		return em;
	}

	public static ValoracionMateria obtenerValoracion(Estudiante e, Profesor p, Materia m, Integer nota) {
		try {
			return (ValoracionMateria) getEntityManager().createNativeQuery(
					"SELECT * FROM valoracionmateria where " + e.getId() + " = idEstudiante and " + p.getId()
							+ " = idProfesor and " + m.getId() + " = idMateria and " + nota + " = valoracion;",
					ValoracionMateria.class).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

	}

	public static void insert(Estudiante e, Profesor p, Materia m, Integer nota, Date fecha) {
		ValoracionMateria v = new ValoracionMateria();
		v.setIdEstudiante(e.getId());
		v.setIdMateria(m.getId());
		v.setIdProfesor(p.getId());
		v.setValoracion(nota);
		v.setFecha(fecha);

		em.getTransaction().begin();
		em.persist(v);
		em.getTransaction().commit();

	}

	public static void update(Estudiante e, Profesor p, Materia m, Integer nota, Date fecha) {
		ValoracionMateria v = obtenerValoracionSinNota(e, p, m);

		v.setValoracion(nota);
		v.setFecha(fecha);

		em.getTransaction().begin();
		em.persist(v);
		em.getTransaction().commit();

	}

	public static ValoracionMateria obtenerValoracionSinNota(Estudiante e, Profesor p, Materia m) {
		try {
			return (ValoracionMateria) getEntityManager()
					.createNativeQuery("SELECT * FROM valoracionmateria where " + e.getId() + " = idEstudiante and "
							+ p.getId() + " = idProfesor and " + m.getId() + " = idMateria;", ValoracionMateria.class)
					.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

	}

	public static Entidad getPrimero() {

		Entidad e = (Entidad) em.find(Entidad.class, minIdEnTabla());

		return e;

	}

	public static Entidad getUltimo() {
		Entidad e = (Entidad) em.find(Entidad.class, maxIdEnTabla());

		return e;
	}

	private static int minIdEnTabla() {
		Query q = em.createNativeQuery("SELECT min() FROM " + nombreTabla + ";", Entidad.class);
		int minId = (int) q.getSingleResult();
		return minId;
	}

	private static int maxIdEnTabla() {
		Query q = em.createNativeQuery("SELECT max() FROM " + nombreTabla + ";", Entidad.class);
		int maxId = (int) q.getSingleResult();
		return maxId;
	}

}