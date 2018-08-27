package pl.marta.kopp.login;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.connector.DBConnector;
import pl.marta.kopp.domain.User;
import pl.marta.kopp.service.UserStorageDataBase;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginControllerTest {
    private DBConnector dbConnector;
    private final UserStorageDataBase userStorageDataBase = new UserStorageDataBase(dbConnector);
    private final LoginController loginController = new LoginController(userStorageDataBase);
    private static final String SOME_LOGIN = "spiderman";
    private static final String ANOTHER_LOGIN = "ironman";
    private static final String SOME_PASSWORD = "spider123";

    @Before
    public void setUp() {
        userStorageDataBase.addUser(new User(SOME_LOGIN, SOME_PASSWORD));
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
