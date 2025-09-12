# Java Programming Exercises

Java implementations of programming exercises and algorithms.

## Structure

- `src/main/java/com/exercises/games/` - Game implementations
- `src/main/java/com/exercises/algorithms/` - Algorithm implementations  
- `src/main/java/com/exercises/datastructures/` - Data structure implementations
- `src/test/java/` - Unit tests

## Requirements

- Java 17 or higher
- Maven 3.6 or higher

## Building and Running

```bash
# Compile the project
mvn compile

# Run tests
mvn test

# Run a specific class (e.g., NoughtsAndCrosses)
mvn exec:java -Dexec.mainClass="com.exercises.games.NoughtsAndCrosses"

# Package as JAR
mvn package
```

## Current Exercises

### Games
- **NoughtsAndCrosses** - Tic-Tac-Toe implementation with MENACE AI
  - Features machine learning through reinforcement
  - Demonstrates game state management and AI decision making
  - Run with: `mvn exec:java`