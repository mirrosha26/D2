import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            DatabaseConnector dbConnector = new H2Connector();
            List<Tree> trees = buildTreesFromDB(dbConnector);

            int totalLeaves = getTotalLeaves(trees);

            writeOutput("output/output.csv", totalLeaves);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Tree> buildTreesFromDB(DatabaseConnector dbConnector) throws SQLException {
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        try (Connection connection = dbConnector.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id, parent_id FROM TREES")) {
            while (resultSet.next()) {
                int nodeId = resultSet.getInt("id");
                int parentId = resultSet.getInt("parent_id");

                TreeNode node = nodeMap.computeIfAbsent(nodeId, TreeNode::new);
                if (nodeId != parentId) {
                    TreeNode parent = nodeMap.computeIfAbsent(parentId, TreeNode::new);
                    parent.addChild(node);
                }
            }
        }

        List<Tree> trees = new ArrayList<>();
        for (TreeNode node : nodeMap.values()) {
            if (node.isRoot()) {
                trees.add(new Tree(node));
            }
        }
        return trees;
    }

    private static int getTotalLeaves(List<Tree> trees) {
        int totalLeaves = 0;
        for (Tree tree : trees) {
            totalLeaves += tree.getAllLeaves().size();
        }
        return totalLeaves;
    }

    private static void writeOutput(String filePath, int totalLeaves) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Total number of leaves in all trees: " + totalLeaves);
        }
    }
}