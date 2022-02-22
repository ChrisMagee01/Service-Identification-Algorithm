import java.util.ArrayList;
public class Node {
    public ArrayList<Node> outgoingLinks;
    public String name;

    public Node(String name) {
        this.name = name;
        this.outgoingLinks = new ArrayList<>();
    }

    public void addLink(Node link){
        outgoingLinks.add(link);
    }

    public int linkCount(){
        return outgoingLinks.size();
    }

}
