package JavaOOPBasics.InterfacesAndAbstraction.MilitaryElite;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        Map<Integer, Private> privates = new HashMap<>();
        List<Soldier> soldiers = new LinkedList<>();

        while(!"End".equals(currentLine)) {
            String[] soldierData = currentLine.split(" ");
            String soldierType = soldierData[0];
            int id = Integer.parseInt(soldierData[1]);
            String firstName = soldierData[2];
            String lastName = soldierData[3];

            switch (soldierType) {
                case "Private":
                    double salary = Double.parseDouble(soldierData[4]);
                    Private privateSoldier = new Private(id, firstName, lastName, salary);
                    privates.putIfAbsent(id, privateSoldier);
                    soldiers.add(privateSoldier);
                    break;
                case "LeutenantGeneral":
                    salary = Double.parseDouble(soldierData[4]);
                    LeutenantGeneral leutenantGeneral = new LeutenantGeneral(id, firstName, lastName, salary);

                    for (int i = 5; i < soldierData.length; i++) {
                        int privateId = Integer.parseInt(soldierData[i]);
                        privateSoldier = privates.get(privateId);
                        leutenantGeneral.addPrivate(privateSoldier);
                    }
                    soldiers.add(leutenantGeneral);
                    break;
                case "Engineer":
                    salary = Double.parseDouble(soldierData[4]);
                    String crops = soldierData[5];

                    if (crops.equals("Airforces") || crops.equals("Marines")) {
                        Engineer engineer = new Engineer(id, firstName, lastName, salary, crops);

                        if (soldierData.length > 6) {
                            for (int i = 6; i < soldierData.length; i+=2) {
                                String partName = soldierData[i];
                                int hoursWorked = Integer.parseInt(soldierData[i + 1]);
                                Repair repair = new Repair(partName, hoursWorked);
                                engineer.addRepair(repair);
                            }
                        }
                        soldiers.add(engineer);
                    }
                    break;
                case "Commando":
                    salary = Double.parseDouble(soldierData[4]);
                    crops = soldierData[5];

                    if (crops.equals("Airforces") || crops.equals("Marines")) {
                        Commando commando = new Commando(id, firstName, lastName, salary, crops);

                        if (soldierData.length > 6) {
                            for (int i = 6; i < soldierData.length; i+=2) {
                                String missionCodeName = soldierData[i];
                                String missionState = soldierData[i + 1];
                                if (missionState.equals("Finished") || missionState.equals("inProgress")) {
                                    Mission mission = new Mission(missionCodeName, missionState);
                                    commando.addMission(mission);
                                }
                            }
                        }
                        soldiers.add(commando);
                    }
                    break;
                case "Spy":
                    String codeNumber = soldierData[4];
                    Spy spy = new Spy(id, firstName, lastName, codeNumber);
                    soldiers.add(spy);
                    break;
            }
            currentLine = reader.readLine();
        }

        for (Soldier soldier: soldiers) {
            System.out.println(soldier.toString());
        }
    }
}
