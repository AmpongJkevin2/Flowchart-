```mermaid
graph TD;
    subgraph addFirst
        A((Start addFirst(item))) --> B{item == null?};
        B -- Yes --> C[Throw IllegalArgumentException];
        B -- No --> D[oldFirst = first];
        D --> E[first = new Node()];
        E --> F[first.item = item];
        F --> G[first.next = oldFirst];
        G --> H[first.prev = null];
        H --> I{isEmpty?};
        I -- Yes --> J[last = first];
        J --> K[size++];
        I -- No --> L[oldFirst.prev = first];
        L --> K;
        K --> M((End addFirst));
    end

    subgraph removeLast
        N((Start removeLast)) --> O{isEmpty?};
        O -- Yes --> P[Throw NoSuchElementException];
        O -- No --> Q[item = last.item];
        Q --> R[last = last.prev];
        R --> S[size--];
        S --> T{isEmpty?};
        T -- Yes --> U[first = null];
        U --> V((End removeLast));
        T -- No --> W[last.next = null];
        W --> V;
    end
```
