let handleCreateClick = () => {
    const confirm = window.confirm("Are you sure?");

    if (confirm){
        const typeForm = document.getElementById("type");
        const selectedValue = typeForm.options[typeForm.selectedIndex].value;

        const name = getValueFromInput("name");
        const maxAmountOfPeople = parseInt(getValueFromInput("maxAmountOfPeople"));
        const surfaceArea = parseFloat(getValueFromInput("surface"));
        const typeOfGround = getValueFromInput("typeOfGround");
        const price = parseFloat(getValueFromInput("price"));


        if (selectedValue === "1"){
            const numberOfBasket = parseInt(getValueFromInput("numberOfBasket"));
            const minHeightOfBasket = parseFloat(getValueFromInput("minheight"));
            const maxHeightOfBasket = parseFloat(getValueFromInput("maxheight"));
            createBasketballFacility(name, price, true, surfaceArea, maxAmountOfPeople, typeOfGround, numberOfBasket, minHeightOfBasket, maxHeightOfBasket);
        }
        if (selectedValue === "2"){
            const radioResult = readSelectedRadioButtonValue("fullsize");
            const fullSize = radioResult === "1";
            const widthOfGoal = parseFloat(getValueFromInput("widthOfGoal"));
            const heightOfGoal = parseFloat(getValueFromInput("heightOfGoal"));
            createFootballFacility(name, price, true, surfaceArea, maxAmountOfPeople, typeOfGround, fullSize, widthOfGoal, heightOfGoal);
        }
    }
};

let createBasketballFacility = (name, price, access, surfaceArea, maxAmountOfPeople, typeOfGround, numberOFBasket,minHeightOfBasket, maxHeightOfBasket) => {
    const data = {
        id: "",
        pricePerHours: price,
        access: access,
        field: {
            surfaceArea: surfaceArea,
            maxAmountOfPeople: maxAmountOfPeople,
            typeOfGround: typeOfGround
        },
        name: name,
        type: "BasketballFacility",
        numberOfBasket: numberOFBasket,
        minHeightOfBasket: minHeightOfBasket,
        maxHeightOfBasket: maxHeightOfBasket
    };

    fetch("http://localhost:8080/pas2/api/facilities/basketball", {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        }
    }).catch(onerror => console.log(onerror));
};


let createFootballFacility = (name, price, access, surfaceArea, maxAmountOfPeople, typeOfGround, fullSize, widthOFGoal, heightOfGoal) =>{
    const data = {
        id: "",
        pricePerHours: price,
        access: access,
        field: {
            surfaceArea: surfaceArea,
            maxAmountOfPeople: maxAmountOfPeople,
            typeOfGround: typeOfGround
        },
        name: name,
        type: "FootballFacility",
        fullsize: fullSize,
        widthOfGoal: widthOFGoal,
        heightOfGoal: heightOfGoal
    };
    fetch("http://localhost:8080/pas2/api/facilities/football", {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        }
    }).catch(onerror => console.log(onerror));
};

let getValueFromInput = (id) => {
    return document.getElementById(id).value
};

let handleChangeSelectedValue = () => {
    const typeForm = document.getElementById("type");
    const selectedValue = typeForm.options[typeForm.selectedIndex].value;

    if (selectedValue === "1"){
        changeDisplay("basket-1", true);
        changeDisplay("basket-2", true);
        changeDisplay("basket-3", true);
        changeDisplay("football-1", false);
        changeDisplay("football-2", false);
        changeDisplay("football-3", false);
    }

    if (selectedValue === "2"){
        changeDisplay("basket-1", false);
        changeDisplay("basket-2", false);
        changeDisplay("basket-3", false);
        changeDisplay("football-1", true);
        changeDisplay("football-2", true);
        changeDisplay("football-3", true);
    }
};

let changeDisplay = (id, visibility) => {
    const element = document.getElementById(id);
    if(visibility){
        element.style.display = "block";
    }
    else{
        element.style.display = "none";
    }
};

let readSelectedRadioButtonValue = (name) => {
    let result;
    const radios = document.getElementsByName(name);
    radios.forEach(radio => {
        if (radio.checked){
            result = radio.value;
        }
    });
    return result;
};
