package application;

import java.util.ArrayList;

/**
 * Clase principal que representa un jugador de fútbol en el sistema.
 * Contiene información básica del jugador y métodos para acceder a sus propiedades.
 */
public class Jugador {
    
    private int id;
    private String nombre;
    private int rating;
    private String club;
    private String pais;
    private String posicion;
    private int cantidad;
    
    /**
     * Constructor para crear una instancia de Jugador.
     * 
     * @param id Identificador único del jugador
     * @param nombre Nombre completo del jugador
     * @param rating Valoración numérica del jugador (0-99)
     * @param club Club actual del jugador
     * @param pais Nacionalidad del jugador
     * @param posicion Posición principal en el campo
     * @param cantidad Cantidad de copias del jugador
     */
    public Jugador(int id, String nombre, int rating, String club, String pais, String posicion, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.rating = rating;
        this.club = club;
        this.pais = pais;
        this.posicion = posicion;
        this.cantidad = cantidad;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public String getClub() { return club; }
    public void setClub(String club) { this.club = club; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    public String getPosicion() { return posicion; }
    public void setPosicion(String posicion) { this.posicion = posicion; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    
    /**
     * Muestra información básica del jugador.
     * 
     * @param jugadores Lista de jugadores (no utilizado en la implementación actual)
     * @return String con el nombre y rating del jugador
     */
    public String mostrar(ArrayList<Jugador> jugadores) {
        return "Nombre: " + getNombre() + " Rating: " + getRating() + "\n";
    }

    }