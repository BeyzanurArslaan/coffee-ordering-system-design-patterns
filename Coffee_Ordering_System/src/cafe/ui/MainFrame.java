package cafe.ui;

import cafe.domain.Configuration;
import cafe.domain.menu.MenuBuilder;
import cafe.domain.menu.MenuCategory;
import cafe.domain.menu.MenuComponent;
import cafe.domain.menu.MenuItem;
import cafe.domain.order.Beverage;
import cafe.domain.order.DrinkOrder;
import cafe.domain.order.DrinkOrderBuilder;
import cafe.domain.order.OrderBuilder;
import cafe.domain.order.OrderCaretaker;
import cafe.domain.order.OrderDirector;
import cafe.domain.order.OrderService;
import cafe.domain.payment.PaymentType;
import cafe.domain.pricing.HappyHourStrategy;
import cafe.domain.pricing.LoyaltyStrategy;
import cafe.domain.pricing.PricingStrategy;
import cafe.domain.pricing.StandardPricingStrategy;
import cafe.domain.preparation.BlenderStation;
import cafe.domain.preparation.EspressoMachine;
import cafe.domain.preparation.FrappePreparation;
import cafe.domain.preparation.LattePreparation;
import cafe.domain.preparation.MochaPreparation;
import cafe.domain.preparation.PourOverStation;
import cafe.domain.preparation.PreparationImplementor;
import cafe.domain.validation.CapacityHandler;
import cafe.domain.validation.InventoryHandler;
import cafe.domain.validation.OrderHandler;
import cafe.domain.validation.PaymentHandler;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// UI frame that shows the cafe app and uses many patterns.

public class MainFrame extends JFrame {
    private final MenuCategory menuRoot;
    private final JTree menuTree;
    private final DefaultListModel<String> orderListModel = new DefaultListModel<>();
    private final OrderBuilder builder = new DrinkOrderBuilder();
    private final OrderCaretaker caretaker = new OrderCaretaker();
    private final OrderDirector director;
    private final JComboBox<String> strategyCombo;
    private final JComboBox<PaymentType> paymentCombo;
    private final JLabel totalLabel = new JLabel("Total: 0");
    private final JTextArea summaryArea = new JTextArea(10, 30);
    private final Map<String, PricingStrategy> strategies = new HashMap<>();
    private final OrderService orderService;
    private final JCheckBox milkBox = new JCheckBox("Milk");
    private final JCheckBox shotBox = new JCheckBox("Extra Shot");
    private final JComboBox<String> syrupCombo = new JComboBox<>(new String[] { "None", "Vanilla", "Caramel", "Hazelnut" });

    public MainFrame() {
        super("Queens of Cafe - Patterns");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 600);
        setLayout(new BorderLayout());

        menuRoot = MenuBuilder.sampleMenu();
        menuTree = new JTree(buildTree(menuRoot));
        strategies.put("Standard", new StandardPricingStrategy());
        strategies.put("Happy Hour", new HappyHourStrategy());
        strategies.put("Loyalty", new LoyaltyStrategy());

        strategyCombo = new JComboBox<>(strategies.keySet().toArray(new String[0]));
        paymentCombo = new JComboBox<>(PaymentType.values());
        director = new OrderDirector(builder);

        OrderHandler chain = new InventoryHandler();
        chain.linkWith(new PaymentHandler()).linkWith(new CapacityHandler(8));
        orderService = new OrderService(strategies.get("Standard"), chain);

        caretaker.saveState(builder.createMemento());

        add(buildLeftPanel(), BorderLayout.WEST);
        add(buildCenterPanel(), BorderLayout.CENTER);
        add(buildBottomPanel(), BorderLayout.SOUTH);

        summaryArea.setEditable(false);
        summaryArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
    }

    // Build the menu tree area.
    private JPanel buildLeftPanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setPreferredSize(new Dimension(280, 0));

        JLabel title = new JLabel("Step 1: Pick a drink from the menu");
        title.setFont(title.getFont().deriveFont(Font.BOLD));
        panel.add(title, BorderLayout.NORTH);
        panel.add(new JScrollPane(menuTree), BorderLayout.CENTER);

        JButton allergenButton = new JButton("View allergens");
        allergenButton.addActionListener(e -> showAllergenReport());
        panel.add(allergenButton, BorderLayout.SOUTH);
        return panel;
    }

    // Build the main flow: customize, manage order, see summary.
    private JPanel buildCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));

        panel.add(buildCustomizationPanel(), BorderLayout.NORTH);
        panel.add(buildOrderPanel(), BorderLayout.CENTER);
        panel.add(buildSummaryPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel buildCustomizationPanel() {
        JPanel container = new JPanel(new GridLayout(2, 1, 8, 4));

        JPanel addons = new JPanel();
        addons.add(new JLabel("Step 2: Add-ons"));
        addons.add(milkBox);
        addons.add(shotBox);
        addons.add(new JLabel("Syrup:"));
        addons.add(syrupCombo);

        JPanel actions = new JPanel();
        JButton addButton = new JButton("Add drink");
        addButton.addActionListener(this::addDrink);
        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> undo());
        JButton redoButton = new JButton("Redo");
        redoButton.addActionListener(e -> redo());
        actions.add(new JLabel("Step 3: Build order"));
        actions.add(addButton);
        actions.add(undoButton);
        actions.add(redoButton);

        container.add(addons);
        container.add(actions);
        return container;
    }

    private JPanel buildOrderPanel() {
        JPanel orderPanel = new JPanel(new BorderLayout(4, 4));
        orderPanel.add(new JLabel("Current order items"), BorderLayout.NORTH);
        orderPanel.add(new JScrollPane(new JList<>(orderListModel)), BorderLayout.CENTER);
        return orderPanel;
    }

    private JPanel buildSummaryPanel() {
        JPanel summaryPanel = new JPanel(new BorderLayout(4, 4));
        summaryPanel.add(new JLabel("Step 4: Pricing & summary"), BorderLayout.NORTH);
        summaryPanel.add(new JScrollPane(summaryArea), BorderLayout.CENTER);
        summaryPanel.add(totalLabel, BorderLayout.SOUTH);
        return summaryPanel;
    }

    // Build pricing, payment, and actions bar.
    private JPanel buildBottomPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Pricing:"));
        strategyCombo.addActionListener(e -> updateStrategy());
        panel.add(strategyCombo);

        panel.add(new JLabel("Payment:"));
        panel.add(paymentCombo);

        JButton submitButton = new JButton("Submit Order");
        submitButton.addActionListener(e -> submit());
        panel.add(submitButton);

        JButton prepButton = new JButton("Simulate Prep");
        prepButton.addActionListener(e -> simulatePreparation());
        panel.add(prepButton);

        panel.add(totalLabel);
        return panel;
    }

    // Use Builder + Director to add a drink.
    private void addDrink(ActionEvent event) {
        Object node = menuTree.getLastSelectedPathComponent();
        if (!(node instanceof DefaultMutableTreeNode)) {
            return;
        }
        Object userObject = ((DefaultMutableTreeNode) node).getUserObject();
        if (!(userObject instanceof MenuItem)) {
            return;
        }

        MenuItem item = (MenuItem) userObject;
        String syrup = Objects.toString(syrupCombo.getSelectedItem(), "None");
        String syrupFlavor = "None".equalsIgnoreCase(syrup) ? "" : syrup;
        director.constructBeverage(item.getName(), item.getPrice(), milkBox.isSelected(), shotBox.isSelected(), syrupFlavor);
        caretaker.saveState(builder.createMemento());
        refreshOrderList();
    }

    // Use Memento stack to undo.
    private void undo() {
        var memento = caretaker.undo();
        if (memento != null) {
            builder.restore(memento);
            refreshOrderList();
        }
    }

    // Use Memento stack to redo.
    private void redo() {
        var memento = caretaker.redo();
        if (memento != null) {
            builder.restore(memento);
            refreshOrderList();
        }
    }

    // Swap Strategy for pricing.
    private void updateStrategy() {
        String key = (String) strategyCombo.getSelectedItem();
        PricingStrategy strategy = strategies.getOrDefault(key, new StandardPricingStrategy());
        orderService.setPricingStrategy(strategy);
        refreshOrderList();
    }

    // Repaint the current order list.
    private void refreshOrderList() {
        orderListModel.clear();
        List<Beverage> beverages = builder.getCurrentBeverages();
        for (Beverage beverage : beverages) {
            orderListModel.addElement(beverage.getDescription() + " - $" + String.format("%.2f", beverage.getCost()));
        }
        DrinkOrder order = builder.buildOrder();
        double subtotal = orderService == null ? 0 : strategies.get(((String) strategyCombo.getSelectedItem())).applyPrice(order);
        double totalWithTax = subtotal + subtotal * Configuration.getInstance().getTaxRate();
        totalLabel.setText("Total: $" + String.format("%.2f", totalWithTax));

        String selectedStrategy = (String) strategyCombo.getSelectedItem();
        PaymentType selectedPayment = (PaymentType) paymentCombo.getSelectedItem();
        StringBuilder sb = new StringBuilder();
        sb.append("Items:\n");
        if (beverages.isEmpty()) {
            sb.append(" - none yet\n");
        } else {
            for (Beverage beverage : beverages) {
                sb.append(" - ").append(beverage.getDescription())
                        .append(" ($").append(String.format("%.2f", beverage.getCost())).append(")\n");
            }
        }
        sb.append("\nPricing mode: ").append(selectedStrategy);
        sb.append("\nPayment: ").append(selectedPayment);
        sb.append("\nSubtotal: $").append(String.format("%.2f", subtotal));
        sb.append("\nTax: ").append(String.format("%.0f", Configuration.getInstance().getTaxRate() * 100)).append("%");
        sb.append("\nTotal with tax: $").append(String.format("%.2f", totalWithTax));
        summaryArea.setText(sb.toString());
    }

    // Validate with Chain, pay with Factory + Strategy.
    private void submit() {
        DrinkOrder order = builder.buildOrder();
        PaymentType paymentType = (PaymentType) paymentCombo.getSelectedItem();
        int confirm = JOptionPane.showConfirmDialog(this, summaryArea.getText(), "Confirm order", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (confirm != JOptionPane.OK_OPTION) {
            return;
        }
        boolean ok = orderService.submitOrder(order, paymentType, 2);
        if (ok) {
            JOptionPane.showMessageDialog(this,
                    "Order submitted!\n\n" + summaryArea.getText() + "\n\nCheck console for preparation logs.",
                    "Order submitted",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Order failed validation.");
        }
    }

    // Run Template + Bridge preparation.
    private void simulatePreparation() {
        List<Beverage> beverages = builder.getCurrentBeverages();
        for (Beverage beverage : beverages) {
            String name = beverage.getDescription();
            if (name.toLowerCase().contains("frappe")) {
                runPrep(new FrappePreparation(new BlenderStation()));
            } else if (name.toLowerCase().contains("mocha")) {
                runPrep(new MochaPreparation(new EspressoMachine()));
            } else if (name.toLowerCase().contains("latte")) {
                runPrep(new LattePreparation(new EspressoMachine()));
            } else {
                runPrep(new LattePreparation(new PourOverStation()));
            }
        }
    }

    private void runPrep(cafe.domain.preparation.DrinkPreparationTemplate template) {
        template.prepare();
    }

    // Visitor to build allergen report.
    private void showAllergenReport() {
        cafe.domain.report.AllergenReportVisitor visitor = new cafe.domain.report.AllergenReportVisitor();
        menuRoot.accept(visitor);
        JOptionPane.showMessageDialog(this, visitor.getReport(), "Allergens", JOptionPane.INFORMATION_MESSAGE);
    }

    // Build tree nodes from Composite menu.
    private DefaultMutableTreeNode buildTree(MenuComponent component) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(component);
        if (component instanceof MenuCategory) {
            for (MenuComponent child : component.getChildren()) {
                node.add(buildTree(child));
            }
        }
        return node;
    }
}
