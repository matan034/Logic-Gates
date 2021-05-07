package circuits;

public class VarGate extends Gate{

	private String name;
	private Boolean value;
	public VarGate(String name) {//initalize vargate
		super(null);
		value=null;
		this.name=name;
	}
	@Override
	public String getName()
	{
		return "V"+name;
	}
	@Override
	protected boolean func(boolean[]inValues) throws CircuitException 
	{
		if(value==null) throw new CircuitException("Var is not inizialized");//catch exception where we didnt set value
		return value;//if value has been set return it
	}
	public void setVal(boolean value){
		this.value=value;
	}
	@Override
	public Gate simplify(){//if value is set return that value if not return the gate
		if(value!=null)
			if(value==true)
				return TrueGate.instance();
			else
				return FalseGate.instance();
		return this;		
	}

}
