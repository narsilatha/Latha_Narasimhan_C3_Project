import javax.swing.plaf.basic.BasicComboBoxUI;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalTime;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    public List<Item> order = new ArrayList<Item>();
    public int value=0;
    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;

    }

    public boolean  isRestaurantOpen() {

        if (getCurrentTime().isAfter(openingTime) && getCurrentTime().isBefore(closingTime)) {
                return true;
            }
            else {return false;}


    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {

        return Collections
                .unmodifiableList(menu);

    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public void itemsOrdered(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName)) {
                Item neworder = new Item(item.getName(),item.getPrice());
                order.add(neworder);
            }
        }

    }
    public  double calculateOrderValue() {


            for (Item item : order) {

                value = value + item.getPrice();
            }

          return value;
    }
    public void displayOrdervalue(){
        System.out.println(value);
    }
}
