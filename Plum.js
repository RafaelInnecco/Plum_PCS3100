//Configuração do projeto do firebase
var firebaseConfig = {
    apiKey: "AIzaSyAWVt_dcYv40hL9P9wDioRtAnw8q7vcNCU",
    authDomain: "enduring-aria-317217.firebaseapp.com",
    projectId: "enduring-aria-317217",
    storageBucket: "enduring-aria-317217.appspot.com",
    messagingSenderId: "28687735536",
    appId: "1:28687735536:web:5b78544e074ae21fdcc035",
    measurementId: "G-DL5ZWWSQR3"
  };
// Inicialização do firebase
firebase.initializeApp(firebaseConfig);
//Referência do firestore
var firestore = firebase.firestore();

var listaAtual = firestore.collection("lista"); //Inicializa a variável lista, que pode ser trocada para as sublistas
//O bloco abaixo serve para pegar os dados fornecidos pelo usuário
//Para cada input/output do documento html é necessário inicializar uma constante ou variável
const inputNome = document.querySelector("#nomedoitem");
const inputQtde = document.querySelector("#quantidade");
const inputValor = document.querySelector("#valor");
const botaoNovoItem = document.querySelector("#salvar");
const inputSublista = document.querySelector("#nomeSublista");
const botaoSublista = document.querySelector("#acessar");
const botaoReset = document.querySelector("#reset");
const botaoDeletar = document.querySelector("#confDel");
const inputDeletar = document.querySelector("#deletar");
var outputLista = document.querySelector("#displayLista");
var outputValor = document.querySelector("#displayValor");
var outputSublistas = document.querySelector("#displaySublistas");

var atualizarLista = function(){
    listaAtual.orderBy("Nome").onSnapshot(function(col){
        outputLista.innerText = ""; //Limpa o display da lista para evitar duplicações
        var valorLista = 0;
        outputSublistas.innerText = ""; //Limpa o display da lista de sublistas
        col.forEach(doc => {
            var data = doc.data();
            if(data.Qtde > 0 && data.Nome.toLowerCase() != "acesso"){ //Indica que não é uma sublista
                var qtdeString = data.Qtde.toString();
                var valorString = data.Valor.toString();
                outputLista.innerText = outputLista.innerText + data.Nome + ":  Qtde = " + qtdeString + "  Valor unid = " + valorString + "\n";
                valorLista = valorLista + (data.Qtde)*(data.Valor);
            } else if(data.Nome.toLowerCase() != "acesso"){ //o item 'acesso' com quantidade e valor = 0 é usado somente para criar uma nova sublista 'vazia'
                outputSublistas.innerText = outputSublistas.innerText + data.Nome + "\n";
            }
        })
        outputValor.innerText = "Soma dos Valores da lista =  " + valorLista.toString() + " reais\n";
    })
}

atualizarLista();

botaoReset.addEventListener("click", function(){
    listaAtual = firestore.collection("lista");
    atualizarLista();
});

botaoNovoItem.addEventListener("click", function(){
    var nomeParaSalvar = inputNome.value.toLowerCase(); //Determina variáveis com os valores fornecidos pelo usuário
    var qtdeParaSalvar = inputQtde.value;
    var valorParaSalvar = inputValor.value;
    console.log("Salvando no firestore...");

    var elemento = listaAtual.doc(nomeParaSalvar);

    elemento.set({ //Procura se o item já existe na lista, cria se não existe e atualiza se sim
        Nome: nomeParaSalvar[0].toUpperCase() + nomeParaSalvar.substring(1),
        Qtde: qtdeParaSalvar,
        Valor: valorParaSalvar,
    }, {merge: true}).then(function() {
        console.log("Salvo!");
    }).catch(function(error){
        console.log("Erro!");
    });
});

botaoSublista.addEventListener("click", function(){
    var nomeSublista = inputSublista.value;
    var nomeItem = inputNome.value;
    var qtdeItem = inputQtde.value;
    var valorItem = inputValor.value;

    var sublistaDoc = listaAtual.doc(nomeSublista.toLowerCase());

    sublistaDoc.set({
        Nome: nomeSublista
    })

    listaAtual = sublistaDoc.collection(nomeSublista.toLowerCase());
    //Para criar uma sublista, o firestore requer o primeiro documento, por isso colocamos os dados do elemenot a ser adicionado em um documenot da sublista
    //Caso não se queira adicionar um item, "acesso" deve ser usado 
    listaAtual.doc(nomeItem.toLowerCase()).set({
        Nome: nomeItem.toLowerCase(),
        Qtde: qtdeItem,
        Valor: valorItem,
    }, {merge: true}).then(function() {
        console.log("Salvo!");
    }).catch(function(error){
        console.log("Erro!");
    });

    atualizarLista();
});

botaoDeletar.addEventListener("click", function(){
    var itemDel = inputDeletar.value.toLowerCase();

    listaAtual.doc(itemDel).delete();

    atualizarLista();
});
