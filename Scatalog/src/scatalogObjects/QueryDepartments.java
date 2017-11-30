package scatalogObjects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QueryDepartments {
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	
	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}
	
	public ArrayList<Department> getAllDepartments() {
		ArrayList<Department> AllDepartments = new ArrayList<Department>();
		try {
			JSONObject json = readJsonFromUrl("http://web-app.usc.edu/web/soc/api/departments/20173");
			JSONArray outerDepartment = json.getJSONArray("department");
			
			for (int i = 0; i < outerDepartment.length(); i++) {
				try {
					JSONArray innerDepartment = outerDepartment.getJSONObject(i).getJSONArray("department");
					String schoolName = outerDepartment.getJSONObject(i).getString("code");
					for(int j = 0; j < innerDepartment.length(); j++) {
//						System.out.println(j + " " + innerDepartment.length());
						String code = innerDepartment.getJSONObject(j).getString("code");
						String deptName = innerDepartment.getJSONObject(j).getString("name");
//						System.out.println(code);
						String url = "http://web-app.usc.edu/web/soc/api/classes/" + code + "/20173";
						Department department = new Department(code, deptName, schoolName);
//						System.out.println(url);
						try {
							JSONObject json2 = readJsonFromUrl(url);
							JSONObject offeredCourses = json2.getJSONObject("OfferedCourses");
							JSONArray course = offeredCourses.getJSONArray("course");
							for (int k = 0; k < course.length(); k++) {
								JSONObject currObj = course.getJSONObject(k);
								JSONObject courseInfo = currObj.getJSONObject("CourseData");
								int number = Integer.parseInt(courseInfo.getString("number"));
								String prefix = courseInfo.getString("prefix");
								String name = courseInfo.getString("title");
								String description = courseInfo.getString("description");
								department.addCourse(new Course(name, number, description, prefix, 0, 0, 0, 0, 0, 0));
							}
							AllDepartments.add(department);
						} catch(Exception e) {
							
						}
					}
				} catch(org.json.JSONException jse) {
				
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return AllDepartments;
	}
	public ArrayList<School> getAllSchools()
	{
		ArrayList<Department> departments = this.getAllDepartments();
		ArrayList<School> schools = new ArrayList<School>();
		
		School dornsife = new School("Dornsife College of Letters, Arts and Sciences");
		schools.add(dornsife);
		for(int i = 0; i < 58; i++)
		{
			dornsife.addDept(departments.get(i));
			System.out.println("dept: " + departments.get(i).getName());
			
		}
		
		School accounting = new School("Leventhal School of Accounting");
		dornsife.addDept(departments.get(58));
			System.out.println("dept: " + departments.get(58).getName());
			
		
		
		return schools;

		
		
	}
}

