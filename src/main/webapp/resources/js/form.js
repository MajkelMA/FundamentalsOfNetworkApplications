const changeVisibility = () => {
    const selectMenu = document.getElementById("createFacilityForm:typeSelect");
    let footballElements = document.getElementsByClassName("football-facility");
    let basketballElements = document.getElementsByClassName("basketball-facility");
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