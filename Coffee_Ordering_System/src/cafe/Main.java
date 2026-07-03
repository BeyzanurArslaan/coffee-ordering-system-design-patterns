package cafe;

import cafe.ui.MainFrame;

import javax.swing.SwingUtilities;

public class Main {
    // Starts the Swing app and shows the main window.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
