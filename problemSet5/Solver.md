```mermaid
graph TD;
    A((Start Solver Constructor)) --> B[Initialize PQ and twinPQ];
    B --> C[Insert initial nodes];
    C --> D{True};
    D -- Yes --> E[lastNode = solve(PQ)];
    E --> F{lastNode != null?};
    F -- Yes --> G[solvable = true];
    G --> H((End));
    F -- No --> I[twinNode = solve(twinPQ)];
    I --> J{twinNode != null?};
    J -- Yes --> K[solvable = false];
    K --> H;
    J -- No --> D;
```
