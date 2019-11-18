const changeVisibility = () => {
    const selectMenu = document.getElementById("createFacilityForm:typeSelect");
    let fullSize = document.getElementById("selectFullsize");
    let widthOfGoal = document.getElementById("widthOfGoal");
    let heightOfGoal = document.getElementById("heightOfGoal");
    let numberOfBasket = document.getElementById("numberOfBasket");
    let minHeightOfBasket = document.getElementById("minHeightOfBasket");
    let maxHeightOfBasket = document.getElementById("maxHeightOfBasket");
    let submitFootballFacility = document.getElementById("submitFootballFacility");
    let submitBasketballFacility = document.getElementById("submitBasketballFacility");
    if(selectMenu.options[selectMenu.selectedIndex].value === "FootballFacility"){
        fullSize.style.visibility = "visible";
        widthOfGoal.style.visibility = "visible";
        heightOfGoal.style.visibility = "visible";
        numberOfBasket.style.visibility = "hidden";
        minHeightOfBasket.style.visibility = "hidden";
        maxHeightOfBasket.style.visibility = "hidden";
        submitFootballFacility.style.visibility = "visible";
        submitBasketballFacility.style.visibility = "hidden";

    }
    else {
        fullSize.style.visibility = "hidden";
        widthOfGoal.style.visibility = "hidden";
        heightOfGoal.style.visibility = "hidden";
        numberOfBasket.style.visibility = "visible";
        minHeightOfBasket.style.visibility = "visible";
        maxHeightOfBasket.style.visibility = "visible";
        submitFootballFacility.style.visibility = "hidden";
        submitBasketballFacility.style.visibility = "visible";
    }
};
