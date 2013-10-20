package se.chalmers.krogkollen.utils;

import se.chalmers.krogkollen.R;

/*
 * This file is part of Krogkollen.
 *
 * Krogkollen is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Krogkollen is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Krogkollen.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * A class containing constant values
 * 
 * @author Jonathan Nilsfors
 * @author Oskar Karrman
 */
public class Constants {

	public static int			MAP_SHORT_QUEUE			= R.drawable.green_marker_bg;
	public static int			MAP_MEDIUM_QUEUE		= R.drawable.yellow_marker_bg;
	public static int			MAP_LONG_QUEUE			= R.drawable.red_marker_bg;
	public static int			MAP_NO_INFO_QUEUE		= R.drawable.gray_marker_bg;

	public static final String	ACTIVITY_FROM			= "FROM";

	public static final String	MAP_ACTIVITY_NAME		= "MapActivity";
	public static final String	HELP_ACTIVITY_NAME		= "HelpActivity";
	public static final String	LIST_ACTIVITY_NAME		= "ListActivity";
	public static final String	DETAILED_ACTIVITY_NAME	= "DetailedActivity";
	public static final String	SEARCH_ACTIVITY_NAME	= "SearchActivity";

	public static final String	SORT_MODE				= "SORT_MODE";

	public static final int		LIST_NUMBER_OF_TABS		= 3;

	/** Identifier for the intent used to start the activity for detailed view. */
	public static final String	MARKER_PUB_ID			= "se.chalmers.krogkollen.MARKER_PUB_ID";

	/** Key value used when sending intents */
	public static final String	MAP_PRESENTER_KEY		= "se.chalmers.krogkollen.MAP_PRESENTER_KEY";
}
