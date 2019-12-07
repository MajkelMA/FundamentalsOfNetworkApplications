package com.lapciakbilicki.pas2.model.sportsfacility;

public class BasketballFacility extends SportsFacility {
    private int numberOfBasket;
    private double minHeightOfBasket;
    private double maxHeightOfBasket;

    public BasketballFacility() {

    }

    public BasketballFacility(String id, double pricePerHours, boolean access, Field field, String name, int numberOfBasket, double minHeightOfBasket, double maxHeightOfBasket) {
        super(id, pricePerHours, access, field, name);
        this.numberOfBasket = numberOfBasket;
        this.minHeightOfBasket = minHeightOfBasket;
        this.maxHeightOfBasket = maxHeightOfBasket;
    }

    public int getNumberOfBasket() {
        return numberOfBasket;
    }

    public void setNumberOfBasket(int numberOfBasket) {
        this.numberOfBasket = numberOfBasket;
    }

    public double getMinHeightOfBasket() {
        return minHeightOfBasket;
    }

    public void setMinHeightOfBasket(double minHeightOfBasket) {
        this.minHeightOfBasket = minHeightOfBasket;
    }

    public double getMaxHeightOfBasket() {
        return maxHeightOfBasket;
    }

    public void setMaxHeightOfBasket(double maxHeightOfBasket) {
        this.maxHeightOfBasket = maxHeightOfBasket;
    }

    @Override
    public void copyAttributionsWithoutId(SportsFacility sportsFacility) {
        super.copyAttributionsWithoutId(sportsFacility);
        BasketballFacility basketballFacility = (BasketballFacility) sportsFacility;
        this.maxHeightOfBasket = basketballFacility.maxHeightOfBasket;
        this.minHeightOfBasket = basketballFacility.minHeightOfBasket;
        this.numberOfBasket = basketballFacility.numberOfBasket;
    }
}
