

public class TodoItem {

  private int id;
  private String item;
  
  public TodoItem(int id, String item) {
    this.id = id;
    this.item = item;
  }

  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public int getId() {
    return id;
  }
  
}
