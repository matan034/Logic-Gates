package circuits;

public class TrueGate extends Gate{
	private static TrueGate instance=null;//set a static instance so we will get only 1 instance
	private TrueGate() {
		super(null);
	}
	public static Gate instance(){
		if(instance==null)//if there isnt an instance of TrueGate already, set a new TrueGate
			instance=new TrueGate();
		return instance;	
	}
	@Override
	protected boolean func(boolean[] inValues)
	{
		return true;
	}
	@Override
	public String getName()
	{
		return "T";
	}
	@Override
	public Gate simplify()
	{
		return this;
	}
	
	
}
