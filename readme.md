# Classic Console Java Checkers game
#### By Netas Neverauskas and Dominykas Vaišnoras
#### This game is Java implementation of the classic Checkers game playable in console. With all the features like king piece and normal piece. The game is playable by choosing the starting row and column ( of the piece you want to move) and then choosing the row and column where you want to move the piece. If you have a chance to strike the enemy, you will have to choose from possible possibilities with which piece to strike. This game requires 2 players, player 1 and player 2.

## Design patterns implemented in Java console checkers game
### Factory Design Pattern
#### Introduction to Factory Design Pattern
#### The Factory Design Pattern is a creational pattern used in object-oriented programming. It provides a way to encapsulate the instantiation logic of objects. This pattern is particularly useful when the exact types of objects to be created cannot be anticipated or when the creation process is complex.

### Implementation in Checkers Game
#### In the Checkers game, the Factory Design Pattern is implemented for creating Cell objects. This approach demonstrates a clear separation of concerns and adheres to the principle of encapsulation.

### Components
#### CellFactory Interface: Defines the contract for creating new Cell objects.
#### ConcreteCellFactory Class: Implements the CellFactory interface, providing the logic for creating Cell instances.
### Code Snippets
### CellFactory Interface
#### The CellFactory interface declares the createCell method. This method signature allows for creating Cell objects.
```
interface CellFactory {
Cell createCell(int row, int col);
}
```
### ConcreteCellFactory Class
#### The ConcreteCellFactory class implements the CellFactory interface. It provides the specific implementation of the createCell method, returning new instances of Cell.
```
class ConcreteCellFactory implements CellFactory {
    @Override
    public Cell createCell(int row, int col) {
        return new Cell(row, col);
    }
}
```
### Usage in Checkers Game
#### In the Checkers game, the ConcreteCellFactory is used to create Cell objects representing each square on the game board. This encapsulates the creation logic and makes the code more maintainable and flexible for future changes or extensions.
### Advantages
#### 1.Abstraction: The Factory Pattern abstracts the process of object creation, making the codebase more flexible and easier to manage.
#### 2.Extensibility: It's easier to introduce new types of Cell objects without changing the existing codebase.
#### 3.Single Responsibility Principle: The creation logic is kept separate from the main business logic of the application.
### Conclusion
#### The implementation of the Factory Design Pattern in this Checkers game serves as a practical example of how to use this pattern in Java. It demonstrates the pattern's effectiveness in creating objects while keeping the code modular and easy to extend.


#

### Singleton Design Pattern
#### Introduction to Singleton Design Pattern
#### The Singleton Design Pattern is a software design pattern that restricts the instantiation of a class to one "single" instance. This is useful when exactly one object is needed to coordinate actions across the system. It ensures that a class has only one instance and provides a global point of access to it.
### Implementation in Checkers Game
#### In the Checkers game, the Singleton Design Pattern is used to ensure that only one instance of the game is created. This pattern is particularly useful for games, as it typically makes sense to have only one game instance running at a time.
### Code Snippets
#### Singleton Instance Declaration
```
public class Checkers {
    private static Checkers instance; // Singleton instance

    private Checkers() {
        initializeBoard();
    }

    public static Checkers getInstance() {
        if (instance == null) {
            instance = new Checkers();
        }
        return instance;
    }
    // other methods and declarations...
}
```
#### In the Checkers class, the instance variable holds the singleton instance. The constructor is made private to prevent the creation of more than one instance. The getInstance method ensures that only one instance of the Checkers class is created. If the instance is null, a new instance is created; otherwise, the existing instance is returned.
### Usage in Main Method
```
public static void main(String[] args) {
    Checkers game = Checkers.getInstance();
    game.printBoard();
    // game logic...
}
```
#### The main method demonstrates how to access the singleton instance of the Checkers game. It calls getInstance() to get the instance and then proceeds with the game logic.
### Advantages
#### 1. Controlled Access: The Singleton pattern provides a controlled access point to the instance, which is crucial for coordinating actions across the system.
#### 2. Lazy Initialization: The instance is only created when it is needed, optimizing resource and memory usage.
#### 3. Global Access: The Singleton instance can be accessed globally, which is useful for operations that require a common point of interaction.
### Conclusion
#### The Singleton Design Pattern in this Checkers game ensures that only one instance of the game is created and used throughout the application. This pattern is ideal for scenarios where a single instance of a class should manage the state and actions of a feature across the entire application.

#

### Decorator Design Pattern
#### The Decorator Design Pattern is a structural pattern used to add new functionalities to objects dynamically without altering their structure. This pattern creates a decorator class which wraps the original class, enhancing its capabilities.
### Implementation in Checkers Game
#### In the Checkers game, the Decorator Pattern is used to add additional features to Cell objects. This is particularly useful for highlighting cells during gameplay without modifying the Cell class's core functionality.
### Code Snippets
#### Cell Decorator Classes
```
abstract class CellDecorator extends Cell {
    protected Cell decoratedCell;

    public CellDecorator(Cell decoratedCell) {
        super(decoratedCell.getRow(), decoratedCell.getColumn());
        this.decoratedCell = decoratedCell;
    }

    public abstract void decorate();
}

class HighlightedCellDecorator extends CellDecorator {
    public HighlightedCellDecorator(Cell decoratedCell) {
        super(decoratedCell);
    }

    @Override
    public void decorate() {
        System.out.println("Cell at [" + getRow() + ", " + getColumn() + "] is highlighted.");
    }
}
```
#### In this implementation, CellDecorator is an abstract class extending Cell, and HighlightedCellDecorator is a concrete decorator that adds highlighting functionality to a Cell.
#### Usage in Game Logic
```
private static void capturePiece(int player, ArrayList<Path> cellsGo, Scanner in) {
    for (Path p : cellsGo) {
        CellDecorator highlightedCell = new HighlightedCellDecorator(p.getEnd());
        highlightedCell.decorate();
    }
    // Rest of the code..
}
```
#### During the game, when a piece can be captured, the HighlightedCellDecorator is used to highlight the cells that are available for capture.
### Advantages
#### 1. Flexibility: Decorators provide a flexible alternative to subclassing for extending functionality.
#### 2. Dynamic Extension: Functionalities can be added to objects dynamically at runtime.
#### 3. Single Responsibility Principle: Each class remains focused on its primary responsibility, with additional features being handled by decorators.
### Conclusion
#### The Decorator Design Pattern in the Checkers game adds a powerful way to extend the functionalities of Cell objects, such as highlighting potential moves. This pattern keeps the codebase flexible and maintainable, enabling dynamic feature extension without modifying existing code.