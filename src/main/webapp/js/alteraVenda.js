window.addEventListener("load", teste)
function teste() {
    x = document.getElementById("mensagem").value;
    if (x != "") {
        alert(x);
    }
}

function somenteNumero(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if ((tecla > 47 && tecla < 58 || tecla == 13))
        return true;
    else {
        if (tecla == 8 || tecla == 0)
            return true;
        else
            return false;
    }
}

function validar(){
    if (confirm("Confirma a exclusão?")) {
        return true;
    } else {
        return false;
    }
}