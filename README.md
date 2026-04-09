# Train Consist Management App (Core Java)

Train Consist Management System implemented using **Core Java** and **data structures** (primarily `LinkedList<Coach>`).

## Prerequisites

- Java JDK installed (Java 17+ recommended)
- `javac` and `java` available in `PATH`

## Run

```bash
javac -d out src/*.java
java -cp out Main
```

## Git Workflow (Strict)

- Base branch: `dev`
- Feature branches (one per use case):
  - `feature/usecase-1-initialize-consist`
  - `feature/usecase-2-add-coach`
  - `feature/usecase-3-remove-coach`
  - `feature/usecase-4-reorder-coaches`
  - `feature/usecase-5-search-coach`
  - `feature/usecase-6-validate-consist`
  - `feature/usecase-7-display-consist`
- `main` is never merged into; all merges land in `dev`.

### Commands Used (Sequence)

```bash
# Clone
git clone https://github.com/atharv4444/Train-Consist-Management-App.git

# Create dev
git checkout -b dev

# For each use case (example)
git checkout dev
git checkout -b feature/<usecase-branch>

# ... implement ...
git add .
git commit -m "<clear message>"

git checkout dev
git merge --no-ff feature/<usecase-branch> -m "merge: <usecase>"
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

### UC5 — Search Coach

- Performs a **linear search** through the `LinkedList` to find a coach by ID and returns position/details.

### UC6 — Validate Consist

- Validates:
  - duplicate coach IDs
  - ordering rules (ENGINE first if present, GUARD last if present)
  - simple capacity constraints (per coach + total)

### UC7 — Display Consist

- Prints the current consist in a readable, indexed table format.
