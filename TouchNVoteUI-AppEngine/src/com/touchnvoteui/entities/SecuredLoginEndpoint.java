package com.touchnvoteui.entities;

import com.touchnvoteui.EMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "securedloginendpoint", namespace = @ApiNamespace(ownerDomain = "touchnvoteui.com", ownerName = "touchnvoteui.com", packagePath = "entities"))
public class SecuredLoginEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listSecuredLogin")
	public CollectionResponse<SecuredLogin> listSecuredLogin(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<SecuredLogin> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr
					.createQuery("select from SecuredLogin as SecuredLogin");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<SecuredLogin>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (SecuredLogin obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<SecuredLogin> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getSecuredLogin")
	public SecuredLogin getSecuredLogin(@Named("id") String id) {
		EntityManager mgr = getEntityManager();
		SecuredLogin securedlogin = null;
		try {
			securedlogin = mgr.find(SecuredLogin.class, id);
		} finally {
			mgr.close();
		}
		return securedlogin;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param securedlogin the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertSecuredLogin")
	public SecuredLogin insertSecuredLogin(SecuredLogin securedlogin) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsSecuredLogin(securedlogin)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(securedlogin);
		} finally {
			mgr.close();
		}
		return securedlogin;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param securedlogin the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateSecuredLogin")
	public SecuredLogin updateSecuredLogin(SecuredLogin securedlogin) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsSecuredLogin(securedlogin)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(securedlogin);
		} finally {
			mgr.close();
		}
		return securedlogin;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeSecuredLogin")
	public void removeSecuredLogin(@Named("id") String id) {
		EntityManager mgr = getEntityManager();
		try {
			SecuredLogin securedlogin = mgr.find(SecuredLogin.class, id);
			mgr.remove(securedlogin);
		} finally {
			mgr.close();
		}
	}

	private boolean containsSecuredLogin(SecuredLogin securedlogin) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			SecuredLogin item = mgr.find(SecuredLogin.class,
					securedlogin.getNetID());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
