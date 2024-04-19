package centroEduactivo.controladores;

import centroEduactivo.model.Materia;

public class MateriaControlador extends Controlador {

	private static MateriaControlador instance = null;

	public MateriaControlador() {
		super("materia", Materia.class);
	}

	public static MateriaControlador getInstance() {
		if (instance == null) {
			instance = new MateriaControlador();
		}
		return instance;
	}

}
