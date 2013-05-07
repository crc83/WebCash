package com.siriusif.helper;

import com.siriusif.model.DinnerTable;

/**
 * This class helps to create single item for test purposes
 * @author sbelei
 *
 */
public class ModelFactory {
	
	/**
	 * Return diner table with following settings:
	 * <pre>
	 * name  : table #1
	 * description : description
	 * top   : 47
	 * left  : 31
	 * height: 37
	 * width : 88
	 * </pre>
	 * @return ready to use DinnerTable
	 */
	public static DinnerTable getDinnerTableInstance(){
		DinnerTable table = new DinnerTable();
		table.setName("table #1");
		table.setDescription("description");
		table.setHall(null);
		table.setTop(47);
		table.setLeft(31);
		table.setHeight(37);
		table.setWidth(88);
		return table;
		
	}

}
