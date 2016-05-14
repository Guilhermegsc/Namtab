function iniciar() {
    alert("ok");

}
window.addEventListener("load", iniciar);
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



document.querySelector("#efetuaVenda").addEventListener("click", validar());