import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by song on 2016. 11. 10..
 */
public class Main {
    public static void main(String[] args){
        ArrayList<Vertex> vertexArr = new ArrayList<>();
        ArrayList<Edge> edgeArr = new ArrayList<>();
        ArrayList<Edge> result = new ArrayList<>();
        vertexArr.add(new Vertex("a"));
        vertexArr.add(new Vertex("b"));
        vertexArr.add(new Vertex("c"));
        vertexArr.add(new Vertex("d"));
        vertexArr.add(new Vertex("e"));
        vertexArr.add(new Vertex("f"));
        vertexArr.add(new Vertex("g"));
        vertexArr.add(new Vertex("h"));
        vertexArr.add(new Vertex("i"));
        edgeArr.add(new Edge(0,1,4));
        edgeArr.add(new Edge(0,7,8));
        edgeArr.add(new Edge(1,2,8));
        edgeArr.add(new Edge(1,7,11));
        edgeArr.add(new Edge(2,3,7));
        edgeArr.add(new Edge(2,5,4));
        edgeArr.add(new Edge(2,8,2));
        edgeArr.add(new Edge(3,4,9));
        edgeArr.add(new Edge(3,5,14));
        edgeArr.add(new Edge(4,5,10));
        edgeArr.add(new Edge(5,6,2));
        edgeArr.add(new Edge(6,7,1));
        edgeArr.add(new Edge(6,8,6));
        edgeArr.add(new Edge(7,8,7));


        for(int i=0;i<vertexArr.size();i++){
            makeSet(vertexArr.get(i));
        }
        edgeArr.sort(new distCompare());
        for(int i=0;i<edgeArr.size();i++){
            if(!findSet(vertexArr.get(edgeArr.get(i).getX())).equals(findSet(vertexArr.get(edgeArr.get(i).getY())))){
                result.add(edgeArr.get(i));
                union(vertexArr.get(edgeArr.get(i).getX()),findSet(vertexArr.get(edgeArr.get(i).getY())));
            }
        }
        for(int i=0;i<result.size();i++){
            System.out.println(result.get(i).getX()+" "+result.get(i).getY()+" "+result.get(i).getDistance());
        }
    }



    public static void makeSet(Vertex x){
        x.setParent(x);
        x.setRank(0);
    }
    public static void union(Vertex x, Vertex y){
        Link(x,y);
    }

    private static void Link(Vertex x, Vertex y) {
        if(x.rank > y.rank)
            y.setParent(x);
        else {
            x.setParent(y);
            if (x.rank == y.rank)
                y.setRank(y.getRank() + 1);
        }
    }
    public static Vertex findSet(Vertex x){
        if(!x.equals(x.getParent()))
            x.setParent(findSet(x.getParent()));
        return x.getParent();
    }
    static class distCompare implements Comparator<Edge>{
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.getDistance() < o2.getDistance() ? -1 : o1.getDistance() > o2.getDistance() ? 1:0;
        }
    }
}
