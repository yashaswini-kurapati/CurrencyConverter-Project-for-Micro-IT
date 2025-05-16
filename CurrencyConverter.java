import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class CurrencyConverter extends JFrame implements ActionListener {
    JComboBox<String> fromCurrency, toCurrency;
    JTextField amountField, resultField;
    JButton convertBtn, clearBtn;

    HashMap<String, Double> exchangeRates;

    public CurrencyConverter() {
        setTitle("Colorful Currency Converter");
        setSize(700, 500); // Window size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(245, 255, 250)); // light mint green

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        // Title
        JLabel title = new JLabel("💱 Currency Converter");
        title.setFont(new Font("Segoe UI", Font.BOLD, 38));  // Increased title font size
        title.setForeground(new Color(25, 25, 112)); // navy blue
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;

        // From Currency
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel fromLabel = new JLabel("From Currency:");
        fromLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        add(fromLabel, gbc);

        gbc.gridx = 1;
        fromCurrency = new JComboBox<>(new String[]{"USD", "INR", "EUR", "GBP", "JPY"});
        fromCurrency.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        add(fromCurrency, gbc);

        // To Currency
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel toLabel = new JLabel("To Currency:");
        toLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        add(toLabel, gbc);

        gbc.gridx = 1;
        toCurrency = new JComboBox<>(new String[]{"USD", "INR", "EUR", "GBP", "JPY"});
        toCurrency.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        add(toCurrency, gbc);

        // Amount Field
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        add(amountLabel, gbc);

        gbc.gridx = 1;
        amountField = new JTextField(15);
        amountField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        add(amountField, gbc);

        // Result Field
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel resultLabel = new JLabel("Converted Amount:");
        resultLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        add(resultLabel, gbc);

        gbc.gridx = 1;
        resultField = new JTextField(15);
        resultField.setFont(new Font("Segoe UI", Font.BOLD, 22));
        resultField.setEditable(false);
        resultField.setBackground(new Color(224, 255, 255));
        add(resultField, gbc);

        // Convert Button
        gbc.gridx = 0; gbc.gridy = 5;
        convertBtn = new JButton("Convert");
        convertBtn.setBackground(new Color(60, 179, 113));
        convertBtn.setForeground(Color.WHITE);
        convertBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        convertBtn.addActionListener(this);
        add(convertBtn, gbc);

        // Clear Button
        gbc.gridx = 1;
        clearBtn = new JButton("Clear");
        clearBtn.setBackground(new Color(255, 99, 71));
        clearBtn.setForeground(Color.WHITE);
        clearBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        clearBtn.addActionListener(e -> {
            amountField.setText("");
            resultField.setText("");
        });
        add(clearBtn, gbc);

        // Sample exchange rates
        exchangeRates = new HashMap<>();
        exchangeRates.put("USDINR", 83.20);
        exchangeRates.put("USDEUR", 0.93);
        exchangeRates.put("USDGBP", 0.79);
        exchangeRates.put("USDJPY", 156.74);
        exchangeRates.put("INRUSD", 0.012);
        exchangeRates.put("INREUR", 0.011);
        exchangeRates.put("INRGBP", 0.0095);
        exchangeRates.put("INRJPY", 1.88);
        exchangeRates.put("EURUSD", 1.08);
        exchangeRates.put("EURINR", 89.42);
        exchangeRates.put("EURGBP", 0.85);
        exchangeRates.put("EURJPY", 168.22);
        exchangeRates.put("GBPUSD", 1.27);
        exchangeRates.put("GBPINR", 104.60);
        exchangeRates.put("GBPEUR", 1.17);
        exchangeRates.put("GBPJPY", 197.10);
        exchangeRates.put("JPYUSD", 0.0064);
        exchangeRates.put("JPYINR", 0.53);
        exchangeRates.put("JPYEUR", 0.0059);
        exchangeRates.put("JPYGBP", 0.0051);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String from = fromCurrency.getSelectedItem().toString();
        String to = toCurrency.getSelectedItem().toString();
        String key = from + to;

        if (from.equals(to)) {
            JOptionPane.showMessageDialog(this, "Please select different currencies.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountField.getText());
            Double rate = exchangeRates.get(key);

            if (rate != null) {
                double result = amount * rate;
                resultField.setText(String.format("%.2f", result));
            } else {
                resultField.setText("Rate not found");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount entered.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrencyConverter().setVisible(true));
    }
}
