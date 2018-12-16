package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Engine implements  Runnable {
    private Connection connection;
    private BufferedReader reader;

    public Engine(Connection connection, BufferedReader reader) {
        this.connection = connection;
        this.reader = reader;
    }

    public void run() {
        try {
            //this.getVillainsName();
            //this.getMinionNames();
            //this.addMinion();
            //this.changeTownNamesCasing();
            //this.removeVillain();
            //this.printAllMinionNames();
            //this.increaseMinionsAge();
            this.increaseAgeStoredProcedure();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private void getVillainsName() throws SQLException {
        String query = "SELECT v.name, COUNT(mv.minion_id) AS count" +
                "FROM minions_villains AS mv" +
                "INNER JOIN villains AS v ON mv.villain_id = v.id" +
                "GROUP BY mv.villain_id" +
                "HAVING count > ?" +
                "ORDER BY count DESC;";

        PreparedStatement stmt = this.connection.prepareStatement(query);
        stmt.setInt(1, 3);

        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            System.out.println(String.format(
                    "%s %d",
                    resultSet.getString(1),
                    resultSet.getInt(2)
            ));
        }

        connection.close();
    }

    private void getMinionNames() throws IOException, SQLException {
        int villainId = Integer.parseInt(reader.readLine());

        String query = "SELECT DISTINCT(m.name) AS minion_name, m.age, v.name AS villain_name" +
                "FROM villains AS v" +
                "LEFT JOIN minions_villains AS mv ON v.id = mv.villain_id" +
                "LEFT JOIN minions AS m ON m.id = mv.minion_id" +
                "WHERE v.id = ?;";

        PreparedStatement stmt = this.connection.prepareStatement(query);
        stmt.setInt(1, villainId);

        ResultSet resultSet = stmt.executeQuery();

        if (!resultSet.next()) {
            System.out.println(String.format("No villain with ID %d exists in the database.", villainId));
        } else {
            StringBuilder result = new StringBuilder();
            result.append(String.format("Villain: %s", resultSet.getString("villain_name")))
                    .append(System.lineSeparator());

            if (resultSet.getString("minion_name") == null) {
                result.append("<no minions>");
            } else {
                int counter = 1;
                while (resultSet.next()) {
                    result.append(String.format(
                            "%d. %s %d",
                            counter,
                            resultSet.getString("minion_name"),
                            resultSet.getInt(2)
                    ))
                    .append(System.lineSeparator());
                    counter++;
                }
            }
            System.out.println(result.toString().trim());
        }
        connection.close();
    }

    private void addMinion() throws IOException, SQLException {
        String[] minionData = reader.readLine().split("\\s+");
        String minionName = minionData[1];
        int minionAge = Integer.parseInt(minionData[2]);
        String minionTown = minionData[3];
        String[] villainData = reader.readLine().split("\\s+");
        String villainName = villainData[1];

        try {
            connection.setAutoCommit(false);

            if (getEntityIdByName("towns", minionTown) == null) {
                String query = "INSERT INTO towns(name) VALUES(?)";
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, minionTown);
                stmt.executeUpdate();
                System.out.println(String.format("Town %s was added to the database.", minionTown));
            }

            if (getEntityIdByName("villains", villainName) == null) {
                String query = "INSERT INTO villains(name, evilness_factor) VALUES(?, ?)";
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, villainName);
                stmt.setString(2, "evil");
                stmt.executeUpdate();
                System.out.println(String.format("Villain %s was added to the database.", villainName));
            }

            Integer townId = getEntityIdByName("towns", minionTown);
            Integer villainId = getEntityIdByName("villains", villainName);

            String query = "INSERT INTO minions(name, age, town_id) VALUES(?, ?, ?)";
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, minionName);
            stmt.setInt(2, minionAge);
            stmt.setInt(3, townId);
            stmt.executeUpdate();

            Integer minionId = getEntityIdByName("minions", minionName);
            query = "INSERT INTO minions_villains(minion_id, villain_id) VALUES(?, ?)";
            stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, minionId);
            stmt.setInt(2, villainId);
            stmt.executeUpdate();

            System.out.println(String.format("Successfully added %s to be minion of %s", minionName, villainName));

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }

        connection.close();
    }

    private void changeTownNamesCasing() throws IOException, SQLException {
        String countryName = reader.readLine();
        String query = "UPDATE towns SET name = UPPER(name) WHERE country = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, countryName);
        stmt.execute();

        List<String> townNames = new ArrayList<>();
        query = "SELECT name FROM towns WHERE country = ?";

        stmt = connection.prepareStatement(query);
        stmt.setString(1, countryName);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            townNames.add(resultSet.getString("name"));
        }

        if (townNames.size() > 0) {
            System.out.println(String.format("%d town names were affected", townNames.size()));
            System.out.println("[" + String.join(", ", townNames) + "]");
        } else {
            System.out.println("No town names were affected.");
        }
        connection.close();
    }

    private void removeVillain() throws IOException, SQLException {
        int villainId = Integer.parseInt(reader.readLine());

        if (getEntityNameById("villains", villainId) == null) {
            System.out.println("No such villain was found");
            return;
        }

        try {
            connection.setAutoCommit(false);
            String query = "DELETE FROM minions_villains WHERE villain_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, villainId);

            int minionsCount = stmt.executeUpdate();
            String villainName = getEntityNameById("villains", villainId);

            query = "DELETE FROM villains WHERE id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, villainId);
            stmt.executeUpdate();

            System.out.println(String.format("%s was deleted", villainName));
            System.out.println(String.format("%d minions released", minionsCount));
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
        connection.close();
    }

    private void printAllMinionNames() throws SQLException {
        List<String> minionNames = new ArrayList<>();
        String query = "SELECT name FROM minions";
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            minionNames.add(resultSet.getString("name"));
        }

        int first = 0;
        int last = minionNames.size() - 1;

        while (true) {
            System.out.println( minionNames.get(first));
            first++;

            if (first >= last) {
                break;
            }

            System.out.println(minionNames.get(last));
            last--;
        }
        connection.close();
    }

    private void increaseMinionsAge() throws IOException, SQLException {
        String[] idsData = reader.readLine().split(" ");
        String ids = String.join(", ", idsData);

        try {
            connection.setAutoCommit(false);
            String query = String.format("UPDATE minions " +
                    "SET name = CONCAT(UPPER(SUBSTRING(name, 1, 1)), LOWER(SUBSTRING(name, 2))), age = age + 1 " +
                    "WHERE id IN (%s)", ids);

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.executeUpdate();

            query = "SELECT name, age FROM minions;";
            stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                System.out.println(String.format(
                        "%s %d",
                        resultSet.getString(1),
                        resultSet.getInt(2)
                ));
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
        connection.close();
    }

    private void increaseAgeStoredProcedure() throws SQLException, IOException {
        int minionId = Integer.parseInt(reader.readLine());

        try {
            connection.setAutoCommit(false);
            String query = "SELECT SPECIFIC_NAME\n" +
                    "FROM information_schema.ROUTINES\n" +
                    "WHERE ROUTINE_NAME = 'usp_get_older'\n";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            if (!resultSet.next()) {
                query = "CREATE PROCEDURE usp_get_older(minion_id INT)\n" +
                        "BEGIN\n" +
                        "   UPDATE minions SET age = age + 1 WHERE id = minion_id;\n" +
                        "END";
                stmt = connection.prepareStatement(query);
                stmt.executeUpdate();
            }

            query = "CALL usp_get_older(?);";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, minionId);
            stmt.executeUpdate();

            query = "SELECT name, age FROM minions WHERE id = ?;";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, minionId);
            stmt.executeQuery();

            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                System.out.println(String.format(
                        "%s %d",
                        resultSet.getString(1),
                        resultSet.getInt(2)
                ));
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }

        connection.close();
    }

    private Integer getEntityIdByName(String tableName, String name) throws SQLException {
        String query = "SELECT id FROM " + tableName + " WHERE name = ?;";
        PreparedStatement stmt = this.connection.prepareStatement(query);
        stmt.setString(1, name);

        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return null;
    }

    private String getEntityNameById(String tableName, int id) throws SQLException {
        String query = "SELECT name FROM " + tableName + " WHERE id = ?;";
        PreparedStatement stmt = this.connection.prepareStatement(query);
        stmt.setInt(1, id);

        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
