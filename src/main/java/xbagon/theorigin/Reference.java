package xbagon.theorigin;

public class Reference {
	
	public static final String MOD_ID = "theorigin";
	public static final String NAME = "The Origin";
	public static final String VERSION = "0.1";
	public static final String ACCEPTED_VERSIONS = "[1.10.2]";

	public static final String CLIENT_PROXY_CLASS = "xbagon.theorigin.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "xbagon.theorigin.proxy.ServerProxy";
	
	public static enum TheOriginItems {
		ORIGINPEARL("originPearl", "ItemOriginPearl");
		
		private String unlocalizedName;
		private String registryName;
		
		TheOriginItems(String unlocalizedName, String registryName) {
			this.unlocalizedName = unlocalizedName;
			this.registryName = registryName;
		}
		
		public String getUnlocalizedName() {
			return unlocalizedName;
		}
		
		public String getRegistryName() {
			return registryName;
		}
	}
}
