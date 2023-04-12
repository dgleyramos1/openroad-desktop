# OpenRoad

O OpenRoad é uma ferramenta essencial para restaurantes, bares e outros estabelecimentos que trabalham com o sistema de comandas. Este tipo de software permite que os atententes façam os pedidos diretamente em uma comanda eletrônica, que é registrada em um sistema central.

A automação dos pedidos por comanda também ajuda a evitar erros no registro de pedidos, garantindo que todos os itens solicitados pelos clientes sejam registrados corretamente e encaminhados para a cozinha ou bar com rapidez. Isso aumenta a eficiência do estabelecimento como um todo e melhora a experiência do cliente.

Em resumo, O OpenRoad é uma ferramenta fundamental para qualquer estabelecimento que trabalhe com esse sistema de atendimento. Ele ajuda a melhorar a eficiência, reduzir erros e proporcionar uma experiência melhor aos clientes.

[![Java](https://img.shields.io/badge/Java-red.svg)](https://www.java.com/pt-BR/)
[![JavaFX](https://img.shields.io/badge/JavaFX-green.svg)](https://openjfx.io/)
[![11 JDK](https://img.shields.io/badge/JDK-11-yellow.svg)](https://www.oracle.com/br/java/technologies/downloads/)
[![2.7.8 SpringBoot](https://img.shields.io/badge/SpringBoot-2.7.8-green.svg)](https://spring.io/projects/spring-boot)

## Instalação

### Download

Faça o download da aplicação [aqui](https://github.com/dgleyramos1/openroad-desktop/releases/download/Lan%C3%A7amento/OpenRoad.exe)

### Passo a passo

-   Vamos executar o instalador

    ![screen1](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/1.jpeg)

-   Escolha o idioma

    ![screen2](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/2.jpeg)

-   Aceite os termos

    ![screen3](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/3.jpeg)

-   Escolha uma pasta para instalação ou use a padrão, depois clique em avançar

    ![screen4](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/4.jpeg)

-   Crie um icone na area de trabalho, se desejar! Depois clique em avançar

    ![screen5](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/5.jpeg)

-   Se você já tiver o Java ou Postgresql instalados não precisa instalar novamente, caso contrario instale ambos, para o software funcionar corretamente

    ![screen6](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/6.jpeg)

-   Instalando o Java, aqui é basicamente next e next

    ![screen7](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/7.jpeg)

-   Clique em next

    ![screen8](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/8.jpeg)

-   Clique em close

    ![screen9](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/9.jpeg)

-   Agora vamos instalar o Postgresql, next

    ![screen10](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/10.jpeg)

-   Escolha uma pasta para instalação ou use a padrão, clique em next

    ![screen11](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/11.jpeg)

-   Deixe todos marcados, clique em next

    ![screen12](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/12.jpeg)

-   Clique em next

    ![screen13](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/13.jpeg)

-   Adicione uma senha para o usuário postgres, por padrão você pode utilizar a senha "admin", mas se escolher outra, cuidado para não esquecer! Clique em next

    ![screen14](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/14.jpeg)

-   É extremamente importante que o valor da port seja <Strong>5432</Strong>, caso contrário a aplicação não irá se conectar com o banco! Clique em next

    ![screen15](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/15.jpeg)

-   Clique em next

    ![screen16](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/16.jpeg)

-   Clique em next

    ![screen17](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/17.jpeg)

-   Clique em next

    ![screen18](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/18.jpeg)

-   Ele irá começar a fazer toda instalação!

    ![screen19](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/19.jpeg)

-   Clique em Finish

    ![screen20](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/20.jpeg)

-   Agora iremos abrir o nosso pgAdmin 4, para criar o nosso usuário e também criar o nosso banco de dados.

    ![screen21](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/21.jpeg)

-   Ele irá pedir que você coloque aquela senha que você criou quando estava instalando a ferramenta, depois de colocar a senha correta, clique em ok.

    ![screen22](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/22.jpeg)

-   Agora iremos criar o nosso usuário. Clique com o botão direito do mouse em Login/Group Role > create > Login/Group Role...

    ![screen23](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/23.jpeg)

-   Na propriedade <strong>Name</strong> coloque o nome do usuário, utilizamos um usuário padrão <strong>openroad</strong>, é importante colocar do jeito que está na imagem.

    ![screen24](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/24.jpeg)

-   Na aba Definition nós iremos definir a senha do nosso usuário, mais uma vez, utilizamos um padrão por isso a senha é a mesma para todos. coloque como senha <strong>openroad123</strong>

    ![screen25](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/25.jpeg)

-   Na aba Privilegis você irá marcar todos os privilegios para esse usuário, depois clique em Save.

    ![screen26](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/26.jpeg)

-   Agora iremos criar nosso banco de dados, clique com o botão direito do mouse em Databases > create > Database...

    ![screen27](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/27.jpeg)

-   Aqui iremos colocar o nome do nosso banco de <strong>openroad</strong>, vamos também selecionar o nosso Owner como o usuário que acabamos de criar, depois clique em Save.

    ![screen28](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/28.jpeg)

## Modo de usar

-   Agora que o banco e usuário foram criados, podemos executar nossa aplicação, ela irá demorar alguns segundinhos para abrir, aguarde!

    ![screen29](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/29.jpeg)

-   Se ela chegou nessa tela, então deu tudo certo! Agora você precisará criar um usuário, como é a primeira vez que a aplicação está sendo executada, quando for abrir novamente, ela abrirá uma tela de login no lugar dessa.

    ![screen30](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/30.jpeg)

-   Bom, essa é nossa tela principal

    ![screen31](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/31.jpeg)

-   No menu inicio, clique em funcionários, aqui você poderá adicionar, editar e excluir seus funcionários, mas vale lembrar que a aplicação não excluirá todos, você terá que deixar um para entrar na proxima vez.

    ![screen32](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/32.jpeg)

-   No menu inicio, clique em categorias.

    ![screen33](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/33.jpeg)

-   Aqui você poderá adicionar sua categorias, todo produto é ligado a uma categoria, então se essa categoria conter algum produto você não conseguirá excluilá.

    ![screen34](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/34.jpeg)

-   Para adicionar uma nova categoria é super facil, basta colocar o nome que você deseja e depois clicar em adicionar.

    ![screen35](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/35.jpeg)

-   Suas categorias aparecerão nessa tabela

    ![screen36](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/36.jpeg)

-   Para editar ou excluir, você precisará clicar na categoria na tabela e logo em seguida clicar em editar ou em deletar, lembrando que você não poderá apagar categorias que já tem produtos adicionados.

    ![screen37](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/37.jpeg)

-   Se você clicar em editar, abrirá um popup, para você mudar o nome da categoria, se clicar em deletar e ela não conter nenhum produto ela será deletada.

    ![screen38](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/38.jpeg)

-   Quando clicar em salvar a categoria será atualizada.

    ![screen39](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/39.jpeg)

-   Agora que já vimos como adicionar as categorias, vamos aprender a adcionar os produtos, no menu inicio clique em Produtos.

    ![screen40](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/40.jpeg)

-   Para adiconar um produto, basta clicar em Adicionar.

    ![screen41](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/41.jpeg)

-   Aqui você poderá preencher os dados do seu produto, em descrição você pode colocar os ingredientes do mesmo, deverá selecionar uma categoria, adicionar um nome e um preço para esse produto, depois só clicar em salvar.

    ![screen42](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/42.jpeg)

-   Seus produtos irão aparecer nessa tabela

    ![screen43](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/43.jpeg)

-   Para editar ou deletar um produto você precisa selecionar ele na tabela e depois clicar no botão da acção que você deseja usar.

    ![screen44](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/44.jpeg)

-   Se clicou em editar, ele abrirá o popup mais uma vez, só que os dados do produto que você deseja editar, depois só precisa clicar em salvar. Caso tenha clicado em deletar, ele irá deletar se o produto ainda não foi vendido, caso contrario ele colocará o produto como inativo e ele sairá da sua lista de produtos.

    ![screen45](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/45.jpeg)

-   Depois de editar ou deletar a tabela será atualizada.

    ![screen46](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/46.jpeg)

-   Agora vamos no menu Ordens, clique em Mesas.Aqui ficarão todas as suas mesas abertas.

    ![screen47](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/47.jpeg)

-   Quando alguma mesa for aberta ela aparecerá assim.

    ![screen51](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/51.jpeg)

-   Quando for adicionado pedido, você terá acesso a comanda da mesa.

    ![screen52](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/52.jpeg)

-   O calculo só é feito quando o garçom coloca o pedido como entregue ao cliente, a conta poderá ser fechada tanto no app desktop quanto pelo garçom, já com o calculo feito.

    ![screen53](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/53.jpeg)

-   Agora vamos no menu Ordens, clique em Pedidos. Aqui ficarão todos os pedidos que estão esperando para serem feitos na cozinha.

    ![screen48](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/48.jpeg)

-   Quando o pedido é enviado para cozinha ele aparecerá assim, em ordem de chegada.

    ![screen54](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/54.jpeg)

-   Agora que você já adicionou seus funcionários, categorias e seus produtos, está na hora de conectar o app mobile com o servidor desktop. Para isso você precisará ir no meu Ajuda, clicar em Conexão.

    ![screen49](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/49.jpeg)

-   Aqui você terá o IP e PORT para colocar no app mobile, assim ele poderá se conectar com o servidor e abriar as mesas, fazer os pedidos e fechar a cont dos clientes. Para que isso funcione bem, o celular precisa está conectado na mesma rede de internet que o computador!

    ![screen50](https://raw.githubusercontent.com/dgleyramos1/openroad-desktop/master/assets/50.jpeg)

## Aplicativo mobile

[mobile](https://github.com/dgleyramos1/openroad-mobile)

## Licença

[MIT](https://github.com/dgleyramos1/aplication-api-openroad/blob/master/LICENSE)

## Autor

-   [@dgleyramos](https://www.instagram.com/dgleyramos/)

<div style="display: inline;">
  <h3>Doações</h3>
  <p>
    <a href="https://www.buymeacoffee.com/dgleyramos">
      <img align="left" src="https://cdn.buymeacoffee.com/buttons/v2/default-yellow.png" height="50" width="210" alt="dgleyramos" />
    </a>
   </p><br><br>
</div><br>
