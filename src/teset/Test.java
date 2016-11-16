package teset;


import java.sql.*;

/**
 * Created by liuchenfei on 2016/11/14.
 */
public class Test {
    public static  void main(String args[]){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1/fingerprint","root","liuchenfei");
//            PreparedStatement pst=connection.prepareStatement("select * from test limit ?,?");
//            pst.setInt(1,0);
//            pst.setInt(2,22);
//            pst.setMaxRows(5);
//            ResultSet rs=pst.executeQuery();
            ResultSet rs=pageWithLimit(2,10,connection);
            while (rs.next()){
                int id=rs.getInt(1);
                System.out.println(id);
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param page 获取第几页
     * @param rows 每一页获取几条数据
     * @param conn 数据库连接
     * */
    public static ResultSet pageWithAbsolute(int page,int rows,Connection conn) throws SQLException {
        PreparedStatement pst=conn.prepareStatement("SELECT * FROM test ");
        pst.setMaxRows(page*rows);//设置为需要获取的最后一个数据
        ResultSet rs=pst.executeQuery();
        rs.absolute((page-1)*rows);//设置为需要获取的第一个数据
        return rs;
    }

    /**
     * @param page 获取第几页
     * @param rows 每一页获取几条数据
     * @param conn 数据库连接
     * */
    public static ResultSet pageWithRelative(int page,int rows,Connection conn) throws SQLException {
        PreparedStatement pst=conn.prepareStatement("SELECT * FROM test ");
        pst.setMaxRows(page*rows);//设置为需要获取的最后一个数据
        ResultSet rs=pst.executeQuery();
        rs.relative((page-1)*rows);//设置为需要获取的第一个数据
        return rs;
    }

    /**
     * @param page 获取第几页
     * @param rows 每一页获取几条数据
     * @param conn 数据库连接
     * */
    public static ResultSet pageWithLimit(int page,int rows,Connection conn) throws SQLException {
        PreparedStatement pst=conn.prepareStatement("SELECT * FROM test limit ?,?");
        pst.setInt(1,(page-1)*rows);
        pst.setInt(2,rows);
        ResultSet rs=pst.executeQuery();
        return rs;
    }
}
