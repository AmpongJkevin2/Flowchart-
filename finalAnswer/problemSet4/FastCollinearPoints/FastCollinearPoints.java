import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
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

        for (int i = 0; i < n; i++) {
            Point p = pointsCopy[i];
            Point[] otherPoints = new Point[n - 1];
            int index = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                otherPoints[index++] = pointsCopy[j];
            }

            Arrays.sort(otherPoints, p.slopeOrder());

            int count = 1;
            for (int j = 0; j < otherPoints.length - 1; j++) {
                if (p.slopeTo(otherPoints[j]) == p.slopeTo(otherPoints[j + 1])) {
                    count++;
                } else {
                    if (count >= 3) {
                        Point[] collinearPoints = new Point[count + 1];
                        collinearPoints[0] = p;
                        for (int k = 0; k < count; k++) {
                            collinearPoints[k + 1] = otherPoints[j - count + 1 + k];
                        }
                        Arrays.sort(collinearPoints);
                        if (p.compareTo(collinearPoints[0]) == 0) {
                            foundSegments.add(new LineSegment(p, collinearPoints[count]));
                        }
                    }
                    count = 1;
                }
            }
            if (count >= 3) {
                Point[] collinearPoints = new Point[count + 1];
                collinearPoints[0] = p;
                for (int k = 0; k < count; k++) {
                    collinearPoints[k + 1] = otherPoints[otherPoints.length - count + k];
                }
                Arrays.sort(collinearPoints);
                if (p.compareTo(collinearPoints[0]) == 0) {
                    foundSegments.add(new LineSegment(p, collinearPoints[count]));
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
