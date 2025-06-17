package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import controleitor.ClubController;
import controleitor.MainMenuController;
import controleitor.SobresController;
/**
 * Clase principal de la aplicación que extiende Application de JavaFX.
 * Controla el flujo principal de la aplicación y la navegación entre pantallas.
 */
public class Main extends Application {
    
    private static Club clube;
    private static ArrayList<Jugador> todos;
    private static AccesoDatos accesoDatos;
    
    /**
     * Método principal de entrada a la aplicación.
     * 
     * @param args Argumentos de línea de comandos
     * @throws SQLException Si hay error al conectar con la base de datos
     */
    public static void main(String[] args) throws SQLException {
    	accesoDatos=new AccesoDatos();
    	accesoDatos.setConnection();
    	clube = new Club();
        // Start JavaFX application
        launch(args);  
        
    }
    
    public Stage primaryStage;
    
    public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	
	 /**
     * Método start requerido por JavaFX, configura el escenario principal.
     * 
     * @param primaryStage Escenario principal de la aplicación
     * @throws Exception Si hay error al cargar las pantallas
     */
	@Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        
        primaryStage.setFullScreen(true);
        mostrarPantallaSobres();
        mostrarMenuPrincipal();
    }
    
	/**
     * Muestra la pantalla principal del menú.
     * 
     * @throws IOException Si hay error al cargar el archivo FXML
     */
    public void mostrarMenuPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PantallaPrincipal.fxml"));
        Parent root = loader.load();
        
        MainMenuController controller = loader.getController();
        controller.setMainApp(this);
        
        // Cargar CSS
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/vista/application.css").toExternalForm());
        
        primaryStage.setTitle("Fuchebol 25");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }
    
    /**
     * Muestra la pantalla de los sobres.
     * 
     * @throws IOException Si hay error al cargar el archivo FXML
     */
    
    public void mostrarPantallaSobres() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PantallaSobres.fxml"));
        Parent root = loader.load();
        
        SobresController controller = loader.getController();
        controller.setMainApp(this);
        
		primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
    }
    
    /**
     * Muestra la pantalla del club (jugadores que tenemos).
     * 
     * @throws IOException Si hay error al cargar el archivo FXML
     */
    
    public void mostrarPantallaClub() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PantallaClub.fxml"));
        Parent root = loader.load();
        
        ClubController controller = loader.getController();
        controller.setMainApp(this);
        
        primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
    }
    
    /**
     * Obtiene la instancia del club del usuario.
     * 
     * @return Instancia de Club
     */
    public static Club getClube() {
        return clube;
    }

    /**
     * Obtiene la lista completa de jugadores disponibles.
     * 
     * @return ArrayList de todos los Jugadores
     */
    public static ArrayList<Jugador> getTodos() {
        return todos;
    }
    
    /**
     * Obtiene la instancia de acceso a datos.
     * 
     * @return Instancia de AccesoDatos
     */
    public static AccesoDatos getAccesoDatos() {
        return accesoDatos;
    }
}