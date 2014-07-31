package org.eclipse.gemini.blueprint.service.importer.support;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;

public enum CollectionTypeEnum {

	/** unused */
	//COLLECTION(OsgiServiceCollection.class),

	/**
	 * Spring-managed list. The returned collection with implement the
	 * {@link List} interface.
	 * 
	 * @see java.util.List
	 */
	LIST(List.class),

	/**
	 * Spring-managed set. The returned collection with implement the
	 * {@link Set} interface.
	 * 
	 * @see java.util.Set
	 */
	SET(Set.class),

	/**
	 * Spring-managed sorted list. The returned collection with implement the
	 * {@link List} interface.
	 * 
	 * @see java.lang.Comparable
	 * @see java.util.Comparator
	 * @see java.util.List
	 * @see java.util.SortedSet
	 */
	SORTED_LIST(List.class),

	/**
	 * Spring-managed sorted Set. The returned collection with implement the
	 * {@link SortedSet} interface.
	 * 
	 * @see java.lang.Comparable
	 * @see java.util.Comparator
	 * @see java.util.SortedSet
	 */
	SORTED_SET(SortedSet.class);

	/** collection type */
	private final Class<?> collectionClass;

	private CollectionTypeEnum(Class<?> collectionClass) {
		this.collectionClass = collectionClass;
	}

	/**
	 * Returns the actual collection class used underneath.
	 * 
	 * @return collection class
	 */
	public Class<?> getCollectionClass() {
		return collectionClass;
	}

}
