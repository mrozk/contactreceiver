package com.example.progs;


import java.util.ArrayList;

public class PhoneElement {
	
	public String id;
	public String name;
	public ArrayList<String> number;
	public ArrayList<String> organization;
	public ArrayList<String> email;
	
	public PhoneElement(String id, String name)
	{
		this.id = id;
		this.name = name;
		this.number = new ArrayList<String>();
		this.email = new ArrayList<String>();
		this.organization = new ArrayList<String>();
	}
	
	public String getID()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	public ArrayList<String> getNumber(){
		return number;
	}
	public ArrayList<String> getEmail()
	{
		return email;
	}
	
	public ArrayList<String> getOrganization()
	{
		return organization;
	}
	public void setNumbers( ArrayList<String> num )
	{
		number = num;
	}
	public void setEmail( ArrayList<String> em )
	{
		email = em;
	}
	public void setOrganization( ArrayList<String> org )
	{
		organization = org;
	}
	public String toString()
	{
		String outputString;
		
		outputString =  id  + ";";
		outputString += name + ";";
		if(number.size() > 0)
		{
			for(String nb:number)
			{
				outputString += nb;
				if( number.iterator().hasNext() )
				{
					outputString += ",";
				}
			}
			outputString += ";";
		}
		if(email.size() > 0)
		{
			for(String em:email)
			{
				outputString += em;
				if( email.iterator().hasNext() )
				{
					outputString += ",";
				}
			}
			outputString += ";";
		}
		if(organization.size() > 0)
		{
			for(String or:organization)
			{	
				
				outputString += or;
				if( organization.iterator().hasNext() )
				{
					outputString += ",";
				}
			}
			outputString += ";";
		}
		
		outputString += "\n";
		
		return outputString;
	}
}
