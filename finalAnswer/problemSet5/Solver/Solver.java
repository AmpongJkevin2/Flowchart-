import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private final Stack<Board> solution;
    private final boolean solvable;

    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int moves;
        private final SearchNode previous;
        private final int manhattan;
        private final int priority;

        public SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
            this.manhattan = board.manhattan();
            this.priority = manhattan + moves;
        }

        public int compareTo(SearchNode that) {
            if (this.priority != that.priority) {
                return Integer.compare(this.priority, that.priority);
            }
            return Integer.compare(this.manhattan, that.manhattan);
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        MinPQ<SearchNode> pq = new MinPQ<>();
        pq.insert(new SearchNode(initial, 0, null));

        MinPQ<SearchNode> twinPq = new MinPQ<>();
        twinPq.insert(new SearchNode(initial.twin(), 0, null));

        SearchNode lastNode = null;
        boolean foundSolvable = false;

        while (true) {
            // Main search
            SearchNode node = pq.delMin();
            if (node.board.isGoal()) {
                lastNode = node;
                foundSolvable = true;
                break;
            }
            for (Board neighbor : node.board.neighbors()) {
                if (node.previous == null || !neighbor.equals(node.previous.board)) {
                    pq.insert(new SearchNode(neighbor, node.moves + 1, node));
                }
            }

            // Twin search
            SearchNode twinNode = twinPq.delMin();
            if (twinNode.board.isGoal()) {
                foundSolvable = false;
                break;
            }
            for (Board neighbor : twinNode.board.neighbors()) {
                if (twinNode.previous == null || !neighbor.equals(twinNode.previous.board)) {
                    twinPq.insert(new SearchNode(neighbor, twinNode.moves + 1, twinNode));
                }
            }
        }

        this.solvable = foundSolvable;
        if (foundSolvable) {
            this.solution = new Stack<>();
            SearchNode current = lastNode;
            while (current != null) {
                this.solution.push(current.board);
                current = current.previous;
            }
        } else {
            this.solution = null;
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) {
            return -1;
        }
        return solution.size() - 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return solution;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
