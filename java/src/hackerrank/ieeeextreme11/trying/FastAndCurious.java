/*
 * Copyright to Eduze@UoM 2017
 */

package hackerrank.ieeeextreme11.trying;

import java.beans.ConstructorProperties;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class FastAndCurious {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] firstLine = scanner.nextLine().split(" ");
        int numOfVertices = Integer.parseInt(firstLine[0]);
        int numOfEdges = Integer.parseInt(firstLine[1]);

        List<Node<Integer>> nodes = new ArrayList<>();
        Graph<Integer> graph = new Graph<>();
        for (int i = 0; i < numOfVertices; i++) {
            nodes.add(graph.createNode(i));
        }

        for (int j = 1; j < numOfEdges; j++) {
            int v = scanner.nextInt() - 1;
            int w = scanner.nextInt() - 1;
            nodes.get(v).linkedTo(nodes.get(w));
            nodes.get(w).linkedTo(nodes.get(v));
        }

        Set<Integer> cyclicNodes = new HashSet<>();
        try {
            graph.topologicalSort();
        } catch (CyclesFoundException e) {
            String lines[] = e.getMessage().split("\n");
            for (int i = 1; i < lines.length; i++) {
                Set<Integer> nodeSet = getCyclicNodes(lines[i]);
                if (nodeSet.size() > 2) {
                    cyclicNodes.addAll(nodeSet);
                }
            }
        }

        for (int i = 0; i < numOfVertices; i++) {
            if (!cyclicNodes.contains(i)) {
                System.out.println(i + 1);
            }
        }
    }

    private static Set<Integer> getCyclicNodes(String line) {
        String[] parts = line.split("->");
        Set<Integer> nodes = new HashSet<>();
        for (String part : parts) {
            nodes.add(Integer.parseInt(part.trim()));
        }

        return nodes;
    }
}

class Graph<T> {
    private final List<Node<T>> nodes = new ArrayList();

    @SafeVarargs
    public static <T> String nodeNames(Node... nodes) {
        return nodeNames(Arrays.asList(nodes));
    }

    public static <T> String nodeNames(List<Node<T>> nodes) {
        return listToString(", ", nodes, (node) -> {
            return node.getValue().toString();
        });
    }

    public static String listToString(String delimiter, List<?> list) {
        return listToString(delimiter, list, Object::toString);
    }

    public static <T> String listToString(String delimiter, List<T> list, Function<T, String> toString) {
        return String.join(delimiter, (Iterable) list.stream().map(toString).collect(Collectors.toList()));
    }

    public Node<T> findOrCreateNode(T value) {
        return (Node) this.findNode(value).orElseGet(() -> {
            return this.createNode(value);
        });
    }

    public Optional<Node<T>> findNode(T value) {
        List<Node<T>> found = this.find((node) -> {
            return node.getValue().equals(value);
        });
        if (found.isEmpty()) {
            return Optional.empty();
        } else if (found.size() == 1) {
            return Optional.of(found.get(0));
        } else {
            throw new IllegalStateException("multiple nodes with the same value to search for: " + value);
        }
    }

    public Node<T> createNode(T value) {
        Node<T> node = new Node(value);
        this.nodes.add(node);
        return node;
    }

    public void topologicalSort() {
        List<List<Node<T>>> stronglyConnectedComponents = this.findStronglyConnectedComponents();
        List<Node<T>> sorted = new ArrayList();
        this.handleCycles(stronglyConnectedComponents, sorted);
        Collections.reverse(sorted);
        this.replaceNodes(sorted);
    }

    public List<List<Node<T>>> findStronglyConnectedComponents() {
        StronglyConnectedComponentsFinder<T> visitor = new StronglyConnectedComponentsFinder();
        this.visit(visitor);
        this.cleanup();
        return visitor.getStronglyConnectedComponents();
    }

    private void handleCycles(List<List<Node<T>>> stronglyConnectedComponents, List<Node<T>> sorted) {
        List<List<Node<T>>> cycles = new ArrayList();
        stronglyConnectedComponents.forEach((scc) -> {
            if (scc.size() > 1) {
                cycles.add(scc);
            }

            Node<T> node = (Node) scc.iterator().next();
            if (node.isLinkedTo(new Node[]{node})) {
                cycles.add(scc);
            }

            sorted.add(node);
        });
        if (!cycles.isEmpty()) {
            throw new CyclesFoundException(cycles);
        }
    }

    private void cleanup() {
        this.unmark(StronglyConnectedComponentsFinder.Root.class);
        this.unmark(Mark.Index.class);
    }

    private void replaceNodes(List<Node<T>> nodes) {
        this.nodes.clear();
        this.nodes.addAll(nodes);
    }

    public int mark(Mark mark) {
        mark.getClass();
        return this.countingVisit(mark::mark);
    }

    public int unmark(Mark mark) {
        mark.getClass();
        return this.countingVisit(mark::unmark);
    }

    public int unmark(Class<? extends Mark> type) {
        return this.countingVisit((node) -> {
            return node.unmark(type).isPresent();
        });
    }

    public int countingVisit(Function<Node<T>, Boolean> visitor) {
        AtomicInteger count = new AtomicInteger(0);
        this.visit((node) -> {
            if (((Boolean) visitor.apply(node)).booleanValue()) {
                count.incrementAndGet();
            }

        });
        return count.get();
    }

    public List<Node<T>> find(Mark mark) {
        mark.getClass();
        return this.find(mark::isMarked);
    }

    public List<Node<T>> find(Predicate<Node<T>> predicate) {
        List<Node<T>> found = new ArrayList();
        this.visit((node) -> {
            if (predicate.test(node)) {
                found.add(node);
            }

        });
        return found;
    }

    public void visit(Consumer<Node<T>> visitor) {
        this.nodes.forEach(visitor);
    }

    public List<Node<T>> remove(Predicate<Node<T>> predicate) {
        List<Node<T>> found = this.find(predicate);
        found.forEach((node) -> {
            this.remove(node);
        });
        return found;
    }

    public boolean remove(Node<T> node) {
        return this.nodes.remove(node);
    }

    public int size() {
        return this.nodes.size();
    }

    public boolean isEmpty() {
        return this.nodes.isEmpty();
    }

    public String toString() {
        return listToString("\n", this.nodes);
    }

    public Graph() {
    }

    public List<Node<T>> getNodes() {
        return this.nodes;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Graph)) {
            return false;
        } else {
            Graph<?> other = (Graph) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$nodes = this.getNodes();
                Object other$nodes = other.getNodes();
                if (this$nodes == null) {
                    if (other$nodes != null) {
                        return false;
                    }
                } else if (!this$nodes.equals(other$nodes)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Graph;
    }
}

class CyclesFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final List<List<Node<?>>> cycles;

    public <T> CyclesFoundException(List<List<Node<T>>> cycles) {
        this.cycles = (List) cycles;
    }

    public String getMessage() {
        return "found " + this.cycles.size() + " cycle(s) in graph:\n  " + (String) this.cycles.stream().map((cycle) -> {
            return Graph.listToString(" -> ", cycle, (node) -> {
                return node.getValue().toString();
            });
        }).collect(Collectors.joining("\n  "));
    }

    public List<List<Node<?>>> getCycles() {
        return this.cycles;
    }
}


interface Mark {
    default <T> boolean mark(Node<T> node) {
        return node.mark(this);
    }

    default <T> boolean isMarked(Node<T> node) {
        return node.isMarked(this);
    }

    default <T> boolean unmark(Node<T> node) {
        return node.unmark(this);
    }

    public static final class Index implements Mark {
        private final int index;

        public static int of(Node<?> node) {
            return ((Mark.Index) node.getMark(Mark.Index.class).get()).getIndex();
        }

        @ConstructorProperties({"index"})
        public Index(int index) {
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof Mark.Index)) {
                return false;
            } else {
                Mark.Index other = (Mark.Index) o;
                return this.getIndex() == other.getIndex();
            }
        }

        public String toString() {
            return "Mark.Index(index=" + this.getIndex() + ")";
        }
    }

    public static final class StringMark implements Mark {
        private final String string;

        public String toString() {
            return this.string;
        }

        @ConstructorProperties({"string"})
        public StringMark(String string) {
            this.string = string;
        }

        public String getString() {
            return this.string;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof Mark.StringMark)) {
                return false;
            } else {
                Mark.StringMark other = (Mark.StringMark) o;
                Object this$string = this.getString();
                Object other$string = other.getString();
                if (this$string == null) {
                    if (other$string != null) {
                        return false;
                    }
                } else if (!this$string.equals(other$string)) {
                    return false;
                }

                return true;
            }
        }
    }
}

class Node<T> {
    private T value;
    private List<Node<T>> links = new ArrayList<>();
    private List<Mark> marks = new ArrayList<>();

    Node(T value) {
        this.value = value;
    }

    public Node<T> linkedTo(Node<T> target) {
        this.links.add(target);
        return this;
    }

    @SafeVarargs
    public final boolean isLinkedTo(Node... nodes) {
        return this.isLinkedTo((Collection) Arrays.asList(nodes));
    }

    public boolean isLinkedTo(Collection<Node<T>> nodes) {
        return this.links.containsAll(nodes);
    }

    public boolean hasLinks() {
        return !this.links.isEmpty();
    }

    public void forEachLink(Consumer<? super Node<T>> consumer) {
        this.links.forEach(consumer);
    }

    public Node<T> marked(Mark mark) {
        this.mark(mark);
        return this;
    }

    public boolean mark(Mark mark) {
        if (this.marks.contains(mark)) {
            return false;
        } else {
            this.marks.add(mark);
            return true;
        }
    }

    public boolean unmark(Mark mark) {
        return this.marks.remove(mark);
    }

    public boolean isMarked(Mark mark) {
        return this.marks.contains(mark);
    }

    public boolean isMarked(Class<? extends Mark> type) {
        return this.getMark(type).isPresent();
    }

    public <M extends Mark> Optional<M> unmark(Class<M> type) {
        Optional<M> mark = (Optional<M>) this.getMark(type);
        mark.ifPresent((m) -> {
            this.marks.remove(m);
        });
        return mark;
    }

    public <M extends Mark> Optional<Mark> getMark(Class<M> type) {
        return this.marks.stream()
                .filter(type::isInstance).findAny()
                .map(Mark.class::cast);
    }

    public String toString() {
        return this.value + (this.marks.isEmpty() ? "" : this.marks.toString()) + " -> {" + Graph.nodeNames(this.links) + "}";
    }

    public T getValue() {
        return this.value;
    }

    public List<Node<T>> getLinks() {
        return this.links;
    }

    public List<Mark> getMarks() {
        return this.marks;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setLinks(List<Node<T>> links) {
        this.links = links;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Node)) {
            return false;
        } else {
            Node<?> other = (Node) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47:
                {
                    Object this$value = this.getValue();
                    Object other$value = other.getValue();
                    if (this$value == null) {
                        if (other$value == null) {
                            break label47;
                        }
                    } else if (this$value.equals(other$value)) {
                        break label47;
                    }

                    return false;
                }

                Object this$links = this.getLinks();
                Object other$links = other.getLinks();
                if (this$links == null) {
                    if (other$links != null) {
                        return false;
                    }
                } else if (!this$links.equals(other$links)) {
                    return false;
                }

                Object this$marks = this.getMarks();
                Object other$marks = other.getMarks();
                if (this$marks == null) {
                    if (other$marks != null) {
                        return false;
                    }
                } else if (!this$marks.equals(other$marks)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Node;
    }
}

class StronglyConnectedComponentsFinder<T> implements Consumer<Node<T>> {
    private final List<List<Node<T>>> stronglyConnectedComponents = new ArrayList();
    private final AtomicInteger index = new AtomicInteger(0);
    private final Stack<Node<T>> stack = new Stack();

    StronglyConnectedComponentsFinder() {
    }

    public void accept(Node<T> node) {
        if (!node.isMarked(Mark.Index.class)) {
            this.strongconnect(node);
        }

    }

    private void strongconnect(Node<T> node) {
        int indexValue = this.index.getAndIncrement();
        node.mark(new Mark.Index(indexValue));
        node.mark(new StronglyConnectedComponentsFinder.Root(indexValue));
        this.stack.push(node);
        Iterator var3 = node.getLinks().iterator();

        while (var3.hasNext()) {
            Node<T> successor = (Node) var3.next();
            if (!successor.isMarked(Mark.Index.class)) {
                this.strongconnect(successor);
                this.setRoot(node, StronglyConnectedComponentsFinder.Root.of(successor));
            } else if (this.stack.contains(successor)) {
                this.setRoot(node, Mark.Index.of(successor));
            }
        }

        if (this.isRoot(node)) {
            this.stronglyConnectedComponents.add(this.popSCC(this.stack, node));
        }

    }

    private void setRoot(Node<T> node, int min) {
        node.mark(new StronglyConnectedComponentsFinder.Root(Math.min(StronglyConnectedComponentsFinder.Root.removeRoot(node), min)));
    }

    private List<Node<T>> popSCC(Stack<Node<T>> stack, Node<T> node) {
        ArrayList scc = new ArrayList();

        Node member;
        do {
            member = (Node) stack.pop();
            scc.add(member);
        } while (member != node);

        return scc;
    }

    private boolean isRoot(Node<T> node) {
        return StronglyConnectedComponentsFinder.Root.of(node) == Mark.Index.of(node);
    }

    public List<List<Node<T>>> getStronglyConnectedComponents() {
        return this.stronglyConnectedComponents;
    }

    static final class Root implements Mark {
        private final int index;

        public static int removeRoot(Node<?> node) {
            return ((StronglyConnectedComponentsFinder.Root) node.unmark(StronglyConnectedComponentsFinder.Root.class).get()).getIndex();
        }

        public static int of(Node<?> node) {
            return ((StronglyConnectedComponentsFinder.Root) node.getMark(StronglyConnectedComponentsFinder.Root.class).get()).getIndex();
        }

        @ConstructorProperties({"index"})
        public Root(int index) {
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof StronglyConnectedComponentsFinder.Root)) {
                return false;
            } else {
                StronglyConnectedComponentsFinder.Root other = (StronglyConnectedComponentsFinder.Root) o;
                return this.getIndex() == other.getIndex();
            }
        }
    }
}