# Architecture 11

Test `optional` parameter for `Layer`.

Test scenario where `Presentation` layer depends on `Domain` one and optional `Data` layer has no files
(reuses `architecture2` project):

```mermaid
%%{init: {'theme': 'forest'}}%%
flowchart LR
    Presentation --> Domain
    Data[Data optional, empty] --> Domain
```
