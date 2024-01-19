### Contributors: Netas Neverauskas and Dominykas Vaišnoras

# Factory Design Pattern in Java - Checkers Game Example
# Introduction to Factory Design Pattern
# The Factory Design Pattern is a creational pattern used in object-oriented programming. It provides a way to encapsulate the instantiation logic of objects. This pattern is particularly useful when the exact types of objects to be created cannot be anticipated or when the creation process is complex.

# Implementation in Checkers Game
# In the Checkers game, the Factory Design Pattern is implemented for creating Cell objects. This approach demonstrates a clear separation of concerns and adheres to the principle of encapsulation.

# Components
# CellFactory Interface: Defines the contract for creating new Cell objects.
# ConcreteCellFactory Class: Implements the CellFactory interface, providing the logic for creating Cell instances.
# Code Snippets
# CellFactory Interface
# The CellFactory interface declares the createCell method. This method signature allows for creating Cell objects.