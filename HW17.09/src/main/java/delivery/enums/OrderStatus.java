package delivery.enums;

public enum OrderStatus {
    DRAFT{  // once created
        @Override
        public boolean canTransitionTo(OrderStatus newStatus) {
            return newStatus == IN_PROGRESS || newStatus == CANCELLED;
        }
    },
    READY{// once paid
        @Override
        public boolean canTransitionTo(OrderStatus newStatus) {
            return newStatus == IN_PROGRESS || newStatus == CANCELLED || newStatus == SCHEDULED;
        }
    },
    IN_PROGRESS {  // once accepted by carrier
        @Override
        public boolean canTransitionTo(OrderStatus newStatus) {
            return newStatus == COMPLETED  || newStatus == CANCELLED || newStatus == DELAYED;
        }
    },
    COMPLETED{// once completed
        @Override
        public boolean canTransitionTo(OrderStatus newStatus) {
            return false; // we cant do more after this?
        }
    },
    DELAYED{// once delayed
        @Override
        public boolean canTransitionTo(OrderStatus newStatus) {
            return newStatus == COMPLETED  || newStatus == CANCELLED;
        }
    },
    SCHEDULED {
        @Override
        public boolean canTransitionTo(OrderStatus newStatus) {
            return newStatus == IN_PROGRESS  || newStatus == CANCELLED;
        }
    }, // USED IN SUBSCRIPTIONORDER
    CANCELLED{
        @Override
        public boolean canTransitionTo(OrderStatus newStatus) {
            return false; // we cant do more after this?
        }
    };

    public abstract boolean canTransitionTo(OrderStatus newStatus);

    }
