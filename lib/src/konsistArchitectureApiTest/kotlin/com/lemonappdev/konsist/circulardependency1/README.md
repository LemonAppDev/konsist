# Circular dependency 1

Test scenario with circular dependency:

```mermaid
%%{init: {'theme': 'forest'}}%%
flowchart LR
    Layer1 --> Layer2
    Layer2 --> Layer1
```
