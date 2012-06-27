import static org.junit.Assert.assertEquals;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class TestTodo {

  @Test
  public void testOperations() throws SQLException, ClassNotFoundException {

    List<String> list = new ArrayList<String>();
    List<TodoItem> todoList = new ArrayList<TodoItem>();

    TodoList app = new TodoList("root", "root", "mysql", "localhost", 3306, "");

    list.add("Redesign toolbar");
    list.add("Implement all pages of site");
    list.add("Buy small travel toothpaste and contact lens solution");
    list.add("Feed the dog");
    app.addItems(list);

    todoList = app.getAllItems();
    assertEquals(4, todoList.size());

    app.deleteItem("Feed the dog");
    todoList = app.getAllItems();
    assertEquals(3, todoList.size());

    todoList = app.getAllItems();
    for (TodoItem item : todoList) {
      System.out.println(item.getId() + "\t" + item.getItem());
    }

  }

}
