window.addEventListener("load", iniciar)

function iniciar(){
    x = document.getElementById("mensagem").value;
    if(x != ""){
        alert(x);
        x = "";
    }
}

