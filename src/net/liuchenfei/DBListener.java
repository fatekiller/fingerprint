package net.liuchenfei; /**
 * Created by liuchenfei on 2016/11/14.
 */

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener()
public class DBListener implements ServletContextListener,
        HttpSessionListener {

    String name="";
    String pass="";
    String url="";

    // Public constructor is required by servlet spec
    public DBListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("读取连接信息");
      try {
          ServletContext ctx=sce.getServletContext();
          name = ctx.getInitParameter("username");
          pass = ctx.getInitParameter("password");
          url = ctx.getInitParameter("url");
          Class.forName("com.mysql.jdbc.Driver");
          System.out.println(name+pass+url);
      }catch (Exception e){
          e.printStackTrace();
      }
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }

    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session=se.getSession();
        System.out.println("建立会话");
        Connection conn=(Connection) session.getAttribute("conn");
        try {
            if(conn==null||conn.isClosed()){
                System.out.println("建立连接");
                conn=DriverManager.getConnection(url, name, pass);
                session.setAttribute("conn",conn);
            }
        } catch (SQLException e) {
            System.out.println("初始化数据库出错");
            e.printStackTrace();
        }
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session=se.getSession();
        Connection conn=(Connection) session.getAttribute("conn");
        try {
            if(conn!=null&&!conn.isClosed()){
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("关闭数据库出错");
            e.printStackTrace();
        }
    }

}
