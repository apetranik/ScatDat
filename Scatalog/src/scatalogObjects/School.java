package scatalogObjects;

import java.util.ArrayList;

public class School {
	private String name;
	private ArrayList<Department> depts;
	
	public School(String name)
	{
		this.name = name;
		depts = new ArrayList<Department>();

	}
	public void addDept(Department dept)
	{
		depts.add(dept);
	}
	
	public ArrayList<Department> getDepts()
	{
		return depts;
	}
	public String getName()
	{
		return name;
	}
}
