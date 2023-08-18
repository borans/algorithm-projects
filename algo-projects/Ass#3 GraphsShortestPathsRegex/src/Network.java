import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Network implements Serializable {
    static final long serialVersionUID = 55L;
    private List<Router> routers = new ArrayList<>();
    private List<Link> links = new ArrayList<>();






    /**
     * The constructor should read the given file and generate necessary Router and Link objects and initialize link
     * and router arrays.
     * Also, you should implement Link class’s calculateAndSetCost() method in order for the costs to be calculated
     * based on the formula given in the instructions.
     *
     * @param filename Input file to generate the network from
     * @throws FileNotFoundException
     */
    public Network(String filename) throws FileNotFoundException {
        // TODO: YOUR CODE HERE

        File file = new File(filename);

        Scanner s = new Scanner(file);

        while(s.hasNextLine()){

            //line as a string
            String line = s.nextLine();

            if(line.charAt(0) == 'R'){

                // regex pattern for router IPs
                String pattern = "(\\d+)([.])(\\d+)([.])(\\d+)([.])(\\d+)";
                Pattern p = Pattern.compile(pattern);
                Matcher m = p.matcher(line);

                while(m.find()){

                    //Network subNetwork = new Network(Integer.parseInt(m.group(5)));
                    Router router = new Router(m.group(), this);
                    routers.add(router);
                }
            }else{
                // regex pattern for links
                String pattern1 = "(\\d+[.]\\d+[.]\\d+[.]\\d+)[-](\\d+[.]\\d+[.]\\d+[.]\\d+)\\s+\\w+[:](\\d+)";
                Pattern p1 = Pattern.compile(pattern1);
                Matcher m1 = p1.matcher(line);



                // linelar birbirlerinin patternlerine girebilir.
                while(m1.find()){

                    Link link = new Link(m1.group(1), m1.group(2), Integer.parseInt(m1.group(3)));
                    links.add(link);

                }
            }
        }

        updateAllRoutingTables();
    }



    /**
     The regular expression that you will return from this method should extract the subnet
     number which the router belongs to (e.g. 192.168.3.55 belongs to Subnet 3) from each IP
     address.
     */
    public static String subnetsRegularExpression() {
        // TODO: REGEX HERE

        return "\\d+[.]\\d+[.](\\d+)[.]\\d+";
    }

    /**
     The regular expression that you will return from this method should return all IP
     addresses within the given subnet.
     */
    public static String routersInSubnetRegularExpression(int subnet) {
        // TODO: REGEX HERE
        return "(" + "\\d+[.]\\d+[.]" + subnet + "[.]\\d+" + ")";
    }



    public List<Router> getRouters() {
        return routers;
    }

    public List<Link> getLinks() {
        return links;
    }

    public List<RoutingTable> getRoutingTablesOfAllRouters() {
        if (routers != null) {
            List<RoutingTable> routingTableList = new ArrayList<>();
            for (Router router : routers)
                routingTableList.add(router.getRoutingTable());
            return routingTableList;
        }
        return null;
    }

    public Router getRouterWithIp(String ip) {
        if (routers != null) {
            for (Router router : routers) {
                if (router.getIpAddress().equals(ip))
                    return router;
            }
        }
        return null;
    }

    public Link getLinkBetweenRouters(String ipAddr1, String ipAddr2) {
        if (links != null) {
            for (Link link : links) {
                if (link.getIpAddress1().equals(ipAddr1) && link.getIpAddress2().equals(ipAddr2)
                        || link.getIpAddress1().equals(ipAddr2) && link.getIpAddress2().equals(ipAddr1))
                    return link;
            }
        }
        return null;
    }

    public List<Link> getLinksOfRouter(Router router) {
        List<Link> routersLinks = new ArrayList<>();
        if (links != null) {
            for (Link link : links) {
                if (link.getIpAddress1().equals(router.getIpAddress()) ||
                        link.getIpAddress2().equals(router.getIpAddress())) {
                    routersLinks.add(link);
                }
            }
        }
        return routersLinks;
    }

    public void updateAllRoutingTables() {
        for (Router router : getRouters()) {
            router.getRoutingTable().updateTable();
        }
    }

    /**
     * Changes the cost of the link with a new value, and update all routing tables.
     *
     * @param link    Link to update
     * @param newCost New link cost
     */
    public void changeLinkCost(Link link, double newCost) {
        // TODO: YOUR CODE HERE
        link.setCost(newCost);
        updateAllRoutingTables();
    }

    /**
     * Add a new Link to the Network, and update all routing tables.
     *
     * @param link Link to be added
     */
    public void addLink(Link link) {
        // TODO: YOUR CODE HERE
        links.add(link);

        updateAllRoutingTables();
    }

    /**
     * Remove a Link from the Network, and update all routing tables.
     *
     * @param link Link to be removed
     */
    public void removeLink(Link link) {
        // TODO: YOUR CODE HERE
        links.remove(link);

        updateAllRoutingTables();
    }

    /**
     * Add a new Router to the Network, and update all routing tables.
     *
     * @param router Router to be added
     */
    public void addRouter(Router router) {
        // TODO: YOUR CODE HERE
        routers.add(router);
        updateAllRoutingTables();
    }

    /**
     * Remove a Router from the Network, and update all routing tables. Beware that removing a router also causes the
     * removal of any links connected to it from the Network. Also beware that a router which was removed should not
     * appear in any routing table entry.
     *
     * @param router Router to be removed
     */
    public void removeRouter(Router router) {
        // TODO: YOUR CODE HERE
        routers.remove(router);
        for(Link l : getLinksOfRouter(router)){
            links.remove(l);
        }
        updateAllRoutingTables();
    }

    /**
     * Change the state of the router (down or live), and update all routing tables. Beware that a router which is down
     * should not be reachable and should not appear in any routing table entry’s path. However, this router might appear
     * in other router’s routing-tables as a separate entry with a totalRouteCost=Infinity value because it was not
     * completely removed from the network.
     *
     * @param router Router to update
     * @param isDown New status of the router
     */
    public void changeStateOfRouter(Router router, boolean isDown) {
        // TODO: YOUR CODE HERE
    }

    public void setRouters(List<Router> routers) {
        this.routers = routers;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
