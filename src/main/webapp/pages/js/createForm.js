let handleChangeSelectedValue = () => {
    const typeForm = document.getElementById("type");
    const selectedValue = typeForm.options[typeForm.selectedIndex].value;

    if (selectedValue === "1") {
        changeDisplay("basket-1", true);
        changeDisplay("basket-2", true);
        changeDisplay("basket-3", true);
        changeDisplay("football-1", false);
        changeDisplay("football-2", false);
        changeDisplay("football-3", false);
    }

    if (selectedValue === "2") {
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
    if (visibility) {
        element.style.display = "block";
    } else {
        element.style.display = "none";
    }
};

let readSelectedRadioButtonValue = (name) => {
    let result;
    const radios = document.getElementsByName(name);
    radios.forEach(radio => {
        if (radio.checked) {
            result = radio.value;
        }
    });
    return result;
};


class Validator {
    state = {
        validate: false,
        form: null,
        typeSelected: "BasketballFacility"
    };

    constructor(form) {
        this.state.form = form;
        this.prepareElements();
    }

    prepareElements = () => {
        document.getElementById("name").addEventListener("input", e => this.basicTextCheck(e.target, true, "name-validation"));
        document.getElementById("maxAmountOfPeople").addEventListener("input", e => this.numberValidation(e.target, true, "maxAmountOfPeople-validation"));
        document.getElementById("surface").addEventListener("input", e => this.floatNumberValidation(e.target, true, "surface-validation"));
        document.getElementById("typeOfGround").addEventListener("input", ev => this.notNullValidation(ev.target, true, "typeOfGround-validation"));
        document.getElementById("price").addEventListener("input", ev => this.floatNumberValidation(ev.target, true, "price-validation"));
        document.getElementById("numberOfBasket").addEventListener("input", ev => this.numberValidation(ev.target, true, "numberOfBasket-validation"));
        document.getElementById("minheight").addEventListener("input", ev => this.floatNumberValidation(ev.target, true, "minheight-validation"));
        document.getElementById("widthOfGoal").addEventListener("input", ev => this.floatNumberValidation(ev.target, true, "widthOfGoal-validation"));
        document.getElementById("maxheight").addEventListener("input", ev => this.floatNumberValidation(ev.target, true, "maxheight-validation"));
        document.getElementById("heightOfGoal").addEventListener("input", ev => this.floatNumberValidation(ev.target, true, "heightOfGoal-validation"));
        document.getElementById("send").addEventListener("click", ev => this.handleCreateClick());
    };

    numberValidation = (item, show, validationDivId) => {
        const value = item.value;
        let validateFlag = true;
        validateFlag = this.notNullValidation(item, false, null);

        for (let i = 0; i < value.length; i++) {
            if (value[i] < '0' || value[i] > '9') {
                validateFlag = false;
            }
        }

        if (show)
            this.showValidation(validationDivId, validateFlag, "Input must be number and not null");

        return validateFlag;
    };


    floatNumberValidation = (item, show, validationDivId) => {
        let value = item.value;
        let isValidate = true;
        isValidate = this.notNullValidation(item, false, null);

        if (value[0] === '.') {
            item.value = "0" + value;
            value = item.value
        }
        for (let i = 0; i < value.length; i++) {
            if ((value[i] < '0' || value[i] > '9') && value[i] !== '.') {
                isValidate = false;
            }
        }

        if (show)
            this.showValidation(validationDivId, isValidate, "Input must be number and not null");

        return isValidate;

    };

    notNullValidation = (item, show, validationDivId) => {
        const value = item.value;
        let isValidate = true;
        if (value === "") {
            isValidate = false;
        }

        if (show)
            this.showValidation(validationDivId, isValidate, "Must be not null");

        return isValidate;
    };

    basicTextCheck = (item, show, validationDivId) => {
        let isValidate = true;
        const value = item.value;
        if (value.length < 3) {
            isValidate = false;
        }

        if (show)
            this.showValidation(validationDivId, isValidate, "Min Length: 3");

        return isValidate;
    };

    showValidation = (id, mode, message) => {
        const element = document.getElementById(id);
        if (mode) {
            element.innerHTML = "";
        } else {
            element.innerHTML = message;
        }
    };

    handleCreateClick = () => {
        const confirm = window.confirm("Are you sure?");

        if (confirm) {
            let validateFlag;

            const typeForm = document.getElementById("type");
            const selectedValue = typeForm.options[typeForm.selectedIndex].value;

            validateFlag = this.basicTextCheck(document.getElementById("name"), false, "");
            const name = this.getValueFromInput("name");
            console.log(document.getElementById("name").value);
            console.log(validateFlag);

            validateFlag = this.numberValidation(document.getElementById("maxAmountOfPeople"), false, "");
            const maxAmountOfPeople = parseInt(this.getValueFromInput("maxAmountOfPeople"));
            console.log(document.getElementById("maxAmountOfPeople").value);
            console.log(validateFlag);

            validateFlag = this.floatNumberValidation(document.getElementById("surface"), false, "");
            const surfaceArea = parseFloat(this.getValueFromInput("surface"));
            console.log(document.getElementById("surface").value);
            console.log(validateFlag);

            validateFlag = this.basicTextCheck(document.getElementById("typeOfGround"), false, "");
            const typeOfGround = this.getValueFromInput("typeOfGround");
            console.log(document.getElementById("typeOfGround").value);
            console.log(validateFlag);

            validateFlag = this.floatNumberValidation(document.getElementById("price"), false, "");
            const price = parseFloat(this.getValueFromInput("price"));
            console.log(document.getElementById("price").value);
            console.log(validateFlag);

            if (selectedValue === "1") {
                validateFlag = this.numberValidation(document.getElementById("numberOfBasket"), false, "");
                const numberOfBasket = parseInt(this.getValueFromInput("numberOfBasket"));
                console.log(document.getElementById("numberOfBasket").value);
                console.log(validateFlag);

                validateFlag = this.floatNumberValidation(document.getElementById("minheight"), false, "");
                const minHeightOfBasket = parseFloat(this.getValueFromInput("minheight"));
                console.log(document.getElementById("minheight").value);
                console.log(validateFlag);

                validateFlag = this.floatNumberValidation(document.getElementById("maxheight"), false, "");
                const maxHeightOfBasket = parseFloat(this.getValueFromInput("maxheight"));
                console.log(document.getElementById("maxheight").value);
                console.log(validateFlag);

                if (validateFlag)
                    this.createBasketballFacility(name, price, true, surfaceArea, maxAmountOfPeople, typeOfGround, numberOfBasket, minHeightOfBasket, maxHeightOfBasket);
                else window.alert("wrong data!");
            }
            if (selectedValue === "2") {
                const radioResult = readSelectedRadioButtonValue("fullsize");
                const fullSize = radioResult === "1";

                validateFlag = this.floatNumberValidation(document.getElementById("widthOfGoal"), false, "");
                const widthOfGoal = parseFloat(this.getValueFromInput("widthOfGoal"));
                console.log(document.getElementById("widthOfGoal").value);
                console.log(validateFlag);

                validateFlag = this.floatNumberValidation(document.getElementById("heightOfGoal"), false, "");
                const heightOfGoal = parseFloat(this.getValueFromInput("heightOfGoal"));
                console.log(document.getElementById("heightOfGoal").value);
                console.log(validateFlag);

                if (validateFlag)
                    this.createFootballFacility(name, price, true, surfaceArea, maxAmountOfPeople, typeOfGround, fullSize, widthOfGoal, heightOfGoal);
                else window.alert("wrong data!");
            }
        }
    };

    createBasketballFacility = (name, price, access, surfaceArea, maxAmountOfPeople, typeOfGround, numberOFBasket, minHeightOfBasket, maxHeightOfBasket) => {
        let id = null;
        if (document.getElementById("id") !== null) {
            id = document.getElementById("id").value;
        }

        if (id === null) {
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

            console.log(data);
            fetch("https://localhost:8181/pas2/api/facilities/basketball", {
                method: 'POST',
                body: JSON.stringify(data),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).catch(onerror => console.log(onerror));
        } else {
            const data = {
                id: id,
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

            console.log(data);
            fetch("https://localhost:8181/pas2/api/facilities/basketball/update", {
                method: 'PUT',
                body: JSON.stringify(data),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).catch(onerror => console.log(onerror));
        }
    };


    createFootballFacility = (name, price, access, surfaceArea, maxAmountOfPeople, typeOfGround, fullSize, widthOFGoal, heightOfGoal) => {
        const id = document.getElementById("id").value;

        if (id === null) {
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
            console.log(data);
            fetch("https://localhost:8181/pas2/api/facilities/football", {
                method: 'POST',
                body: JSON.stringify(data),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).catch(onerror => console.log(onerror));
        } else {
            const data = {
                id: id,
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
            console.log(data);
            fetch("https://localhost:8181/pas2/api/facilities/football/update", {
                method: 'PUT',
                body: JSON.stringify(data),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).catch(onerror => console.log(onerror));
        }
    };

    getValueFromInput = (id) => {
        return document.getElementById(id).value
    };

}