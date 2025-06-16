package application;

import java.util.ArrayList;

/**
 * Clase que gestiona el club de jugadores del usuario.
 * Mantiene una lista de jugadores y proporciona métodos para manipularla.
 */
public class Club {

    private ArrayList<Jugador> jugadores = new ArrayList<>();
    
    /**
     * Añade un jugador al club o incrementa su cantidad si ya existe.
     * 
     * @param j Jugador a añadir o actualizar
     */
    public void guardarJugadores(Jugador j) {
        for(Jugador jugador : jugadores) {
            if(jugador.getId() == j.getId()) {
                j.setCantidad(j.getCantidad() + 1);
                return;
            }
        }
        jugadores.add(j);
    }
    
    /**
     * Genera una representación en texto de todos los jugadores del club.
     * 
     * @return String con los nombres y ratings de los jugadores
     */
    public String mostrar() {
        String resultado = "";
        for (Jugador j : jugadores) {
            resultado += "Name: " + j.getNombre() + "  Rating: " + j.getRating() + "\n";
        }
        return resultado;
    }
    
    /**
     * Obtiene la lista de jugadores del club.
     * 
     * @return ArrayList de Jugadores en el club
     */
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
}



