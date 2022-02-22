import java.util.ArrayList;
public class Class {
    public ArrayList<Class> outgoingLinks;
    public String name;

    public Class(String name) {
        this.name = name;
        this.outgoingLinks = new ArrayList<>();
    }

    public void addLink(Class link){
        outgoingLinks.add(link);
    }

    public int linkCount(){
        return outgoingLinks.size();
    }

    @Override
    public String toString() {
        return name;
    }
}
