package edu.osu.cse5234.business.view;

import java.util.List;

public class Inventory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -872344271112964171L;
	private List<Item> list;

	public List<Item> getList() {
		return list;
	}

	public void setList(List<Item> items) {
		this.list = items;
	}
	
}
