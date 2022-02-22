import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        
    }

public static Node[] setUpComparison(){
    Node[] nodes = new Node[8];
    nodes[0] = new Node("Role");
    nodes[1] = new Node("Controller");
    nodes[2] = new Node("Student");
    nodes[3] = new Node("Student Array");
    nodes[4] = new Node("Academic Staff Member");
    nodes[5] = new Node("ASM Array");
    nodes[6] = new Node("Module");
    nodes[7] = new Node("Module Code");
    nodes[1].addLink(nodes[0]);
    nodes[1].addLink(nodes[2]);
    nodes[1].addLink(nodes[3]);
    nodes[1].addLink(nodes[4]);
    nodes[1].addLink(nodes[5]);
    nodes[1].addLink(nodes[7]);
    nodes[2].addLink(nodes[7]);
    nodes[2].addLink(nodes[6]);
    nodes[3].addLink(nodes[2]);
    nodes[4].addLink(nodes[6]);
    nodes[4].addLink(nodes[7]);
    nodes[5].addLink(nodes[4]);
    nodes[6].addLink(nodes[7]);
    return nodes;
    }
}
