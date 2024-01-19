#### Contributors: Netas Neverauskas and Dominykas Vaišnoras
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

#

