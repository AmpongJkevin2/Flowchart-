import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.ArrayList;

public class KdTree {
    private static class Node {
        private final Point2D p;
        private final RectHV rect;
        private Node lb; // left/bottom
        private Node rt; // right/top

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }
    }

    private Node root;
    private int size;

    public KdTree() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        root = insert(root, p, 0.0, 0.0, 1.0, 1.0, true);
    }

    private Node insert(Node x, Point2D p, double xmin, double ymin, double xmax, double ymax, boolean vertical) {
        if (x == null) {
            size++;
            return new Node(p, new RectHV(xmin, ymin, xmax, ymax));
        }
        if (x.p.equals(p)) return x;

        int cmp = vertical ? Double.compare(p.x(), x.p.x()) : Double.compare(p.y(), x.p.y());

        if (cmp < 0) {
            if (vertical) x.lb = insert(x.lb, p, xmin, ymin, x.p.x(), ymax, !vertical);
            else x.lb = insert(x.lb, p, xmin, ymin, xmax, x.p.y(), !vertical);
        } else {
            if (vertical) x.rt = insert(x.rt, p, x.p.x(), ymin, xmax, ymax, !vertical);
            else x.rt = insert(x.rt, p, xmin, x.p.y(), xmax, ymax, !vertical);
        }
        return x;
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return contains(root, p, true);
    }

    private boolean contains(Node x, Point2D p, boolean vertical) {
        if (x == null) return false;
        if (x.p.equals(p)) return true;

        int cmp = vertical ? Double.compare(p.x(), x.p.x()) : Double.compare(p.y(), x.p.y());
        if (cmp < 0) return contains(x.lb, p, !vertical);
        else return contains(x.rt, p, !vertical);
    }

    public void draw() {
        // Implementation omitted for brevity as it's not required for unit testing logic
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        ArrayList<Point2D> result = new ArrayList<>();
        range(root, rect, result);
        return result;
    }

    private void range(Node x, RectHV rect, ArrayList<Point2D> result) {
        if (x == null) return;
        if (!rect.intersects(x.rect)) return;
        if (rect.contains(x.p)) result.add(x.p);
        range(x.lb, rect, result);
        range(x.rt, rect, result);
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (isEmpty()) return null;
        return nearest(root, p, root.p, true);
    }

    private Point2D nearest(Node x, Point2D p, Point2D nearest, boolean vertical) {
        if (x == null) return nearest;
        if (x.rect.distanceSquaredTo(p) >= p.distanceSquaredTo(nearest)) return nearest;

        if (x.p.distanceSquaredTo(p) < nearest.distanceSquaredTo(p)) {
            nearest = x.p;
        }

        Node first = x.lb;
        Node second = x.rt;

        if (vertical) {
            if (p.x() >= x.p.x()) {
                first = x.rt;
                second = x.lb;
            }
        } else {
            if (p.y() >= x.p.y()) {
                first = x.rt;
                second = x.lb;
            }
        }

        nearest = nearest(first, p, nearest, !vertical);
        nearest = nearest(second, p, nearest, !vertical);

        return nearest;
    }
}
