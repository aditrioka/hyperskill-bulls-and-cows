# Bulls and Cows Game 🎮
A repository documenting the learning process for the Kotlin project *Bulls and Cows* from [Hyperskill](https://hyperskill.org/).

## About
This project implements an advanced version of the classic code-breaking game "Bulls and Cows" using Kotlin. The game features:
- Customizable secret code length and symbol range
- Support for both numbers (0-9) and letters (a-z)
- Turn-based gameplay with immediate feedback
- Robust input validation and error handling
- Clear game state management using OOP principles

## Features
- **Customizable Game Settings**:
  - Players can choose the length of the secret code
  - Players can specify the number of possible symbols (2-36)
  - Supports combinations of numbers (0-9) and letters (a-z)
- **Intelligent Code Generation**:
  - Generates unique random codes without duplicates
  - Uses Kotlin's Random class for secure randomization
  - Validates code length against available symbols
- **Advanced Grading System**:
  - Tracks both "bulls" (correct position and symbol)
  - Tracks "cows" (correct symbol, wrong position)
  - Provides clear feedback after each guess
- **Error Handling**:
  - Validates input lengths and ranges
  - Handles invalid numeric inputs
  - Prevents impossible game configurations
  - Provides clear error messages

## Learning Outcomes
Through this project, I learned:
- Object-Oriented Programming in Kotlin with companion objects
- Working with Kotlin collections (MutableList, MutableSet)
- String manipulation and character ranges
- Random number generation in Kotlin
- Input validation and error handling using try-catch
- Game state management using class properties
- Implementing custom game logic and scoring systems

## Code Structure
- `BullsAndCows` class: Main game logic
  - `play()`: Main game loop
  - `promptInput()`: Handles user input validation
  - `grade()`: Implements the grading system
  - `generateCode()`: Creates the secret code
  - `getTextOfRange()`: Generates range representation

## Acknowledgements
Project provided by Hyperskill: [Bulls and Cows (Kotlin)](https://hyperskill.org/projects/364)
