package controleitor;

import java.io.IOException;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controlador para el menú principal de la aplicación Fuchebol 25.
 * Gestiona la navegación entre las diferentes pantallas de la aplicación.
 */
public class MainMenuController {
    
    // Elementos FXML inyectados
    
    @FXML private Button envelopesButton;    // Botón para acceder a la pantalla de sobres
    @FXML private Button meuClubeButton;     // Botón para acceder a la pantalla del club
    @FXML private ImageView ImagenPrincipal; // Componente para mostrar imagen principal
    
    private Main mainApp; // Referencia a la aplicación principal
    
    /**
     * Establece la referencia a la aplicación principal.
     * 
     * @param mainApp Instancia de la clase Main de la aplicación
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
        // Configuración de estilos para los botones
        envelopesButton.setStyle("-fx-font-size: 16px;");
        meuClubeButton.setStyle("-fx-font-size: 16px;");
        
        // Configuración de eventos para los botones
        envelopesButton.setOnAction(event -> {
            try {
                onEnvelopesClicked();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        
        meuClubeButton.setOnAction(event -> {
            try {
                onMeuClubeClicked();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    
    /**
     * Maneja el evento de clic en el botón de sobres.
     * Navega a la pantalla de sobres.
     * 
     * @throws IOException Si ocurre un error al cargar la pantalla
     */
    @FXML
    private void onEnvelopesClicked() throws IOException {
        mainApp.mostrarPantallaSobres();
    }

    /**
     * Maneja el evento de clic en el botón "Meu Clube".
     * Navega a la pantalla del club.
     * 
     * @throws IOException Si ocurre un error al cargar la pantalla
     */
    @FXML
    private void onMeuClubeClicked() throws IOException {
        mainApp.mostrarPantallaClub();
    }
}