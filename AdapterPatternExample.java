interface PaymentProcessor {
    void processPayment(double amount);
}

class PayPalGateway {
    public void sendPayment(double amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}

class StripeGateway {
    public void makeStripePayment(double amount) {
        System.out.println("Paid " + amount + " using Stripe.");
    }
}

class RazorpayGateway {
    public void performTransaction(double amount) {
        System.out.println("Paid " + amount + " using Razorpay.");
    }
}

class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway paypal;

    public PayPalAdapter(PayPalGateway paypal) {
        this.paypal = paypal;
    }

    public void processPayment(double amount) {
        paypal.sendPayment(amount);
    }
}

class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripe;

    public StripeAdapter(StripeGateway stripe) {
        this.stripe = stripe;
    }

    public void processPayment(double amount) {
        stripe.makeStripePayment(amount);
    }
}

class RazorpayAdapter implements PaymentProcessor {
    private RazorpayGateway razorpay;

    public RazorpayAdapter(RazorpayGateway razorpay) {
        this.razorpay = razorpay;
    }

    public void processPayment(double amount) {
        razorpay.performTransaction(amount);
    }
}

public class AdapterPatternExample {
    public static void main(String[] args) {
        PaymentProcessor paypalProcessor = new PayPalAdapter(new PayPalGateway());
        paypalProcessor.processPayment(100.0);

        PaymentProcessor stripeProcessor = new StripeAdapter(new StripeGateway());
        stripeProcessor.processPayment(250.5);

        PaymentProcessor razorpayProcessor = new RazorpayAdapter(new RazorpayGateway());
        razorpayProcessor.processPayment(75.25);
    }
}
