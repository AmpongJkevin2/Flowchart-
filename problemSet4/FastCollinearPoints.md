```mermaid
graph TD;
    A((Start FastCollinearPoints)) --> B[Sort points];
    B --> C[i = 0];
    C --> D{i < n};
    D -- Yes --> E[p = points[i]];
    E --> F[Sort other points by slope to p];
    F --> G[j = 0];
    G --> H{j < otherPoints.length - 1};
    H -- Yes --> I[count = 1];
    I --> J{slopes are equal?};
    J -- Yes --> K[count++];
    K --> L[j++];
    L --> H;
    J -- No --> M{count >= 3?};
    M -- Yes --> N[Add segment if p is the origin];
    N --> O[j++];
    M -- No --> O;
    O --> H;
    H -- No --> P[i++];
    P --> D;
    D -- No --> Q((End));
```
