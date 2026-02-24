```mermaid
graph TD;
    A((Start nearest(p))) --> B{isEmpty?};
    B -- Yes --> C[Return null];
    B -- No --> D[minDistance = Infinity];
    D --> E[nearest = null];
    E --> F[Iterate points];
    F --> G[distance = point.distanceSquaredTo(p)];
    G --> H{distance < minDistance?};
    H -- Yes --> I[minDistance = distance];
    I --> J[nearest = point];
    J --> K[Next point?];
    H -- No --> K;
    K -- Yes --> F;
    K -- No --> L[Return nearest];
    L --> M((End nearest));
```
