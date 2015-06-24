package behaviours.ambulancia;

import agents.Ambulancia;
import ontologia.entidades.Emergencia;
import environment.Cidade;
import environment.Objeto;
import jade.core.behaviours.Behaviour;

public class TransportarPaciente extends Behaviour {
	private boolean chegou;
	private Ambulancia ambulancia;
	private Emergencia emergencia;

	public TransportarPaciente(Emergencia e) {
		ambulancia = (Ambulancia) myAgent;
		emergencia = e;
		chegou = false;
		ambulancia.setStatusTransportarPaciente(e);
	}

	@Override
	public void action() {
		Objeto amb = Cidade.singleton.map_get(ambulancia.endereco);
		Objeto e = Cidade.singleton.map_get(emergencia.endereco);
		chegou = amb.walkTo(e);
		if (chegou)
			ambulancia.addBehaviour(new TransportarPaciente(e));
		}

	@Override
	public boolean done() {
		return chegou;
	}
}
