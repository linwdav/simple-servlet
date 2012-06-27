import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TodoList {

  private Connection con = null;
  private Statement stmt = null;

  public TodoList(String username, String password, String dbms, String server, int port,
      String database) throws SQLException, ClassNotFoundException {
    String jdbcUrl = "jdbc:" + dbms + "://" + server + ":" + port + "/" + database;
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection(jdbcUrl, username, password);
    stmt = con.createStatement();
    createDatabase();
  }

  private void createDatabase() throws SQLException {
    //stmt.execute("drop database if exists todo");
    stmt.execute("create database if not exists todo");
    stmt.execute("use todo");
    stmt.execute("create table if not exists todoList (id int not null auto_increment, item varchar(255) not null, primary key(id))");
  }

  public void addItem(String item) throws SQLException {
    String sql = "insert into todoList (item) values ('" + item + "')";
    stmt.execute(sql);
  }

  public void addItems(List<String> itemList) throws SQLException {
    String sql = "insert into todoList (item) values (?)";
    PreparedStatement pstmt = con.prepareStatement(sql);
    for (String item : itemList) {
      pstmt.setObject(1, item);
      pstmt.addBatch();
    }
    pstmt.executeBatch();
  }

  public void deleteItem(String item) throws SQLException {
    String sql = "delete from todoList where item = ?";
    PreparedStatement pstmt = con.prepareStatement(sql);
    pstmt.setObject(1, item);
    pstmt.execute();
  }
  
  public void deleteItem(int id) throws SQLException {
    String sql = "delete from todoList where id = ?";
    PreparedStatement pstmt = con.prepareStatement(sql);
    pstmt.setObject(1, id);
    pstmt.execute();
  }

  public List<TodoItem> getAllItems() throws SQLException {
    List<TodoItem> list = new ArrayList<TodoItem>();
    String sql = "select * from todoList";
    ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
      list.add(new TodoItem(rs.getInt("id"), rs.getString("item")));
    }
    return list;
  }

}
