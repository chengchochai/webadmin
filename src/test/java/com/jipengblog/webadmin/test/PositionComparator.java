package com.jipengblog.webadmin.test;

import java.util.Comparator;

class PositionComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		return o1.getPosition().compareTo(o2.getPosition());
	}

}
