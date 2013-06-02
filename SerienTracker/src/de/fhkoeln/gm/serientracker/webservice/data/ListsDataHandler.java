package de.fhkoeln.gm.serientracker.webservice.data;

import java.util.List;

import de.fhkoeln.gm.serientracker.jaxb.Lists;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.webservice.utils.FileHandler;

public class ListsDataHandler {
	private static final String XMLFILE = "Database/lists.xml";

	private FileHandler<Lists> filehandler;

	private Lists data;
	private List<de.fhkoeln.gm.serientracker.jaxb.List> lists;

	/**
	 * Constructor. Inits file handler and loads the XMLFILE.
	 */
	public ListsDataHandler() {
		Logger.log( "ListsDataHandler called" );

		this.filehandler = new FileHandler<Lists>( Lists.class );

		this.data = (Lists) filehandler.readXML( XMLFILE );
		this.lists = this.data.getList();
	}

	/**
	 * Saves object to XML file.
	 *
	 * @return boolean
	 */
	private boolean save() {
		Logger.log( "Lists saved to file" );

		return this.filehandler.writeXML( this.data, XMLFILE );
	}

	/**
	 * Returns lists.
	 *
	 * @return Lists
	 */
	public Lists getLists() {
		return this.data;
	}

	/**
	 * Checks if a list exists by ID.
	 *
	 * @param String id
	 * @return boolean
	 */
	public boolean ListExistsByID( String id ) {
		for ( de.fhkoeln.gm.serientracker.jaxb.List list : this.lists )
			if ( list.getListID().equals( id ) )
				return true;

		return false;
	}

	/**
	 * Returns a list by ID.
	 *
	 * @param String id
	 * @return List
	 */
	public de.fhkoeln.gm.serientracker.jaxb.List getListByID( String id ) {
		for ( de.fhkoeln.gm.serientracker.jaxb.List list : this.lists )
			if ( list.getListID().equals( id ) )
				return list;

		return null;
	}
	
	
	/**
	 * Checks if a list of a type exists.
	 *
	 * @param String id
	 * @return boolean
	 */
	public boolean ListExistsByType( String type ) {
		for ( de.fhkoeln.gm.serientracker.jaxb.List list : this.lists )
			if ( list.getType().equals( type ) )
				return true;

		return false;
	}

	/**
	 * Returns a list by type.
	 *
	 * @param String genre
	 * @return List
	 */
	public de.fhkoeln.gm.serientracker.jaxb.List getListByType( String type ) {
		for ( de.fhkoeln.gm.serientracker.jaxb.List list : this.lists )
			if ( list.getType().equals( type ) )
				return list;

		return null;
	}
	
	
	/**
	 * Checks if a list with the name of a genre.
	 *
	 * @param String id
	 * @return boolean
	 */
	public boolean ListExistsBySpecificGenre( String name) {
		for ( de.fhkoeln.gm.serientracker.jaxb.List list : this.lists )
			if ( list.getName().equals(name) )
				return true;

		return false;
	}

	/**
	 * Returns a list by genre depending on the name.
	 *
	 * @param String genre
	 * @return List
	 */
	public de.fhkoeln.gm.serientracker.jaxb.List getListBySpecificGenre( String name ) {
		for ( de.fhkoeln.gm.serientracker.jaxb.List list : this.lists )
			if ( list.getName().equals( name ) )
				return list;

		return null;
	}
	
	

	/**
	 * Adds a new list.
	 *
	 * @param List list
	 * @return boolean
	 */
	public boolean addList( de.fhkoeln.gm.serientracker.jaxb.List list ) {
		this.lists.add( list );

		return this.save();
	}

	/**
	 * Updates an existing list.
	 *
	 * @param List newList
	 * @return boolean
	 */
	public boolean updateList( de.fhkoeln.gm.serientracker.jaxb.List newList ) {
		String id = newList.getListID();

		int i = 0;
		for ( de.fhkoeln.gm.serientracker.jaxb.List list : this.lists ) {
			if ( list.getListID().equals( id ) ) {
				// TODO: Only replace the fields of serie, otherwise seasons/episodes
				// can be accidentally deleted.
				this.lists.set( i, newList );

				return this.save();
			}

			i++;
		}

		return false;
	}

	/**
	 * Removes and existing list.
	 *
	 * @param String id
	 * @return boolean
	 */
	public boolean removeList( String id ) {
		de.fhkoeln.gm.serientracker.jaxb.List list = getListByID( id );

		if ( list == null )
			return false;

		this.lists.remove( list );

		return this.save();
	}

}
