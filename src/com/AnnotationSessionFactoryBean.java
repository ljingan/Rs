package com;

import java.io.IOException;

import javax.persistence.Entity;

import org.hibernate.HibernateException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;

/**
 * 姝ょ被鐢ㄦ潵鎵弿绫昏矾涓嬬殑瀹炰綋绫?
 * 
 * @author Thomas Zheng
 * 
 */
public class AnnotationSessionFactoryBean
		extends
		org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean {

	private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
	private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
	private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(
			this.resourcePatternResolver);
	private final TypeFilter entityFilter = new AnnotationTypeFilter(
			Entity.class);
	private String resourcePattern = DEFAULT_RESOURCE_PATTERN;
	private String[] basePackages;

	public void setBasePackages(String... basePackages) {
		this.basePackages = basePackages;
	}

	@SuppressWarnings("unchecked")
	protected void postProcessAnnotationConfiguration(
			AnnotationConfiguration config) throws HibernateException {
		for (String basePackage : basePackages) {
			try {
				String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
						+ ClassUtils
								.convertClassNameToResourcePath(basePackage)
						+ "/" + this.resourcePattern;
				Resource[] resources = this.resourcePatternResolver
						.getResources(packageSearchPath);
				for (int i = 0; i < resources.length; i++) {
					Resource resource = resources[i];
					MetadataReader metadataReader = this.metadataReaderFactory
							.getMetadataReader(resource);
					if (isEntity(metadataReader)) {
						String classFileFullPath = resource.getURL().getPath();
						String basePackageResourcePath = ClassUtils
								.convertClassNameToResourcePath(basePackage);
						int startIndex = classFileFullPath
								.indexOf(basePackageResourcePath);
						final String classFilePath = classFileFullPath
								.substring(startIndex, classFileFullPath
										.length()
										- ClassUtils.CLASS_FILE_SUFFIX.length());
						Class entityClass = null;
						try {
							entityClass = ClassUtils
									.forName(ClassUtils
											.convertResourcePathToClassName(classFilePath));
						} catch (ClassNotFoundException e) {
							throw new HibernateException("路径  " + classFilePath
									+ " 没有实体类", e);
						}
						config.addAnnotatedClass(entityClass);
					}
				}
			} catch (IOException ex) {
				throw new HibernateException("路径不存在 " + basePackage, ex);
			}
		}
	}

	private boolean isEntity(MetadataReader metadataReader) throws IOException {
		if (entityFilter.match(metadataReader, this.metadataReaderFactory)) {
			return true;
		}
		return false;
	}
}
