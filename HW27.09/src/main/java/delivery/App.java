package delivery;

import delivery.Models.Service.DefaultDataService;
import delivery.Models.Service.DeliveryService;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



public class App {
        private static final Logger logger = LogManager.getLogger(App.class);

        public static void main(String[] args) throws Exception {
                logger.info("Delivery Service application started");
                try {
                        DeliveryService.startProgram(DefaultDataService.generateDefaultCompany());
                } catch (Exception e) {
                        logger.error("Error occurred " + e.getMessage());
                }

        }

}
