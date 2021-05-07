package circuits;

public class AndGate extends Gate {

	public AndGate(Gate[] inGates) {
		super(inGates);
	}
	@Override
	protected boolean func(boolean[] inValues) throws CircuitException
	{
		if(inValues==null) throw new CircuitException("no entry values");
		for(int i=0;i<inValues.length;i++)
			if(inValues[i]==false) return false;
		return true;
	}
	@Override
	public String getName()
	{
		return "AND";
	}
	@Override
	public Gate simplify()
	{
		Gate[]temp=new Gate[inGates.length];
		for(int i=0;i<inGates.length;i++) temp[i]=inGates[i];
		
		do{
			temp=simp(temp);
			if(temp[0] instanceof FalseGate) return FalseGate.instance();
			if(temp.length==1) return temp[0];
			if(temp.length==0)return TrueGate.instance();
			
			for(int i=0;i<temp.length;i++)
				temp[i]=temp[i].simplify();

		}while(isSimplified(temp)==false);
				
		return new AndGate(temp);
	
	}
	private Gate[] simp(Gate[] in){//this func returns simplifies according to  3.b.1 and 3.b.2 and returns and array that is either true or array ignoring false gates simplify will check our array size
		int j=0;
		Gate temp[]=new Gate[in.length];
		for(int i=0;i<in.length;i++)
		{
			if(in[i] instanceof FalseGate) //if gate is false return a false gate
			{
				temp[0] =FalseGate.instance();;//set in pos 0 since we return an array
				return temp;
			}
			if(!(in[i] instanceof TrueGate)) temp[j++]=in[i]; //ignore true gates and add them to our new array	
		}
		Gate res[]=new Gate[j];
		for(int i=0;i<j;i++)res[i]=temp[i];;//we create a new array in the correct size according to temp length
		return res;
	}
	private boolean isSimplified(Gate[]in)
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
