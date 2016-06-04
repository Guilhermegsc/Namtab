window.addEventListener("load", iniciar);
function iniciar() {
    var cpf = document.getElementById("cpf").value; 
    if(cpf !== 0){
            document.getElementById("cpf").disabled = true;
    }
}
function pesquisar(){
    document.querySelector("form").submit();
    

}
function camposSenha(){
    document.querySelector("#senha").type = "password";
}