package com.dam2.acceso.examenTemplate.utilidades.jaxb.logic;

import com.dam2.acceso.examenTemplate.utilidades.jaxb.models.BibliotecaVideojuegos;
import com.dam2.acceso.examenTemplate.utilidades.jaxb.models.Intercambio;
import com.dam2.acceso.examenTemplate.utilidades.jaxb.models.Usuario;
import com.dam2.acceso.examenTemplate.utilidades.jaxb.utils.JaxbUtils;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        // carga de archivo
        Path path = Path.of("src/main/java/com/dam2/acceso/examenTemplate/utilidades/jaxb/juegos.xml");
        BibliotecaVideojuegos bibliotecaVideojuegos = JaxbUtils.readXml(path, BibliotecaVideojuegos.class);

        //logica
        mostrarIntercambioDeUnJuego(bibliotecaVideojuegos, "101");
        eliminarUsuarioYTodosSusIntercambiosPorID(bibliotecaVideojuegos,"2"); // lo siento Ana :(

        // escritura
        JaxbUtils.writeXml(bibliotecaVideojuegos, path);
    }

    private static void mostrarIntercambioDeUnJuego(BibliotecaVideojuegos bibliotecaVideojuegos, String idJuego) {
        Optional<Intercambio> posibleIntercambio = bibliotecaVideojuegos.getIntercambios()
                .stream()
                .filter(intercambio -> intercambio.getIdJuego().equals(idJuego))
                .findFirst();

        if (posibleIntercambio.isEmpty()) {
            System.out.println("El juego "+ idJuego +" no se ha intercambiado");
            return;
        }

        Intercambio intercambio = posibleIntercambio.get();
        System.out.println("El usuario emisor del intercambio es " + intercambio.getIdUsuarioEmisor());
        System.out.printf("El usuario receptor del intercambio es %s", intercambio.getIdUsuarioReceptor());
    }

    private static void eliminarUsuarioYTodosSusIntercambiosPorID(BibliotecaVideojuegos bibliotecaVideojuegos, String idUsuario) {
        Map<Boolean, List<Usuario>> particion = bibliotecaVideojuegos.getUsuarios().stream()
                .collect(
                        Collectors.partitioningBy(usuario -> usuario.getIdUsuario().equals(idUsuario)));

        if (particion.get(true).isEmpty()) {
            System.out.println("No hay ningún usuario con este id: " + idUsuario);
            return;
        }

        List<Usuario> usuariosActuales = particion.get(false);

        bibliotecaVideojuegos.setUsuarios(usuariosActuales);

        List<Intercambio> nuevaListaIntercambio = bibliotecaVideojuegos.getIntercambios().stream()
                .filter(intercambio -> !intercambio.getIdUsuarioReceptor().equals(idUsuario) && !intercambio.getIdUsuarioEmisor().equals(idUsuario))
                .toList();

        bibliotecaVideojuegos.setIntercambios(nuevaListaIntercambio);

    }

}
