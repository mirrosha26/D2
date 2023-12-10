import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private int id;
    private TreeNode parent;
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public List<TreeNode> getChildren() {
        return new ArrayList<>(children);
    }

    public void addChild(TreeNode child) {
        children.add(child);
        child.setParent(this);
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public boolean isRoot() {
        return parent == null;
    }
}