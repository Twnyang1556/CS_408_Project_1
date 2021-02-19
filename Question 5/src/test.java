import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class test {
    static class Node {
        int num;
        Node(int i) {
            num = i;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return num == node.num;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num);
        }
    }

    public static void main(String[] args) {
        Set<Node> test = new HashSet<>();
        Node n1 = new Node(1);
        Node n2 = new Node(1);
        test.add(n1);
        System.out.println(test.contains(n2));
    }
}
