package net.liuchenfei;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuchenfei on 2016/11/14.
 */
@WebServlet(name = "StatServlet",urlPatterns = "/getData")
public class StatServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson=new Gson();
        Connection conn= (Connection) request.getSession().getAttribute("conn");
        int total=0;
        Map<String,Object> data=new HashMap<String,Object>();
        int rows=Integer.valueOf(request.getParameter("rows"));
        int page=Integer.valueOf(request.getParameter("page"));
        List<TableItems> list=new ArrayList<>();
        try {
            PreparedStatement pst2=conn.prepareStatement("SELECT COUNT(*) FROM t_data");
            ResultSet totalRs=pst2.executeQuery();
            if(totalRs.next()){
                total=totalRs.getInt(1);
            }
            PreparedStatement pst=conn.prepareStatement("SELECT * FROM t_data limit "+((page-1)*rows)+","+rows);
            ResultSet rs=pst.executeQuery();
            while (rs.next()){
                TableItems ti=new TableItems(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),//data字段太长，设置不加载
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getFloat(12),
                        rs.getInt(13)
                );
                list.add(ti);
            }
            rs.close();
            data.put("total",total);
            data.put("rows",list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PrintWriter out=response.getWriter();
        out.println(gson.toJson(data));
        out.flush();
        out.close();
    }
}
