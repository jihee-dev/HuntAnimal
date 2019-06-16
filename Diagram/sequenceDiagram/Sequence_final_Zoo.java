
public class Sequence_final_Zoo {
	/**
	@startuml

	loop until the game ends
	alt Buff
		Zoo -> Hunter : changeHunterSpeed(Hunter hunter)
		Hunter -> Hunter : getActionInfo().setDelay(int delay)
	else Debuff
		Zoo -> Hunter : changeHunterSpeed(Hunter hunter)
		Hunter -> Hunter : getActionInfo().setDelay(int delay)
	end
					
	@enduml
	*/
}
