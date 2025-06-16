package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que gestiona todas las operaciones de acceso a la base de datos.
 * Proporciona métodos para interactuar con la base de datos MySQL.
 */
public class AccesoDatos {

	
	Connection con = null;

	/**
     * Establece la conexión con la base de datos MySQL.
     */
	public void setConnection() {
		try {
			String bdd="jdbc:mysql://localhost:3306/Fuchebol";
			String usr="root";
			String pwd="Juanjito_113";
			
			con = DriverManager.getConnection (bdd, usr, pwd);
			System.out.println("Conexión realizada con éxito");

			
			
		} catch (SQLException e) {
			System.out.println("Conexión no realizada");
			e.printStackTrace();
		}
	}
	
	
	/**
     * Abre un sobre virtual obteniendo 3 jugadores aleatorios de la base de datos.
     * 
     * @return ArrayList con 3 Jugadores aleatorios
     */
	public ArrayList<Jugador> abrirSobre(){
		
		ArrayList<Jugador> jugadores = new ArrayList<>();
		 
		String sql = "SELECT * FROM jugadores ORDER BY RAND() LIMIT 3";
	    
	    try (PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {
	        
	        System.out.println("¡Has obtenido estos jugadores!");
	        while (rs.next()) {
	            Jugador jugador = new Jugador(
	                rs.getInt("id"),
	                rs.getString("nombre"),
	                rs.getInt("rating"),
	                rs.getString("club"),
	                rs.getString("pais"),
	                rs.getString("posicion"),
	                1  // Cantidad inicial
	            );
	            
	            jugadores.add(jugador);
	            guardarJugadorClub(jugador.getId());
	            
	            Main.getClube().guardarJugadores(jugador);
	        }
	    
	            
	    }catch(Exception e){
	    	System.out.println("No se pudieron obtener jugadores");
	    	e.printStackTrace();
	    }
	    
	    return jugadores;
	}
	
	public void guardarJugadorClub(int jugadorId) throws SQLException {
		String checkSql="SELECT cantidad FROM jugadoresclub WHERE id ="+jugadorId;
		PreparedStatement checkStmt = con.prepareStatement(checkSql);
		
		
		try(ResultSet rs=checkStmt.executeQuery()){
			if(rs.next()) {
				int cantidadActual=rs.getInt("cantidad");
				actualizarCantidadJugador(jugadorId, cantidadActual + 1);
			}else {
				insertarNuevoJugadorClub(jugadorId);
			}
			
		}
	}
	
	private void insertarNuevoJugadorClub(int jugadorId) throws SQLException {
        String sql = "INSERT INTO jugadoresclub (id, cantidad) VALUES (?, 1)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, jugadorId);
            stmt.executeUpdate();
        }
    }
	
	private void actualizarCantidadJugador(int jugadorId, int nuevaCantidad) throws SQLException {
        String sql = "UPDATE jugadoresclub SET cantidad = ? WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, nuevaCantidad);
            stmt.setInt(2, jugadorId);
            stmt.executeUpdate();
        }
    }
	
	
	//Clube
	
	public ArrayList<Jugador> obtenerJugadoresClub() throws SQLException {
	    ArrayList<Jugador> jugadores = new ArrayList<>();
	    String sql = "SELECT j.*, jc.cantidad FROM jugadores j " +
	                 "JOIN jugadoresclub jc ON j.id = jc.id";
	    
	    try (PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {
	        
	        while (rs.next()) {
	            Jugador jugador = new Jugador(
	                rs.getInt("id"),
	                rs.getString("nombre"),
	                rs.getInt("rating"),
	                rs.getString("club"),
	                rs.getString("pais"),
	                rs.getString("posicion"),
	                rs.getInt("cantidad")
	            );
	            jugadores.add(jugador);
	        }
	    }
	    
	    return jugadores;
	}
	
}
