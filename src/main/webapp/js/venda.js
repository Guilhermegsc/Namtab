
function habilitaCampo(x) {
    if (x == "prod") {
        document.getElementById("quantidade").disabled = false;
        document.getElementById("valor").disabled = true;
        document.getElementById("quantidade").focus();
    } else if (x == "comb") {
        document.getElementById("valor").disabled = false;
        document.getElementById("quantidade").disabled = true;
        document.getElementById("valor").focus();
    } else {
        document.getElementById("valor").disabled = true;
        document.getElementById("quantidade").disabled = true;
    }
    limpaCampos();


}

function limpaCampos() {
    document.getElementById("quantidade").value = "";
    document.getElementById("valor").value = "";
}

function calcValor(x) {
    preco = 10;
    document.getElementById("valor").value = preco * x;
}

function calcLitros(x) {
    preco = 3.5;
    document.getElementById("quantidade").value = (x / preco).toFixed(3);
}

function validar() {
    campoQtd = document.getElementById("quantidade"); 
    campoValor = document.getElementById("valor");
    if (campoQtd.value = "0") {
        alert("Favor, prenncher os campos pendentes.");
        campoQtd.focus();
    } else if (campoValor.value = "0") {
        alert("Favor, prenncher os campos pendentes.");
        document.getElementById("valor").focus();
    }
}

function MascaraMoeda(objTextBox, SeparadorMilesimo, SeparadorDecimal, e) {
    var sep = 0;
    var key = '';
    var i = j = 0;
    var len = len2 = 0;
    var strCheck = '0123456789';
    var aux = aux2 = '';
    var whichCode = (window.Event) ? e.which : e.keyCode;
    if ((whichCode == 13) || (whichCode == 0) || (whichCode == 8))
        return true;
    key = String.fromCharCode(whichCode); // Valor para o código da Chave
    if (strCheck.indexOf(key) == -1)
        return false; // Chave inválida
    len = objTextBox.value.length;
    if(len <= 5){
         for (i = 0; i < len; i++)
        if ((objTextBox.value.charAt(i) != '0') && (objTextBox.value.charAt(i) != SeparadorDecimal))
            break;
    aux = '';
    for (; i < len; i++)
        if (strCheck.indexOf(objTextBox.value.charAt(i)) != -1)
            aux += objTextBox.value.charAt(i);
    aux += key;
    len = aux.length;
    if (len == 0)
        objTextBox.value = '';
    if (len == 1)
        objTextBox.value = '0' + SeparadorDecimal + '0' + aux;
    if (len == 2)
        objTextBox.value = '0' + SeparadorDecimal + aux;
    if (len > 2) {
        aux2 = '';
        for (j = 0, i = len - 3; i >= 0; i--) {
            if (j == 3) {
                aux2 += SeparadorMilesimo;
                j = 0;
            }
            aux2 += aux.charAt(i);
            j++;
        }
        objTextBox.value = '';
        len2 = aux2.length;
        for (i = len2 - 1; i >= 0; i--)
            objTextBox.value += aux2.charAt(i);
        objTextBox.value += SeparadorDecimal + aux.substr(len - 2, len);
    }
    calcLitros(parseFloat(objTextBox.value));
    return false;
    }else{
        alert("O valor maximo é de R$999,99.");
    }   
}

function teste(){
   alert( parseFloat(document.getElementById("valor").value.replace("," , ".")) + 1 );
}
