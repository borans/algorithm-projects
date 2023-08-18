import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Main class
 */
// FREE CODE HERE
public class Main {
    public static final int AUAV_CAPACITY = 10;
    public static void main(String[] args) {


        try {

            /** MISSION FIREWALL BELOW **/


            System.out.println("##MISSION FIREWALL INITIATED##");
            System.out.println("Started scanning...");
            System.out.println("--------------------------------------------------------------------------------");

            // Parse the XML file given by the first command-line argument
            // Instantiate the MalwareScanner class by the dictionary you generated by parsing
            // and scan the file given by the second command-line argument
            Map<String, Malware> malwareDB = XMLParser.parse(args[0]);


            MalwareScanner scanner = new MalwareScanner(malwareDB);
            scanner.scanFile(args[1]);
            System.out.println("##MISSION FIREWALL COMPLETED##");


            /** MISSION NUKE'M BELOW **/


            System.out.println("##MISSION NUKE'M INITIATED##");
            // Parse the input file given by the third command-line argument
            // Instantiate the DefenseAgainstEnemyTroops class using the parsed numberOfEnemiesArrivingPerHour data
            // Calculate the optimal solution and then print the required output to the console
            ArrayList<Integer> numberOfEnemiesArrivingPerHour = Util.readTroopsDeploymentSchedule(args[2]);
            DefenseAgainstEnemyTroops defense = new DefenseAgainstEnemyTroops(numberOfEnemiesArrivingPerHour);
            OptimalEnemyDefenseSolution solution = defense.getOptimalDefenseSolutionDP();
            solution.printEnemyDefenseSolution(numberOfEnemiesArrivingPerHour);
            System.out.println("##MISSION NUKE'M COMPLETED##");


            /** MISSION EXTERMINATE BELOW **/


            System.out.println("##MISSION EXTERMINATE INITIATED##");
            // Parse the input file given by the fourth command-line argument
            // Instantiate the OptimalFinalDefenseGP class using the bomb weights
            // Calculate the solution by using the maximum number of available AUAVs and AUAV capacity
            // Print the required output to the console
            ArrayList<Integer> bombWeights = Util.readBombWeights(args[3]);
            int maxNumberOfAvailableAUAVs = Util.readNumberOfAvailableAUAVs(args[3]);
            OptimalFinalDefenseGP finalDefense = new OptimalFinalDefenseGP(bombWeights);
            finalDefense.printFinalDefenseOutcome(maxNumberOfAvailableAUAVs, AUAV_CAPACITY);
            System.out.println("##MISSION EXTERMINATE COMPLETED##");


        }
        catch (IOException | ParserConfigurationException | SAXException ignored){
        }


    }


}
