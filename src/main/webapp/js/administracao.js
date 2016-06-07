window.addEventListener("load", iniciar);
function iniciar() {
    msg = document.getElementById("mensagem").value;
    if (msg != "") {
        alert(msg);
        document.getElementById("mensagem").value = "";
    }
    var cpf = document.getElementById("cpf").value;
    if (cpf !== "") {
        document.getElementById("cpf").disabled = true;
        document.getElementById("senha").style.display = "none";
        document.getElementById("cnfSenha").style.display = "none";
        document.getElementById("lblSenha").style.display = "none";
        document.getElementById("lblConfirma").style.display = "none";
        document.getElementById("check").style.display = "inline";
    }
    else {
        document.getElementById("cpf").disabled = false;
        document.getElementById("senha").style.display = "inline";
        document.getElementById("cnfSenha").style.display = "inline";
        document.getElementById("lblSenha").style.display = "inline";
        document.getElementById("lblConfirma").style.display = "inline";
        document.getElementById("check").style.display = "none";

    }
}
function pesquisar() {
    document.querySelector("form").submit();


}
function camposSenha() {
    document.querySelector("#senha").type = "password";
}

function SomenteNumero(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if ((tecla > 47 && tecla < 58))
        return true;
    else {
        if (tecla == 8 || tecla == 0)
            return true;
        else
            return false;
    }
}

function verificaCadastrar(){
    
    if(document.getElementById("cpf").disabled === true){
        alert("CPF já cadastrado no sistema.");
        return false;
    }
    else true;
}

function statusAlterado(){
    document.getElementById("statusAlterado").value = "true";
    
}

