# Atividade IV - Sistema de Monitoramento da Qualidade do Ar  
Este repositório armazena a implementação de um sistema para monitoramento ambiental utilizando conceitos de orientação a objetos, como parte da disciplina **Estrutura de Dados** (ESPM - Sistemas de Informação - 3° semestre), lecionada pelo **Prof. Dr. Antonio Marcos Selmini**.

## 🌱 Contexto

Em resposta aos crescentes desastres ambientais, a aplicação simula um sistema de **monitoramento da qualidade do ar** em diferentes regiões do país. O objetivo é detectar níveis perigosos de poluentes (AQI - Air Quality Index) e apoiar ações emergenciais.

## 🧩 Conceitos aplicados

📌 **Estruturas e princípios utilizados:**
- Programação orientada a objetos (encapsulamento, herança, interface)
- Uso da classe `TreeSet` do Java para armazenar zonas monitoradas
- Classes especializadas: `ZonaUrbana`, `ZonaRural`, `Sensor`
- Interface `Emergencia`

## ⚙️ Funcionalidades

### 📍 Classe `ZonaRural`

- `relatorio()`: retorna o nome da região com a mensagem  
  `"Zona sem sensores instalados. Monitoramento indireto via satélite."`

### 🏙️ Classe `ZonaUrbana`

- `adicionarSensor(Sensor sensor)`: adiciona sensores à lista de sensores da zona.
- `calcularTotal()`: soma os valores AQI registrados pelos sensores.
- `calcularMedia()`: calcula a média semanal dos valores AQI.
- `classificarNivelEmergencia()`: retorna o nível de emergência com base na tabela abaixo:

| Média Semanal AQI | Nível de Emergência                                |
|--------------------|----------------------------------------------------|
| Até 50             | Sem risco                                          |
| 51 a 100           | Monitoramento intensificado                        |
| 101 a 150          | Alerta para grupos sensíveis                      |
| 151 a 200          | Alerta Amarelo                                     |
| 201 a 300          | Alerta Laranja                                     |
| Acima de 300       | Alerta Vermelho (emergência total)                |

- `relatorio()`: exibe nome da zona, total de AQI, média semanal e nível de emergência.  
  ➕ Se o nível for "Alerta Vermelho", exibe também:  
  `"Ação imediata recomendada: evacuação ou restrição de atividades externas."`

### 🧑‍💻 Classe `Main`

Apresenta o menu principal com as opções:
1. **Registrar Zona** (urbana ou rural, inserida em um `TreeSet`)
2. **Adicionar Sensor** (apenas para zonas urbanas)
3. **Imprimir Relatório** (busca por nome e imprime o relatório da zona)
4. **Finalizar**

⚠️ Se a média semanal AQI for **acima de 300**, exibir no `Main`:  
`>>> ALERTA EXTREMO: Média crítica ultrapassada!`