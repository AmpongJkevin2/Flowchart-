```mermaid
graph TD;
    A((Start main)) --> B[/Read k from args/];
    B --> C[rq = new RandomizedQueue<String>()];
    C --> D{StdIn.isEmpty()};
    D -- No --> E[rq.enqueue(StdIn.readString())];
    E --> D;
    D -- Yes --> F[i = 0];
    F --> G{i < k};
    G -- Yes --> H[/System.out.println(rq.dequeue())/];
    H --> I[i++];
    I --> G;
    G -- No --> J((End main));
```
