package br.espm.main;

import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import static javax.swing.JOptionPane.*;

import java.util.TreeSet;

import br.espm.sensor.Sensor;
import br.espm.zona.ZonaRural;
import br.espm.zona.ZonaUrbana;

public class Main {

    private TreeSet<ZonaUrbana> listaZonaUrbana = new TreeSet<>();
    private TreeSet<ZonaRural> listaZonaRural = new TreeSet<>();
    
    public void menu() {
        try {
            String opcao;

            showMessageDialog(null, "Bem-vindo ao Sistema de Monitoramento de Qualidade do Ar em Regiões de Risco");

            while(true) {
                try {
                    String menu = "";
                    menu += "  1. Registrar Zona";
                    menu += "\n  2. Adicionar Sensor";
                    menu += "\n  3. Imprimir relatório";
                    menu += "\n  4. Finalizar";

                    opcao = showInputDialog(menu);

                    if (opcao.isEmpty()) {
                            throw new UnsupportedOperationException("Comando inválido.");
                        }

                    if (parseInt(opcao) < 1 || parseInt(opcao) > 4) {
                        throw new UnsupportedOperationException("Comando inválido.");
                    }

                    switch (parseInt(opcao)) {
                        case 1:
                        // Registrar a Zona Rural ou Urbana
                        String subMenu = "  Deseja registrar uma zona Rural ou Urbana?";
                        subMenu += "\n  1. Rural";
                        subMenu += "\n  2. Urbana";

                        String op = showInputDialog(subMenu);

                        if (op.isEmpty()) {
                            throw new UnsupportedOperationException("Comando inválido.");
                        }

                        if (parseInt(op) < 1 || parseInt(op) > 2) {
                            throw new UnsupportedOperationException("Comando inválido.");
                        }

                        switch (parseInt(op)) {
                            case 1:
                                // Registrar Zona Rural
                                String nomeZonaRural = showInputDialog("Digite o nome da Zona Rural:");
                                if (nomeZonaRural.isEmpty()) {
                                    throw new UnsupportedOperationException("Nome da Zona Rural não pode ser vazio.");
                                }
                                boolean existeRural = false;
                                for (ZonaRural zona : listaZonaRural) {
                                    if (zona.getNome().trim().equalsIgnoreCase(nomeZonaRural.trim())) {
                                        existeRural = true;
                                        break;
                                    }
                                }
                                if (existeRural) {
                                    showMessageDialog(null, "A zona " + nomeZonaRural + " já existe!");
                                } else {
                                    ZonaRural zonaRural = new ZonaRural(nomeZonaRural);
                                    listaZonaRural.add(zonaRural);
                                    showMessageDialog(null, "Zona Rural '" + nomeZonaRural + "' registrada com sucesso!");
                                }
                                break;
                            case 2:
                                // Registrar Zona Urbana
                                String nomeZonaUrbana = showInputDialog("Digite o nome da Zona Urbana:");
                                if (nomeZonaUrbana.isEmpty()) {
                                    throw new UnsupportedOperationException("Nome da Zona Urbana não pode ser vazio.");
                                }
                                boolean existe = false;
                                for (ZonaUrbana zona : listaZonaUrbana) {
                                    if (zona.getNome().trim().equalsIgnoreCase(nomeZonaUrbana.trim())) {
                                        existe = true;
                                        break;
                                    }
                                }
                                if (existe) {
                                    showMessageDialog(null, "A zona " + nomeZonaUrbana + " já existe!");
                                } else {
                                    ZonaUrbana zonaUrbana = new ZonaUrbana(nomeZonaUrbana);
                                    listaZonaUrbana.add(zonaUrbana);
                                    showMessageDialog(null, "Zona Urbana '" + nomeZonaUrbana + "' registrada com sucesso!");
                                }
                                break;
                        }
                        break;

                        case 2:
                        // Adicionar Sensor
                        String nomeZona = showInputDialog("Digite o nome da Zona Urbana onde deseja adicionar o sensor:");
                        if (nomeZona.isEmpty()) {
                            throw new UnsupportedOperationException("Nome da Zona não pode ser vazio.");
                        }
                        ZonaUrbana zonaEncontrada = null;
                        for (ZonaUrbana zona : listaZonaUrbana) {
                            if (zona.getNome().trim().equalsIgnoreCase(nomeZona)) {
                                zonaEncontrada = zona;
                                break;
                            }
                        }
                        if (zonaEncontrada == null) {
                            showMessageDialog(null, "Zona Urbana não encontrada.");
                        } else {
                            int idSensor = zonaEncontrada.getSensores().size() + 1;
                            String data = showInputDialog("Digite a data do sensor (dd/mm/yyyy):");
                            if (data.isEmpty()) {
                                throw new UnsupportedOperationException("Data não pode ser vazia.");
                            }
                            String valorStr = showInputDialog("Digite o valor do sensor:");
                            if (valorStr.isEmpty()) {
                                throw new UnsupportedOperationException("Valor não pode ser vazio.");
                            }
                            double valor = parseDouble(valorStr);

                            Sensor sensor = new Sensor(idSensor, data, valor);
                            zonaEncontrada.adicionarSensor(sensor);
                            showMessageDialog(null, "Sensor adicionado com sucesso na Zona Urbana '" + nomeZona + "'!");
                        }
                        break;

                        case 3:
                        // Imprimir relatório
                        String subMenu2 = "  Deseja imprimir um relatório de Zona Rural ou Zona Urbana?";
                        subMenu2 += "\n  1. Rural";
                        subMenu2 += "\n  2. Urbana";

                        String op2 = showInputDialog(subMenu2);

                        if (op2.isEmpty()) {
                            throw new UnsupportedOperationException("Comando inválido.");
                        }

                        if (parseInt(op2) < 1 || parseInt(op2) > 2) {
                            throw new UnsupportedOperationException("Comando inválido.");
                        }

                        switch (parseInt(op2)) {
                            case 1:
                                // Imprimir relatório de Zona Rural
                                String nomeZonaRuralRelatorio = showInputDialog("Digite o nome da Zona Rural:");
                                if (nomeZonaRuralRelatorio.isEmpty()) {
                                    throw new UnsupportedOperationException("Nome da Zona Rural não pode estar vazio.");
                                }
                                ZonaRural zonaRuralRelatorio = buscarZonaRural(nomeZonaRuralRelatorio);
                                if (zonaRuralRelatorio != null) {
                                    showMessageDialog(null, zonaRuralRelatorio.relatorio());
                                } else {
                                    showMessageDialog(null, "Zona Rural não encontrada.");
                                }
                                break;
                            case 2:
                                // Imprimir relatório de Zona Urbana
                                String nomeZonaUrbanaRelatorio = showInputDialog("Digite o nome da Zona Urbana:");
                                if (nomeZonaUrbanaRelatorio.isEmpty()) {
                                    throw new UnsupportedOperationException("Nome da Zona Urbana não pode estar vazio.");
                                }
                                ZonaUrbana zonaUrbanaRelatorio = buscarZonaUrbana(nomeZonaUrbanaRelatorio);
                                if (zonaUrbanaRelatorio != null) {
                                    if (zonaUrbanaRelatorio.calcularMedia() > 300) {
                                        showMessageDialog(null, zonaUrbanaRelatorio.relatorio() + "\nALERTA EXTREMO: Média Crítica Ultrapassada!");
                                    } else {
                                        showMessageDialog(null, zonaUrbanaRelatorio.relatorio());
                                    }
                                } else {
                                    showMessageDialog(null, "Zona Urbana não encontrada.");
                                }
                                break;
                        }
                            break;
                        case 4:
                            return;
                        default:
                            showMessageDialog(null, "Opção inválida.");
                            break;
                    }

                } catch(Exception e) {
                    showMessageDialog(null, e.getMessage());
                }
            }
        } catch (Exception e) {
            showMessageDialog(null, e.getMessage());
        }
    }

    private ZonaUrbana buscarZonaUrbana(String nome) {
        for (ZonaUrbana zona : listaZonaUrbana) {
            if (zona.getNome().trim().equalsIgnoreCase(nome.trim())) {
                return zona;
            }
        }
        return null;
    }

    private ZonaRural buscarZonaRural(String nome) {
        for (ZonaRural zona : listaZonaRural) {
            if (zona.getNome().trim().equalsIgnoreCase(nome.trim())) {
                return zona;
            }
        }
        return null;
    }
}
