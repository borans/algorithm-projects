import java.util.ArrayList;
import java.util.Collections;

/**
 * This class accomplishes Mission Nuke'm
 */
public class DefenseAgainstEnemyTroops {
    private ArrayList<Integer> numberOfEnemiesArrivingPerHour;

    public DefenseAgainstEnemyTroops(ArrayList<Integer> numberOfEnemiesArrivingPerHour){
        this.numberOfEnemiesArrivingPerHour = numberOfEnemiesArrivingPerHour;
    }

    public ArrayList<Integer> getNumberOfEnemiesArrivingPerHour() {
        return numberOfEnemiesArrivingPerHour;
    }

    private int getRechargedWeaponPower(int hoursCharging){
        return hoursCharging*hoursCharging;
    }

    /**
     *     Function to implement the given dynamic programming algorithm
     *     SOL(0) <- 0
     *     HOURS(0) <- [ ]
     *     For{j <- 1...N}
     *         SOL(j) <- max_{0<=i<j} [ (SOL(i) + min[ E(j), P(j − i) ] ]
     *         HOURS(j) <- [HOURS(i), j]
     *     EndFor
     *
     * @return OptimalEnemyDefenseSolution
     */
    public OptimalEnemyDefenseSolution getOptimalDefenseSolutionDP(){
        // TODO: YOUR CODE HERE

        int N = numberOfEnemiesArrivingPerHour.size();

        int[] sol = new int[N+1];
        sol[0] = 0;

        ArrayList<ArrayList<Integer>> hours = new ArrayList<>();
        hours.add(0, new ArrayList<Integer>(){});

        for(int j=1; j<N+1; j++){
            int max=0;
            int hour=0;
            for(int i=0; i<j; i++){

                if((sol[i] + Math.min(numberOfEnemiesArrivingPerHour.get(j-1), getRechargedWeaponPower(j-i))) > max){
                    max = sol[i] + Math.min(numberOfEnemiesArrivingPerHour.get(j-1), getRechargedWeaponPower(j-i));
                    hour = i;
                }
            }
            sol[j] = max;
            // initializing hours[j]
            if(j >= hours.size()){
                hours.add(new ArrayList<>());
            }
            hours.get(j).addAll(hours.get(hour));

            hours.get(j).add(j);

        }

        // creating optimal enemy defense solution object

        return new OptimalEnemyDefenseSolution(sol[N], hours.get(N));
    }
}
