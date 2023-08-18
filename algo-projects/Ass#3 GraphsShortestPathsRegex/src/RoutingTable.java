import java.io.Serializable;
import java.util.*;

public class RoutingTable implements Serializable {

    static final long serialVersionUID = 99L;
    private final Router router;
    private final Network network;
    private final List<RoutingTableEntry> entryList;


    public RoutingTable(Router router) {
        this.router = router;
        this.network = router.getNetwork();
        this.entryList = new ArrayList<>();
    }




    /**
     * updateTable() should calculate routing information and then instantiate RoutingTableEntry objects, and finally add
     * them to RoutingTable object’s entryList.
     */
    public void updateTable() {
        // TODO: YOUR CODE HERE
        for(Router r : network.getRouters()){

            if(r.equals(router)){
                continue;
            }

            Stack<Link> shortestPath = pathTo(r);


            RoutingTableEntry routingTableEntry = new RoutingTableEntry(router.getIpAddress(), r.getIpAddress(), shortestPath );

            entryList.add(routingTableEntry);
        }
    }


    public void Dijkstra(){

        for(Router r : network.getRouters()){
            r.setDistTo(Double.POSITIVE_INFINITY);
            r.setVisited(false);
        }
        router.setDistTo(0);


        for(Router r1 : network.getRouters()){

            Router minRouter = findMinimum();
            minRouter.setVisited(true);

            for(Link l : network.getLinksOfRouter(minRouter)){


                Router nextR = network.getRouterWithIp(l.getOtherIpAddress(minRouter.getIpAddress()));

                if(!nextR.isVisited() && minRouter.getDistTo() != Double.POSITIVE_INFINITY){
                    double newDistance = minRouter.getDistTo() + l.getCost();
                    if(newDistance < nextR.getDistTo()){
                        nextR.setDistTo(newDistance);
                        nextR.setEdgeTo(l);
                    }
                }
            }
        }
    }



    public Router findMinimum(){

        Router minRouter = null;
        for(Router router1 : network.getRouters()){
            if(!router1.isVisited() && (minRouter == null || router1.getDistTo() < minRouter.getDistTo())){
                minRouter = router1;
            }
        }
        return minRouter;
    }



    public boolean hasPathTo(Router destination){
        return destination.getDistTo() < Double.POSITIVE_INFINITY;
    }



    /**
     * pathTo(Router destination) should return a Stack<Link> object which contains a stack of Link objects,
     * which represents a valid path from the owner Router to the destination Router.
     *
     * @param destination Destination router
     * @return Stack of links on the path to the destination router
     */
    public Stack<Link> pathTo(Router destination) {
        // TODO: YOUR CODE
        Stack<Link> path = new Stack<>();

        Dijkstra();

        if(!hasPathTo(destination)){
            return path;
        }

        if(!hasPathTo(router)){
            return path;
        }


        Router r = destination;
        Link l = destination.getEdgeTo();

        while(r != router){

            Router r1 = r;
            path.push(l);

            r = network.getRouterWithIp(l.getOtherIpAddress(r.getIpAddress()));
            l = network.getRouterWithIp(l.getOtherIpAddress(r1.getIpAddress())).getEdgeTo();

        }

        return path;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutingTable that = (RoutingTable) o;
        return router.equals(that.router) && entryList.equals(that.entryList);
    }

    public Router getRouter() {
        return router;
    }

    public Network getNetwork() {
        return network;
    }

    public List<RoutingTableEntry> getEntryList() {
        return entryList;
    }
}
