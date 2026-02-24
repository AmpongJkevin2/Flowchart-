import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("points array is null");
        }
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException("point is null");
            }
        }
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);
        for (int i = 0; i < pointsCopy.length - 1; i++) {
            if (pointsCopy[i].compareTo(pointsCopy[i + 1]) == 0) {
                throw new IllegalArgumentException("duplicate points");
            }
        }

        ArrayList<LineSegment> foundSegments = new ArrayList<>();
        int n = pointsCopy.length;
        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int l = k + 1; l < n; l++) {
                        Point p = pointsCopy[i];
                        Point q = pointsCopy[j];
                        Point r = pointsCopy[k];
                        Point s = pointsCopy[l];
                        double slopePQ = p.slopeTo(q);
                        double slopePR = p.slopeTo(r);
                        double slopePS = p.slopeTo(s);
                        if (slopePQ == slopePR && slopePQ == slopePS) {
                            foundSegments.add(new LineSegment(p, s));
                        }
                    }
                }
            }
        }
        segments = foundSegments.toArray(new LineSegment[0]);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segments.length);
    }
}
