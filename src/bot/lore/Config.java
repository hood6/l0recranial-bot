package bot.lore;

public class Config
{
	
	private static final String channel = "#adroseth";
	private static final long delay = 2400000;  //
	private static LoreArray lA = new LoreArray();
	private static TwitchBot bot = new TwitchBot();
	
	private static void repeat()
	{
		while(true)
		{
			try
			{	
				bot.sendMessage(channel, lA.randFact());
				Thread.sleep(delay);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
		}
	}

	public static void main(String[] args)
	throws Exception
	{
		bot.setVerbose(true);
		bot.connect("irc.twitch.tv", 6667, "oauth:zsfkny7svt6cl2pgpdai7cjflv0u76");
		bot.joinChannel(channel);
		bot.getName();
		repeat();
	}
	
}
