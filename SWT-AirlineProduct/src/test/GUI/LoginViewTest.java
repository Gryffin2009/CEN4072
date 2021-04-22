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

  /*
  Test Case ID: #L-1-1
  Requirement ID/Description: #L-1, The system shall be inaccessible without user credentials.
  Purpose: Verify that the Login screen is displayed on program start and all fields are
    visible to the user.
  Test Setup: Open the program from the Login screen with AssertJ.
  Test Strategy: Use AssertJ to verify that all fields and buttons appear for the user to login.
  Input: The user opens the program.
  Expected Output: The Login screen is displayed with fields for username and password and
    a submit button.
   */
  @Test
  public void isLoginComponentsVisible() {
    JTextComponentFixture userNameTextField = window.textBox("userTxt").requireVisible();
    JTextComponentFixture passwordTextField = window.textBox("passwordTxt").requireVisible();
    JButtonFixture loginButton = window.button("loginBtn").requireVisible();
    JButtonFixture cancelButton = window.button("cancelBtn").requireVisible();
  }

  /*
  Test Case ID: #L-1-2
  Requirement ID/Description: #L-1, The system shall be inaccessible without user credentials.
  Purpose: Verify that all fields on the Login screen are editable and the login and cancel
    buttons are enabled.
  Test Setup: Open the program from the Login screen with AssertJ.
  Test Strategy: Use AssertJ to verify that all fields are editable and all buttons are clickable.
  Input: The user opens the program.
  Expected Output: The Login screen is displayed, and the test confirms that these fields are set
    to editable and the buttons are enabled for clicking.
   */
  @Test
  public void isLoginComponentsFunctional() {
    JTextComponentFixture userNameTextField = window.textBox("userTxt").requireEditable();
    JTextComponentFixture passwordTextField = window.textBox("passwordTxt").requireEditable();
    JButtonFixture loginButton = window.button("loginBtn").requireEnabled();
    JButtonFixture cancelButton = window.button("cancelBtn").requireEnabled();
  }

  /*
  Test Case ID: #L-1-3
  Requirement ID/Description: #L-1, The system shall be inaccessible without user credentials.
  Purpose: Verify that valid credentials logs a user into the system.
  Test Setup: Open the program from the Login screen with AssertJ.
  Test Strategy: Enter a valid username and password into the username and password fields, then
    click the submit button and check to see if the program accepts the information and logs the
    user into the system.
  Input: The user opens the Login screen and enters “john” for the username and “Passw0rd” for the
    password, then clicks the login button.
  Expected Output: The user is brought to the main screen without an error message.
   */
  @Test
  public void loginTestComponents() {
    JTextComponentFixture userNameTextField = window.textBox("userTxt").enterText("john");
    JTextComponentFixture passwordTextField = window.textBox("passwordTxt").enterText("Passw0rd");
    JButtonFixture loginButton = window.button("loginBtn").click();
  }

  /*
  Test Case ID: #L-1-4
  Requirement ID/Description: #L-1, The system shall be inaccessible without user credentials.
  Purpose: Verify that the cancel button on the Login screen refuses entry to the system and
    exits the program.
  Test Setup: Open the program from the Login screen with AssertJ.
  Test Strategy: Use AssertJ to automatically click the cancel button on the Login screen to check
    if this refuses entry to the system and closes the program.
  Input: The user opens the Login screen and clicks the “cancel” button.
  Expected Output: The program is closed without access to the system.
   */
  @Test
  public void closeLoginWindow() {
    JButtonFixture cancelButton = window.button("cancelBtn").click();
  }

  @AfterEach
  public void tearDown() {
    window.cleanUp();
  }
}