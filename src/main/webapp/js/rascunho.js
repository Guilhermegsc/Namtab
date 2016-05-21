var xhr, texto, obj;

function readResult(t){
texto = t.target.response;
texto = texto.split("\n").join("<br/>");
document.querySelector("#caixa").textContent = texto; 
//obj = JSON.parse(texto); Transforma em objeto
//document.querySelector("#caixa").textContent = obj.name;
}

function carregar(){
	xhr = new XMLHttpRequest();
	xhr.open("get","texto.txt", true);
	xhr.onload = readResult;
	xhr.send();
        }
function iniciar(){
    document.querySelector("#carregar").addEventListener("click", carregar);    


}
window.addEventListener("load",iniciar);