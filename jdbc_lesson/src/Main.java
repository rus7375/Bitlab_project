import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int choice;
        DBManager db = new DBManager();
        db.connectToDB();
        while(true) {
            System.out.println(
                            "PRESS [1] TO ADD ITEM\n"+
                            "\n"+
                            "PRESS [2] TO LIST ITEMS\n" +
                            "\n" +
                            "PRESS [3] TO DELETE ITEM\n"+
                            "\n"+
                            "PRESS [0] TO EXIT");
            choice = in.nextInt();

            if(choice ==1) {
                System.out.println("Insert name");
                String name = in.next();
                System.out.println("Insert price");
                Double price = in.nextDouble();
                saveItem(new Item(null, name, price));
            }
            else if (choice==2) {
                ArrayList<Item> items = new ArrayList<>();
                items = getItems();
                for (Item item :items) {
                    System.out.println(item);
                }
            }
            else if (choice ==3) {
                System.out.println("Insert id");
                int id = in.nextInt();
                deleteItem(id);
            }

            else if (choice ==0){
                System.exit(0);
            }

        }

    }



    public static void saveItem(Item item){
        try {
            PreparedStatement st = DBManager.getConnection().
                    prepareStatement("insert into items(name, price) values(?,?)");
            st.setString(1,item.getName());
            st.setDouble(2,item.getPrice());

            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Item> getItems(){
        ArrayList<Item> items = new ArrayList<>();
        try {
            PreparedStatement st = DBManager.getConnection().prepareStatement("select * from items");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");

                items.add(new Item(id,name,price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
    public static void deleteItem(int id){

        try {
            PreparedStatement st = DBManager.getConnection().prepareStatement("delete from items where id = ?");
            st.setInt(1,id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
