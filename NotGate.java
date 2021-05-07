package circuits;

public class NotGate extends Gate {

	public NotGate(Gate in) {
		super(new Gate[] {in});
	}
	/*this func return the gate name*/
	@Override
	public String getName()
	{
		return "NOT";
	}
	/*this func calculate the output value of a not gate*/
	@Override
	protected boolean func(boolean[] inValues)throws CircuitException{
		if(inValues==null) throw new CircuitException("no entry values");//if the not gate didnt got an input gate throw exception
		if(inValues[0]==true)return false;
		return true;
	}
	/*the func simplifies a not gate*/
	@Override
	public Gate simplify()
	{
		if(inGates[0].simplify().getName()=="T") return FalseGate.instance();
		if(inGates[0].simplify().getName()=="F") return TrueGate.instance();
		if(inGates[0].simplify().getName()=="NOT") return inGates[0].inGates[0];//return the grandson
		return this;
	}
	
}
