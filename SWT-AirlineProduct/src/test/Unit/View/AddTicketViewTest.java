package Unit.View;

import View.Login;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import View.AddTicket;

public class AddTicketViewTest {
    private FrameFixture window;

    @BeforeEach
    public void setUp() {
        AddTicket frame = GuiActionRunner.execute(AddTicket::new);
        window = new FrameFixture(frame);
        window.show(); // shows the frame to tests
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp();
    }


}