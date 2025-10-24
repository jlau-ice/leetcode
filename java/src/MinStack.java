class MinStack {

    private class Node {
        Integer val;
        int min;
        Node next;
        public Node() {
        }
        public Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

    private Node root;

    public MinStack() {
        root = null;
    }

    public void push(int val) {
        if (root != null) {
            root = new Node(val,Math.min(val, root.min),root);
        }else {
            root = new Node(val,val,null);
        }
    }

    public void pop() {
        if (root != null) {
            root = root.next;
        }
    }

    public int top() {
        return root.val;
    }

    public int getMin() {
        return root.min;
    }
}
