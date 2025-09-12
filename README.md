# Programming Exercises

A collection of programming exercises, algorithms, and problem solutions implemented in multiple languages.

## Structure

This project is organized by programming language, with each language having its own build system and dependencies:

```
programming-exercises/
├── java/          # Java solutions using Maven
├── javascript/    # JavaScript/Node.js solutions using npm
└── python/        # Python solutions using pip
```

Each language directory contains exercises organized by category:
- `games/` - Game implementations (Tic-Tac-Toe, Chess variants, etc.)
- `algorithms/` - Algorithm implementations (sorting, searching, etc.)
- `datastructures/` - Data structure implementations (trees, graphs, etc.)

## Quick Start

### Java
```bash
cd java
mvn compile
mvn test
mvn exec:java -Dexec.mainClass="com.exercises.games.NoughtsAndCrosses"
```

### JavaScript
```bash
cd javascript
npm install
npm test
npm start
```

### Python
```bash
cd python
pip install -r requirements.txt
python -m pytest
python src/games/noughts_and_crosses.py
```

## Adding New Exercises

1. Choose the appropriate language directory
2. Place the solution in the relevant category subdirectory
3. Follow the existing naming conventions
4. Add tests where appropriate
5. Update the language-specific README if needed

## Languages

- **Java**: Uses Maven for dependency management and build automation
- **JavaScript**: Uses Node.js and npm for package management
- **Python**: Uses pip for package management and pytest for testing