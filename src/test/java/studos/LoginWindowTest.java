package studos;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import studos.gui.GuiStarter;

/**
 * Unit test for simple App.
 */
class LoginWindowTest extends ApplicationTest {

    @Override
  public void start(final Stage stage) throws Exception {
        final Parent mainNode = FXMLLoader
            .load(GuiStarter.class.getResource("view/loginWindow.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @BeforeEach
  public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
  public void testUserAndPasswordInput() {
        clickOn("#usernameTextField");
        write("This is a test!");
        clickOn("#passwordTextField");
        write("Password");
    }
}