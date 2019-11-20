package com.lapciakbilicki.pas2.controller;

import com.lapciakbilicki.pas2.model.sportsfacility.BasketballFacility;
import com.lapciakbilicki.pas2.model.sportsfacility.Field;
import com.lapciakbilicki.pas2.model.sportsfacility.FootballFacility;
import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;
import com.lapciakbilicki.pas2.service.SportsFacilityService;

import javax.annotation.PostConstruct;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@Named
@ViewScoped
public class ModifySportsFacilityController implements Serializable {

    @Inject
    private SportsFacilityService sportsFacilityService;
    private double pricePerHours, widthOfGoal, heightOfGoal, minHeightOfBasket, maxHeightOfBasket, surfaceArea;
    private int numberOfBasket, maxAmountOfPeople;
    private String id, typeOfGround, type, name, access, fullSize;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    @PostConstruct
    public void init(){
        SportsFacility sportsFacility = sportsFacilityService.get(requestMap.get("id"));
        type = sportsFacility.getClass().getSimpleName();
        id = sportsFacility.getId();
        name = sportsFacility.getName();
        pricePerHours = sportsFacility.getPricePerHours();
        access = String.valueOf(sportsFacility.isAccess());
        typeOfGround = sportsFacility.getField().getTypeOfGround();
        maxAmountOfPeople = sportsFacility.getField().getMaxAmountOfPeople();
        surfaceArea = sportsFacility.getField().getSurfaceArea();
        if(type.equals("BasketballFacility")){
            BasketballFacility basketballFacility = (BasketballFacility) sportsFacility;
            maxHeightOfBasket = basketballFacility.getMaxHeightOfBasket();
            minHeightOfBasket = basketballFacility.getMinHeightOfBasket();
            numberOfBasket = basketballFacility.getNumberOfBasket();
        }
        else{
            FootballFacility footballFacility = (FootballFacility) sportsFacility;
            widthOfGoal = footballFacility.getWidthOfGoal();
            heightOfGoal = footballFacility.getHeightOfGoal();
            fullSize = String.valueOf(footballFacility.isFullSize());
        }
    }

    public void updateBasketBallFacility(){
        sportsFacilityService.update(new BasketballFacility(
                UUID.fromString(id).toString(),
                pricePerHours,
                Boolean.parseBoolean(access),
                new Field(surfaceArea, maxAmountOfPeople, typeOfGround),
                name,
                numberOfBasket,
                minHeightOfBasket,
                maxHeightOfBasket
                ));
    }

    public void updateFootballFacility(){
        sportsFacilityService.update(new FootballFacility(
                UUID.fromString(id).toString(),
                pricePerHours,
                Boolean.parseBoolean(access),
                new Field(surfaceArea, maxAmountOfPeople, typeOfGround),
                name,
                Boolean.parseBoolean(fullSize),
                widthOfGoal,
                heightOfGoal
        ));
    }

    //<editor-fold desc="getters and setters">
    public double getPricePerHours() {
        return pricePerHours;
    }

    public void setPricePerHours(double pricePerHours) {
        this.pricePerHours = pricePerHours;
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

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public int getNumberOfBasket() {
        return numberOfBasket;
    }

    public void setNumberOfBasket(int numberOfBasket) {
        this.numberOfBasket = numberOfBasket;
    }

    public int getMaxAmountOfPeople() {
        return maxAmountOfPeople;
    }

    public void setMaxAmountOfPeople(int maxAmountOfPeople) {
        this.maxAmountOfPeople = maxAmountOfPeople;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getFullSize() {
        return fullSize;
    }

    public void setFullSize(String fullSize){
        this.fullSize = fullSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeOfGround() {
        return typeOfGround;
    }

    public void setTypeOfGround(String typeOfGround) {
        this.typeOfGround = typeOfGround;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //</editor-fold>
}
