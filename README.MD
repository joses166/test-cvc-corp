A aplicação de transferência financeira foi criada para funcionar da seguinte forma:

- O usuário para comunicar com a api possui dois endpoints:

POST = /api/financial/schedulling 

Neste endpoint é possível encaminhar um payload com as seguintes informações:

originAccount (conta de origem, contém 6 dígitos);
destinyAccount (conta de destino, contém 6 dígitos);
transferValue (valor transferência formato padrão, exemplo 1000.00);
transferDate (data transferência no formato dd/MM/yyyy);

Após informado estes dados e feita a requisição o sistema valida as informações para verificar se segue o padrão definido na classe: br.com.cvc.api.dto.FinancialTransferDTO;
Logo após finalizado a validação é possível termos o retorno de mensagens de erro, caso exista falhas no json passado, caso esteja tudo nos conformes, o sistema encaminha os dados para um serviço chamado: br.com.cvc.api.services.FinancialTransferService que é uma interface utilizada na classe br.com.cvc.api.services.impl.FinancialTransferServiceImpl nesta classe o processamento se inicia fazendo o processo de pegar as informações do DTO e passar para a entidade FinancialTransfer em si, no momento que chega no processo de cálculo da taxa é chamado um método que tem a estrutura para averiguar a que tipo de condição a transferência está vinculada, neste método é feito primeiro a recuperação das datas de agendamento e a registrada para a transferência a partir do método Utils.gettingDayToDate para verificar quantos dias existem de diferença entre as datas para ser possível calcular a taxa de acordo com os dias e o valor da transferência, caso não esteja em nenhuma das opções disponíveis do sistema é emitido um alerta para o usuário, caso contrário é feito o cálculo.

Classes que fazem o cálculo da Taxa:

br.com.cvc.api.services.impl.FortyDaysTax = Faz o cálculo caso a data de transferência seja até quarenta dias da data atual.
br.com.cvc.api.services.impl.MoreFortyDaysTax = Faz o cálculo caso a data de transferência seja maior que quarenta dias da data atual e maior que 100.000 o valor.
br.com.cvc.api.services.impl.MoreTenDaysTax = Faz o cálculo caso a data de transferência seja maior que dez dias da data atual.
br.com.cvc.api.services.impl.TenDaysTax = Faz o cálculo caso a data de transferência seja até dez dias da data atual.
br.com.cvc.api.services.impl.ThirtyDaysTax = Faz o cálculo caso a data de transferência seja até trinta dias da data atual.
br.com.cvc.api.services.impl.TodayTax = Faz o cálculo caso a data de transferência seja a mesma da data atual.
br.com.cvc.api.services.impl.TwentyDaysTax = Faz o cálculo caso a data de transferência seja até vinte dias da data atual.

Todas as classes voltadas ao cálculo são implementadas pela interface do br.com.cvc.api.services.Tax;

A interface br.com.cvc.api.services.CalculateService é responsável por receber os parâmetros de transferência financeira e o tipo de taxa que se refere;
A classe br.com.cvc.api.services.impl.CalculateServiceImpl implementa a interface br.com.cvc.api.services.CalculateService que possui o método calculate responsável por chamar a objeto passado por parâmetro da classe Tax e chamar o método de cálculo da Taxa em si (calculatingTax).

Foi criada uma classe Response responsável por devolver a informação do controller dentro de um atributo data que possui uma classe genérica definida no momento de instanciar a classe para um objeto ou se caso tiver erros pode ser adicionado no atributo errors que é um array de string.

Na classe br.com.cvc.api.utils.Utils temos os métodos:

calculateDate(Integer dias) => responsável por calcular a data adicionando a quantidade de dias passados por parâmetro;
gettingDayToDate(Integer dias) => responsável por calcular o valor em milisegundos da data atual + uma quantidade de dias passadas pelo parâmetro;
gettingDayToDate(Date data) => responsável por calcular o valor em milisegundos da data que foi informado no parâmetro;

GET = /api/financial

- Retorna todas as transferências financeiras existentes na base de dados. 