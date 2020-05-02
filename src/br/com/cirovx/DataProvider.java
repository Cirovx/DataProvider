package br.com.cirovx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataProvider extends HttpServlet {

	private static final String SKY_API_URL = "https://api.redesky.com/servers/online/";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse httpResponse) throws ServletException, IOException {

		URL url = new URL(SKY_API_URL);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestProperty("User-Agent", "Chrome/23.0.1271.95");
		connection.setRequestMethod("GET");
		connection.setReadTimeout(5000);

		int responseCode = connection.getResponseCode();
		String response;

		if (responseCode == 200) {

			BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			response = buffer.readLine();

		} else {
			response = "ERROR " + responseCode;
		}

		PrintWriter writer = httpResponse.getWriter();
		writer.print(response);

	}

}
