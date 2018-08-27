package pl.marta.kopp.domain;

import org.junit.Before;
import org.junit.Test;
import pl.marta.kopp.connector.DBConnector;
import pl.marta.kopp.service.UserStorageDataBase;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserStorageDataBaseTest {
    private static final String SOME_LOGIN = "spiderman";
    private static final String SOME_PASSWORD = "spider123";
    private static final String ANOTHER_LOGIN = "batman";
    private static final String ANOTHER_PASSWORD="bat123";
    private DBConnector dbConnector;

    @Before
    public void setUp() {
        userStorageDataBase.addUser(new User(SOME_LOGIN, SOME_PASSWORD));
    }

    private final UserStorageDataBase userStorageDataBase = new UserStorageDataBase(dbConnector);

    @Test
    public void shouldRecognizeWhenLoginIsNotPresentIsStore() {
        assertFalse(userStorageDataBase.isUserExists(ANOTHER_LOGIN));
    }

    @Test
    public void shouldRecognizeWhenUserIsPresetIsStore() {
        assertTrue(userStorageDataBase.isUserExists(SOME_LOGIN));
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void shouldThrowExceptionWhenUserWithTheSomeLoginAddTwice() {
        userStorageDataBase.addUser(new User(SOME_LOGIN, SOME_PASSWORD));
    }

    @Test
    public void shouldRecognizeWhenThereIsNotUserWithGivenLogin()  {
        assertFalse(userStorageDataBase.isUserExists(ANOTHER_LOGIN,SOME_PASSWORD));
    }

    @Test
    public void shouldRecognizeWhenThereIsNotUserWithGivenPassword()  {
        assertFalse(userStorageDataBase.isUserExists(SOME_LOGIN,ANOTHER_PASSWORD));
    }

    @Test
    public void shouldRecognizeWhenUserIsPresentIsStore() {
        assertTrue(userStorageDataBase.isUserExists(SOME_LOGIN,SOME_PASSWORD));
    }
}
