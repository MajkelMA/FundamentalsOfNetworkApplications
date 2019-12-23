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
                    `<td>${facility.id}</td>` +
                    `<td>${facility.name}</td>` +
                    `<td>${facility.type}</td>` +
                    "</tr>";
            }
            document.getElementById("tableBody").innerHTML = str;
        })
        .catch(onerror => console.log(onerror));
};