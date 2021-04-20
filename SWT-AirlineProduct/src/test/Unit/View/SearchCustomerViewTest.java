package Unit.View;

import View.Main;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JInternalFrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class SearchCustomerViewTest {
    private FrameFixture window;
    private JInternalFrameFixture sut;

    @BeforeEach
    public void setUp() {
        JFrame frame = GuiActionRunner.execute(Main::new);
        window = new FrameFixture(frame);
        window.show(); // shows the frame to tests

        window.menuItem("jMenuItem2").click();
        sut = window.internalFrame("SearchCustomer");
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp();
    }

    @Test
    void testUpdateCustomer() throws InterruptedException {
        sut.textBox("txtcustid").setText("CS001");
        sut.button("jButton4").click();

        sut.textBox("txtfirstname").setText("Brandon");
        sut.textBox("txtlastname").setText("Baker");
        sut.button("jButton2").click();
    }
}
