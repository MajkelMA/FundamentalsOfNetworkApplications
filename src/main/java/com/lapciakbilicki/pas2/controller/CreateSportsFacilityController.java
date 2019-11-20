package com.lapciakbilicki.pas2.controller;

import com.lapciakbilicki.pas2.model.sportsfacility.BasketballFacility;
import com.lapciakbilicki.pas2.model.sportsfacility.Field;
import com.lapciakbilicki.pas2.model.sportsfacility.FootballFacility;
import com.lapciakbilicki.pas2.service.SportsFacilityService;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.component.UISelectItem;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlSelectOneListbox;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@ViewScoped
@Named
public class CreateSportsFacilityController implements Serializable {
    private double pricePerHours;
    private double penalty;
    private Field field = new Field();
    private String name;
    private String fullSize;
    private double widthOfGoal;
    private double heightOfGoal;
    private int numberOfBasket;
    private double minHeightOfBasket;
    private double maxHeightOfBasket;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    @Inject
    private SportsFacilityService sportsFacilityService;

    //<editor-fold desc="getter and setters">
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

    public String getType(){
        if(this.requestMap.containsKey("type")){
            return requestMap.get("type");
        }
        return null;
    }

    //</editor-fold>

    public void createFootballFacility(){
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
    }

    public void createBasketballFacility(){
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
    }
}
