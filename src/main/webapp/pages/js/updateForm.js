let initUpdate = (id) => {
    fetch(`https://localhost:8181/pas2/api/facilities/${id}`)
        .then(response => {
            if (response.ok) {
                return response;
            }
            throw new Error("error during get facility");
        })
        .then(response => response.json())
        .then(data => {
            document.getElementById("id").value = data.id;
            document.getElementById("name").value = data.name;
            document.getElementById("maxAmountOfPeople").value = data.field.maxAmountOfPeople;
            document.getElementById("surface").value = data.field.surfaceArea;
            document.getElementById("typeOfGround").value = data.field.typeOfGround;
            document.getElementById("price").value = data.pricePerHours;
            if (data.type === "BasketballFacility") {
                document.getElementById("type").value = 1;

                changeDisplay("basket-1", true);
                changeDisplay("basket-2", true);
                changeDisplay("basket-3", true);
                changeDisplay("football-1", false);
                changeDisplay("football-2", false);
                changeDisplay("football-3", false);

                document.getElementById("numberOfBasket").value = data.numberOfBasket;
                document.getElementById("minheight").value = data.minHeightOfBasket;
                document.getElementById("maxheight").value = data.maxHeightOfBasket;
            } else {
                document.getElementById("type").value = 2;

                changeDisplay("basket-1", false);
                changeDisplay("basket-2", false);
                changeDisplay("basket-3", false);
                changeDisplay("football-1", true);
                changeDisplay("football-2", true);
                changeDisplay("football-3", true);

                if (data.fullSize === true) {
                    document.getElementById("fullsize-0").checked = true;
                    document.getElementById("fullsize-1").checked = false;
                } else {
                    document.getElementById("fullsize-0").checked = false;
                    document.getElementById("fullsize-1").checked = true;
                }
                document.getElementById("widthOfGoal").value = data.widthOfGoal;
                document.getElementById("heightOfGoal").value = data.heightOfGoal;
            }
        })
        .catch(onerror => console.log(onerror));
};