package com.touchnvoteui.entities;

import com.touchnvoteui.entities.PMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JDOCursorHelper;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@Api(name = "userdetailsendpoint", namespace = @ApiNamespace(ownerDomain = "touchnvoteui.com", ownerName = "touchnvoteui.com", packagePath = "entities"))
public class UserDetailsEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listUserDetails")
	public CollectionResponse<UserDetails> listUserDetails(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		PersistenceManager mgr = null;
		Cursor cursor = null;
		List<UserDetails> execute = null;

		try {
			mgr = getPersistenceManager();
			Query query = mgr.newQuery(UserDetails.class);
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				HashMap<String, Object> extensionMap = new HashMap<String, Object>();
				extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
				query.setExtensions(extensionMap);
			}

			if (limit != null) {
				query.setRange(0, limit);
			}

			execute = (List<UserDetails>) query.execute();
			cursor = JDOCursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (UserDetails obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<UserDetails> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getUserDetails")
	public UserDetails getUserDetails(@Named("id") Long id) {
		PersistenceManager mgr = getPersistenceManager();
		UserDetails userdetails = null;
		try {
			userdetails = mgr.getObjectById(UserDetails.class, id);
		} finally {
			mgr.close();
		}
		return userdetails;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param userdetails the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertUserDetails")
	public UserDetails insertUserDetails(UserDetails userdetails) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			if (containsUserDetails(userdetails)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.makePersistent(userdetails);
		} finally {
			mgr.close();
		}
		return userdetails;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param userdetails the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateUserDetails")
	public UserDetails updateUserDetails(UserDetails userdetails) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			if (!containsUserDetails(userdetails)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.makePersistent(userdetails);
		} finally {
			mgr.close();
		}
		return userdetails;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeUserDetails")
	public void removeUserDetails(@Named("id") Long id) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			UserDetails userdetails = mgr.getObjectById(UserDetails.class, id);
			mgr.deletePersistent(userdetails);
		} finally {
			mgr.close();
		}
	}

	private boolean containsUserDetails(UserDetails userdetails) {
		PersistenceManager mgr = getPersistenceManager();
		boolean contains = true;
		try {
			mgr.getObjectById(UserDetails.class, userdetails.getNetID());
		} catch (javax.jdo.JDOObjectNotFoundException ex) {
			contains = false;
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static PersistenceManager getPersistenceManager() {
		return PMF.get().getPersistenceManager();
	}

}
