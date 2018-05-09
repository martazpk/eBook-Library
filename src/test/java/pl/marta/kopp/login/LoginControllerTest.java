package pl.marta.kopp.login;

import communication.Response;
import domain.User;
import domain.UserStorage;
import login.LoginController;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginControllerTest {
    private final UserStorage userStorage = new UserStorage();
    private final LoginController loginController = new LoginController(userStorage);
    private static final String SOME_LOGIN = "spiderman";
    private static final String ANOTHER_LOGIN = "ironman";
    private static final String SOME_PASSWORD = "spider123";

    @Before
    public void setUp() {
        userStorage.addUser(new User(SOME_LOGIN, SOME_PASSWORD));
    }

    @Test
    public void shouldLoginWhenUserExists() {
        Response result = loginController.login(SOME_LOGIN, SOME_PASSWORD);
        assertTrue(result.getSuccess());
    }

    @Test
    public void shouldNotLoginWhenPasswordOrLoginIsIncorrect()  {
        Response result =loginController.login(ANOTHER_LOGIN,SOME_PASSWORD);
        assertFalse(result.getSuccess());
        assertEquals("Login or password invalid",result.getMessage());
    }
}
