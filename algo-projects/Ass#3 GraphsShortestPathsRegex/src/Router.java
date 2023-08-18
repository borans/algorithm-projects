import java.io.Serializable;
import java.util.Objects;

public class Router implements Serializable {
    static final long serialVersionUID = 33L;
    private String ipAddress;
    private boolean isDown;
    private Network network;
    private RoutingTable routingTable;
    private boolean visited;
    private double distTo;
    private Link edgeTo;


    public Router(String ipAddress, Network network) {
        this.ipAddress = ipAddress;
        this.network = network;
        this.routingTable = new RoutingTable(this);
    }

    public boolean isDown() {
        return isDown;
    }

    public void setDown(boolean down) {
        isDown = down;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public RoutingTable getRoutingTable() {
        return routingTable;
    }


    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }


    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public double getDistTo() {
        return distTo;
    }

    public void setDistTo(double distTo) {
        this.distTo = distTo;
    }


    public Link getEdgeTo() {
        return edgeTo;
    }

    public void setEdgeTo(Link edgeTo) {
        this.edgeTo = edgeTo;
    }

    @Override
    public String toString() {
        return "Router{" +
                "ipAddress='" + ipAddress + '\'' +
                ", isDown=" + isDown +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Router router = (Router) o;
        return isDown == router.isDown && Objects.equals(ipAddress, router.ipAddress);
    }

    public void setRoutingTable(RoutingTable routingTable) {
        this.routingTable = routingTable;
    }
}
