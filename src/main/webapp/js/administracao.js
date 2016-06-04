window.addEventListener("load", iniciar);
function iniciar() {
   
}
function pesquisar(){
    document.querySelector("form").submit();
    document.getElementById("cpf").disabled = true;

}
function camposSenha(){
    document.querySelector("#senha").type = "password";
}