package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    //there is no user with such login in the Storage yet
    //user's login is at least 6 characters
    //user's password is at least 6 characters
    //user's age is at least 18 years old

    @Override
    public User register(User user) {
        if (storageDao.get(user.getLogin()) != null) {
            throw new InvalidUserException("User already exists");
        }
        if (user.getLogin() == null || user.getAge() == null || user.getPassword() == null) {
            throw new InvalidUserException("You can't use null");
        }
        if (user.getLogin().length() >= 6
                && user.getPassword().length() >= 6
                && user.getAge() >= 18) {
            storageDao.add(user);
        } else {
            throw new InvalidUserException("Incorrect data");
        }
        return user;
    }
}
