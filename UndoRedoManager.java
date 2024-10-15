// package DoubleLinkedList;

/**
 * Implement an application that supports undo/redo functionality. Use a linked
 * list to maintain a sequence of states.
 * Each state change is stored as a node in the list, allowing for easy
 * navigation
 * 1<>2<>3<>4<>5
 */

public class UndoRedoManager<T> {
    private class Node {
        private T state;
        private Node prev;
        private Node next;

        private Node(T state) {
            this.state = state;
        }
    }

    private Node currentState;

    // Undo operation
    public T undo() {
        if (currentState == null) {
            System.out.println("No state to undo");
            return null;
        }
        // Get the previous state
        Node previousState = currentState.prev;
        if (previousState == null) {
            System.out.println("Reached the initial state");
            return null;
        } else {
            // Update the current state to the previous state
            currentState = previousState;
        }
        return currentState.state;
    }

    // Redo operation
    public T redo() {
        if (currentState == null || currentState.next == null) {
            System.out.println("No state to redo");
            return null;
        }
        // Move forward to the next state
        currentState = currentState.next;
        return currentState.state;
    }

    // Perform an operation (add a new state)
    public void addState(T newState) {
        // Create a new node for the new state
        Node newNode = new Node(newState);

        // Set the links for the new node
        newNode.prev = currentState;
        newNode.next = null;

        // Update the next link for the current state
        if (currentState != null) {
            currentState.next = newNode;
        }

        // Update the current state to the new state
        currentState = newNode;
    }

    // Main method for testing
    public static void main(String[] args) {
        UndoRedoManager<String> undoRedoManager = new UndoRedoManager<>();
        undoRedoManager.addState("State 1");
        undoRedoManager.addState("State 2");
        undoRedoManager.addState("State 3");
        undoRedoManager.addState("State 4");
        undoRedoManager.addState("State 5");

        // Testing undo operations
        System.out.println("Current State: " + undoRedoManager.currentState.state);
        undoRedoManager.undo();
        System.out.println("Current State after undo: " + undoRedoManager.currentState.state);
        undoRedoManager.undo();
        System.out.println("Current State after undo: " + undoRedoManager.currentState.state);

        // Testing redo operations
        undoRedoManager.redo();
        System.out.println("Current State after redo: " + undoRedoManager.currentState.state);
        undoRedoManager.redo();
        System.out.println("Current State after redo: " + undoRedoManager.currentState.state);

        // Further testing undo and redo
        undoRedoManager.undo();
        System.out.println("Current State after undo: " + undoRedoManager.currentState.state);
        undoRedoManager.redo();
        System.out.println("Current State after redo: " + undoRedoManager.currentState.state);
    }
}
