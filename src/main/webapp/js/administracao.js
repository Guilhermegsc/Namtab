window.addEventListener("load", iniciar);
function iniciar() {
    var cpf = document.getElementById("cpf").value; 
    if(cpf !== ""){
            document.getElementById("cpf").disabled = true;
            document.getElementById("senha").style.display = "none";
            document.getElementById("cnfSenha").style.display = "none";
            document.getElementById("lblSenha").style.display = "none";
            document.getElementById("lblConfirma").style.display = "none";
    }
    else{
                    document.getElementById("cpf").disabled = false;
            document.getElementById("senha").style.display = "inline";
            document.getElementById("cnfSenha").style.display = "inline";
            document.getElementById("lblSenha").style.display = "inline";
            document.getElementById("lblConfirma").style.display = "inline";
        
    }
}
function pesquisar(){
    document.querySelector("form").submit();
    

}
function camposSenha(){
    document.querySelector("#senha").type = "password";
}