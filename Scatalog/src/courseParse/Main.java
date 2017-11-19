package courseParse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;

public class Main {

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

	public static void parseDepartment(String url){
		
		System.out.println(url);
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/scatdat?user=root&password=root&useSSL=false");
		}catch(SQLException sqle) {
			System.out.println (sqle.getMessage());
		}catch(ClassNotFoundException cnfe) {
			System.out.println (cnfe.getMessage());
		}
		java.sql.PreparedStatement ps = null;
		try {
		JSONObject json = readJsonFromUrl(url);
		JSONObject offeredCourses = json.getJSONObject("OfferedCourses");
		JSONArray course = offeredCourses.getJSONArray("course");
		
		for (int i = 0; i < course.length(); i++) {
			try {
				ps = (java.sql.PreparedStatement) conn.prepareStatement("INSERT INTO course(number, prefix, type, name, numRegistered, overallSCore, enjoyment, difficulty, value, workload, description) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject currObj = course.getJSONObject(i);
			JSONObject courseInfo = currObj.getJSONObject("CourseData");
			String number = courseInfo.getString("number");
			String prefix = courseInfo.getString("prefix");
			int type = 0;
			String name = courseInfo.getString("title");
			int numRegistered = 0;
			int overallSCore = 0;
			int enjoyment = 0;
			int difficulty = 0;
			int value = 0;
			int workload = 0;
			String description = courseInfo.getString("description");
			if(prefix.equals("http://web-app.usc.edu/web/soc/api/classes/MATH/20173")) {
				System.out.println(prefix+number+": "+name);
			}
			try {
				ps.setString(1, number);
				ps.setString(2, prefix);
				ps.setInt(3, type);
				ps.setString(4, name);
				ps.setInt(5, numRegistered);
				ps.setInt(6, overallSCore);
				ps.setInt(7, enjoyment);
				ps.setInt(8, difficulty);
				ps.setInt(9, value);
				ps.setInt(10, workload);
				ps.setString(11, description);
				ps.executeUpdate();
				System.out.println(prefix+number+": "+name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		} catch(Exception e) {
			
		}
	}

	public static void main(String[] args) {
		try {
			JSONObject json = readJsonFromUrl("http://web-app.usc.edu/web/soc/api/departments/20173");
			JSONArray outerDepartment = json.getJSONArray("department");
			for (int i = 0; i < outerDepartment.length(); i++) {
				try {
				JSONArray innerDepartment = outerDepartment.getJSONObject(i).getJSONArray("department");
				
					for(int j = 0; j<innerDepartment.length(); j++) {
						System.out.println(j + " " + innerDepartment.length());
						String code = innerDepartment.getJSONObject(j).getString("code");
						System.out.println(code);
						String url = "http://web-app.usc.edu/web/soc/api/classes/" + code + "/20173";
						parseDepartment(url);
					}
				} catch(org.json.JSONException jse) {
					
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println(json.get("id"));
	}
}
