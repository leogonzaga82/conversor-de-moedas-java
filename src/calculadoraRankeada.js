// Função que calcula o saldo e determina o nível
function calcularNivel(vitorias, derrotas) {
    // Cálculo do saldo conforme o objetivo
    let saldoVitorias = vitorias - derrotas;
    let nivel = "";

    // Estrutura de decisão para determinar o nível baseado nas vitórias
    if (vitorias <= 10) {
        nivel = "Ferro";
    } else if (vitorias >= 11 && vitorias <= 20) {
        nivel = "Bronze";
    } else if (vitorias >= 21 && vitorias <= 50) {
        nivel = "Prata";
    } else if (vitorias >= 51 && vitorias <= 80) {
        nivel = "Ouro";
    } else if (vitorias >= 81 && vitorias <= 90) {
        nivel = "Diamante";
    } else if (vitorias >= 91 && vitorias <= 100) {
        nivel = "Lendário";
    } else {
        nivel = "Imortal";
    }

    // Retorno do resultado formatado
    return `O Herói tem de saldo de ${saldoVitorias} está no nível de ${nivel}`;
}

// Exemplo de uso
let resultado = calcularNivel(75, 20);
console.log(resultado);