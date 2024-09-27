package delivery.enums;


public enum OrderType {
    ORDINARY(1,1),
    EXPRESS(5,3),
    INTERNATIONAL(3,35){
        @Override
        public boolean requiresCustomsClearance() {
            return true;
        }
    },
    SUBSCRIPTION(2,3);

     private final int priority;
     protected final float baseCost;

    OrderType(int priority, float baseCost) {
        this.priority = priority;
        this.baseCost = baseCost;
    }

    public float getBaseCost() {
        return baseCost;
    }

    public int getPriority() {
        return priority;
    }

    public boolean requiresCustomsClearance(){
        return false;
    }
}
