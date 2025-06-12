package br.espm.zona;

import java.util.LinkedList;
import java.util.List;

import br.espm.emergencia.Emergencia;
import br.espm.sensor.Sensor;

public class ZonaUrbana extends Zona implements Emergencia{

    private List<Sensor> sensores = new LinkedList<>();

    public ZonaUrbana(String nome) {
        super(nome);
    }

    public void adicionarSensor(Sensor sensor) {
        sensores.add(sensor);
    }

    public double calcularTotal() {
        double total = 0;
        for (Sensor sensor : sensores) {
            total += sensor.getValor();
        }
        return total;
    }

    public double calcularMedia() {
        return calcularTotal() / sensores.size();
    }

    public List<Sensor> getSensores() {
        return sensores;
    }

    @Override
    public String classificarNivelEmergencia() {
        double aqi = calcularMedia();
        if (aqi <= 50) {
            return "Sem risco.";
        } else if (aqi >= 51 && aqi <= 100) {
            return "Monitoramento intensificado";
        } else if (aqi >= 101 && aqi <= 150) {
            return "Alerta para grupos sensíveis";
        } else if (aqi >= 151 && aqi <= 200) {
            return "Alerta Amarelo";
        } else if (aqi >= 201 && aqi <= 300) {
            return "Alerta Laranja";
        } else if (aqi > 300) {
            return "Alerta Vermelho \nAção imediata recomendada: evacuação ou restrição de atividades externas";
        }
        return "Nível de emergência desconhecido";
        
    }

    @Override
    public String relatorio() {
        // Deve retornar o nome da zona, o valor total, a média semanal e o nível de alerta
        return "Zona: " + this.getNome() + "\nTotal Semanal: " + calcularTotal() + "\nMédia Semanal: " + calcularMedia() + "\nNível de emergência: " + classificarNivelEmergencia(); 
    }
}
