# Architecture 7

Test scenario where `Adapter` depends on `Common` and `Port`, `Port` depends on `Domain` and `Common`, `Domain`
depends on `Common`, `Common` depends on nothing:

```mermaid
%%{init: {'theme': 'forest'}}%%
flowchart LR
    Domain --> Common
    Adapter --> Common
    Port --> Domain & Common
    Adapter --> Port
```
