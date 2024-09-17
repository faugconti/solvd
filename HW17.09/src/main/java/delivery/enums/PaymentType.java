package delivery.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum PaymentType {
    CREDIT_CARD{
        @Override
        public boolean processPayment(double amount) {
            logger.info("Processing credit card payment of $" + amount);
            return Math.random() < 0.90; // 95% success rate
        }
    },
    PAYPAL{
        @Override
        public boolean processPayment(double amount) {
            logger.info("Processing PayPal payment of $" + amount);
            return Math.random() < 0.98; // 98% success rate
        }
    },
    CASH{
        @Override
        public boolean processPayment(double amount) {
            logger.info("Preparing cash on delivery for $" + amount);
            return true; // Always successful
        }
    },
    CRYPTO{
        @Override
        public boolean processPayment(double amount) {
            logger.info("Preparing crypto payment for $" + amount);
            return Math.random() < 0.98;
        }
    };


    public abstract boolean processPayment(double amount);
    private static final Logger logger = LogManager.getLogger(PaymentType.class);

}
