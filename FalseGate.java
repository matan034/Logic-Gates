package circuits;

public class FalseGate extends Gate{
	private static FalseGate instance=null;//set a static instance so we will get only 1 instance
	private FalseGate() {
		super(null);
	}
	public static Gate instance(){
		if(instance==null)//if there isnt an instance of FalseGate already, set a new FalseGate
			instance=new FalseGate();
		return instance;	
	}
	@Override
	protected boolean func(boolean[] inValues)
	{
		return false;
	}
	@Override
	public String getName()
	{
		return "F";
	}
	@Override
	public Gate simplify()
	{
		return this;
	}
}
