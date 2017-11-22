package com.module;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class BaseDao<T, PK extends Serializable> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> entityClass;

    public BaseDao() {
        Type type = getClass().getGenericSuperclass();
        Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
        entityClass = (Class<T>) trueType;
        initialize();
    }

    public void initialize() {
        try {
            // log.error(getEntityClass().getSimpleName());
            readXml(getEntityClass().getSimpleName());
        } catch (Exception e) {
        }
    }

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    protected Class<T> getEntityClass() {
        return entityClass;
    }

    public T get(PK id) {

        return (T) getCurrentSession().get(getEntityClass(), id);
    }

    public List<T> loadAll() {
//        return (List<T>) getCurrentSession().findAll(getEntityClass());
        return null;
    }

    public void update(T entity) {
        getCurrentSession().update(entity);
    }

    public void save(T entity) {
        getCurrentSession().save(entity);
    }

    public void saveAll(Collection<T> entities) {
        for (T entity : entities) {
            save(entity);
        }
    }

    public void deleteByKey(PK id) {
        StringBuffer queryString = new StringBuffer("DELETE FROM ").append(
                getEntityClass().getSimpleName()).append(
                " entity WHERE entity.").append(getIdName()).append("=?");
        // bulkUpdate(queryString.toString(), new Object[] { id });
    }

    protected String getIdName() {
        return "id";
    }

    public List<T> findAll() {
        return null;
    }

    public boolean contain(PK id) {
        return false;
    }

    private void readXml(String simpleName) throws Exception {
        simpleName = "Pet";
        // List<T> list = (List<T>) XmlUtil.readXml2List(getClass()
        // .getResourceAsStream("/xml/" + simpleName + ".xml"));
        // List<T> temList = new ArrayList<T>();
        // Map<Object, T> tempDatas = new HashMap<Object, T>();
        // for (T value : list) {
        // PK id = getId(value);
        // if (id == null || !tempDatas.containsKey(id)) {
        // // û����ͬ��ʵ����
        // temList.add(value);
        // }
        // tempDatas.put(id, value);
        // }
        // dataList = temList;
        // datas = tempDatas;
    }
}
