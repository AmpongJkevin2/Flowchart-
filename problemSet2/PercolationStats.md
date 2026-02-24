```mermaid
graph TD;
    A((Start PercolationStats(n, trials))) --> B[i = 0];
    B --> C{i < trials};
    C -- Yes --> D[perc = new Percolation(n)];
    D --> E{perc.percolates()};
    E -- No --> F[row = StdRandom.uniform(1, n + 1)];
    F --> G[col = StdRandom.uniform(1, n + 1)];
    G --> H[perc.open(row, col)];
    H --> E;
    E -- Yes --> I[thresholds[i] = (double) perc.numberOfOpenSites() / (n * n)];
    I --> J[i++];
    J --> C;
    C -- No --> K((End Constructor));

    subgraph main
        L((Start main)) --> M[/Read n and T from args/];
        M --> N[stats = new PercolationStats(n, T)];
        N --> O[/Print mean, stddev, confidence interval/];
        O --> P((End main));
    end
```
