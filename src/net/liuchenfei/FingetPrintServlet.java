package net.liuchenfei;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FingetPrintServlet extends HttpServlet {

	public Connection conn = null;
	private Gson gson;
	String name="";
	String pass="";
	String url="";

	@Override
	public void init() throws ServletException {
		super.init();
		name = getInitParameter("username");
		pass = getInitParameter("password");
		url = getInitParameter("url");
		try {
			gson = new Gson();
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FingetPrintServlet() {
		super();
		System.out.println("init database");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4928326029189825652L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Connection conn = null;// 获取连接
		try {
			conn = DriverManager.getConnection(url, name, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("header"+req.getHeader("accept"));
		String userNameString = req.getParameter("userName");
		String deviceNameString = req.getParameter("deviceName");
		String data = req.getParameter("data");
		String fingerprint = req.getParameter("fingerprint");
		int i = 0;
		try {
			Features features = gson.fromJson(req.getParameter("features"),
					Features.class);
			PreparedStatement pst = conn
					.prepareStatement("insert into t_data(userName,passWord,data,fingerprint,date,user_agent,plugins,fonts,video,supercookies,http_accept,timezone,cookie_enabled) values (?,?,?,?,?,?,?,?,?,?,?,?,?);");
			pst.setString(1, userNameString);
			pst.setString(2, deviceNameString);
			pst.setString(3, data);
			pst.setString(4, fingerprint);
			pst.setString(5,
					new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			pst.setString(6, features.getUser_agent());
			pst.setString(7, features.getPlugins());
			pst.setString(8, features.getFonts());
			pst.setInt(9, features.isVideo() ? 1 : 0);
			pst.setString(10, features.getSupercookies());
			pst.setString(11, (String)req.getSession().getAttribute("accept"));
			pst.setInt(12, features.getTimezone());
			pst.setInt(13, features.isCookie_enabled() ? 1 : 0);
			i = pst.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		if (i == 1) {
			out.println("提取成功，谢谢");
		} else {
			out.println("提取失败");
		}
		out.flush();
		out.close();
	}

}
