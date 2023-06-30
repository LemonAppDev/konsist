# Circular dependency 3

Test scenario with circular dependency:

```mermaid
%%{init: {'theme': 'forest'}}%%
flowchart LR
    Layer1 --> Layer2 & Layer3
    Layer2 --> Layer4 & Layer5
    Layer3 --> Layer5
    Layer4 --> Layer1
```
