package GUI;

import View.Login;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginViewTest {
  private FrameFixture window;

  @BeforeEach
  public void setUp() {
    Login frame = GuiActionRunner.execute(Login::new);

    window = new FrameFixture(frame);
    window.show(); // shows the frame to test
  }

  @Test
  public void isLoginComponentsVisible() {
    JTextComponentFixture userNameTextField = window.textBox("userTxt").requireVisible();
    JTextComponentFixture passwordTextField = window.textBox("passwordTxt").requireVisible();
    JButtonFixture loginButton = window.button("loginBtn").requireVisible();
  }

  @Test
  public void isLoginComponentsFunctional() {
    JTextComponentFixture userNameTextField = window.textBox("userTxt").requireEditable();
    JTextComponentFixture passwordTextField = window.textBox("passwordTxt").requireEditable();
    JButtonFixture loginButton = window.button("loginBtn").requireEnabled();
    JButtonFixture cancelButton = window.button("cancelBtn").requireEnabled();
  }

  @Test
  public void loginTestComponents() {
    JTextComponentFixture userNameTextField = window.textBox("userTxt").enterText("john");
    JTextComponentFixture passwordTextField = window.textBox("passwordTxt").enterText("Passw0rd");
    JButtonFixture loginButton = window.button("loginBtn").click();
  }

  @Test
  public void closeLoginWindow() {
    JButtonFixture cancelButton = window.button("cancelBtn").click();
  }

  @AfterEach
  public void tearDown() {
    window.cleanUp();
  }
}