# Architecture 4

Here we're testing situation where Presentation depends on Application, Application depends on Domain and Infrastructure, 
Domain depends on Infrastructure.
See below diagram:

```mermaid
%%{init: {'theme': 'forest'}}%%
flowchart LR
    Presentation --> Application
    Application & Domain --> Infrastructure
    Application --> Domain
```
