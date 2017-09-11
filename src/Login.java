import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/Selenium")
public class Login {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		String input = read_input_servlet(req);

		HashMap<String, String> out_map = get_value(input);

	}

	public static String read_input_servlet(HttpServletRequest req) {
		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = req.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

		return stringBuilder.toString();

	}

	public static HashMap<String, String> get_value(String input) {
		HashMap<String, String> input_map = new HashMap<String, String>();
		// TODO Auto-generated method stub
		input = input.substring(1, input.length() - 1);
		String[] s = input.split("},");
		for (int i = 0; i < s.length; i++) {
			s[i] = s[i] + "}";
			JSONObject jObj = new JSONObject(s[i]);
			input_map.put(jObj.get("name").toString(), jObj.get("value").toString());

		}

		return input_map;
	}
	
	
	 public static void main(String[] args) {
		
		 String input =
	 "[{\"username\":\"type_e\",\"password\":\"Both\"}}]";
		 

		HashMap<String, String> out_map = get_value(input);

	
	 
	 }
	 
}
