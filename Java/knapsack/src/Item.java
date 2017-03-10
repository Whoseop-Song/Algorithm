/**
 * Created by song on 2016. 11. 21..
 */
public class Item {
    private int itemNo;
    private int value;
    private int weight;

    public Item(int no,int val, int wei){
        itemNo = no;
        value = val;
        weight = wei;
    }

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String toString(){
        return "["+itemNo+","+value+","+weight+"]";
    }

}
