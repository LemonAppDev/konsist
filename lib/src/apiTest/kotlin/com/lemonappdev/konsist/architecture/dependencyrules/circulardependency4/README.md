# Circular dependency 4

Test scenario with circular dependency:

```mermaid
%%{init: {'theme': 'forest'}}%%
flowchart LR
    Layer1 --> Layer2 
    Layer2 --> Layer3
    Layer3 --> Layer1 & Layer4
```
