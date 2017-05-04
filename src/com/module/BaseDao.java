package com.module;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class BaseDao<T, PK extends Serializable> extends
		HibernateDaoSupport {
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

	protected Class<T> getEntityClass() {
		return entityClass;
	}

	public T get(PK id) {

		return (T) getHibernateTemplate().get(getEntityClass(), id);
	}

	public List<T> loadAll() {
		return (List<T>) getHibernateTemplate().loadAll(getEntityClass());
	}

	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	public void save(T entity) {
		getHibernateTemplate().save(entity);
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

	private void readXml(String simpleName) throws Exception {
		simpleName = "Pet";
		// List<T> list = (List<T>) XmlUtil.readXml2List(getClass()
		// .getResourceAsStream("/xml/" + simpleName + ".xml"));
		// List<T> temList = new ArrayList<T>();
		// Map<Object, T> tempDatas = new HashMap<Object, T>();
		// for (T value : list) {
		// PK id = getId(value);
		// if (id == null || !tempDatas.containsKey(id)) {
		// // 没有相同的实体类
		// temList.add(value);
		// }
		// tempDatas.put(id, value);
		// }
		// dataList = temList;
		// datas = tempDatas;
	}
}
