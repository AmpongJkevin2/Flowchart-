```mermaid
graph TD;
    A((Start)) --> B[champion = ""];
    B --> C[count = 0];
    C --> D{StdIn.isEmpty()};
    D -- No --> E[word = StdIn.readString()];
    E --> F[count++];
    F --> G{StdRandom.bernoulli(1.0 / count)};
    G -- Yes --> H[champion = word];
    H --> D;
    G -- No --> D;
    D -- Yes --> I[/Print champion/];
    I --> J((End));
```
