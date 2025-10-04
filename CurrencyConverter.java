import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {
    private static Map<String, Double> exchangeRates = new HashMap<>();
    private static Map<String, String> currencySymbols = new HashMap<>();

    public static void main(String[] args) {
        initializeData();
        Scanner scanner = new Scanner(System.in);

        // Currency Selection
        System.out.println("Available currencies: USD, EUR, GBP, JPY, INR");
        System.out.print("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();
        System.out.print("Enter target currency (e.g., EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        // Validate currencies
        if (!exchangeRates.containsKey(baseCurrency) || !exchangeRates.containsKey(targetCurrency)) {
            System.out.println("Invalid currency selected. Please choose from USD, EUR, GBP, JPY, INR.");
            scanner.close();
            return;
        }

        // Amount Input
        System.out.print("Enter amount to convert: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
            if (amount < 0) {
                System.out.println("Amount cannot be negative.");
                scanner.close();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered.");
            scanner.close();
            return;
        }

        // Currency Conversion
        double convertedAmount = convertCurrency(amount, baseCurrency, targetCurrency);

        // Display Result
        String symbol = currencySymbols.getOrDefault(targetCurrency, "");
        System.out.printf("Converted Amount: %.2f %s %s%n", convertedAmount, symbol, targetCurrency);

        scanner.close();
    }

    // Initialize mock exchange rates and currency symbols
    private static void initializeData() {
        // Mock exchange rates relative to USD (as of a hypothetical date)
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("GBP", 0.73);
        exchangeRates.put("JPY", 110.0);
        exchangeRates.put("INR", 83.0);

        // Currency symbols
        currencySymbols.put("USD", "$");
        currencySymbols.put("EUR", "€");
        currencySymbols.put("GBP", "£");
        currencySymbols.put("JPY", "¥");
        currencySymbols.put("INR", "₹");
    }

    // Convert currency using exchange rates
    private static double convertCurrency(double amount, String baseCurrency, String targetCurrency) {
        // In a real application, replace this with an API call to fetch real-time rates
        double baseToUSD = exchangeRates.get(baseCurrency);
        double targetToUSD = exchangeRates.get(targetCurrency);
        // Convert amount to USD first, then to target currency
        return amount / baseToUSD * targetToUSD;
    }
}