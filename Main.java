import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Class[] comparison = setUpComparison();

        ArrayList<Service> singletons = new ArrayList<>();
        for (Class item : comparison) {
            Service temp = new Service();
            temp.addClass(item);
            singletons.add(temp);
        }

        singletons = step2(singletons);
        System.out.println(singletons.size());
        for (Service service : singletons) {
            System.out.println(service);
        }
    }

    public static ArrayList<Service> step2(ArrayList<Service> originalServices) {

        ArrayList<Service> newServices = new ArrayList<>();

        for (int i = 0; i < originalServices.size(); i++) {
            for (int j = i + 1; j < originalServices.size(); j++) {
                Service temp = new Service();

                ArrayList<Class> classesInServiceA = originalServices.get(j).containedClasses;
                //Add classes to the new service. First check the class is not already in the service
                for (int k = 0; k < classesInServiceA.size(); k++) {
                    if (!temp.containedClasses.contains(classesInServiceA.get(k))) {
                        temp.addClass(classesInServiceA.get(k));
                    }
                }


                ArrayList<Class> classesInServiceB = originalServices.get(i).containedClasses;
                //Add classes to the new service. First check that the class is not already in the service
                for (int k = 0; k < classesInServiceB.size(); k++) {
                    if (!temp.containedClasses.contains(classesInServiceB.get(k)))
                        temp.addClass(classesInServiceB.get(k));
                }


                //add temp service to service arraylist
                //but first check for duplicate against other new services
                boolean duplicate = true;
                for (int k = 0; k < newServices.size(); k++) {
                    for (int l = 0; l < newServices.get(k).containedClasses.size(); l++) {

                        if (!temp.containedClasses.contains(newServices.get(k).containedClasses.get(l))) {
                            duplicate = false;
                            break;
                        }

                    }
                    if (!duplicate) {
                        break;
                    }
                }
                if (duplicate) {

                    //add temp service to service arraylist
                    //but first check for duplicate against other original services
                    for (int k = 0; k < originalServices.size(); k++) {
                        for (int l = 0; l < originalServices.get(k).containedClasses.size(); l++) {

                            if (!temp.containedClasses.contains(originalServices.get(k).containedClasses.get(l))) {
                                duplicate = false;
                                break;
                            }

                        }
                        if (!duplicate) {
                            break;
                        }
                    }

                }

                newServices.add(temp);
            }
        }
        newServices = step3(newServices);
        for (int i = 0; i < newServices.size(); i++) {
            originalServices.add(newServices.get(i));
        }
        return step4(originalServices);
    }

    public static ArrayList<Service> step3(ArrayList<Service> originalServices) {
        ArrayList<Service> cohesiveServices = new ArrayList<Service>();
        for (Service service : originalServices) {
            if (service.isCohesive())
                cohesiveServices.add(service);
        }

        return cohesiveServices;
    }

    public static ArrayList<Service> step4(ArrayList<Service> originalServices) {
        ArrayList<Service> maxCohesion = new ArrayList<>();
        double cohesion = 0;
        for (Service service : originalServices) {
            if (service.getCohesion() > cohesion) {
                cohesion = service.getCohesion();
                maxCohesion.removeAll(maxCohesion);
                maxCohesion.add(service);
            } else if (service.getCohesion() == cohesion) {
                maxCohesion.add(service);
            }
        }
        originalServices = step56(originalServices.get(8), originalServices);

/*
        //at this point max cohesion contains all the most cohesive services.
        double maxReturnedCohesion = 0;
        ArrayList<ArrayList> mostCohesive = new ArrayList<>();
        for (Service service: maxCohesion) {
            ArrayList<Service> returned = step56(service, originalServices);
            //
            double returnedCohesion = 0;
            for (Service returnedService : returned) {
                returnedCohesion += returnedService.getCohesion();
            }
            returnedCohesion = returnedCohesion / returned.size();
            if (returnedCohesion > maxReturnedCohesion) {
                maxReturnedCohesion = returnedCohesion;
                mostCohesive.removeAll(mostCohesive);
                mostCohesive.add(returned);
            } else if(returnedCohesion==maxReturnedCohesion){
                mostCohesive.add(returned);
            }
        }*/
        return originalServices;
    }

    private static ArrayList<Service> step56(Service selected, ArrayList<Service> originalServices) {
        System.out.println(selected);
        System.out.println(originalServices);
        ArrayList<Service> newServices = new ArrayList<>();
        for (Service original : originalServices) {
            Service temp = new Service();
            for (Class currentClass : original.containedClasses) {
                if (!selected.containedClasses.contains(currentClass)) {
                    temp.addClass(currentClass);
                }
            }
            boolean duplicate =false;
            for (Service newService: newServices) {
                duplicate = newService.isEqual(temp);
                if(duplicate) {
                    break;
                }
            }
            if (!temp.containedClasses.isEmpty() && !duplicate) {
                newServices.add(temp);
            }
        }

        newServices.add(selected);



        return step7(newServices);
    }

    private static ArrayList<Service> step7(ArrayList<Service> originalServices){
        ArrayList<Service> newServices = new ArrayList<>();
        for (Service originalService: originalServices) {
            boolean isUnique = false;
            for (Class classInService: originalService.containedClasses) {
                isUnique = classCount(originalServices, classInService)==1;
            }
            if(originalService.isCohesive() || isUnique){
                newServices.add(originalService);
            }
        }
        return step8(newServices);
    }

    private static ArrayList<Service> step8(ArrayList<Service> newServices) {
        if(newServices.size()<=1){
            return newServices;
        } else{
            return step2(newServices);
        }
    }

    public static int classCount(ArrayList<Service> services, Class a) {
        int appearances = 0;
        for (Service service :
                services) {
            if (service.containedClasses.contains(a))
                appearances++;
        }
        return appearances;
    }

    /**
     * In future, this would be changed to file read in to make generalisable
     *
     * @return an array of all classes
     */
    public static Class[] setUpComparison() {
        Class[] nodes = new Class[8];
        nodes[0] = new Class("Role");
        nodes[1] = new Class("Controller");
        nodes[2] = new Class("Student");
        nodes[3] = new Class("Student Array");
        nodes[4] = new Class("Academic Staff Member");
        nodes[5] = new Class("ASM Array");
        nodes[6] = new Class("Module");
        nodes[7] = new Class("Module Code");
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
