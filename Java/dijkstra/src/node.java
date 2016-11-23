public class node{
    private int distance;
    private int index;
    node(int distance,int index){
        this.distance = distance;
        this.index = index;
    }
    public int getDistance(){
        return distance;
    }
    public void setDistance(int distance){
        this.distance = distance;
    }
    public int getIndex(){ return index;}
    public void setIndex(int index){
        this.index = index;
    }
    public String toString(){
        return "["+ distance +","+index+"]";
    }
}