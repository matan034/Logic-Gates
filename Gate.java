package circuits;

public abstract class Gate {
	protected Gate[] inGates;
	private boolean []values;//this array will contain each in gate output value
	
	public Gate(Gate[] inGates) 
	{
		this.inGates=inGates;
		if(inGates==null) values=new boolean[1];//if there is no input to the gate we need one cell 
		else values=new boolean [inGates.length];
	}
	/*the function calculate the output value of the given gate and return it*/
	public boolean calc () throws CircuitException 
	{	
		if(inGates==null)//if there is no input to the gate (True,False or var)
			try {
			values[0]=this.func(null);
			}catch (CircuitException e) {throw new CircuitException(e.getMessage());}//catch if varGate not initialized
		else{//we have input gates
			for(int i=0;i<values.length;i++)
			{
				if(inGates[i].getName()!="T"&&inGates[i].getName()!="F")//current gate is not true or false
				{
					try {//try to calc current gate output value
					values[i]=inGates[i].calc();
					}catch (CircuitException e) {throw new CircuitException(e.getMessage());}//catch if one of the gates didnt got correct input
				}
				else//if the current gate is True of False
					values[i]=inGates[i].func(null);
			}
		}
		return func(values);	
		
	}
	
	protected abstract boolean func(boolean[] inValues) throws CircuitException;
	public abstract String getName();
	public abstract Gate simplify();

	public String toString()
	{
		StringBuilder b= new StringBuilder();//create StringBuilder
		if(inGates==null)//if there are no input gates
		{
				b.append(this.getName());
				return b.toString();
		}
		else//when we have input gates
		{
			b.append(this.getName());
			b.append("[");
			for(int i=0;i<inGates.length;i++)
			{
				b.append(inGates[i].toString());
				if(i<inGates.length-1)
					b.append(", ");
			}
			b.append("]");
		}
		return b.toString();
	}

}
