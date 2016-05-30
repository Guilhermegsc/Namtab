function validaDatas() {
    dataI = document.getElementById("dataI").value;
    dataF = document.getElementById("dataF").value;
    hoje = dataHoje();
    if (dataI == "" || dataF == "") {
        alert("Por favor, preencha as datas corretamente.");
        return false;
    } else {
        dataI = new Date(dataI).getTime();
        dataF = new Date(dataF).getTime();
        if (hoje < dataI ) {
            alert("Não existe relatório para a data informada.");
            return false;
        } else if(dataI > dataF){
            alert("Datas inválidas. Tente novamente.");
            return false;
        }else{
            return true;
        }
        
    }
}
function dataHoje() {
    hoje = new Date();
    data = hoje.getDate();
    mes = hoje.getMonth();
    ano = hoje.getFullYear();

    if (data < 10) {
        data = "0" + data;
    }
    nMes = new Array(12);
    nMes[0] = 1;
    nMes[1] = 2;
    nMes[2] = 3;
    nMes[3] = 4;
    nMes[4] = 5;
    nMes[5] = 6;
    nMes[6] = 7;
    nMes[7] = 8;
    nMes[8] = 9;
    nMes[9] = 10;
    nMes[10] = 11;
    nMes[11] = 12;

    x = new Date(ano + "/" + nMes[mes] + "/" + data).getTime();
    return x;
}