package pl.marta.kopp.domain.service;

import org.junit.Before;
import org.junit.Test;
import pl.marta.kopp.domain.model.User;
import pl.marta.kopp.persistence.UserStorage;
import pl.marta.kopp.persistence.UserStorageJpa;

import static org.junit.Assert.*;

public class UserServiceTest {
    private UserStorage userStorage;
    private UserService service;
    private static final String SOME_LOGIN = "marta";
    private static final String ANOTHER_LOGIN = "agnieszka";
    private static final String SOME_PASSWORD = "marta";
    private static final String ANOTHER_PASSWORD = "agnieszka";

    @Before
    public void setUp() throws Exception {
        userStorage = new UserStorageJpa();
        service = new UserService(userStorage);
    }

    @Test
    public void shouldAddNewUser() throws Exception {
        long id = givenUser(SOME_LOGIN, SOME_PASSWORD);
        assertTrue(service.isExists(id));
    }

    private long givenUser(String someLogin, String somePassword) {
        User user = new User(someLogin, somePassword);
        return service.add(user);
    }

    @Test
    public void shouldGetUserWhenUserExists() throws Exception {
        long userId = givenUser(SOME_LOGIN, SOME_PASSWORD);
        User user = service.get(userId);

        assertEquals(SOME_LOGIN, user.getLogin());
        assertEquals(SOME_PASSWORD, user.getPassword());
    }

    @Test
    public void shouldRecognizeWhenUserHasInvalidPassword() {
        service.add(new User(SOME_LOGIN, SOME_PASSWORD));
        assertFalse(service.isUserExists(SOME_LOGIN, ANOTHER_PASSWORD));
    }

    @Test
    public void shouldRecognizeWhenLoginIsNotPresentInTheStore() {
        assertFalse(service.isLoginExists(ANOTHER_LOGIN));
    }

    @Test
    public void shouldRecognizeWhenLoginIsPresentIsStore() {
        givenUser(SOME_LOGIN, SOME_PASSWORD);
        assertTrue(service.isLoginExists(SOME_LOGIN));
    }

    @Test
    public void shouldRecognizeWhenIsNotLoginInStore() {
        assertFalse(service.isUserExists(ANOTHER_LOGIN, SOME_PASSWORD));
    }

    @Test
    public void shouldUpdateUser() throws Exception {
        long userId = givenUser(SOME_LOGIN, SOME_PASSWORD);
        User byId = service.get(userId);
        byId.setPassword(ANOTHER_PASSWORD);
        service.update(byId);
        assertTrue(service.isUserExists(SOME_LOGIN, ANOTHER_PASSWORD));
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        long userId = givenUser(SOME_LOGIN, SOME_PASSWORD);
        service.delete(userId);
        assertFalse(service.isUserExists(SOME_LOGIN, SOME_PASSWORD));
    }


}