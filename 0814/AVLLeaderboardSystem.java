import java.util.*;

public class AVLLeaderboardSystem {

    private static final class Entry {
        final int score;
        final String id;
        Entry(int s, String i){ score = s; id = i; }
    }

    private static final class Node {
        Entry key;
        Node left, right;
        int height = 1;
        int size = 1; 
        Node(Entry k){ key = k; }
    }

    private Node root;
    private final Map<String,Integer> scoreById = new HashMap<>();

    private static int cmp(Entry a, Entry b){
        if(a.score != b.score) return Integer.compare(b.score, a.score); // desc
        return a.id.compareTo(b.id); // asc
    }

    public boolean addPlayer(String id, int score){
        if(scoreById.containsKey(id)) return false;
        Entry e = new Entry(score, id);
        root = insert(root, e);
        scoreById.put(id, score);
        return true;
    }

    public void updateScore(String id, int newScore){
        Integer old = scoreById.get(id);
        if(old == null){
            addPlayer(id, newScore);
            return;
        }
        root = delete(root, new Entry(old, id));
        root = insert(root, new Entry(newScore, id));
        scoreById.put(id, newScore);
    }

    public int getRank(String id){
        Integer sc = scoreById.get(id);
        if(sc == null) return -1;
        return rank(root, new Entry(sc, id));
    }

    public List<Map.Entry<String,Integer>> topK(int k){
        ArrayList<Map.Entry<String,Integer>> res = new ArrayList<>();
        collectInOrder(root, res, k);
        return res;
    }

    private static int h(Node n){ return n==null?0:n.height; }
    private static int sz(Node n){ return n==null?0:n.size; }
    private static void pull(Node n){
        if(n!=null){
            n.height = 1 + Math.max(h(n.left), h(n.right));
            n.size = 1 + sz(n.left) + sz(n.right);
        }
    }

    private static Node rotL(Node x){
        Node y = x.right, t2 = y.left;
        y.left = x; x.right = t2;
        pull(x); pull(y);
        return y;
    }

    private static Node rotR(Node y){
        Node x = y.left, t2 = x.right;
        x.right = y; y.left = t2;
        pull(y); pull(x);
        return x;
    }

    private static int bf(Node n){ return n==null?0:h(n.left)-h(n.right); }

    private static Node balance(Node n){
        pull(n);
        int b = bf(n);
        if(b > 1){
            if(bf(n.left) < 0) n.left = rotL(n.left);
            return rotR(n);
        }
        if(b < -1){
            if(bf(n.right) > 0) n.right = rotR(n.right);
            return rotL(n);
        }
        return n;
    }

    private Node insert(Node n, Entry key){
        if(n==null) return new Node(key);
        int c = cmp(key, n.key);
        if(c < 0) n.left = insert(n.left, key);
        else if(c > 0) n.right = insert(n.right, key);
        else return n; 
        return balance(n);
    }

    private Node delete(Node n, Entry key){
        if(n==null) return null;
        int c = cmp(key, n.key);
        if(c < 0) n.left = delete(n.left, key);
        else if(c > 0) n.right = delete(n.right, key);
        else {
            if(n.left==null || n.right==null){
                n = (n.left!=null) ? n.left : n.right;
            }else{
                Node succ = minNode(n.right);
                n.key = succ.key;
                n.right = delete(n.right, succ.key);
            }
        }
        if(n==null) return null;
        return balance(n);
    }

    private Node minNode(Node n){
        while(n.left!=null) n = n.left;
        return n;
    }

    private int rank(Node n, Entry key){
        int r = 1;
        while(n != null){
            int c = cmp(key, n.key);
            if(c < 0){
                n = n.left;
            }else if(c > 0){
                
                r += sz(n.left) + 1;
                n = n.right;
            }else{
                r += sz(n.left);
                return r;
            }
        }
        return -1; 
    }

    private void collectInOrder(Node n, List<Map.Entry<String,Integer>> out, int k){
        if(n==null || out.size()>=k) return;
        collectInOrder(n.left, out, k);
        if(out.size()<k) out.add(new AbstractMap.SimpleEntry<>(n.key.id, n.key.score));
        collectInOrder(n.right, out, k);
    }

    public static void main(String[] args){
        AVLLeaderboardSystem lb = new AVLLeaderboardSystem();
        lb.addPlayer("alice", 90);
        lb.addPlayer("bob", 95);
        lb.addPlayer("cindy", 88);
        lb.updateScore("alice", 99);
        System.out.println("Rank(alice) = " + lb.getRank("alice")); // 1
        System.out.println("Top2 = " + lb.topK(2));
    }
}
