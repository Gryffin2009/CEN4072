package GUI;

import View.Main;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainViewTest {
    private FrameFixture window;

    @BeforeEach
    public void setUp() {
        Main frame = GuiActionRunner.execute(Main::new);
        window = new FrameFixture(frame);
        window.show(); // shows the frame to test
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp();
    }

    /*
    Test Case ID: #MUI-1-1
    Requirement ID/Description: All other UI windows shall be accessible from the Main UI window.
    Test Setup: Create a window frame of the Main screen with AssertJ.
    Test Strategy: Use AssertJ to systematically click every toolbar navigation option to open
        the following windows:
        add customer
        search customer
        book ticket
        add flight
        ticket report
        add user
    Input: The user clicks on every toolbar navigation option.
    Expected Output: All relevant windows are opened from the Main window with no errors or
        missing windows.
     */
    @Test
    public void testAllMenuItems() {
        String[] menuItems = {"jMenuItem1","jMenuItem2","jMenuItem3","jMenuItem4","jMenuItem5","jMenuItem6"};
        for (String name: menuItems) {
            window.menuItem(name).requireVisible();
            window.menuItem(name).requireEnabled();
            window.menuItem(name).click();
        }
    }

}
