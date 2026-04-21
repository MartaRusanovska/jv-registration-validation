package core.basesyntax.service;

import core.basesyntax.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceImplTest {
    private RegistrationServiceImpl registrationService;
    private User user;

    private User getValidUser() {
        User user = new User();
        user.setAge(20);
        user.setLogin("Maksym");
        user.setPassword("maksym");
        return user;
    }

    @BeforeEach
    void init() {
        registrationService = new RegistrationServiceImpl();
        user = getValidUser();
    }

    @Test
    void register_nullAge_notOk() {
        user.setAge(null);
        assertThrows(InvalidUserException.class, ()
                -> registrationService.register(user));
    }

    @Test
    void register_nullLogin_notOk() {
        user.setLogin(null);
        assertThrows(InvalidUserException.class, ()
                -> registrationService.register(user));
    }

    @Test
    void register_nullPassword_notOk() {
        user.setPassword(null);
        assertThrows(InvalidUserException.class, ()
                -> registrationService.register(user));
    }

    @Test
    void register_minAge_notOk() {
        user.setAge(15);
        assertThrows(InvalidUserException.class, ()
                -> registrationService.register(user));
    }

    @Test
    void register_minLengthLogin_notOk() {
        user.setLogin("Maks");
        assertThrows(InvalidUserException.class, ()
                -> registrationService.register(user));
    }

    @Test
    void register_minLengthPassword_notOk() {
        user.setPassword("maks");
        assertThrows(InvalidUserException.class, ()
                -> registrationService.register(user));
    }

    @Test
    void register_ValidUser_Ok() {
        User user1 = new User();
        user1.setLogin("Danylo");
        user1.setAge(25);
        user1.setPassword("danylo");
        User registered = registrationService.register(user1);
        assertNotNull(registered);
        assertEquals("Danylo", registered.getLogin());
    }

    @Test
    void register_Age_Ok() {
        user.setAge(23);
        int expected = 23;
        assertEquals(user.getAge(), expected);
    }

}