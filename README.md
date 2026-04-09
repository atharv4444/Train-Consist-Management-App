# Train Consist Management App (Core Java)

Train Consist Management System implemented using **Core Java** and **data structures** (primarily `LinkedList<Coach>`).

## Run

```bash
javac -d out src/*.java
java -cp out Main
```

## Use Cases

### UC1 — Initialize Consist

- Creates an empty `TrainConsist` backed by `LinkedList<Coach>`.
- Prints the initial consist summary.

### UC2 — Add Coach

- Adds a coach at:
  - beginning (`addFirst`)
  - end (`addLast`)
  - specific position (`add(index, coach)`)

### UC3 — Remove Coach

- Removes a coach by **Coach ID** using linear traversal and safe iterator removal.

### UC4 — Reorder Coaches

- Moves a coach from one index to another (simulates shunting) using remove+add on `LinkedList`.
