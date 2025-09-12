# Python Programming Exercises

Python implementations of programming exercises and algorithms.

## Structure

- `src/games/` - Game implementations
- `src/algorithms/` - Algorithm implementations  
- `src/datastructures/` - Data structure implementations
- `test/` - Unit tests

## Requirements

- Python 3.8 or higher
- pip

## Building and Running

```bash
# Install dependencies
pip install -r requirements.txt

# Install in development mode
pip install -e .

# Run tests
python -m pytest

# Run tests with coverage
python -m pytest --cov=src

# Run a specific program (e.g., Noughts and Crosses)
python src/games/noughts_and_crosses.py
```

## Current Exercises

### Games
- **Noughts and Crosses** - Coming soon (Python port of Java version)

## Adding New Exercises

1. Create your implementation in the appropriate `src/` subdirectory
2. Add corresponding tests in the `test/` directory
3. Follow Python naming conventions (snake_case)
4. Include docstrings and type hints where appropriate
5. Follow PEP 8 style guidelines