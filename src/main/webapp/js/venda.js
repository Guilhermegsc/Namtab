function iniciar() {
    alert("ok");

}

function validar() {
    cmpProduto = document.getElementById("#Produto");
    cmpComb = document.getElementById("#combustivel");
    cmpValor = document.getElementById("#valor");

    if (cmpProduto == "") {
        alert("Selecione o produto");
    } else if (cmpComb == "") {
        alert("Selecione o combustivel");
    } else if (cmpValor == "") {
        alert("Digite o valor");
    }

}


window.addEventListener("load", iniciar);
document.querySelector("#efetuaVenda").addEventListener("click", validar());