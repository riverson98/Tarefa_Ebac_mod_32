package org.rcosta.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.rcosta.dao.ProductDao;
import org.rcosta.domain.Product;

import java.util.Collection;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public Product register(Product product) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("TaskEbac");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return product;
    }

    @Override
    public void delete(Product product) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("TaskEbac");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        product = entityManager.merge(product);
        entityManager.remove(product);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void deleteById(Long id) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("TaskEbac");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Product product = entityManager.find(Product.class, id);
        product = entityManager.merge(product);
        entityManager.remove(product);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public Collection<Product> findAll() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("TaskEbac");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder =entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root);

        TypedQuery<Product> typedQuery =
                entityManager.createQuery(query);
        Collection<Product> Products = typedQuery.getResultList();

        entityManager.close();
        entityManagerFactory.close();
        return Products;
    }

    @Override
    public Product findById(Long id) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("TaskEbac");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        var product = entityManager.find(Product.class, id);

        entityManager.close();
        entityManagerFactory.close();

        return product;
    }

    @Override
    public Product updatesInformation(Product product) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("TaskEbac");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.merge(product);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return product;
    }
}
