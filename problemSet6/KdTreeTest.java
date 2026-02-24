import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTreeTest {
    public static void main(String[] args) {
        PointSET brutenorce = new PointSET();
        KdTree kdtree = new KdTree();

        Point2D p1 = new Point2D(0.7, 0.2);
        Point2D p2 = new Point2D(0.5, 0.4);
        Point2D p3 = new Point2D(0.2, 0.3);
        Point2D p4 = new Point2D(0.4, 0.7);
        Point2D p5 = new Point2D(0.9, 0.6);

        brutenorce.insert(p1); kdtree.insert(p1);
        brutenorce.insert(p2); kdtree.insert(p2);
        brutenorce.insert(p3); kdtree.insert(p3);
        brutenorce.insert(p4); kdtree.insert(p4);
        brutenorce.insert(p5); kdtree.insert(p5);

        System.out.println("Size (PointSET): " + brutenorce.size());
        System.out.println("Size (KdTree): " + kdtree.size());

        Point2D query = new Point2D(0.8, 0.1);
        System.out.println("Nearest to (0.8, 0.1) (PointSET): " + brutenorce.nearest(query));
        System.out.println("Nearest to (0.8, 0.1) (KdTree): " + kdtree.nearest(query));
    }
}
