/**
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 * 
 *  方法一：深搜，使用一个哈希表存储克隆过的节点，从第一个节点引用开始遍历，
 *  若当前节点被克隆过，则直接返回哈希表中存储的遍历过的节点，否则进行克隆
 *  然后再克隆其邻居节点，使得克隆的节点的邻居节点添加该节点的克隆
 *  
 *  方法二：广搜，同样需要一个哈希表存储克隆的节点
 *  然后遍历队列，对队列里的每个节点遍历邻居节点，若被克隆过则直接添加到对应克隆位置
 *  否则先加入哈希表并放入队列中，然后再添加到克隆节点的对应位置
 */

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
class cloneGraph2 {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        HashMap<Node, Node> visited = new HashMap<Node, Node>();
        LinkedList<Node> queue = new LinkedList<Node>();

        queue.add(node);
        visited.put(node, new Node(node.val, new ArrayList<Node>()));

        while( ! queue.isEmpty()) {
            Node n = queue.remove();

            for (Node t : n.neighbors) {
                if ( ! visited.containsKey(t)) {
                    queue.add(t);
                    visited.put(t, new Node(t.val, new ArrayList<Node>()));
                }
                
                visited.get(n).neighbors.add(visited.get(t));  // 注意从visited中取克隆的值
            }
        }

        return visited.get(node);
    }
}
class cloneGraph1 {
    HashMap<Node, Node> visited = new HashMap<Node, Node>();
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        Node cloneNode = new Node(node.val, new ArrayList<Node>());
        visited.put(node, cloneNode);

        for (Node t : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(t));
        }

        return cloneNode;
    }
}