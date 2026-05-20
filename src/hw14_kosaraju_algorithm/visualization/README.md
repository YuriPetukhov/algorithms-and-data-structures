```mermaid

### Двудольный граф A(3,4)

Левая доля: A = {A0, A1, A2}  
Правая доля: B = {B0, B1, B2, B3}

Рёбра:  
A0-B0, A0-B1, A1-B1, A1-B2, A2-B3

flowchart LR
    subgraph A["A"]
        direction TB
        A0((A0))
        A1((A1))
        A2((A2))
    end

    subgraph B["B"]
        direction TB
        B0((B0))
        B1((B1))
        B2((B2))
        B3((B3))
    end

    A0 --- B0
    A0 --- B1
    A1 --- B1
    A1 --- B2
    A2 --- B3
```