package Performance;

import Model.User;
import Model.User.InvalidUserInputException;
import Service.AutoIDService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class SecurityTest {
  static String id;

  User user;

  @BeforeAll
  // Before all test generate user ID
  static void beforeAll() {
    //pass table name and prefix to generateAutoID method.
    AutoIDService.generateAutoID("user", "UO");
  }

  @BeforeEach
  void beforeEach() throws InvalidUserInputException {
    user = new User(id, "Todd", "Bauer", "TBFlow", "testPassw0rd");
  }

  @Test
  void testValidPassword() {
    Assertions.assertDoesNotThrow(() -> user.setPassword("testPassw0rd"));
  }

  // TODO there is not a test case table for these
  // They might be able to be combined into one test case table?
  // testInvalidPasswordNoLetters
  // testInvalidPasswordNoLowercase
  // testInvalidPasswordTooManyChar
  // testInvalidPasswordTooLittleChar

  // But there is a test case table for:
  // testInvalidPasswordNoUppercase
  // testInvalidPasswordNoNumbers
  // the following test tests all 6 above test cases
  @ParameterizedTest
  @ValueSource(strings = {"123451123", "ADFADFA12",
      "fadifjadijfaoijdsfoiajdsfoijadsoi1Pf", "a",
      "testpassw0rd", "testPassword"})
  void testInvalidPasswordNoLetters(String password) {
    Exception e = Assertions.assertThrows(InvalidUserInputException.class, () -> user.setPassword(password));
    Assertions.assertEquals(e.getMessage(), "Invalid Password.");
  }
}
