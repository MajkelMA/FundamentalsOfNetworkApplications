package com.lapciakbilicki.pas2.model.sportsfacility;

public class FootballFacility extends SportsFacility {
    private boolean fullSize;
    private double widthOfGoal;
    private double heightOfGoal;


    public FootballFacility(){

    }

    public FootballFacility(String id, double pricePerHours, boolean access,  Field field, String name,boolean fullSize, double widthOfGoal, double heightOfGoal) {
        super(id, pricePerHours, access, field, name);
        this.fullSize = fullSize;
        this.widthOfGoal = widthOfGoal;
        this.heightOfGoal = heightOfGoal;
    }

    public boolean isFullSize() {
        return fullSize;
    }

    public void setFullSize(boolean fullSize) {
        this.fullSize = fullSize;
    }

    public double getWidthOfGoal() {
        return widthOfGoal;
    }

    public void setWidthOfGoal(double widthOfGoal) {
        this.widthOfGoal = widthOfGoal;
    }

    public double getHeightOfGoal() {
        return heightOfGoal;
    }

    public void setHeightOfGoal(double heightOfGoal) {
        this.heightOfGoal = heightOfGoal;
    }

    @Override
    public void copyAttributionsWithoutId(SportsFacility sportsFacility){
        super.copyAttributionsWithoutId(sportsFacility);
        FootballFacility footballFacility = (FootballFacility) sportsFacility;
        this.fullSize = footballFacility.fullSize;
        this.heightOfGoal = footballFacility.heightOfGoal;
        this.widthOfGoal = footballFacility.widthOfGoal;
    }
}
