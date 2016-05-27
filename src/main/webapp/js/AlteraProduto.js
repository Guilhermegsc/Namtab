window.addEventListener("load", iniciar);
function iniciar() {
    msg = document.getElementById("mensagem").value;
    if (msg != "") {
        alert(msg);
        document.getElementById("mensagem").value = "";
    }
}
function validar(prod) {
    preco = document.getElementById("novoPreco").value;
    nomeProduto = prod.selectedOptions[0].getAttribute("data-nome");
    
    if(nomeProduto == null){
        alert("Selecione o produto.");
        return false;
    }
    if(preco == "" || preco == "0,00"){
        alert("Por favor, digite um preco valido.");
        return false;
    }

    if (confirm("Confirma o valor R$" + preco + " para " + nomeProduto + "?")) {
        return true;
    } else {
        return false;
    }

}
window.onkeydown = function (event) {
    if (event.which == 8 || event.which == 46 || event.which == 37 || event.which == 39) {
        document.getElementById("novoPreco").value = "";
    }
}

function preenchePreco(prod) {
    preco = prod.selectedOptions[0].getAttribute("data-preco");
    document.getElementById("PrecoAtual").value = preco;
    document.getElementById("novoPreco").focus();
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
        return false;
    } else {
        alert("O valor maximo é de R$999,99.");
    }
}
