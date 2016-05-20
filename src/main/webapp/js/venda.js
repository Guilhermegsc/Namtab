window.addEventListener("load", iniciar);
function iniciar() {
    msg = document.getElementById("mensagem").value;
    if (msg != "") {
        alert(msg);
        document.getElementById("mensagem").value = "";
    }
}

window.onkeydown = function (event) {
    if (event.which == 8 || event.which == 46 || event.which == 37 || event.which == 39) {
        limpaCampos();
    }
}
function habilitaCampo(campo) {
    x = campo.value;
    tamanho = campo.length;

    if (x >= 1 && x <= 4) {
        document.getElementById("valor").disabled = false;
        document.getElementById("quantidade").disabled = true;
        document.getElementById("valor").focus();
    } else if (x >= 5 && x <= tamanho) {
        document.getElementById("quantidade").disabled = false;
        document.getElementById("valor").disabled = true;
        document.getElementById("quantidade").focus();
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

function calcValor(qtd, campo) {
    preco = campo.selectedOptions[0].getAttribute("data-preco");
    result = parseFloat(preco * qtd).toFixed(2);
    if (result >= 1000) {
        alert("O valor maximo é de R$999,99.");
        document.getElementById("quantidade").value--;
    } else {
        document.getElementById("valor").value = result.replace(".", ",");
    }
}

function calcLitros(valor, campo) {
    valor = converte(valor);
    preco = campo.selectedOptions[0].getAttribute("data-preco");
    result = parseFloat(valor / preco).toFixed(3);
    document.getElementById("quantidade").value = result;
}

function MascaraMoeda(objTextBox, SeparadorMilesimo, SeparadorDecimal, e, campo) {
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
    if (len <= 5) {
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
        calcLitros(objTextBox.value, campo);
        return false;
    } else {
        alert("O valor maximo é de R$999,99.");
    }
}

function converte(n) {
    n = parseFloat(document.getElementById("valor").value.replace(",", "."));
    return n;
}

function validar() {
    prod = document.getElementById("produto").value;
    valor = document.getElementById("valor").value;
    qtd = document.getElementById("quantidade").value;
    if (prod == "") {
        alert("Por favor, selecione o produto");
        return false;
    } else if (valor == "" || qtd == "") {
        alert("Por favor, preencha os campos");
        return false;
    } else{
        return true;
    }


}