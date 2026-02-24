```mermaid
graph TD;
    A((Start manhattan)) --> B[manhattan = 0];
    B --> C[i = 0];
    C --> D{i < n};
    D -- Yes --> E[j = 0];
    E --> F{j < n};
    F -- Yes --> G{tiles[i][j] != 0?};
    G -- Yes --> H[targetX = (tiles[i][j] - 1) / n];
    H --> I[targetY = (tiles[i][j] - 1) % n];
    I --> J[manhattan += |i - targetX| + |j - targetY|];
    J --> K[j++];
    G -- No --> K;
    K --> F;
    F -- No --> L[i++];
    L --> D;
    D -- No --> M((End manhattan));
```
