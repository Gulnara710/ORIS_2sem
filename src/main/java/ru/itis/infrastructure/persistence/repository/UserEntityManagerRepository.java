//package ru.itis.persistence.repository;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.TypedQuery;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import ru.itis.persistence.entity.UserEntity;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Repository
//@Transactional
//public class UserEntityManagerRepository implements UserRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public void save(UserEntity user) {
//        if (user.getId() == null) {
//            entityManager.persist(user);
//        } else {
//            entityManager.merge(user);
//        }
//    }
//
//    @Override
//    public Optional<UserEntity> getById(UUID uuid) {
//        UserEntity user = entityManager.find(UserEntity.class, uuid);
//        return Optional.ofNullable(user);
//    }
//
//    @Override
//    public List<UserEntity> getAll() {
//        TypedQuery<UserEntity> query = entityManager.createQuery(
//                "select u from UserEntity u", UserEntity.class);
//        return query.getResultList();
//    }
//
//    @Override
//    public void update(UserEntity user) {
//        UserEntity managed = entityManager.find(UserEntity.class, user.getId());
//        if (managed != null) {
//            managed.setName(user.getName());
//            managed.setBirthDate(user.getBirthDate());
//            managed.setStatus(user.getStatus());
//        }
//    }
//
//    @Override
//    public boolean deleteById(UUID uuid) {
//        UserEntity user = entityManager.find(UserEntity.class, uuid);
//        if (user != null) {
//            entityManager.remove(user);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public void deleteAll() {
//        entityManager.createQuery("delete from UserEntity").executeUpdate();
//    }
//}