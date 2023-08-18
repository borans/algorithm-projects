import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.io.IOException;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws IOException {



        Sort sort = new Sort();

        File file = new File(sort);

        file.readFile("TrafficFlowDataset.csv");



        for(int i=1; i<=4; i++){
            file.sortingExperiment(i);
            System.out.println("------------------------------------------------------\n");

        }

    }
}
