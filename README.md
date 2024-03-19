# Dificuldades/Facilidades de Comunicação com Sockets:

## Dificuldades:

- Inicialmente, houve dificuldades na compreensão do funcionamento básico de sockets e como eles facilitam a comunicação entre cliente e servidor.
- A gestão manual de entrada e saída de dados, especialmente no tratamento de erros e exceções, exigiu uma compreensão detalhada das classes DataInputStream e DataOutputStream.
- Identificar e corrigir problemas de comunicação, como a mistura de fluxos de dados ou formatos de mensagem incorretos.
  
## Facilidades:

- A estrutura básica de criação de servidor e cliente com sockets, como demonstrado nos exemplos fornecidos, ajudou a estabelecer rapidamente uma conexão funcional entre as partes.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

# Dificuldades/Facilidades de Passar Mensagens e de Interpretá-las:

## Dificuldades:

- Interpretar corretamente as mensagens recebidas, especialmente quando estas são enviadas em diferentes formatos (strings, objetos serializados, etc.), exigiu atenção aos detalhes e uma compreensão clara do protocolo de comunicação estabelecido.
- Garantir que as mensagens fossem interpretadas corretamente em ambas as extremidades, cliente e servidor, era essencial para evitar erros de comunicação e comportamentos inesperados.

## Facilidades:
- O uso de uma estrutura de comando simples, onde cada mensagem consistia em um comando seguido de argumentos, facilitou a interpretação e processamento tanto no cliente quanto no servidor.
