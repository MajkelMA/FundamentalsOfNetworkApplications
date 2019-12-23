let handleFilterChange = () => {
    const value = document.getElementById("filter").value;
    if (value.length === 0){
        getFacilities();
    }
    else{
        getFilteredFacilities(value);
    }
};

let getFilteredFacilities = (name) => {
    fetch(`http://localhost:8080/pas2/api/facilities/filter/${name}`)
        .then(response =>{
            if (response.ok)
                return response;
            throw new Error("error during get data");
        })
        .then(response => response.json())
        .then(data =>{
            let str = "";
            for(const facility of data){
                str += "<tr>" +
                    `<td><a href='details.html?id=${facility.id}'>${facility.id}</a></td>` +
                    `<td>${facility.name}</td>` +
                    `<td>${facility.type}</td>` +
                    "</tr>";
            }
            document.getElementById("tableBody").innerHTML = str;
        })
        .catch(onerror => console.log(onerror));
};

let getFacilities = () => {
    fetch("http://localhost:8080/pas2/api/facilities")
        .then(response =>{
            if (response.ok)
                return response;
            throw new Error("error during get data");
        })
        .then(response => response.json())
        .then(data =>{
            let str = "";
            for(const facility of data){
                str += "<tr>" +
                    `<td><a href='details.html?id=${facility.id}'>${facility.id}</a></td>` +
                    `<td>${facility.name}</td>` +
                    `<td>${facility.type}</td>` +
                    `<td><button class='button' onclick='handleDeleteButtonClick("${facility.id}")'>Delete</button></td>` +
                    "</tr>";
            }
            document.getElementById("tableBody").innerHTML = str;
        })
        .catch(onerror => console.log(onerror));
};

let getFacility = (id) => {
    fetch(`http://localhost:8080/pas2/api/facilities/${id}`)
        .then(response =>{
            if(response.ok){
                return response;
            }
            throw new Error("error during get facility");
        })
        .then(response => response.json())
        .then(data => {
            let str = "";
            str += `<tr><td><i>Name: </i></td><td>${data.name}</td></tr>` +
                `<tr><td><i>Price per hours: </i></td><td>${data.pricePerHours}</td></tr>` +
                `<tr><td><i>Access: </i></td><td>${data.access}</td></tr>`;
             if (data.type === "BasketballFacility"){
                 str += `<tr><td><i>Number of Basket: </i></td><td>${data.numberOfBasket}</td></tr>` +
                     `<tr><td><i>Min height of Basket: </i></td><td>${data.minHeightOfBasket}</td></tr>` +
                     `<tr><td><i>max height of basket: </i></td><td>${data.maxHeightOfBasket}</td></tr>`;
             }
             else{
                 str += `<tr><td><i>Full Size: </i></td><td>${data.fullSize}</td></tr>` +
                     `<tr><td><i>Width of Goal: </i></td><td>${data.widthOfGoal}</td></tr>` +
                     `<tr><td><i>Height of Goal: </i></td><td>${data.heightOfGoal}</td></tr>`;
             }

             str += `<tr><td><i>Field Info: </i></td><td></td></tr>` +
                 `<tr><td><i>Surface area: </i></td><td>${data.field.surfaceArea}</td></tr>` +
                 `<tr><td><i>Max amount of people: </i></td><td>${data.field.maxAmountOfPeople}</td></tr>` +
                 `<tr><td><i>Type of Ground: </i></td><td>${data.field.typeOfGround}</td></tr>`;
             document.getElementById("detailBody").innerHTML = str;
        })
        .catch(onerror => console.log(onerror));
};

let handleDeleteButtonClick = (id) => {
    const confirm = window.confirm("Are you sure?");
    if (confirm){
    fetch(`http://localhost:8080/pas2/api/facilities/${id}`,{method: 'DELETE'})
        .then(response => {
            handleFilterChange();
        })
        .catch(onerror => window.alert("Error during delete"));
    }
};