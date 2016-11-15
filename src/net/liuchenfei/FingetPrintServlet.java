package net.liuchenfei;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FingetPrintServlet extends HttpServlet {

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
			conn =(Connection) req.getSession().getAttribute("conn");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson=new Gson();
		System.out.println("header"+req.getHeader("accept"));
		String userNameString = req.getParameter("userName");
		String deviceNameString = req.getParameter("deviceName");
		String data = req.getParameter("data");
        List<Data> jsd=gson.fromJson(data,new TypeToken<List<Data>>(){}.getType());
        String fingerprint = req.getParameter("fingerprint");
		int i = 0;
		try {
			Features features = gson.fromJson(req.getParameter("features"),
					Features.class);
			PreparedStatement pst = conn
					.prepareStatement("insert into t_data(userName,deviceName,data,fingerprint,date,user_agent,plugins,fonts,video,supercookies,http_accept,timezone,cookie_enabled) values (?,?,?,?,?,?,?,?,?,?,?,?,?);");
			pst.setString(1, userNameString);
			pst.setString(2, deviceNameString);
			pst.setString(3, data);
			pst.setString(4, fingerprint);
			pst.setString(5,
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pst.setString(6, getValue(jsd,"user_agent"));
			pst.setString(7, getValue(jsd,"regular_plugins"));
			pst.setString(8, features.getFonts());
			pst.setInt(9, features.isVideo() ? 1 : 0);
			pst.setString(10, features.getSupercookies());
			pst.setString(11, (String)req.getSession().getAttribute("accept"));
			pst.setFloat(12, Float.valueOf(getValue(jsd,"timezone_offset")));
			pst.setInt(13, features.isCookie_enabled() ? 1 : 0);
			i = pst.executeUpdate();
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

	private String getValue(List<Data> datas,String key){
        for(Data d :datas){
            if(d.key.equals(key)){
                return d.value.toString();
            }
        }
        return null;
    }

    class Data{
        String key;
        Object value;
    }


}
