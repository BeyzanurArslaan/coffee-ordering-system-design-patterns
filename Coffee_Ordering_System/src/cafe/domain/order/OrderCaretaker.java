package cafe.domain.order;

import java.util.Stack;

// Memento caretaker holding undo/redo stacks.
public class OrderCaretaker {
    private final Stack<OrderMemento> undoStack = new Stack<>();
    private final Stack<OrderMemento> redoStack = new Stack<>();

    // Save a new state; drop old redos.
    public void saveState(OrderMemento memento) {
        undoStack.push(memento);
        redoStack.clear();
    }

    // Step back one state if possible.
    public OrderMemento undo() {
        if (undoStack.size() <= 1) {
            return null;
        }
        OrderMemento current = undoStack.pop();
        redoStack.push(current);
        return undoStack.peek();
    }

    // Step forward if we have a redo.
    public OrderMemento redo() {
        if (redoStack.isEmpty()) {
            return null;
        }
        OrderMemento restored = redoStack.pop();
        undoStack.push(restored);
        return restored;
    }
}
