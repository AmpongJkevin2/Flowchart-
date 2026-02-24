```mermaid
graph TD;
    A((Start BruteCollinearPoints)) --> B[Sort points];
    B --> C[i = 0];
    C --> D{i < n - 3};
    D -- Yes --> E[j = i + 1];
    E --> F{j < n - 2};
    F -- Yes --> G[k = j + 1];
    G --> H{k < n - 1};
    H -- Yes --> I[l = k + 1];
    I --> J{l < n};
    J -- Yes --> K{p, q, r, s are collinear?};
    K -- Yes --> L[Add segment (p, s)];
    L --> M[l++];
    K -- No --> M;
    M --> J;
    J -- No --> N[k++];
    N --> H;
    H -- No --> O[j++];
    O --> F;
    F -- No --> P[i++];
    P --> D;
    D -- No --> Q((End));
```
