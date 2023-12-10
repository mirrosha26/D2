import java.util.ArrayList;
import java.util.List;

public class Tree {
    private TreeNode root;

    public Tree(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public List<TreeNode> getAllNodes() {
        return getAllNodes(root);
    }

    private List<TreeNode> getAllNodes(TreeNode node) {
        List<TreeNode> nodes = new ArrayList<>();
        if (node != null) {
            nodes.add(node);
            for (TreeNode child : node.getChildren()) {
                nodes.addAll(getAllNodes(child));
            }
        }
        return nodes;
    }

    public List<TreeNode> getAllLeaves() {
        return getAllLeaves(root);
    }

    private List<TreeNode> getAllLeaves(TreeNode node) {
        List<TreeNode> leaves = new ArrayList<>();
        if (node != null) {
            if (node.isLeaf()) {
                leaves.add(node);
            } else {
                for (TreeNode child : node.getChildren()) {
                    leaves.addAll(getAllLeaves(child));
                }
            }
        }
        return leaves;
    }
}