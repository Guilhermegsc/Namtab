function validaDatas() {
    dataI = document.getElementById("dataI").value;
    dataF = document.getElementById("dataF").value;
    if (dataI == "" || dataF == "") {
        alert("Por favor, preencha as datas corretamente.");
        return false;
    } else {
        dataI = new Date(dataI).getTime();
        dataF = new Date(dataF).getTime();
        if (dataI > dataF) {
            alert("Datas inválidas. Tente novamente.");
            return false;
        } else {
            return true;
        }
    }

}



