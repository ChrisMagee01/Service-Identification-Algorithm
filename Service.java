import java.util.ArrayList;
public class Service {
    public ArrayList<Class> containedClasses;
    public int maxLinks;
    public Service() {
        containedClasses = new ArrayList<>();
        maxLinks = 0;
    }
    public void addClass(Class c){
        maxLinks += containedClasses.size();
        containedClasses.add(c);
    }

    public double getCohesion(){
        if (maxLinks == 0)
            return 0;
        return getInternalLinks() / maxLinks;
    }

    public boolean isCohesive(){
        if (maxLinks==0)
            return false;
        return (getInternalLinks()/maxLinks)>0.8;
    }

    public int getInternalLinks(){
        int links = 0;
        for (Class node: containedClasses) {
            for (Class link: node.outgoingLinks) {
                if(containedClasses.contains(link)){
                    links++;
                }
            }
        }
        return links;
    }

    @Override
    public String toString() {
        String name = "Cohesion: "+getCohesion()+"\tClasses: ";
        for (int i = 0; i < containedClasses.size(); i++) {
            name +="[" + containedClasses.get(i).toString() + "] ";
        }
        return name;
    }

    public boolean isEqual(Service a) {
        boolean dupe = true;
        for (Class classA : a.containedClasses) {
            if (!containedClasses.contains(classA)) {
                dupe = false;
            }
            if (!dupe) {
                break;
            }
        }
        if (dupe) {
            for (Class ownClass : containedClasses) {
                if (!a.containedClasses.contains(ownClass)) {
                    dupe = false;
                }
                if (!dupe) {
                    break;
                }
            }
        }
        return dupe;
    }
}
