package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoimpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public List<User> getallUsers() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void editUser(long id, User updatedUser) {
        User userToUpdate = getUserById(id);
        userToUpdate.setUsername(updatedUser.getUsername());
        userToUpdate.setPassword(new BCryptPasswordEncoder().encode(updatedUser.getPassword()));
        userToUpdate.setUsername(updatedUser.getUsername());
        entityManager.merge(userToUpdate);
    }

    @Override
    public void deleteUser(long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }
    @Override
    public User getUserByUsername(String username) {

        return entityManager.createQuery("select user from User user where user.username =: username", User.class)
                .setParameter("username", username).getSingleResult();
    }


}
