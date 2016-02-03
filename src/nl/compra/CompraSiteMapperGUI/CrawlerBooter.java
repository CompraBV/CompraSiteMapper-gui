package nl.compra.CompraSiteMapperGUI;

public class CrawlerBooter extends Thread {

	private String URL;
	
	public void SetURL (String URL)
	{
		
		this.URL = URL;
		
	}
	
	@Override 
	public void run ()
	{
		
		new nl.compra.SiteMapGenerator.Program (URL, "sitemaps"); // Boot the Crawler
		
	}
	
}
