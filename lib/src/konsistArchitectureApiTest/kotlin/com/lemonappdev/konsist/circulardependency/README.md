# Circular dependency

Here we test several situations where circular dependency is set. See below diagrams:

## Test `simply circular dependency with two layers`
```mermaid
%%{init: {'theme': 'forest'}}%%
flowchart LR
    Layer1 --> Layer2
    Layer2 --> Layer1
```

## Test `simply circular dependency with three layers`
```mermaid
%%{init: {'theme': 'forest'}}%%
flowchart LR
    Layer1 --> Layer2
    Layer2 --> Layer3
    Layer3 --> Layer1
```

## Test `complex circular dependency 1`
```mermaid
%%{init: {'theme': 'forest'}}%%
flowchart LR
    Layer1 --> Layer2 & Layer3
    Layer2 --> Layer4 & Layer5
    Layer3 --> Layer5
    Layer4 --> Layer1
```

## Test `complex circular dependency 2`
```mermaid
%%{init: {'theme': 'forest'}}%%
flowchart LR
    Layer1 --> Layer2 
    Layer2 --> Layer3
    Layer3 --> Layer1 & Layer4
```

## Test `complex circular dependency 3`
```mermaid
%%{init: {'theme': 'forest'}}%%
flowchart LR
    Layer1 --> Layer2 & Layer3
    Layer2 --> Layer4
    Layer3 --> Layer4
    Layer4 --> Layer1
```
