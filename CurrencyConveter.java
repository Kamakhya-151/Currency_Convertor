import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CurrencyConveter extends JFrame {
    private JLabel amountLab;
    private JTextField amountTextField;
    private JLabel fromLab;
    private JComboBox<String> fromComboBox;
    private JLabel toLab;
    private JComboBox<String> toComboBox;
    private JButton convertButton;
    private JLabel resLab;

    private static final String[] currencies = {"USD", "EUR", "GBP", "JPY"};

    public CurrencyConveter() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        amountLab = new JLabel("Amount (USD):");
        amountTextField = new JTextField(50);
        fromLab = new JLabel("From Currency:");
        fromComboBox = new JComboBox<>(currencies);
        toLab = new JLabel("To Currency:");
        toComboBox = new JComboBox<>(currencies);
        convertButton = new JButton("Convert");
        resLab = new JLabel();

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountTextField.getText());
                String fromCurrency = (String) fromComboBox.getSelectedItem();
                String toCurrency = (String) toComboBox.getSelectedItem();

                double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency);

                resLab.setText(String.format("%.2f %s = %.2f %s", amount, fromCurrency, convertedAmount, toCurrency));
            }
        });

        add(amountLab);
        add(amountTextField);
        add(fromLab);
        add(fromComboBox);
        add(toLab);
        add(toComboBox);
        add(convertButton);
        add(resLab);

        pack();
        setLocationRelativeTo(null);
    }

    private double convertCurrency(double amount, String fromCurr, String toCurr) {
        double usdToEur = 0.85;
        double usdToGbp = 0.72;
        double usdToJpy = 109.67;

        double rateFrom;
        double rateTo;

        switch (fromCurr) {
            case "USD":
                rateFrom = 1.0;
                break;
            case "EUR":
                rateFrom = usdToEur;
                break;
            case "GBP":
                rateFrom = usdToGbp;
                break;
            case "JPY":
                rateFrom = usdToJpy;
                break;
            default:
                rateFrom = 1.0;
        }

        switch (toCurr) {
            case "USD":
                rateTo = 1.0;
                break;
            case "EUR":
                rateTo = usdToEur;
                break;
            case "GBP":
                rateTo = usdToGbp;
                break;
            case "JPY":
                rateTo = usdToJpy;
                break;
            default:
                rateTo = 1.0;
        }

        return (amount / rateFrom) * rateTo;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CurrencyConveter().setVisible(true);
            }
        });
    }
}
