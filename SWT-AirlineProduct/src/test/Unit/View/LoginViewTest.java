package Unit.View;

import View.Login;
import javax.swing.JTextField;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.*;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginViewTest {
  private FrameFixture window;

  @BeforeEach
  public void setUp() {
    Login frame = GuiActionRunner.execute(() -> new Login());
    window = new FrameFixture(frame);
    window.show(); // shows the frame to test
  }

  @Test
  public void loginComponentsVisible() {
    JTextComponentFixture userNameTextField = window.textBox("userTxt").requireVisible();
    JTextComponentFixture passwordTextField = window.textBox("passwordTxt").requireVisible();
    JButtonFixture loginButton = window.button("loginBtn").requireVisible();
  }

  @Test
  public void logincomponentsFunctional() {
    JTextComponentFixture userNameTextField = window.textBox("userTxt").requireEditable();
    JTextComponentFixture passwordTextField = window.textBox("passwordTxt").requireEditable();
    JButtonFixture loginButton = window.button("loginBtn").requireEnabled();
  }

  @Test
  public void loginTestComponents() {
    JTextComponentFixture userNameTextField = window.textBox("userTxt").enterText("john");
    JTextComponentFixture passwordTextField = window.textBox("passwordTxt").enterText("123");
    JButtonFixture loginButton = window.button("loginBtn").click();
  }

  @AfterEach
  public void tearDown() {
    window.cleanUp();
  }
}