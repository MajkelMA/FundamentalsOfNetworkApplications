const changeVisibility = () => {

    const selectMenu = document.getElementById("createFacilityForm:typeSelect");
    let footballElements = document.getElementsByClassName("football-facility");
    let basketballElements = document.getElementsByClassName("basketball-facility");
    const selected = selectMenu.selectedIndex;
    selectMenu.selectedIndex = selected;
    if(selectMenu.options[selectMenu.selectedIndex].value === "BasketballFacility"){
        Array.from(footballElements).forEach(setDisplayNone);
        Array.from(basketballElements).forEach(setDisplayBlock);

    }
    else {
        Array.from(footballElements).forEach(setDisplayBlock);
        Array.from(basketballElements).forEach(setDisplayNone);
    }
};

const setDisplayBlock = (item) => {
    item.style.display = "inline-flex";
};

const setDisplayNone = (item) => {
    item.style.display = "none";
};

const clearForm = () => document.getElementsByTagName("form").item(0).reset();

document.addEventListener("DOMContentLoaded", changeVisibility);

const fillDataDuringCreateBasketballField = () =>{
    document.getElementById("createFacilityForm:widthofgoal").value = 30;
    document.getElementById("createFacilityForm:heightofgoal").value = 30;
};

const fillDataDuringCreateFootballField = () => {
    document.getElementById("createFacilityForm:numberofbaket").value = 100;
    document.getElementById("createFacilityForm:minheightofbasket").value = 100;
    document.getElementById("createFacilityForm:maxbasketballheight").value = 101;
};

const changeVisibilityInCreateForm = () => {

    const selectMenu = document.getElementById("createFacilityForm:typeSelect");
    let footballElements = document.getElementsByClassName("football-facility");
    let basketballElements = document.getElementsByClassName("basketball-facility");
    const selected = selectMenu.selectedIndex;
    selectMenu.selectedIndex = selected;
    if(selectMenu.options[selectMenu.selectedIndex].value === "BasketballFacility"){
        Array.from(footballElements).forEach(setDisplayNone);
        Array.from(basketballElements).forEach(setDisplayBlock);
        document.getElementById("createFacilityForm:widthofgoal").value = 30;
        document.getElementById("createFacilityForm:heightofgoal").value = 30;
        document.getElementById("createFacilityForm:numberofbaket").value = 0;
        document.getElementById("createFacilityForm:minheightofbasket").value = 0;
        document.getElementById("createFacilityForm:maxbasketballheight").value = 0;
    }
    else {
        Array.from(footballElements).forEach(setDisplayBlock);
        Array.from(basketballElements).forEach(setDisplayNone);
        document.getElementById("createFacilityForm:widthofgoal").value = 0;
        document.getElementById("createFacilityForm:heightofgoal").value = 0;
        document.getElementById("createFacilityForm:numberofbaket").value = 100;
        document.getElementById("createFacilityForm:minheightofbasket").value = 100;
        document.getElementById("createFacilityForm:maxbasketballheight").value = 101;
    }
};


window.onload = () => {
    if (window.history.replaceState) {
        window.history.replaceState(null, null, window.location.href);
    }
};
