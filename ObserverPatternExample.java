import java.util.ArrayList;
import java.util.List;

interface Stock {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

interface Observer {
    void update(String stockName, double price);
}

class StockMarket implements Stock {
    private List<Observer> observers = new ArrayList<>();
    private String stockName;
    private double stockPrice;

    public void setStockPrice(String stockName, double price) {
        this.stockName = stockName;
        this.stockPrice = price;
        notifyObservers();
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(stockName, stockPrice);
        }
    }
}

class MobileApp implements Observer {
    public void update(String stockName, double price) {
        System.out.println("MobileApp: " + stockName + " price updated to " + price);
    }
}

class WebApp implements Observer {
    public void update(String stockName, double price) {
        System.out.println("WebApp: " + stockName + " price updated to " + price);
    }
}

public class ObserverPatternExample {
    public static void main(String[] args) {
        StockMarket market = new StockMarket();

        Observer mobileApp = new MobileApp();
        Observer webApp = new WebApp();

        market.registerObserver(mobileApp);
        market.registerObserver(webApp);

        market.setStockPrice("AAPL", 150.75);
        market.setStockPrice("GOOG", 2720.50);

        market.removeObserver(mobileApp);
        market.setStockPrice("MSFT", 310.00);
    }
}
