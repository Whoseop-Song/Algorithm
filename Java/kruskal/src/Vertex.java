/**
 * Created by song on 2016. 11. 10..
 */
public class Vertex {
    String vertex;
    Vertex parent;
    int rank;

    public Vertex(String vertex){
        this.vertex = vertex;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getVertex() {
        return vertex;
    }

    public void setVertex(String vertex) {
        this.vertex = vertex;
    }
}
