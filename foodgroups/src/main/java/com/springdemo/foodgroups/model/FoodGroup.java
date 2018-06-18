package com.springdemo.foodgroups.model;

public class FoodGroup {
	int id;
	String name;
	String description;

	public FoodGroup(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public FoodGroup() {
	}

	public FoodGroup(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return "Name : " + name + ", Description : " + description;
	}
}
