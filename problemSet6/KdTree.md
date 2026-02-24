```mermaid
graph TD;
    A((Start insert(x, p, ...))) --> B{x == null?};
    B -- Yes --> C[size++, Return new Node(p, rect)];
    B -- No --> D{x.p.equals(p)?};
    D -- Yes --> E[Return x];
    D -- No --> F{vertical?};
    F -- Yes --> G[cmp = Double.compare(p.x, x.p.x)];
    F -- No --> H[cmp = Double.compare(p.y, x.p.y)];
    G --> I{cmp < 0?};
    H --> I;
    I -- Yes --> J[x.lb = insert(x.lb, p, ...), Return x];
    I -- No --> K[x.rt = insert(x.rt, p, ...), Return x];
```
