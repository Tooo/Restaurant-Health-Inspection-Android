package com.example.restauranthealthinspector.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * A manager to store inspections.
 */
public class InspectionsManager implements Iterable<Inspection> {
    private ArrayList<Inspection> inspectionList = new ArrayList<>();
    private static InspectionsManager instance;

    private InspectionsManager(){

    }

    public static InspectionsManager getInstance() throws FileNotFoundException {
        if (instance == null){
            instance = new InspectionsManager();
            storeInspections();
        }
        return instance;
    }

    private static void storeInspections() throws FileNotFoundException {
        File file = new File("./src/main/res/raw/inspectionreports_itr1.csv");
        Scanner scan = new Scanner(file);
        Inspection inspection;
        String line;
        int numCritical;
        int numNonCritical;
        Date date;
        ViolationManager violationManager;
        scan.nextLine();

        while (scan.hasNextLine()) {
            line = scan.nextLine();
            line = line.replaceAll("\"", "");
            String[] lineArray = line.split(",");

            date = intToDate(lineArray[1]);
            numCritical = Integer.parseInt(lineArray[3]);
            numNonCritical = Integer.parseInt(lineArray[4]);
            violationManager = vioLumpToViolationManager(lineArray);

            inspection = new Inspection(lineArray[0], date, lineArray[2], numCritical, numNonCritical, lineArray[5], violationManager);
            instance.add(inspection);
        }
        scan.close();
    }

    private static Date intToDate(String inspectionDate) {
        int day = Integer.parseInt(inspectionDate.substring(6, 8));
        int month = Integer.parseInt(inspectionDate.substring(4, 6));
        int year = Integer.parseInt(inspectionDate.substring(0, 4));
        return new Date(day, month, year);
    }

    private static ViolationManager vioLumpToViolationManager(String[] lineArray) {
        // No Violations
        if (lineArray.length == 6) {
            return null;
        }

        StringBuilder vioLump = new StringBuilder(lineArray[6]);
        for (int i = 7; i < lineArray.length; i++) {
            vioLump.append(",").append(lineArray[i]);
        }
        return new ViolationManager(vioLump.toString());
    }

    public void add(Inspection inspection){
        inspectionList.add(inspection);
    }

    public Inspection get(int i){
        return inspectionList.get(i);
    }

    @Override
    public Iterator<Inspection> iterator() {
        return inspectionList.iterator();
    }

}