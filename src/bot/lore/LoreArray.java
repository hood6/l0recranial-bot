package bot.lore;

import java.io.*;
import java.util.*;

public class LoreArray {

	private String[] loreBits;
	private int[] used;
	private int usedLastInd;
	private final String file = "BotLore.txt";
	private Random rand;
	private static final int HEAD = 0;
	
	public LoreArray()
	{
		init();
		rand = new Random();
	}
	
	private void init()
	{
		try
		{
			Scanner fS = new Scanner(new File(file));
			int count = 0;
			while(fS.hasNextLine())
			{
				fS.nextLine();
				count++;
			}
			loreBits = new String[count];
			used = new int[count];
			fS = new Scanner(new File(file));
			count = 0;
			while(fS.hasNextLine())
			{
				String line = fS.nextLine();
				loreBits[count] = line;
				count++;
			}
			fS.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		used = new int[loreBits.length];
		usedLastInd = 0;
	}
	
	public void printTo()
	{
		for(int i = 0; i < loreBits.length; i++)
		{
			System.out.println(loreBits[i]);
		}
	}
	
	private void sortUsed()
	{
		sortUsed(HEAD, usedLastInd-1);
	}
	
	private void sortUsed(int min, int max)
	{
		int pInd = partition(min, max);
		if(min < pInd-1)
		{
			sortUsed(min, pInd-1);
		}
		if(pInd < max)
		{
			sortUsed(pInd, max);
		}
	}
	
	private int partition(int min, int max)
	{
		int i = min;
		int j = max;
		int piv = used[(min+max)/2];
		while(i <= j)
		{
			while(used[i] < piv)
			{
				i++;
			}
			while(used[j] > piv)
			{
				j--;
			}
			if(i <= j)
			{
				int temp = used[i];
				used[i] = used[j];
				used[j] = temp;
				i++;
				j--;
			}
		}
		return i;
	}
	
	private boolean searchUsed(int data)
	{
		return searchUsed(data, HEAD, used.length);
	}
	
	private boolean searchUsed(int data, int min, int max)
	{
		int mid = (min+max)/2;
		if(min > max)
		{
			return false;
		}
		if(used[mid] == data)
		{
			return true;
		}
		else if(used[mid] < data)
		{
			return searchUsed(data, min, mid-1);
		}
		else if(used[mid] > data)
		{
			return searchUsed(data, mid+1, max);
		}
		return false;
	}
	
	public String randFact()
	{
		int ret;
		sortUsed();
		while(true)
		{
			ret = rand.nextInt(loreBits.length);
			if(!searchUsed(ret))
			{
				used[usedLastInd++] = ret;
				break;
			}
		}
		return loreBits[ret];
	}
	
}
