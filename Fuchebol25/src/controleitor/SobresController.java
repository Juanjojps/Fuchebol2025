package controleitor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import application.AccesoDatos;
import application.Club;
import application.Jugador;
import application.Main;

/**
 * Controlador para la pantalla de sobres de jugadores.
 * Gestiona la interacción con sobres virtuales que contienen jugadores aleatorios.
 */
public class SobresController {

    // Elementos de la UI inyectados desde FXML
    @FXML private ImageView sobreImagen;  // Imagen del sobre
    @FXML private Button abrirButton;     // Botón para abrir sobres
    @FXML private Button volverButton;    // Botón para volver al menú
    
    private Main mainApp;  // Referencia a la aplicación principal

    /**
     * Establece la referencia a la aplicación principal.
     * @param mainApp Instancia principal de la aplicación
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    
    /**
     * Método de inicialización del controlador.
     * Configura los estilos y eventos de los componentes.
     */
    @FXML
    private void initialize() {
        // Configuración de estilos
        abrirButton.setStyle("-fx-font-size: 16px;");
        volverButton.setStyle("-fx-font-size: 16px;");
        
        // Configuración de eventos
        abrirButton.setOnAction(event -> {
            try {
                abrirSobre();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        volverButton.setOnAction(event -> volverAMenuPrincipal());
    }
    
    /**
     * Abre un sobre virtual y muestra los jugadores obtenidos.
     * @throws SQLException Si ocurre un error al acceder a la base de datos
     */
    private void abrirSobre() throws SQLException {
        ArrayList<Jugador> jugGuardados = Main.getAccesoDatos().abrirSobre();
        
        // Construir mensaje con los jugadores obtenidos
        StringBuilder contenido = new StringBuilder();
        for(Jugador jugador : jugGuardados) {
            contenido.append(jugador.mostrar(jugGuardados)).append("\n");
        }
        
        // Mostrar diálogo con los resultados
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sobre abierto");
        alert.setHeaderText(null);
        alert.setContentText(contenido.toString());
        
        Stage stage = (Stage) abrirButton.getScene().getWindow();
        alert.initOwner(stage);
        alert.showAndWait();
    }
    
    /**
     * Vuelve al menú principal de la aplicación.
     */
    private void volverAMenuPrincipal() {
        try {
            mainApp.mostrarMenuPrincipal();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarError("No se pudo cargar el menú principal");
        }
    }
    
    /**
     * Muestra un mensaje de error al usuario.
     * @param mensaje Texto del error a mostrar
     */
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}