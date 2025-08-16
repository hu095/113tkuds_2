import java.util.*;


public class PersistentAVLExercise {

    private static final class Node {
        final int key;
        final Node left, right;
        final int height;
        Node(int key, Node left, Node right){
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = 1 + Math.max(h(left), h(right));
        }
    }

    private final List<Node> versions = new ArrayList<>(); // roots
    public PersistentAVLExercise(){ versions.add(null); }   // 版本 0：空樹


    public int insert(int baseVersion, int key){
        Node base = versions.get(baseVersion);
        Node neu = insertImm(base, key);
        versions.add(neu);
        return versions.size()-1;
    }

    public boolean contains(int version, int key){
        Node n = versions.get(version);
        while(n != null){
            if(key == n.key) return true;
            n = key < n.key ? n.left : n.right;
        }
        return false;
    }

    public int height(int version){ return h(versions.get(version)); }

    public List<Integer> inOrder(int version){
        List<Integer> out = new ArrayList<>();
        dfs(versions.get(version), out);
        return out;
    }

    public int latestVersion(){ return versions.size()-1; }

    private static int h(Node n){ return n==null?0:n.height; }

    private static int bf(Node n){ return h(n.left) - h(n.right); }

    private static Node mk(int key, Node L, Node R){ return new Node(key, L, R); }

    private Node insertImm(Node n, int key){
        if(n == null) return mk(key, null, null);
        if(key == n.key) return n; 
        if(key < n.key){
            Node L = insertImm(n.left, key);
            Node raw = mk(n.key, L, n.right);
            return balance(raw);
        }else{
            Node R = insertImm(n.right, key);
            Node raw = mk(n.key, n.left, R);
            return balance(raw);
        }
    }

    private Node balance(Node n){
        int b = bf(n);
        if(b > 1){
            // LL or LR
            if(bf(n.left) < 0){
                // LR: 先左旋 n.left
                Node L = rotL(n.left);
                Node raw = mk(n.key, L, n.right);
                return rotR(raw);
            }else{
                // LL
                return rotR(n);
            }
        }else if(b < -1){
            // RR or RL
            if(bf(n.right) > 0){
                // RL
                Node R = rotR(n.right);
                Node raw = mk(n.key, n.left, R);
                return rotL(raw);
            }else{
                // RR
                return rotL(n);
            }
        }
        return n;
        }

    private Node rotR(Node y){
        Node x = y.left;
        Node T2 = x.right;
        return mk(x.key, x.left, mk(y.key, T2, y.right));
    }

    private Node rotL(Node x){
        Node y = x.right;
        Node T2 = y.left;
        return mk(y.key, mk(x.key, x.left, T2), y.right);
    }

    private void dfs(Node n, List<Integer> out){
        if(n==null) return;
        dfs(n.left, out);
        out.add(n.key);
        dfs(n.right, out);
    }

    public static void main(String[] args){
        PersistentAVLExercise p = new PersistentAVLExercise();
        int v1 = p.insert(0, 10); 
        int v2 = p.insert(v1, 20); 
        int v3 = p.insert(v2, 5); 
        int v4 = p.insert(v3, 15); 

        System.out.println("v1 inorder=" + p.inOrder(v1));
        System.out.println("v3 inorder=" + p.inOrder(v3));
        System.out.println("v4 inorder=" + p.inOrder(v4));
        System.out.println("v3 contains(15)? " + p.contains(v3, 15)); 
        System.out.println("v4 contains(15)? " + p.contains(v4, 15)); 
    }
}
