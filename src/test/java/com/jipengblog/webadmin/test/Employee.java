package com.jipengblog.webadmin.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

class Employee implements Comparable<Employee> {

	private int id;
	private String name;
	private Position position;

	public Employee(int _id, String _name, Position _position) {
		id = _id;
		name = _name;
		position = _position;
	}

	@Override
	public int compareTo(Employee o) {
		return new CompareToBuilder().append(id, o.id).toComparison();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public static void main(String[] args) {
		List<Employee> list = new ArrayList<Employee>(5);
		list.add(new Employee(1001, "zhang3", Position.Boss));
		list.add(new Employee(1006, "zhao7", Position.Manager));
		list.add(new Employee(1003, "wang5", Position.Manager));
		list.add(new Employee(1002, "li4", Position.Staff));
		list.add(new Employee(1005, "ma6", Position.Staff));
		Collections.sort(list);
		//Collections.sort(list,new PositionComparator());
		//Collections.sort(list,Collections.reverseOrder(new PositionComparator()));
		for (Employee e : list) {
			System.out.println(e);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
