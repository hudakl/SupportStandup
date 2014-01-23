/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.support.standup.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.support.standup.model.StandUpEntry;
import com.liferay.support.standup.service.GlobalInformationLocalService;
import com.liferay.support.standup.service.GlobalInformationService;
import com.liferay.support.standup.service.StandUpEntryLocalService;
import com.liferay.support.standup.service.StandUpEntryService;
import com.liferay.support.standup.service.persistence.GlobalInformationPersistence;
import com.liferay.support.standup.service.persistence.StandUpEntryPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * The base implementation of the stand up entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.support.standup.service.impl.StandUpEntryLocalServiceImpl}.
 * </p>
 *
 * @author Daniel Javorszky
 * @see com.liferay.support.standup.service.impl.StandUpEntryLocalServiceImpl
 * @see com.liferay.support.standup.service.StandUpEntryLocalServiceUtil
 * @generated
 */
public abstract class StandUpEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements StandUpEntryLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.support.standup.service.StandUpEntryLocalServiceUtil} to access the stand up entry local service.
	 */

	/**
	 * Adds the stand up entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param standUpEntry the stand up entry
	 * @return the stand up entry that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public StandUpEntry addStandUpEntry(StandUpEntry standUpEntry)
		throws SystemException {
		standUpEntry.setNew(true);

		return standUpEntryPersistence.update(standUpEntry, false);
	}

	/**
	 * Creates a new stand up entry with the primary key. Does not add the stand up entry to the database.
	 *
	 * @param entryId the primary key for the new stand up entry
	 * @return the new stand up entry
	 */
	public StandUpEntry createStandUpEntry(long entryId) {
		return standUpEntryPersistence.create(entryId);
	}

	/**
	 * Deletes the stand up entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the stand up entry
	 * @return the stand up entry that was removed
	 * @throws PortalException if a stand up entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	public StandUpEntry deleteStandUpEntry(long entryId)
		throws PortalException, SystemException {
		return standUpEntryPersistence.remove(entryId);
	}

	/**
	 * Deletes the stand up entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param standUpEntry the stand up entry
	 * @return the stand up entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	public StandUpEntry deleteStandUpEntry(StandUpEntry standUpEntry)
		throws SystemException {
		return standUpEntryPersistence.remove(standUpEntry);
	}

	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(StandUpEntry.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return standUpEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return standUpEntryPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return standUpEntryPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return standUpEntryPersistence.countWithDynamicQuery(dynamicQuery);
	}

	public StandUpEntry fetchStandUpEntry(long entryId)
		throws SystemException {
		return standUpEntryPersistence.fetchByPrimaryKey(entryId);
	}

	/**
	 * Returns the stand up entry with the primary key.
	 *
	 * @param entryId the primary key of the stand up entry
	 * @return the stand up entry
	 * @throws PortalException if a stand up entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public StandUpEntry getStandUpEntry(long entryId)
		throws PortalException, SystemException {
		return standUpEntryPersistence.findByPrimaryKey(entryId);
	}

	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return standUpEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the stand up entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of stand up entries
	 * @param end the upper bound of the range of stand up entries (not inclusive)
	 * @return the range of stand up entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<StandUpEntry> getStandUpEntries(int start, int end)
		throws SystemException {
		return standUpEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of stand up entries.
	 *
	 * @return the number of stand up entries
	 * @throws SystemException if a system exception occurred
	 */
	public int getStandUpEntriesCount() throws SystemException {
		return standUpEntryPersistence.countAll();
	}

	/**
	 * Updates the stand up entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param standUpEntry the stand up entry
	 * @return the stand up entry that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public StandUpEntry updateStandUpEntry(StandUpEntry standUpEntry)
		throws SystemException {
		return updateStandUpEntry(standUpEntry, true);
	}

	/**
	 * Updates the stand up entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param standUpEntry the stand up entry
	 * @param merge whether to merge the stand up entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the stand up entry that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public StandUpEntry updateStandUpEntry(StandUpEntry standUpEntry,
		boolean merge) throws SystemException {
		standUpEntry.setNew(false);

		return standUpEntryPersistence.update(standUpEntry, merge);
	}

	/**
	 * Returns the global information local service.
	 *
	 * @return the global information local service
	 */
	public GlobalInformationLocalService getGlobalInformationLocalService() {
		return globalInformationLocalService;
	}

	/**
	 * Sets the global information local service.
	 *
	 * @param globalInformationLocalService the global information local service
	 */
	public void setGlobalInformationLocalService(
		GlobalInformationLocalService globalInformationLocalService) {
		this.globalInformationLocalService = globalInformationLocalService;
	}

	/**
	 * Returns the global information remote service.
	 *
	 * @return the global information remote service
	 */
	public GlobalInformationService getGlobalInformationService() {
		return globalInformationService;
	}

	/**
	 * Sets the global information remote service.
	 *
	 * @param globalInformationService the global information remote service
	 */
	public void setGlobalInformationService(
		GlobalInformationService globalInformationService) {
		this.globalInformationService = globalInformationService;
	}

	/**
	 * Returns the global information persistence.
	 *
	 * @return the global information persistence
	 */
	public GlobalInformationPersistence getGlobalInformationPersistence() {
		return globalInformationPersistence;
	}

	/**
	 * Sets the global information persistence.
	 *
	 * @param globalInformationPersistence the global information persistence
	 */
	public void setGlobalInformationPersistence(
		GlobalInformationPersistence globalInformationPersistence) {
		this.globalInformationPersistence = globalInformationPersistence;
	}

	/**
	 * Returns the stand up entry local service.
	 *
	 * @return the stand up entry local service
	 */
	public StandUpEntryLocalService getStandUpEntryLocalService() {
		return standUpEntryLocalService;
	}

	/**
	 * Sets the stand up entry local service.
	 *
	 * @param standUpEntryLocalService the stand up entry local service
	 */
	public void setStandUpEntryLocalService(
		StandUpEntryLocalService standUpEntryLocalService) {
		this.standUpEntryLocalService = standUpEntryLocalService;
	}

	/**
	 * Returns the stand up entry remote service.
	 *
	 * @return the stand up entry remote service
	 */
	public StandUpEntryService getStandUpEntryService() {
		return standUpEntryService;
	}

	/**
	 * Sets the stand up entry remote service.
	 *
	 * @param standUpEntryService the stand up entry remote service
	 */
	public void setStandUpEntryService(StandUpEntryService standUpEntryService) {
		this.standUpEntryService = standUpEntryService;
	}

	/**
	 * Returns the stand up entry persistence.
	 *
	 * @return the stand up entry persistence
	 */
	public StandUpEntryPersistence getStandUpEntryPersistence() {
		return standUpEntryPersistence;
	}

	/**
	 * Sets the stand up entry persistence.
	 *
	 * @param standUpEntryPersistence the stand up entry persistence
	 */
	public void setStandUpEntryPersistence(
		StandUpEntryPersistence standUpEntryPersistence) {
		this.standUpEntryPersistence = standUpEntryPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the resource remote service.
	 *
	 * @return the resource remote service
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * Sets the resource remote service.
	 *
	 * @param resourceService the resource remote service
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * Returns the resource persistence.
	 *
	 * @return the resource persistence
	 */
	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	/**
	 * Sets the resource persistence.
	 *
	 * @param resourcePersistence the resource persistence
	 */
	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		PersistedModelLocalServiceRegistryUtil.register("com.liferay.support.standup.model.StandUpEntry",
			standUpEntryLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.support.standup.model.StandUpEntry");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
	}

	protected Class<?> getModelClass() {
		return StandUpEntry.class;
	}

	protected String getModelClassName() {
		return StandUpEntry.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = standUpEntryPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = GlobalInformationLocalService.class)
	protected GlobalInformationLocalService globalInformationLocalService;
	@BeanReference(type = GlobalInformationService.class)
	protected GlobalInformationService globalInformationService;
	@BeanReference(type = GlobalInformationPersistence.class)
	protected GlobalInformationPersistence globalInformationPersistence;
	@BeanReference(type = StandUpEntryLocalService.class)
	protected StandUpEntryLocalService standUpEntryLocalService;
	@BeanReference(type = StandUpEntryService.class)
	protected StandUpEntryService standUpEntryService;
	@BeanReference(type = StandUpEntryPersistence.class)
	protected StandUpEntryPersistence standUpEntryPersistence;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private StandUpEntryLocalServiceClpInvoker _clpInvoker = new StandUpEntryLocalServiceClpInvoker();
}