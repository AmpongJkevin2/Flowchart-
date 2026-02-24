```mermaid
graph TD;
    subgraph enqueue
        A((Start enqueue(item))) --> B{item == null?};
        B -- Yes --> C[Throw IllegalArgumentException];
        B -- No --> D{n == q.length?};
        D -- Yes --> E[resize(2 * q.length)];
        E --> F[q[n++] = item];
        D -- No --> F;
        F --> G((End enqueue));
    end

    subgraph dequeue
        H((Start dequeue)) --> I{isEmpty?};
        I -- Yes --> J[Throw NoSuchElementException];
        I -- No --> K[rand = StdRandom.uniform(n)];
        K --> L[item = q[rand]];
        L --> M[q[rand] = q[n - 1]];
        M --> N[q[n - 1] = null];
        N --> O[n--];
        O --> P{n > 0 && n == q.length / 4?};
        P -- Yes --> Q[resize(q.length / 2)];
        Q --> R((End dequeue));
        P -- No --> R;
    end
```
