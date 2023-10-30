Linguagem de Comunicação Cara a Cara - 1.0

A Linguagem de Comunicação Cara a Cara (LCCC) é uma linguagem usada para a comunicação dos clientes no aplicativo Cara a Cara, implementado no  modelo cliente servidor. Essa linguagem representa os comandos do jogo , os quais são interpretados pelo servidor , permitindo assim a comunicação entre os clientes.

Comandos:

%esperandoPronto%
Quem executa: Servidor
Funcionalidade: Comando enviado para o cliente que informa que o servidor está esperando que o cliente esteja pronto para iniciar a partida.

%esperandoJogador%
Quem executa: Servidor
Funcionalidade: Comando enviado para o cliente informando que está à espera do segundo jogador.

%pronto%
Quem executa: Cliente
Funcionalidade: Indica que o cliente está pronto para começar a partida.

%todosPersonagensRecebidos%
Quem executa: Cliente
Funcionalidade: Informa ao servidor que os personagens foram recebidos.

%iniciaJogo%
Quem executa: Servidor
Funcionalidade: Informa ao cliente o que o jogo está iniciado.

%personagem%
Quem executa: Cliente / Servidor
Funcionalidade: Comando para indicar que as informações seguintes serão de um personagem.

%nome%
Quem executa: Cliente / Servidor
Funcionalidade: Usado para informar o nome do personagem. (Comando deve ser enviado dentro da tag % Personagem% ou %PersonagemSorteado%).

%listaPersonagens%
Quem executa: Servidor
Funcionalidade: Lista com todos os personagens que serão utilizados durante o jogo.

%esperandoJogada%
Quem executa: Servidor
Funcionalidade: Comando enviado para o cliente para esperar a jogada.

%jogada%
Quem executa: Cliente
Funcionalidade: Comando onde dentro dessa tag terá a jogada do cliente.

%pergunta%
Quem executa: Cliente
Funcionalidade: Comando utilizado dentro da tag %jogada%, onde o cliente colocará uma pergunta que será passada para o outro cliente.

%chute%
Quem executa: Cliente
Funcionalidade: Comando utilizado dentro da tag % jogada%, onde o cliente colocará qual o personagem ele acha que é o personagem sorteado do outro cliente.

%repassaJogada%
Quem executa: Servidor
Funcionalidade: Comando usado para repassar a jogada de um cliente para o outro.

%repassaPergunta%
Quem executa: Servidor
Funcionalidade: Comando que repassa a pergunta feita por um cliente para o outro(Comando deve ser enviado dentro da tag %repassaJogada%) .

%respostaChute%
Quem executa: Servidor / Cliente
Funcionalidade: Retorna para ambos clientes a resposta do chute realizado por um deles. (Comando deve ser enviado dentro da TAG %repassaJogada%).

%chuteCorreto%
Quem executa: Servidor
Funcionalidade: Comando utilizado dentro do comando %respostaChute% e indica que o chute foi correto,  ou seja, que o jogador adivinhou o personagem.
%chuteErrado%
Quem executa: Servidor
Funcionalidade: Comando utilizado dentro do comando %respostaChute% e indica que o chute foi errado,  ou seja, que o jogador não adivinhou o personagem.

%respostaPergunta%
Quem executa: Cliente
Funcionalidade: Envia a resposta de um cliente para a pergunta feita pelo outro cliente com Sim ou Não (Comando deve ser enviado dentro da TAG repassaJogada).

%fimDeJogo%
Quem executa: Servidor
Funcionalidade: Comando enviado para os clientes avisando que o jogo acabou.

%voceVenceu%
Quem executa: Servidor
Funcionalidade: Comando que o servidor envia ao cliente informando que ele ganhou o jogo. Deve ser enviado dentro da  tag %fimDeJogo%.


%vocePerdeu%
Quem executa: Servidor.
Funcionalidade: Comando que o servidor envia ao cliente informando que ele perdeu o jogo.Deve ser enviado dentro da  tag %fimDeJogo%.

	1.22.	%personagemSorteado% 
Quem executa: Servidor
Funcionalidade: Comando que contém o id do personagem sorteado para cada cliente, esse é o personagem que o outro cliente terá que acertar.
