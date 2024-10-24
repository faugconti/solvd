package travelAgency.DAO.myBatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import travelAgency.DAO.DAO;

public class MyBatisDAO<T> implements DAO<T> {

    protected SqlSessionFactory sqlSessionFactory;
    protected Class<T> entityClass;

    public MyBatisDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
        try {
            String resource = "MyBatis/batisConfig.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error initializing SqlSessionFactory", e);
        }
    }

    @Override
    public T getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne(entityClass.getSimpleName() +  ".findById", id);
        }
    }

    @Override
    public List<T> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList(entityClass.getSimpleName() + ".findAll");
        }
    }

    @Override
    public void save(T t) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.insert(entityClass.getSimpleName() + ".add", t);
            session.commit();
        }
    }

    @Override
    public void update(T t, String[] params) {

    }

    @Override
    public void remove(T t) {

    }



}