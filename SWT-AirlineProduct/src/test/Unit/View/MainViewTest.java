package Unit.View;

import View.Login;
import View.Main;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

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
