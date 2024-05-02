package PhysioClinicIFaces;
import java.util.List;
import PhysioClinicPOJOs.Machine;

public interface MachineManager {

	public void createMachine(Machine m);
	public List<Machine> showAllMachines();
	public void deleteMachineByID(int machine_id);
}