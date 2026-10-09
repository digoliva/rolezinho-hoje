# Rolezinho hoje? 🍻🎉

##  Descrição do Objetivo
O Rolezinho hoje é um aplicativo voltado para grupos de amigos que decidem sair de última hora e enfrentam a indecisão sobre onde ir.
O app apresenta cartões no estilo de arrastar para o lado para aceitar ou recusar, com opções reais de bares, restaurantes e atrações de Ribeirão Preto - SP, gerando um itinerário final baseado no gosto coletivo do grupo.

## Tecnologias e Bibliotecas Utilizadas
- **Linguagem:** Kotlin
- **Plataforma:** Android Studio (Android Views / XML Layouts)
- **Componentes e Recursos:**
    - `ViewBinding`: Conexão segura entre layouts XML e a lógica Kotlin.
    - `MaterialCardView` & `MaterialButton`: Componentes do Material Design com cantos arredondados e sombras.
    - `Intent Explícita`: Navegação com envio de dados extras (`NOME_GRUPO`, `TOTAL_APROVADOS`, `LOCAL_ESCOLHIDO`) entre `MainActivity` e `ItineraryActivity`.
    - `Data Class Imutável`: Modelo de dados `RoleItem` com campos opcionais/nulos tratados com segurança.
    - Animação de Gestos (`OnTouchListener`): Sistema de *drag/swipe* customizado com rotação e transição suave.

## Como Rodar o Projeto Localmente

### Pré-requisitos
- **Android Studio** (versão Ladybug ou superior).
- **JDK 17** ou superior configurado.
- Emulador Android (API 24+) ou dispositivo físico conectado via Depuração USB.

### Passo a Passo
1. Clone este repositório para a sua máquina local:
   git clone [https://github.com/SEU_USUARIO/rolezinho-hoje.git](https://github.com/SEU_USUARIO/rolezinho-hoje.git)
2. Abra o Android Studio.
3. Selecione File > Open e escolha a pasta do projeto clonado.
4. Aguarde o Gradle baixar as dependências e realizar a sincronização automática.
5. Selecione o emulador ou dispositivo físico no menu superior e clique no botão Run (▶) (ou pressione Shift + F10).