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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ViewScoped
@Named
public class SportsFacilityController implements Serializable {

    private double pricePerHours, penalty, widthOfGoal, heightOfGoal, minHeightOfBasket, maxHeightOfBasket, surfaceArea;
    private Field field = new Field();
    private int numberOfBasket, maxAmountOfPeople;
    private String fullSize, name, id, typeOfGround, facilityType, access;
    private SportsFacility sportsFacility;

    @Inject
    private SportsFacilityService sportsFacilityService;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    private List<SportsFacility> sportsFacilities;

    @PostConstruct
    public void init() {
        sportsFacilities = new ArrayList<>(sportsFacilityService.getAll());
        if (requestMap.containsKey("id")) {

            sportsFacility = sportsFacilityService.get(requestMap.get("id"));
            facilityType = sportsFacility.getClass().getSimpleName();
            id = sportsFacility.getId();
            name = sportsFacility.getName();
            pricePerHours = sportsFacility.getPricePerHours();
            access = String.valueOf(sportsFacility.isAccess());
            typeOfGround = sportsFacility.getField().getTypeOfGround();
            maxAmountOfPeople = sportsFacility.getField().getMaxAmountOfPeople();
            surfaceArea = sportsFacility.getField().getSurfaceArea();
            if (facilityType.equals("BasketballFacility")) {
                BasketballFacility basketballFacility = (BasketballFacility) sportsFacility;
                maxHeightOfBasket = basketballFacility.getMaxHeightOfBasket();
                minHeightOfBasket = basketballFacility.getMinHeightOfBasket();
                numberOfBasket = basketballFacility.getNumberOfBasket();
            } else {
                FootballFacility footballFacility = (FootballFacility) sportsFacility;
                widthOfGoal = footballFacility.getWidthOfGoal();
                heightOfGoal = footballFacility.getHeightOfGoal();
                fullSize = String.valueOf(footballFacility.isFullSize());
            }
        }
    }

    public List<SportsFacility> getAll() {
        return sportsFacilities;
    }

    public SportsFacility getActiveSportsFacility() {
        return this.sportsFacilityService.get(requestMap.get("id"));
    }

    public void deleteSportsFacility(String id) {
        sportsFacilityService.deleteSportsFacility(id);
        init();
    }

    public void updateBasketBallFacility() {
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
        init();
    }

    public void updateFootballFacility() {
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
        init();
    }

    public void changeAccess(String id) {
        sportsFacilityService.changeSportsFacilityAccess(id);
    }

    public void createFootballFacility() {
        boolean result = sportsFacilityService.add(new FootballFacility(
                UUID.randomUUID().toString(),
                pricePerHours,
                true,
                field,
                name,
                Boolean.parseBoolean(fullSize),
                widthOfGoal,
                heightOfGoal
        ));
        init();
    }

    public void createBasketballFacility() {
        boolean result = sportsFacilityService.add(new BasketballFacility(
                UUID.randomUUID().toString(),
                pricePerHours,
                true,
                field,
                name,
                numberOfBasket,
                minHeightOfBasket,
                maxHeightOfBasket
        ));
        init();
    }

    //<editor-fold desc="getter and setters">
    public double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public int getMaxAmountOfPeople() {
        return maxAmountOfPeople;
    }

    public void setMaxAmountOfPeople(int maxAmountOfPeople) {
        this.maxAmountOfPeople = maxAmountOfPeople;
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

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public double getPricePerHours() {
        return pricePerHours;
    }

    public void setPricePerHours(double pricePerHours) {
        this.pricePerHours = pricePerHours;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullSize() {
        return fullSize;
    }

    public void setFullSize(String fullSize) {
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

    public int getNumberOfBasket() {
        return numberOfBasket;
    }

    public void setNumberOfBasket(int numberOfBasket) {
        this.numberOfBasket = numberOfBasket;
    }

    public String getFacilityType() {
        return facilityType;
    }
    //</editor-fold>
}
