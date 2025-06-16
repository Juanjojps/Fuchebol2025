package controleitor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import application.Main;
import application.Jugador;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;

/**
 * Controlador para la pantalla de gestión del club de jugadores.
 * Permite visualizar, filtrar y ver detalles de los jugadores del club.
 */
public class ClubController {

	// Elementos UI inyectados desde FXML
	@FXML private GridPane jugadoresGrid;      // Grid para mostrar jugadores
	@FXML private TextField filtroTextField;  // Campo para filtrar jugadores
	@FXML private Button volverButton;        // Botón para volver al menú
	@FXML private Button botonJugador;       // Botón dinámico para cada jugador

	private Main mainApp;                    // Referencia a la clase principal
	private List<Jugador> todosJugadores;    // Lista completa de jugadores
    
	/**
	 * Establece la referencia a la aplicación principal.
	 * @param mainApp Instancia de la clase Main
	 */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    
    /**
     * Inicializa el controlador después de cargar el FXML.
     * Configura eventos y carga los jugadores iniciales.
     */
    @FXML
    private void initialize() {
        // Configurar el botón de volver
        volverButton.setOnAction(event -> {
            try {
                mainApp.mostrarMenuPrincipal();
            } catch (IOException e) {
                mostrarError("No se pudo volver al menú principal");
            }
        });
        
        // Cargar jugadores del club
        cargarJugadores();
    }
    
    /**
     * Carga los jugadores desde la base de datos y actualiza la vista.
     * Maneja errores de conexión a la base de datos.
     */
    private void cargarJugadores() {
        try {
            // Obtener jugadores de la base de datos
            ArrayList<Jugador> jugadoresDB = Main.getAccesoDatos().obtenerJugadoresClub();
            
            // Actualizar el club local
            for (Jugador j : jugadoresDB) {
                Main.getClube().guardarJugadores(j);
            }
            
            // Mostrar los jugadores
            todosJugadores = new ArrayList<>(Main.getClube().getJugadores());
            actualizarGridJugadores(todosJugadores);
        } catch (SQLException e) {
            mostrarError("Error al cargar jugadores de la base de datos");
            e.printStackTrace();
        }
    }
    
    /**
     * Filtra los jugadores según el texto introducido.
     * Se activa al escribir en el campo de filtro.
     */
    @FXML
    private void filtrarJugadores() {
        String textoFiltro = filtroTextField.getText().toLowerCase();
        
        if (textoFiltro.isEmpty()) {
            // Si no hay texto de filtro, mostrar todos los jugadores
            actualizarGridJugadores(todosJugadores);
        } else {
            // Filtrar jugadores cuyo nombre contenga el texto de filtro
            List<Jugador> jugadoresFiltrados = new ArrayList<>();
            for (Jugador jugador : todosJugadores) {
                if (jugador.getNombre().toLowerCase().contains(textoFiltro)) {
                    jugadoresFiltrados.add(jugador);
                }
            }
            actualizarGridJugadores(jugadoresFiltrados);
        }
    }
    
    /**
     * Actualiza el GridPane con la lista de jugadores proporcionada.
     * @param jugadores Lista de jugadores a mostrar
     */
    private void actualizarGridJugadores(List<Jugador> jugadores) {
        // Limpiar el grid primero
        jugadoresGrid.getChildren().clear();
        
        // Mostrar jugadores en el grid (3 columnas)
        int row = 0;
        int col = 0;
        for (Jugador jugador : jugadores) {
            botonJugador = new Button(jugador.getNombre() + "\nRating: " + jugador.getRating());
            botonJugador.setMaxWidth(Double.MAX_VALUE);
            botonJugador.setWrapText(true);
            botonJugador.setStyle("-fx-alignment: CENTER;");
            
            // Mostrar detalles al hacer clic
            botonJugador.setOnAction(event -> mostrarDetallesJugador(jugador));
            
            jugadoresGrid.add(botonJugador, col, row);
            
            col++;
            if (col > 2) {
                col = 0;
                row++;
            }
        }
        
        // Mostrar mensaje si no hay jugadores
        if (jugadores.isEmpty()) {
            jugadoresGrid.add(new Label("No se encontraron jugadores"), 0, 0, 3, 1);
        }
    }
    
    /**
     * Muestra un diálogo con los detalles completos de un jugador.
     * @param jugador Jugador cuyos detalles se mostrarán
     */
    private void mostrarDetallesJugador(Jugador jugador) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Detalles del Jugador");
        alert.setHeaderText(jugador.getNombre() + " - " + jugador.getRating() + " (" + jugador.getPosicion() + ")");
        alert.setContentText(
            "Club: " + jugador.getClub() + "\n" +
            "Nacionalidad: " + jugador.getPais() + "\n" +
            "Cantidad: " + jugador.getCantidad()
        );
        
        Stage stage=(Stage) botonJugador.getScene().getWindow();
        alert.initOwner(stage);
        alert.showAndWait();
    }
    
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}