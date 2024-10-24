package travelAgency.DAO.MyBatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void update(T t,String[] params) {
        try (SqlSession session = sqlSessionFactory.openSession()) {

            Map<String, Object> mapParameters = new HashMap<>();
            mapParameters.put("id", params[0]);
            mapParameters.put("params", params);
            mapParameters.put("entity", t);
            session.update(entityClass.getSimpleName() + ".update", mapParameters);
            session.commit();
        }
    }

    @Override
    public void remove(T t) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.update(entityClass.getSimpleName()+".disableForeignKeyChecks");
            session.delete(entityClass.getSimpleName() + ".remove", t);
            session.update(entityClass.getSimpleName()+".enableForeignKeyChecks");
            session.commit();
        }
    }



}