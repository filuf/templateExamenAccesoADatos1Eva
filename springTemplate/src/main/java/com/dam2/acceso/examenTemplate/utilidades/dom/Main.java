package com.dam2.acceso.examenTemplate.utilidades.dom;


public class Main {

    public static void main(String[] args) {

        String uriFicheroXml = "src/main/java/com/dam2/acceso/examenTemplate/utilidades/dom/universidad.xml";
        GestionUniversidad gestionUniversidad = new GestionUniversidad(uriFicheroXml);

        gestionUniversidad.listarCarrerasPorNombreDeFacultad("Facultad de Ciencias Sociales");

        gestionUniversidad.listarCarrerasPorNombreDeFacultad("Facultad de Ingeniería");

        gestionUniversidad.eliminarEstudiantesMenoresDeNAniosPorDepartamento(22, "Computación");
        gestionUniversidad.aniadirMateriaAEstudiantePorIDEstudiante("2", "miCodigo", "Mecánica de fluidos", 4.99);
    }
}
