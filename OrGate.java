package circuits;

public class OrGate extends Gate {

	public OrGate(Gate[] inGates) {
		super(inGates);
	}
	@Override
	protected boolean func(boolean[] inValues) throws CircuitException
	{
		if(inValues==null) throw new CircuitException("no entry values");//catch exception where no values are sent to the gate
		for(int i=0;i<inValues.length;i++)
			if(inValues[i]==true) return true;//if one of the values is true the gate returns true
		return false;//if no true values enter the gate return false
	}
	@Override
	public String getName()
	{
		return "OR";
	}
	@Override
	public Gate simplify()
	{
		Gate[]temp=new Gate[inGates.length];
		for(int i=0;i<inGates.length;i++) temp[i]=inGates[i];//set temp to be in gates 
		while(isSimplified(temp)==false){//run a function to check if our gate has been simplified false means not simplified 
			temp=simp(temp);//get our new array to check 3.b conditions
			if(temp[0] instanceof TrueGate) return TrueGate.instance();
			if(temp.length==1) return temp[0];
			if(temp.length==0)return FalseGate.instance();
			
			for(int i=0;i<temp.length;i++)
				temp[i]=temp[i].simplify();//run simplify on each of our sons(gates entering our gate)
		}
				
		return new OrGate(temp);//returns simplified gate 
	
	}
	private Gate[] simp(Gate[] in){//this func returns simplifies according to  3.b.1 and 3.b.2 and returns and array that is either true or array ignoring false gates simplify will check our array size
		int j=0;
		Gate temp[]=new Gate[in.length];
		for(int i=0;i<in.length;i++)
		{
			if(in[i] instanceof TrueGate) //if gate is true return a true gate
			{
				temp[0] =TrueGate.instance();//set in pos 0 since we return an array
				return temp;
			}
			if(!(in[i] instanceof FalseGate)) temp[j++]=in[i]; //ignore false gates and add them to our new array	
		}
		Gate res[]=new Gate[j];
		for(int i=0;i<j;i++)res[i]=temp[i];//we create a new array in the correct size according to temp length
		return res;
	}
	private boolean isSimplified(Gate[]in)//func to check if our gates are simplified
	{
		for(int i=0;i<in.length;i++)
		{
			if(in[i].getName()=="T"||in[i].getName()=="F") return false;//if we still have true or false gate we can simplfy further
			if(in[i] instanceof VarGate)//if we still have a var gate that has a set value we can still simplify further
				try {
					((VarGate)in[i]).func(null);
					return false;
				}catch(CircuitException e) {}
		}
		return true;//if we have nothing more to simplify
	}

}
