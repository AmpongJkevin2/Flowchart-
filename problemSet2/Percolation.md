```mermaid
graph TD;
    A((Start open(row, col))) --> B{isOpen(row, col)?};
    B -- Yes --> Z((End));
    B -- No --> C[grid[row - 1][col - 1] = true];
    C --> D[openSites++];
    D --> E[siteIndex = xyTo1D(row, col)];
    E --> F{row == 1?};
    F -- Yes --> G[uf.union(siteIndex, topVirtualSite)];
    G --> H{row == n?};
    F -- No --> H;
    H -- Yes --> I[uf.union(siteIndex, bottomVirtualSite)];
    I --> J{row > 1 && isOpen(row - 1, col)?};
    H -- No --> J;
    J -- Yes --> K[uf.union(siteIndex, xyTo1D(row - 1, col))];
    K --> L{row < n && isOpen(row + 1, col)?};
    J -- No --> L;
    L -- Yes --> M[uf.union(siteIndex, xyTo1D(row + 1, col))];
    M --> N{col > 1 && isOpen(row, col - 1)?};
    L -- No --> N;
    N -- Yes --> O[uf.union(siteIndex, xyTo1D(row, col - 1))];
    O --> P{col < n && isOpen(row, col + 1)?};
    N -- No --> P;
    P -- Yes --> Q[uf.union(siteIndex, xyTo1D(row, col + 1))];
    Q --> Z;
    P -- No --> Z;
```
